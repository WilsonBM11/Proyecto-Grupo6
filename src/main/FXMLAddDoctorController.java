/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main;

import domain.CircularDoublyLinkedList;
import domain.CircularLinkedList;
import domain.Doctor;
import domain.ListException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import static main.FXMLMainMenuController.loadPage;

/**
 * FXML Controller class
 *
 * @author Duran Family
 */
public class FXMLAddDoctorController implements Initializable {

    private CircularDoublyLinkedList doctorlist;
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

    }

    @FXML
    private void registerOnAction(ActionEvent event) throws ParseException {
         if (doctorlist == null && util.Utility.getCircularDoublyLinkedList().isEmpty()) {
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
                 Calendar date = Calendar.getInstance();
                date.set(calendarChoice.getValue().getYear(),calendarChoice.getValue().getMonthValue(), calendarChoice.getValue().getDayOfMonth());
                Doctor doctor = new Doctor(Integer.parseInt(idTextField.getText()), firstNTextField.getText(),
                        lastNTextField.getText(), PhoneTextField.getText(),TF_Email.getText(),TF_Address.getText(), date.getTime());
                doctorlist = new CircularDoublyLinkedList();
                doctorlist.add(doctor);
                util.Utility.setCircularDoublyLinkedList(doctorlist);
                idTextField.setText("");
                lastNTextField.setText("");
                firstNTextField.setText("");
                PhoneTextField.setText("");
                TF_Address.setText("");
                TF_Email.setText("");
                calendarChoice.getEditor().clear();

                alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setTitle("Employee added");
                alert.show();

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

                Calendar date = Calendar.getInstance();
                date.set(calendarChoice.getValue().getYear(), calendarChoice.getValue().getMonthValue(), calendarChoice.getValue().getDayOfMonth());

                Doctor d = new Doctor(Integer.parseInt(idTextField.getText()), firstNTextField.getText(),
                        lastNTextField.getText(), PhoneTextField.getText(),TF_Email.getText(),TF_Address.getText(), date.getTime());
                util.Utility.getCircularDoublyLinkedList().add(d);
                util.Utility.setCircularDoublyLinkedList(util.Utility.getCircularDoublyLinkedList());
                idTextField.setText("");
                lastNTextField.setText("");
                firstNTextField.setText("");
                PhoneTextField.setText("");
                calendarChoice.getEditor().clear();
                TF_Address.setText("");
                TF_Email.setText("");

                alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setTitle("Add Employee");
                alert.setHeaderText("Employee added");
                alert.show();

            }
        }
    }
    }
