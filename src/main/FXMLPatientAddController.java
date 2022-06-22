/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main;

import domain.CircularLinkedList;
import domain.ListException;
import domain.Patient;
import domain.QueueException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Duran Family
 */
public class FXMLPatientAddController implements Initializable {

    private CircularLinkedList patientList;
    private Alert alert;
    private BorderPane bp;
    @FXML
    private DatePicker calendarChoice;
    @FXML
    private Button registerButton;
    @FXML
    private TextField lastNTextField;
    @FXML
    private TextField idTextField;
    @FXML
    private TextField firstNTextField;
    @FXML
    private TextField PhoneTextField;
    @FXML
    private TextField TF_Email;
    @FXML
    private TextField TF_Address;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        patientList = new CircularLinkedList();
        if(util.Data.fileExists("patients")){
            try {
                patientList = (CircularLinkedList) util.Data.getDataFile("patients", patientList);
            } catch (QueueException | IOException ex) {
                Logger.getLogger(FXMLPatientAddController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void registerOnAction(ActionEvent event) throws ParseException, ListException, QueueException, IOException {
        if (patientList == null && patientList.isEmpty()) {
            if ("".equals(firstNTextField.getText()) || "".equals(lastNTextField.getText())
                    || "".equals(calendarChoice) || "".equals(PhoneTextField.getText())
                    || "".equals(idTextField.getText())) {

                alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Add Doctor");
                alert.setHeaderText("ERROR");
                alert.setContentText("Fill all the spaces");
                alert.show();

            } else {
                try {
                    Calendar date = Calendar.getInstance();
                    date.set(calendarChoice.getValue().getYear(), calendarChoice.getValue().getMonthValue(), calendarChoice.getValue().getDayOfMonth());
                    Patient patient = new Patient(Integer.parseInt(idTextField.getText()), firstNTextField.getText(),
                            lastNTextField.getText(), PhoneTextField.getText(), TF_Email.getText(), TF_Address.getText(), date.getTime());
                    patientList.add(patient);
                    util.Data.setDataFile("patients", patientList);
                    idTextField.setText("");
                    lastNTextField.setText("");
                    firstNTextField.setText("");
                    PhoneTextField.setText("");
                    TF_Address.setText("");
                    TF_Email.setText("");
                    calendarChoice.getEditor().clear();

                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setTitle("Patient added");
                    alert.show();
                } catch (NumberFormatException ex) {
                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Add Patient");
                    alert.setHeaderText("ERROR");
                    alert.setContentText("Wrong character, fill again the space");
                    alert.show();
                }

            }
        } else {
            if ("".equals(firstNTextField.getText()) || "".equals(lastNTextField.getText())
                    || "".equals(calendarChoice) || "".equals(PhoneTextField.getText())
                    || "".equals(idTextField.getText())) {

                alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Add Patient");
                alert.setHeaderText("ERROR");
                alert.setContentText("Fill all the spaces");
                alert.show();

            } else {
                try {
                    Calendar date = Calendar.getInstance();
                    date.set(calendarChoice.getValue().getYear(), calendarChoice.getValue().getMonthValue(), calendarChoice.getValue().getDayOfMonth());

                    Patient d = new Patient(Integer.parseInt(idTextField.getText()), firstNTextField.getText(),
                            lastNTextField.getText(), PhoneTextField.getText(), TF_Email.getText(), TF_Address.getText(), date.getTime());
                    patientList.add(d);
                    util.Data.setDataFile("patients", patientList);
                    idTextField.setText("");
                    lastNTextField.setText("");
                    firstNTextField.setText("");
                    PhoneTextField.setText("");
                    calendarChoice.getEditor().clear();
                    TF_Address.setText("");
                    TF_Email.setText("");

                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setTitle("Add Patient");
                    alert.setHeaderText("Patient added");
                    alert.show();
                } catch (NumberFormatException ex) {
                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Add Patient");
                    alert.setHeaderText("ERROR");
                    alert.setContentText("Wrong character, fill again the space");
                    alert.show();

                }
            }
        }
    }
}
