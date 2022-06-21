/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.net.URL;
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
public class FXMLAddPatientController implements Initializable {

    @FXML
    private TextField lastNTextField;
    @FXML
    private TextField idTextField;
    @FXML
    private TextField firstNTextField;
    @FXML
    private TextField PhoneTextField;
    @FXML
    private DatePicker calendarChoice;
    @FXML
    private TextField TF_Email;
    @FXML
    private TextField TF_Address;
    @FXML
    private Button registerButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void registerOnAction(ActionEvent event) {
    }
    
}
