/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domain.CircularDoublyLinkedList;
import domain.CircularLinkedList;
import domain.Doctor;
import domain.Patient;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Wilson Bonilla Mata
 */
public class FXMLPatientRegisterController implements Initializable {

    private Alert alert;
    private CircularLinkedList patientRegisterList;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField idTextField;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private DatePicker birthdayDatePicker;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private Button registerButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void registerOnAction(ActionEvent event) {
        if (patientRegisterList == null && util.Utility.getCircularDoublyLinkedList().isEmpty()) {
            if ("".equals(firstNameTextField.getText()) || "".equals(lastNameTextField.getText())
                    || "".equals(birthdayDatePicker) || "".equals(phoneNumberTextField.getText())
                    || "".equals(idTextField.getText())) {

                alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Add Employee");
                alert.setHeaderText("ERROR");
                alert.setContentText("Fill all the spaces");
                alert.show();

            } else {
                Calendar date = Calendar.getInstance();
                date.set(birthdayDatePicker.getValue().getYear(), birthdayDatePicker.getValue().getMonthValue(), birthdayDatePicker.getValue().getDayOfMonth());
                Patient patient = new Patient(Integer.parseInt(idTextField.getText()), firstNameTextField.getText(),
                        lastNameTextField.getText(), phoneNumberTextField.getText(), emailTextField.getText(), addressTextField.getText(), date.getTime());
                patientRegisterList = new CircularLinkedList();
                patientRegisterList.add(patient);
                util.Utility.setCircularLinkedList(patientRegisterList);
                idTextField.setText("");
                lastNameTextField.setText("");
                firstNameTextField.setText("");
                phoneNumberTextField.setText("");
                emailTextField.setText("");
                addressTextField.setText("");
                birthdayDatePicker.getEditor().clear();

                alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setTitle("Employee added");
                alert.show();

            }
        } else {
            if ("".equals(firstNameTextField.getText()) || "".equals(lastNameTextField.getText())
                    || "".equals(birthdayDatePicker) || "".equals(phoneNumberTextField.getText())
                    || "".equals(idTextField.getText())) {

                alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Add Employee");
                alert.setHeaderText("ERROR");
                alert.setContentText("Fill all the spaces");
                alert.show();

            } else {
                Calendar date = Calendar.getInstance();

                date.set(birthdayDatePicker.getValue().getYear(), birthdayDatePicker.getValue().getMonthValue(), birthdayDatePicker.getValue().getDayOfMonth());
                Patient patient = new Patient(Integer.parseInt(idTextField.getText()), firstNameTextField.getText(),
                        lastNameTextField.getText(), phoneNumberTextField.getText(), emailTextField.getText(), addressTextField.getText(), date.getTime());
                util.Utility.getCircularLinkedList().add(patient);
                util.Utility.setCircularLinkedList(util.Utility.getCircularLinkedList());;
                idTextField.setText("");
                lastNameTextField.setText("");
                firstNameTextField.setText("");
                phoneNumberTextField.setText("");
                emailTextField.setText("");
                addressTextField.setText("");
                birthdayDatePicker.getEditor().clear();

                alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setTitle("Add Employee");
                alert.setHeaderText("Employee added");
                alert.show();

            }
        }
    }
}
