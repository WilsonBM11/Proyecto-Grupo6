/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domain.CircularLinkedList;
import domain.Patient;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Wilson Bonilla Mata
 */
public class FXMLPatientRegisterController implements Initializable {
    
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
        Calendar date = Calendar.getInstance();
        date.set(birthdayDatePicker.getValue().getYear(),birthdayDatePicker.getValue().getMonthValue(), birthdayDatePicker.getValue().getDayOfMonth());

        patientRegisterList.add(new Patient(Integer.parseInt(idTextField.getText()), lastNameTextField.getText(), firstNameTextField.getText()
        , date.getTime(), phoneNumberTextField.getText(), emailTextField.getText(), addressTextField.getText()));
        
    }
    
}
