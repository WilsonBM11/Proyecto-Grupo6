/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main;

import domain.CircularLinkedList;
import domain.ListException;
import domain.QueueException;
import domain.Sickness;
import domain.SinglyLinkedList;
import java.io.IOException;
import java.net.URL;
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
        if(util.Data.fileExists("sickness")){
            try {
                sicknesslist = (SinglyLinkedList) util.Data.getDataFile("sickness", sicknesslist);
            } catch (QueueException | IOException ex) {
                Logger.getLogger(FXMLMantenimientoDoctoresController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void registerOnAction(ActionEvent event) throws ListException {
        if (sicknesslist == null && sicknesslist.isEmpty()) {
            if (TF_Description.getText().equals("")) {

                alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Add Sick");
                alert.setHeaderText("ERROR");
                alert.setContentText("Fill all the spaces");
                alert.show();

            } else {

                try {
                    Sickness sick = new Sickness(TF_Description.getText());
                    sicknesslist.add(sick);
                    util.Data.setDataFile("sickness", sicknesslist);
                    TF_Description.setText("");
                    
                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setTitle("Sick added");
                    alert.show();
                } catch (QueueException ex) {
                    Logger.getLogger(FXMLAddSickController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(FXMLAddSickController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } else {
            if ("".equals(TF_Description.getText())) {

                alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Sick Employee");
                alert.setHeaderText("ERROR");
                alert.setContentText("Fill all the spaces");
                alert.show();

            } else {

                try {
                    Sickness S = new Sickness(TF_Description.getText());
                    sicknesslist.add(S);
                    util.Data.setDataFile("sickness", sicknesslist);
                    TF_Description.setText("");
                    
                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setTitle("Add Sick");
                    alert.setHeaderText("Sickness added");
                    alert.show();
                } catch (QueueException ex) {
                    Logger.getLogger(FXMLAddSickController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(FXMLAddSickController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }
}
