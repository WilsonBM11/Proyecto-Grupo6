/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main;

import domain.CircularLinkedList;
import domain.Doctor;
import domain.Encoder;
import domain.ListException;
import domain.Patient;
import domain.QueueException;
import domain.Security;
import domain.SinglyLinkedList;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.Calendar;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javax.mail.MessagingException;
import util.Mail;

/**
 * FXML Controller class
 *
 * @author Duran Family
 */
public class FXMLPatientAddController implements Initializable {

    private CircularLinkedList patientList;
    private Encoder e;
    private SinglyLinkedList securityList;
    private Alert alert;
    Mail m = new Mail();
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
        
        
        try {
            
            
            patientList = new CircularLinkedList();
            if (util.Data.fileExists("patients")) {
                patientList = (CircularLinkedList) util.Data.getDataFile("patients", patientList);
            }
            securityList = new SinglyLinkedList();
            if (util.Data.fileExists("security")) {
                securityList = (SinglyLinkedList) util.Data.getDataFile("security", securityList);
            }
        } catch (QueueException | IOException ex) {
            Logger.getLogger(FXMLPatientAddController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void registerOnAction(ActionEvent event) throws ParseException, ListException, QueueException, IOException {
        if (patientList.isEmpty()) {
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
                    Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
                    Matcher mather = pattern.matcher(TF_Email.getText());
                    if (mather.find() == true) {
                            Calendar date = Calendar.getInstance();
                            date.set(calendarChoice.getValue().getYear(), calendarChoice.getValue().getMonthValue(), calendarChoice.getValue().getDayOfMonth());
                            Patient patient = new Patient(Integer.parseInt(idTextField.getText()), firstNTextField.getText(),
                                    lastNTextField.getText(), PhoneTextField.getText(), TF_Email.getText(), TF_Address.getText(), date.getTime());
                            patientList.add(patient);
                            util.Data.setDataFile("patients", patientList);
                            int contraseña = util.Utility.random(0, 10000);
                            securityList.add(new Security(idTextField.getText(), String.valueOf(contraseña), "patient"));
                             e = new Encoder();
//                            contraseña = Encoder.e (contraseña.getText().trim());
                            util.Data.setDataFile("security", securityList);
                            m.sendEmail(TF_Email.getText(), "Registro de Usuario - " + m.getClinicName(),
                                    "¡Se registro con exito su usario!<br><br>"
                                    + "A continuación se muestran los datos de su registro:<br><br>"
                                    + "Cédula: " + idTextField.getText() + "<br><br>"
                                    + "Nombre: " + firstNTextField.getText() + " " + lastNTextField.getText() + "<br><br>"
                                    + "Teléfono: " + PhoneTextField.getText() + "<br><br>"
                                    + "Dirección: " + TF_Address.getText() + "<br><br>"
                                    + "Email: " + TF_Email.getText() + "<br><br>"
                                    + "Su usuario de ingreso al sistema es: " + idTextField.getText() + "<br><br>"
                                    + "Su contraseña temporal de ingreso al sistema es: " + contraseña + "<br><br>"
                                    + "¡Gracias por poner su salud en nuestras manos!");

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
                    alert.setTitle("Add Patient");
                    alert.setHeaderText("ERROR");
                    alert.setContentText("Wrong character, fill again the space");
                    alert.show();
                } catch (MessagingException ex) {
                    Logger.getLogger(FXMLPatientAddController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } else 
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
                    Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
                    Matcher mather = pattern.matcher(TF_Email.getText());
                    if (mather.find() == true) {
                        if (!patientList.contains(new Patient(Integer.parseInt(idTextField.getText()), "", "", "", "", "", null))) {
                            Calendar date = Calendar.getInstance();
                            date.set(calendarChoice.getValue().getYear(), calendarChoice.getValue().getMonthValue(), calendarChoice.getValue().getDayOfMonth());
                            Patient patient = new Patient(Integer.parseInt(idTextField.getText()), firstNTextField.getText(),
                                    lastNTextField.getText(), PhoneTextField.getText(), TF_Email.getText(), TF_Address.getText(), date.getTime());
                            patientList.add(patient);
                            util.Data.setDataFile("patients", patientList);
                            int contraseña = util.Utility.random(0, 10000);
                            securityList.add(new Security(idTextField.getText(), String.valueOf(contraseña), "patient"));
                            util.Data.setDataFile("security", securityList);
                            m.sendEmail(TF_Email.getText(), "Registro de Usuario - " + m.getClinicName(),
                                    "¡Se registro con exito su usario!<br><br>"
                                    + "A continuación se muestran los datos de su registro:<br><br>"
                                    + "Cédula: " + idTextField.getText() + "<br><br>"
                                    + "Nombre: " + firstNTextField.getText() + " " + lastNTextField.getText() + "<br><br>"
                                    + "Teléfono: " + PhoneTextField.getText() + "<br><br>"
                                    + "Dirección: " + TF_Address.getText() + "<br><br>"
                                    + "Email: " + TF_Email.getText() + "<br><br>"
                                    + "Su usuario de ingreso al sistema es: " + idTextField.getText() + "<br><br>"
                                    + "Su contraseña temporal de ingreso al sistema es: " + contraseña + "<br><br>"
                                    + "¡Gracias por poner su salud en nuestras manos!");

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

                        } else {
                            alert = new Alert(Alert.AlertType.NONE);
                            alert.setAlertType(Alert.AlertType.ERROR);
                            alert.setTitle("Doctor");
                            alert.setHeaderText("ERROR");
                            alert.setContentText("Id Patient already register");
                            alert.show();
                        }
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
                    alert.setTitle("Add Patient");
                    alert.setHeaderText("ERROR");
                    alert.setContentText("Wrong character, fill again the space");
                    alert.show();
                } catch (MessagingException ex) {
                    Logger.getLogger(FXMLPatientAddController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
}
