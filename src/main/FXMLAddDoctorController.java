/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main;

import domain.CircularDoublyLinkedList;
import domain.Doctor;
import domain.ListException;
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
public class FXMLAddDoctorController implements Initializable {

    private CircularDoublyLinkedList doctorList;
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
        doctorList = new CircularDoublyLinkedList();
        if(util.Data.fileExists("doctors")){
            try {
                doctorList = (CircularDoublyLinkedList) util.Data.getDataFile("doctors", doctorList);
            } catch (QueueException | IOException ex) {
                Logger.getLogger(FXMLAddDoctorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void registerOnAction(ActionEvent event) throws ParseException, ListException, QueueException, IOException {
        if (doctorList == null && doctorList.isEmpty()) {
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
                    Doctor doctor = new Doctor(Integer.parseInt(idTextField.getText()), firstNTextField.getText(),
                            lastNTextField.getText(), PhoneTextField.getText(), TF_Email.getText(), TF_Address.getText(), date.getTime());
                    doctorList.add(doctor);
                    util.Data.setDataFile("doctors", doctorList);
                    idTextField.setText("");
                    lastNTextField.setText("");
                    firstNTextField.setText("");
                    PhoneTextField.setText("");
                    TF_Address.setText("");
                    TF_Email.setText("");
                    calendarChoice.getEditor().clear();

                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setTitle("Doctor added");
                    alert.show();
                } catch (NumberFormatException ex) {
                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Add Doctor");
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
                alert.setTitle("Add Employee");
                alert.setHeaderText("ERROR");
                alert.setContentText("Fill all the spaces");
                alert.show();

            } else {
                try {
                    Calendar date = Calendar.getInstance();
                    date.set(calendarChoice.getValue().getYear(), calendarChoice.getValue().getMonthValue(), calendarChoice.getValue().getDayOfMonth());

                    Doctor d = new Doctor(Integer.parseInt(idTextField.getText()), firstNTextField.getText(),
                            lastNTextField.getText(), PhoneTextField.getText(), TF_Email.getText(), TF_Address.getText(), date.getTime());
                    doctorList.add(d);
                    util.Data.setDataFile("doctors",doctorList);
                    idTextField.setText("");
                    lastNTextField.setText("");
                    firstNTextField.setText("");
                    PhoneTextField.setText("");
                    calendarChoice.getEditor().clear();
                    TF_Address.setText("");
                    TF_Email.setText("");

                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setTitle("Add Doctor");
                    alert.setHeaderText("Doctor added");
                    alert.show();
                } catch (NumberFormatException ex) {
                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Add Doctor");
                    alert.setHeaderText("ERROR");
                    alert.setContentText("Wrong character, fill again the space");
                    alert.show();

                }
            }
        }
    }
}
