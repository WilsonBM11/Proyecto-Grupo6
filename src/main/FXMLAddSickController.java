/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main;

import domain.Appointment;
import domain.CircularLinkedList;
import domain.Doctor;
import domain.ListException;
import domain.QueueException;
import domain.Sickness;
import domain.SinglyLinkedList;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Duran Family
 */
public class FXMLAddSickController implements Initializable {

    SinglyLinkedList sicknesslist;
    Alert alert;

    @FXML
    private Button registerButton;
    @FXML
    private TextArea TF_Description;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sicknesslist = new SinglyLinkedList();
        if (util.Data.fileExists("sickness")) {
            try {
                sicknesslist = (SinglyLinkedList) util.Data.getDataFile("sickness", sicknesslist);
            } catch (QueueException | IOException ex) {
                Logger.getLogger(FXMLMantenimientoDoctoresController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void registerOnAction(ActionEvent event) throws ListException {
        Sickness sick = new Sickness(TF_Description.getText());
        if (sicknesslist.isEmpty()) {
            if ("".equals(TF_Description.getText())) {

                alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Sick Add");
                alert.setHeaderText("ERROR");
                alert.setContentText("Fill all the spaces");
                alert.show();

            } else {
                try {
                    sicknesslist.add(sick);
                    sick.setId(1);
                    
                    try {
                        util.Data.setDataFile("sickness", sicknesslist);
                    } catch (QueueException ex) {
                        Logger.getLogger(FXMLAddSickController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLAddSickController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    TF_Description.setText("");

                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setTitle("Sickness added");
                    alert.show();
                } catch (NumberFormatException ex) {
                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Sickness");
                    alert.setHeaderText("ERROR");
                    alert.setContentText("Wrong character, fill again the space");
                    alert.show();
                }
            }
        } else {
            if ("".equals(TF_Description.getText())) {

                alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Sick Add");
                alert.setHeaderText("ERROR");
                alert.setContentText("Fill all the spaces");
                alert.show();

            } else {
                try {
                     Sickness sick2 = new Sickness(TF_Description.getText());
                    if (!sicknesslist.contains(new Sickness(TF_Description.getText()))) {
                        if (sicknesslist.size() == 1) {
                            Sickness.setConsecutivo(sick.getId());
                            sick2.setId(Sickness.getConsecutivo());
                        } else {
                            Sickness.setConsecutivo(sicknesslist.size());
                            sick2.setId(Sickness.getConsecutivo() + 1);
                        }//Se agrega el elemento y esto se agrega al archivo txt
                        sicknesslist.add(sick);
                        try {
                            util.Data.setDataFile("sickness", sicknesslist);
                        } catch (QueueException | IOException ex) {
                            Logger.getLogger(FXMLAddSickController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        TF_Description.setText("");

                        alert = new Alert(Alert.AlertType.NONE);
                        alert.setAlertType(Alert.AlertType.INFORMATION);
                        alert.setTitle("Sickness added");
                        alert.show();
                    } else {
                        if (sicknesslist.size() == 1) {
                            Sickness.setConsecutivo(sick.getId());
                            sick2.setId(Sickness.getConsecutivo());
                        } else {
                            Sickness.setConsecutivo(sicknesslist.size() + 1);
                            sick2.setId(Sickness.getConsecutivo());
                        }
                            alert = new Alert(Alert.AlertType.NONE);
                            alert.setAlertType(Alert.AlertType.ERROR);
                            alert.setTitle("Sickness");
                            alert.setHeaderText("ERROR");
                            alert.setContentText("Disease is already registered");
                            alert.show();

                        }
                    }catch (NumberFormatException ex) {
                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Sickness");
                    alert.setHeaderText("ERROR");
                    alert.setContentText("Wrong character, fill again the space");
                    alert.show();
                }

                }
            }
        }
    }
