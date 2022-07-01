/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main;

import domain.Appointment;
import domain.CircularDoublyLinkedList;
import domain.CircularLinkedList;
import domain.Doctor;
import domain.DoublyLinkedList;
import domain.ListException;
import domain.Patient;
import domain.QueueException;
import domain.SinglyLinkedList;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import static java.util.Calendar.SATURDAY;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Duran Family
 */
public class FXMLAddCITASController implements Initializable {

    DoublyLinkedList AppointmentList;
    CircularDoublyLinkedList DoctorList;
    CircularLinkedList PatientList;
    SinglyLinkedList SicknessList;
    SinglyLinkedList numbersList;
    Alert alert;
    @FXML
    private TextField IDPatient;
    @FXML
    private TextField IDDoctor;
    @FXML
    private Button registerButton;
    @FXML
    private ComboBox DoctorComboBox;
    @FXML
    private ComboBox PatientComboBox;
    @FXML
    private Button GETDATABUTTON;
    @FXML
    private TextField TEXTFIELDTIME;
    @FXML
    private ComboBox TIMECOMBOBOX;
    @FXML
    private DatePicker CalendarChoice;
    @FXML
    private TextField TF_REMARKS;

    @Override
    @SuppressWarnings("empty-statement")
    public void initialize(URL url, ResourceBundle rb) {

        DoctorList = new CircularDoublyLinkedList();

        if (util.Data.fileExists("doctors")) {
            try {
                DoctorList = (CircularDoublyLinkedList) util.Data.getDataFile("doctors", DoctorList);
            } catch (QueueException | IOException ex) {
                Logger.getLogger(FXMLMantenimientoDoctoresController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (this.DoctorList != null && !this.DoctorList.isEmpty()) {
            try {
                for (int i = 1; i <= DoctorList.size(); i++) {
                    this.DoctorComboBox.setItems(getDataDoctor());
                }
            } catch (ListException ex) {
                Logger.getLogger(FXMLAddCITASController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        PatientList = new CircularLinkedList();

        if (util.Data.fileExists("patients")) {
            try {
                PatientList = (CircularLinkedList) util.Data.getDataFile("patients", PatientList);
            } catch (QueueException | IOException ex) {
                Logger.getLogger(FXMLMantenimientoDoctoresController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (this.PatientList != null && !this.PatientList.isEmpty()) {
            try {
                for (int i = 1; i <= PatientList.size(); i++) {
                    this.PatientComboBox.setItems(getDataPatient());
                    
                }
            } catch (ListException ex) {
                Logger.getLogger(FXMLAddCITASController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        AppointmentList = new DoublyLinkedList();

        if (util.Data.fileExists("appointment")) {
            try {
                AppointmentList = (DoublyLinkedList) util.Data.getDataFile("appointment", AppointmentList);
            } catch (QueueException | IOException ex) {
                Logger.getLogger(FXMLMantenimientoDoctoresController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void registerOnAction(ActionEvent event) {
        String str = TEXTFIELDTIME.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);

        if (AppointmentList == null && AppointmentList.isEmpty()) {
            if ("".equals(TEXTFIELDTIME.getText()) || "".equals(IDDoctor.getText())
                    || "".equals(IDPatient) || "".equals(TF_REMARKS.getText())) {

                alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Add Appointment");
                alert.setHeaderText("ERROR");
                alert.setContentText("Fill all the spaces");
                alert.show();

            } else {
                try {
                    Appointment appointment = new Appointment(Integer.parseInt(IDPatient.getText()),
                            Integer.parseInt(IDDoctor.getText()), dateTime, TF_REMARKS.getText());
                    appointment.setId(1);
                    AppointmentList.add(appointment);
                    try {
                        util.Data.setDataFile("appointment", AppointmentList);
                    } catch (QueueException ex) {
                        Logger.getLogger(FXMLAddCITASController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ListException ex) {
                        Logger.getLogger(FXMLAddCITASController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLAddCITASController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    IDDoctor.setText("");
                    IDPatient.setText("");
                    TF_REMARKS.setText("");
                    TEXTFIELDTIME.setText("");
                    CalendarChoice.getEditor().clear();

                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setTitle("Apointment added");
                    alert.show();
                } catch (NumberFormatException ex) {
                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Add Appointment");
                    alert.setHeaderText("ERROR");
                    alert.setContentText("Wrong character, fill again the space");
                    alert.show();
                }

            }
        } else {
            if ("".equals(TEXTFIELDTIME.getText()) || "".equals(IDDoctor.getText())
                    || "".equals(IDPatient) || "".equals(TF_REMARKS.getText())) {

                alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Add Appointment");
                alert.setHeaderText("ERROR");
                alert.setContentText("Fill all the spaces");
                alert.show();

            } else {
                try {
                    Appointment appointment = new Appointment(Integer.parseInt(IDPatient.getText()),
                            Integer.parseInt(IDDoctor.getText()), dateTime, TF_REMARKS.getText());
                    AppointmentList.add(appointment);
                    try {
                        util.Data.setDataFile("appointment", AppointmentList);
                    } catch (QueueException ex) {
                        Logger.getLogger(FXMLAddCITASController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ListException ex) {
                        Logger.getLogger(FXMLAddCITASController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLAddCITASController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    IDDoctor.setText("");
                    IDPatient.setText("");
                    TF_REMARKS.setText("");
                    TEXTFIELDTIME.setText("");
                    CalendarChoice.getEditor().clear();

                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setTitle("Add Appointment");
                    alert.setHeaderText("Appointment added");
                    alert.show();
                } catch (NumberFormatException ex) {
                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Add Appointment");
                    alert.setHeaderText("ERROR");
                    alert.setContentText("Wrong character, fill again the space");
                    alert.show();

                }
            }
        }
    }

    @FXML
    private void GETDATAOnAction(ActionEvent event) {
        String choiceDoctor = String.valueOf(DoctorComboBox.getValue());
        String[] parts = choiceDoctor.split(",");
        String IdDoctor = parts[0];
        IDDoctor.setText(IdDoctor.substring(1, IdDoctor.length()));
        String choicePatient = String.valueOf(PatientComboBox.getValue());
        String[] parts1 = choicePatient.split(",");
        String IdPatient = parts1[0];
        IDPatient.setText(IdPatient.substring(1, IdPatient.length()));
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.set(CalendarChoice.getValue().getYear(), CalendarChoice.getValue().getMonthValue() - 1, CalendarChoice.getValue().getDayOfMonth());
        Date date = dateCalendar.getTime();

        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
        String date1 = format1.format(date);
        String time = (String) TIMECOMBOBOX.getValue();
        TEXTFIELDTIME.setText(date1 + " " + time);
    }

    private ObservableList<List<String>> getDataDoctor() {
        ObservableList<List<String>> data = FXCollections.observableArrayList();
        if (this.DoctorList != null && !this.DoctorList.isEmpty()) {
            try {
                for (int i = 1; i <= this.DoctorList.size(); i++) {
                    Doctor D = (Doctor) this.DoctorList.getNode(i).data;
                    List<String> arrayList = new ArrayList<>();
                    arrayList.add(String.valueOf(D.getId()));
                    arrayList.add(D.getFirstName());
                    arrayList.add(D.getLastName());
                    data.add(arrayList);
                }
            } catch (ListException ex) {

            }
        }
        return data;
    }

    private ObservableList<List<String>> getDataPatient() {
        ObservableList<List<String>> data = FXCollections.observableArrayList();
        if (this.PatientList != null && !this.PatientList.isEmpty()) {
            try {
                for (int i = 1; i <= this.PatientList.size(); i++) {
                    Patient D = (Patient) this.PatientList.getNode(i).data;
                    List<String> arrayList = new ArrayList<>();
                    arrayList.add(String.valueOf(D.getId()));
                    arrayList.add(D.getFirstname());
                    arrayList.add(D.getLastname());

                 
                    data.add(arrayList);
                }
            } catch (ListException ex) {

            }
        }
        return data;
    }

    @FXML
    private void DaySelectedCode(ActionEvent event) {
        switch (CalendarChoice.getValue().getDayOfWeek()) {
            case SATURDAY:
                String numeros[] = {"14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00"};
                TIMECOMBOBOX.getItems().clear();
                TIMECOMBOBOX.getItems().addAll(numeros);
                break;
            case MONDAY:
                String numeros1[] = {"10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00"};
                TIMECOMBOBOX.getItems().clear();
                TIMECOMBOBOX.getItems().addAll(numeros1);
                break;
            case THURSDAY:
                String numeros2[] = {"10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00"};
                TIMECOMBOBOX.getItems().clear();
                TIMECOMBOBOX.getItems().addAll(numeros2);
                break;     
            case TUESDAY:
                String numeros3[] = {"10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00"};
                TIMECOMBOBOX.getItems().clear();
                TIMECOMBOBOX.getItems().addAll(numeros3);
                break; 
             case WEDNESDAY:
                String numeros4[] = {"10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00"};
                TIMECOMBOBOX.getItems().clear();
                TIMECOMBOBOX.getItems().addAll(numeros4);
                break;
              case FRIDAY:
                String numeros5[] = {"10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00"};
                TIMECOMBOBOX.getItems().clear();
                TIMECOMBOBOX.getItems().addAll(numeros5);
                break; 
              case SUNDAY:
                  TIMECOMBOBOX.getItems().clear();
                
        }
    }
}
