/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main;

import domain.Appointment;
import domain.BST;
import domain.CircularDoublyLinkedList;
import domain.Configurations;
import domain.ListException;
import domain.QueueException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Duran Family
 */
public class FXMLAddConfigurationController implements Initializable {

    BST tree;
    Alert alert;

    @FXML
    private TextField TF_Email;
    @FXML
    private TextField idClinicName;
    @FXML
    private TextField TF_PhoneNumber;
    @FXML
    private Button registerButton;

    @FXML
    private AnchorPane pane;
    @FXML
    private TextField TF_image;
    @FXML
    private TextField TF_image1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tree = new BST();
        if (util.Data.fileExists("configuration")) {
            try {
                tree = (BST) util.Data.getDataFile("configuration", tree);
            } catch (QueueException | IOException ex) {
                Logger.getLogger(FXMLAddDoctorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void registerOnAction(ActionEvent event) {
        if (tree != null) {
            if ("".equals(TF_Email.getText())
                    || "".equals(TF_PhoneNumber) || "".equals(TF_image.getText())
                    || "".equals(idClinicName.getText())) {
                alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Configuration");
                alert.setHeaderText("ERROR");
                alert.setContentText("Fill all the spaces");
                alert.show();

            } else {
                try {
                    Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
                    Matcher mather = pattern.matcher(TF_Email.getText());
                    if (mather.find() == true) {
                        Configurations configurations = new Configurations(idClinicName.getText(), TF_PhoneNumber.getText(), TF_Email.getText(), TF_image.getText(), TF_image1.getText());
                        tree.clear();
                        tree.add(configurations);
                        try {
                            util.Data.setDataFile("configuration", tree);
                        } catch (QueueException ex) {
                            Logger.getLogger(FXMLAddConfigurationController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ListException ex) {
                            Logger.getLogger(FXMLAddConfigurationController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(FXMLAddConfigurationController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            util.Data.setDataFile("configuration", tree);
                        } catch (QueueException ex) {
                            Logger.getLogger(FXMLAddCITASController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ListException ex) {
                            Logger.getLogger(FXMLAddCITASController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(FXMLAddCITASController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        idClinicName.setText("");
                        TF_image.setText("");
                        TF_PhoneNumber.setText("");
                        TF_Email.setText("");
                        TF_image1.setText("");

                        alert = new Alert(Alert.AlertType.NONE);
                        alert.setAlertType(Alert.AlertType.INFORMATION);
                        alert.setTitle("Configurations");
                        alert.setContentText("The changes were made successfully");
                        alert.show();
                    } else {
                        alert = new Alert(Alert.AlertType.NONE);
                        alert.setAlertType(Alert.AlertType.ERROR);
                        alert.setTitle("Doctor");
                        alert.setHeaderText("ERROR");
                        alert.setContentText("Invalid Email");
                        alert.show();
                    }
                } catch (NumberFormatException ex) {
                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Configurations");
                    alert.setHeaderText("ERROR");
                    alert.setContentText("Wrong character, fill again the space");
                    alert.show();
                }
            }
        }
    }

    @FXML
    private void ChangeLogoCode(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            TF_image.setText(selectedFile.toURI().toString());
            TF_image1.setText(selectedFile.getAbsolutePath());
        } else {
            TF_image.setText("Is not valid");
        }
    }

    @FXML
    private void ScheduleCode(ActionEvent event) {
        
    }

}
