/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main;

import domain.Appointment;
import domain.BST;
import domain.BTreeNode;
import domain.CircularDoublyLinkedList;
import domain.CircularLinkedList;
import domain.Doctor;
import domain.DoublyLinkedList;
import domain.ListException;
import domain.MedicalCare;
import domain.Node;
import domain.Patient;
import domain.QueueException;
import domain.Sickness;
import domain.SinglyLinkedList;
import domain.TreeException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
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
import util.Data;

/**
 * FXML Controller class
 *
 * @author Duran Family
 */
public class FXMLAddCITASController implements Initializable {

    //Se inician las listas que se van a utilizar
    DoublyLinkedList AppointmentList;
    CircularDoublyLinkedList DoctorList;
    CircularLinkedList PatientList;
    SinglyLinkedList SicknessList;
    SinglyLinkedList numbersList;
    SinglyLinkedList treeHours;
    SinglyLinkedList treeHoursS;
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
    private ObservableList<List<String>> tableViewData;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableViewData = FXCollections.observableArrayList();
        IDPatient.setEditable(false);
        IDDoctor.setEditable(false);
        TEXTFIELDTIME.setEditable(false);
        //Se asigna cada lista a un archivo txt que es lo que va a permitir la persisencia de los datos
        DoctorList = new CircularDoublyLinkedList();
        if (util.Data.fileExists("doctors")) {
            try {
                DoctorList = (CircularDoublyLinkedList) util.Data.getDataFile("doctors", DoctorList);
            } catch (QueueException | IOException ex) {
                Logger.getLogger(FXMLMantenimientoDoctoresController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //Se carga los datos de los doctores en el combobox
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
                //Se cargan los datos de los pascientes en el combobox
                for (int i = 1; i <= PatientList.size(); i++) {
                    this.PatientComboBox.setItems(getDataPatient());

                }
            } catch (ListException ex) {
                Logger.getLogger(FXMLAddCITASController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        AppointmentList = new DoublyLinkedList();
        //Se asigna la lista appointment al archivo txt para la persistencia de los datos

        if (util.Data.fileExists("appointment")) {
            try {
                AppointmentList = (DoublyLinkedList) util.Data.getDataFile("appointment", AppointmentList);
            } catch (QueueException | IOException ex) {
                Logger.getLogger(FXMLMantenimientoDoctoresController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        treeHours = new SinglyLinkedList();
        if (util.Data.fileExists("Week")) {
            try {
                treeHours = (SinglyLinkedList) Data.getDataFile("Week", treeHours);
            } catch (QueueException | IOException ex) {
                Logger.getLogger(FXMLAddConfigurationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        treeHoursS = new SinglyLinkedList();
        if (util.Data.fileExists("Saturday")) {
            try {
                treeHoursS = (SinglyLinkedList) Data.getDataFile("Saturday", treeHoursS);
            } catch (QueueException | IOException ex) {
                Logger.getLogger(FXMLAddConfigurationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void registerOnAction(ActionEvent event) throws ListException {
        String str = TEXTFIELDTIME.getText();
        //Se formatea la fecha para que trabaje con horas y minutos
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy H:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        //Se crea un objeto appointment que es el primero que se va a agregar cuando la lista este vacia
        Appointment appointment = new Appointment(Integer.parseInt(IDPatient.getText()),
                Integer.parseInt(IDDoctor.getText()), dateTime, TF_REMARKS.getText());

        if (AppointmentList.isEmpty()) {
            //Se verifica que todos los campos de texto esten llenos
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
                    //Se agrega el primero objeto de la lista y se hace un set del id a 1
                    AppointmentList.add(appointment);
                    appointment.setId(1);
                    Appointment.setConsecutivo(appointment.getId() + 1);
                    try {
                        //Se carga el primer dato al archivos txt
                        util.Data.setDataFile("appointment", AppointmentList);
                    } catch (QueueException ex) {
                        Logger.getLogger(FXMLAddCITASController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ListException ex) {
                        Logger.getLogger(FXMLAddCITASController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLAddCITASController.class.getName()).log(Level.SEVERE, null, ex);
                    }
//Se borra toda la informacion escrita
                    IDDoctor.setText("");
                    IDPatient.setText("");
                    TF_REMARKS.setText("");
                    TEXTFIELDTIME.setText("");
                    CalendarChoice.getEditor().clear();
                    PatientComboBox.setValue("Patients");
                    DoctorComboBox.setValue("Doctors");
                    TIMECOMBOBOX.setValue("");
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
                    //Se crea un segundo objeto que van a ser los posteriores objetos que se van a agregar cuando la lista
                    //no este vacia, esto ya que ahora se va a tener que verificar que ningun pasciente tenga un mismo doctor
                    //en la misma hora y fecha
                    Appointment appointment2 = new Appointment(Integer.parseInt(IDPatient.getText()),
                            Integer.parseInt(IDDoctor.getText()), dateTime, TF_REMARKS.getText());
                    //El contains verifica que no exista otra cita con el mismo doctor en esa fecha
                    if (!AppointmentList.contains(new Appointment(0, Integer.parseInt(IDDoctor.getText()), dateTime, ""))) {
                        //El contains genera problemas en el id consecutivo y por eso se genero este if y else hacer un set
                        //del id y que siga manteniendo los valores que debe
                        if (AppointmentList.size() == 1) {
                            Appointment.setConsecutivo(appointment.getId());
                            appointment2.setId(Appointment.getConsecutivo());
                        } else {
                            Appointment.setConsecutivo(AppointmentList.size() + 1);
                            appointment2.setId(Appointment.getConsecutivo() + 1);
                            System.out.println(appointment2.getId());
                            System.out.println(Appointment.getConsecutivo());
                        }//Se agrega el elemento y esto se agrega al archivo txt
                        AppointmentList.add(appointment2);
                        try {

                            util.Data.setDataFile("appointment", AppointmentList);
                        } catch (QueueException | ListException | IOException ex) {
                            Logger.getLogger(FXMLAddCITASController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        //Se borra la lista 
                        IDDoctor.setText("");
                        IDPatient.setText("");
                        TF_REMARKS.setText("");
                        TEXTFIELDTIME.setText("");
                        CalendarChoice.getEditor().clear();
                        PatientComboBox.setValue("Patients");
                        DoctorComboBox.setValue("Doctors");
                        TIMECOMBOBOX.setValue("");
                        //Si se agrega correctamente muestra el mensaje 
                        alert = new Alert(Alert.AlertType.NONE);
                        alert.setAlertType(Alert.AlertType.INFORMATION);
                        alert.setTitle("Add Appointment");
                        alert.setHeaderText("Appointment added");
                        alert.show();
                    } else {
                        //Si hay un elemento con la misma fecha y doctor se muestra el siguiente mensaje
                        //Y se hace un set del id 
                        if (AppointmentList.size() == 1) {
                            Appointment.setConsecutivo(appointment.getId());
                            appointment2.setId(Appointment.getConsecutivo());
                        } else {
                            Appointment.setConsecutivo(AppointmentList.size() + 1);
                            appointment2.setId(Appointment.getConsecutivo());
                        }
                        alert = new Alert(Alert.AlertType.NONE);
                        alert.setAlertType(Alert.AlertType.ERROR);
                        alert.setTitle("Add Appointment");
                        alert.setHeaderText("ERROR");
                        alert.setContentText("There is already an appointment registered on that date with that doctor");
                        alert.show();
                    }
                    //Number format Exception en caso de que se escriba un caracter incorrecto
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
    //Este boton se va a encargar de traer todos los datos de los combobox a textfield para que se visualice y tenga
    //Un mejor manejo

    @FXML
    private void GETDATAOnAction(ActionEvent event) {
        //Se utiliza el metodo split y subString para solo obtener id del doctor y el pasciente
        String choiceDoctor = String.valueOf(DoctorComboBox.getValue());
        String[] parts = choiceDoctor.split(",");
        String IdDoctor = parts[0];
        IDDoctor.setText(IdDoctor.substring(1, IdDoctor.length()));
        String choicePatient = String.valueOf(PatientComboBox.getValue());
        String[] parts1 = choicePatient.split(",");
        String IdPatient = parts1[0];
        IDPatient.setText(IdPatient.substring(1, IdPatient.length()));
        Calendar dateCalendar = Calendar.getInstance();
        //Se obtiene la fecha del calendario
        dateCalendar.set(CalendarChoice.getValue().getYear(), CalendarChoice.getValue().getMonthValue() - 1, CalendarChoice.getValue().getDayOfMonth());
        Date date = dateCalendar.getTime();
        //Se formatea la fecha

        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
        String date1 = format1.format(date);
        //Las horas se obtienen del combobox time y posteriomente se une con la fecha 
        String time = (String) TIMECOMBOBOX.getValue();
        TEXTFIELDTIME.setText(date1 + " " + time+":00");
    }
//Se crea un getData de tipo Doctor list para obtener los datos del archivo txt y meterlos a un arraylist 
    //Que posteriomente ira en el combobox de doctores

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
    //Se realiza el mismo getData pero para pascientes

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


     private ObservableList<String> getComboBoxHours() throws ListException {
        ObservableList<String> data = FXCollections.observableArrayList();

      
                for (int i = 1; i <= treeHours.size(); i++) {
                    int horas = (int) treeHours.getNode(i).data;
                    data.add(String.valueOf(horas));
                }
                return data;
     }
     private ObservableList<String> getComboBoxHoursS() throws ListException {
        ObservableList<String> data = FXCollections.observableArrayList();

      
                for (int i = 1; i <= treeHoursS.size(); i++) {
                    int horas = (int) treeHoursS.getNode(i).data;
                    data.add(String.valueOf(horas));
                }
                return data;
     }

    @FXML
    private void DaySelectedCode(ActionEvent event) throws ListException {
        switch (CalendarChoice.getValue().getDayOfWeek()) {
            case SATURDAY:
               TIMECOMBOBOX.getItems().clear();
                TIMECOMBOBOX.setItems(getComboBoxHoursS());
                break;
            case MONDAY:
                TIMECOMBOBOX.getItems().clear();
                TIMECOMBOBOX.setItems(getComboBoxHours());
                break;
            case THURSDAY:
                 TIMECOMBOBOX.getItems().clear();
                TIMECOMBOBOX.setItems(getComboBoxHours());
                break;
            case TUESDAY:
                TIMECOMBOBOX.getItems().clear();
                TIMECOMBOBOX.setItems(getComboBoxHours());
                break;
            case WEDNESDAY:
                TIMECOMBOBOX.getItems().clear();
                TIMECOMBOBOX.setItems(getComboBoxHours());
                break;
            case FRIDAY:
                TIMECOMBOBOX.getItems().clear();
                TIMECOMBOBOX.setItems(getComboBoxHours());
                break;
            case SUNDAY:
                TIMECOMBOBOX.getItems().clear();

        }
    }
}
