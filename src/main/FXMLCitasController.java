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
import domain.Sickness;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Duran Family
 */
public class FXMLCitasController implements Initializable {

    DoublyLinkedList AppointmentList;
    Alert alert;
    CircularDoublyLinkedList DoctorList;
    CircularLinkedList PatientList;
    @FXML
    private BorderPane bp;
    @FXML
    private TableView<List<String>> TableView;
    @FXML
    private TableColumn<List<String>, String> DOCTORIDCOLUMN;
    @FXML
    private TableColumn<List<String>, String> PATIENTIDCOLUMN;
    @FXML
    private TableColumn<List<String>, String> APPOINTMENTCOLUMN;
    @FXML
    private TableColumn<List<String>, String> RemarksCOLUMN;
    @FXML
    private TableColumn<List<String>, String> IDCOLUMN;
    @FXML
    private ComboBox DcotorCombobox;
    @FXML
    private ComboBox PatientCombobox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AppointmentList = new DoublyLinkedList();

        if (util.Data.fileExists("appointment")) {
            try {
                AppointmentList = (DoublyLinkedList) util.Data.getDataFile("appointment", AppointmentList);
            } catch (QueueException | IOException ex) {
                Logger.getLogger(FXMLMantenimientoDoctoresController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
                    this.DcotorCombobox.setItems(getDataDoctor());
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
                    this.PatientCombobox.setItems(getDataPatient());

                }

                this.IDCOLUMN.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                        return new ReadOnlyObjectWrapper<>(data.getValue().get(0));
                    }
                });

                this.PATIENTIDCOLUMN.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                        return new ReadOnlyObjectWrapper<>(data.getValue().get(1));
                    }
                });

                this.DOCTORIDCOLUMN.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                        return new ReadOnlyObjectWrapper<>(data.getValue().get(2));
                    }
                });

                this.APPOINTMENTCOLUMN.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                        return new ReadOnlyObjectWrapper<>(data.getValue().get(3));
                    }
                });

                this.RemarksCOLUMN.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                        return new ReadOnlyObjectWrapper<>(data.getValue().get(4));
                    }
                });
                this.TableView.setEditable(true);
                RemarksCOLUMN.setCellFactory(TextFieldTableCell.forTableColumn());
                PATIENTIDCOLUMN.setCellFactory(TextFieldTableCell.forTableColumn());
                DOCTORIDCOLUMN.setCellFactory(TextFieldTableCell.forTableColumn());
                APPOINTMENTCOLUMN.setCellFactory(TextFieldTableCell.forTableColumn());

                try {
                    if (this.AppointmentList != null && !this.AppointmentList.isEmpty()) {
                        for (int i = 1; i <= AppointmentList.size(); i++) {
                            this.TableView.setItems(getData());
                        }
                    }
                } catch (ListException ex) {
                }
            } catch (ListException ex) {
                Logger.getLogger(FXMLCitasController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void AddCode(ActionEvent event) {
        FXMLMainMenuController.loadPage(getClass().getResource("FXMLAddCITAS.fxml"), bp);
    }

    @FXML
    private void DeleteCode(ActionEvent event) {
        try {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Appointment Remove");
            dialog.setHeaderText("Enter the ID Patient:");
            Optional<String> id = dialog.showAndWait();
            TextInputDialog dialog2 = new TextInputDialog();
            dialog2.setTitle("Appointment Remove");
            dialog2.setHeaderText("Enter the Date of the appointment:");
            Optional<String> id2 = dialog2.showAndWait();

            if ((!id.isPresent() || id.get().compareTo("") == 0 && !id2.isPresent() || id2.get().compareTo("") == 0)) {
                alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Doctor Remove");
                alert.setHeaderText("The list dont have the element");
                alert.show();

            }
            if (AppointmentList.size() == 1) {
                String strDatewithTime = id2.get();
                LocalDateTime aLDT = LocalDateTime.parse(strDatewithTime);

                if (AppointmentList.contains(new Appointment(Integer.parseInt(id.get()), 0, aLDT, ""))) {
                    AppointmentList.clear();
                    TableView.getItems().clear();
                    util.Data.setDataFile("appointment", AppointmentList);
                } else {
                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Appointment Remove");
                    alert.setHeaderText("The list dont have the element");
                    alert.show();
                }
            } else {
                String strDatewithTime = id2.get();
                LocalDateTime aLDT = LocalDateTime.parse(strDatewithTime);
                if (AppointmentList.contains(new Appointment(Integer.parseInt(id.get()), 0, aLDT, ""))) {
                    AppointmentList.remove(new Appointment(Integer.parseInt(id.get()), 0, aLDT, ""));
                    util.Data.setDataFile("appointment", AppointmentList);
                    TableView.getItems().clear();
                    TableView.getItems().addAll(getData());
                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setTitle("Appointment - Remove");
                    alert.setHeaderText("The element was removed");
                    alert.show();
                } else {
                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Appointment Remove");
                    alert.setHeaderText("The list dont have the element");
                    alert.show();

                }
            }
        } catch (ListException ex) {
            alert = new Alert(Alert.AlertType.NONE);
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Appointment - Remove");
            alert.setHeaderText(ex.getMessage());
            alert.show();
        } catch (QueueException | IOException ex) {
            Logger.getLogger(FXMLMantenimientoDoctoresController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchElementException ex) {
            System.err.println("ERROR " + ex);
        }
    }

    @FXML
    private void ContainsCode(ActionEvent event) {
        try {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Appointment Contains");
            dialog.setHeaderText("Enter the Patient id: ");
            Optional<String> id = dialog.showAndWait();
            TextInputDialog dialog2 = new TextInputDialog();
            dialog2.setTitle("Appointment Contains");
            dialog2.setHeaderText("Enter the Date of appointment: ");
            Optional<String> id2 = dialog2.showAndWait();
            String strDatewithTime = id2.get();
            LocalDateTime aLDT = LocalDateTime.parse(strDatewithTime);

            Appointment appointment = new Appointment(Integer.parseInt(id.get()), 0, aLDT, "");

            if (!id.isPresent() || id.get().compareTo("") == 0) {
                alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Appointment Position Contains");
                alert.setHeaderText("The list doesn't contains the element");
                alert.show();

            } else {
                if (AppointmentList.contains(appointment)) {
                    Object foundElement = AppointmentList.getNodeById(appointment);
                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setTitle("Appointment Contains");
                    alert.setHeaderText("The list contains: ");
                    alert.setContentText(foundElement.toString());
                    alert.show();
                } else {
                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Doctor Position Contains");
                    alert.setHeaderText("The list dont have the element");
                    alert.show();

                }
            }
        } catch (ListException ex) {
            alert = new Alert(Alert.AlertType.NONE);
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Doctor Position Contains");
            alert.setHeaderText(ex.getMessage());
            alert.show();
        } catch (NoSuchElementException ex) {
            System.err.println("ERROR " + ex);
        }
    }

    private ObservableList<List<String>> getData() {
        ObservableList<List<String>> data = FXCollections.observableArrayList();
        if (this.AppointmentList != null && !this.AppointmentList.isEmpty()) {
            try {
                for (int i = 1; i <= this.AppointmentList.size(); i++) {
                    Appointment A = (Appointment) this.AppointmentList.getNode(i).data;
                    List<String> arrayList = new ArrayList<>();
                    arrayList.add(String.valueOf(A.getId()));
                    arrayList.add(String.valueOf(A.getPatientID()));
                    arrayList.add(String.valueOf(A.getDoctorID()));
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    arrayList.add(String.valueOf(A.getDateTime()));
                    arrayList.add(A.getRemarks());
                    data.add(arrayList);
                }
            } catch (ListException ex) {

            }
        }
        return data;
    }

    @FXML
    private void PatientCommit(TableColumn.CellEditEvent<List<String>, String> event) throws ListException {
        int PatientId = Integer.parseInt(event.getRowValue().get(1));
        String strDatewithTime = event.getRowValue().get(3);
        LocalDateTime aLDT = LocalDateTime.parse(strDatewithTime);
        Appointment oldDAppointment = new Appointment(PatientId, Integer.parseInt(event.getRowValue().get(2)), aLDT, event.getRowValue().get(4));
        Appointment newAppointment = new Appointment(Integer.parseInt(event.getNewValue()), Integer.parseInt(event.getRowValue().get(2)), aLDT, event.getRowValue().get(4));
        if (PatientList.contains(new Patient(Integer.parseInt(event.getNewValue()), "", "", "", "", "", null))) {
            this.AppointmentList.modificar(oldDAppointment, newAppointment);
            Appointment.setConsecutivo(oldDAppointment.getId());
            newAppointment.setId(Appointment.getConsecutivo() - 1);
            try {
                util.Data.setDataFile("appointment", AppointmentList);
            } catch (QueueException | ListException | IOException ex) {
                Logger.getLogger(FXMLCitasController.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (this.AppointmentList != null && !this.AppointmentList.isEmpty()) {
                this.TableView.setItems(getData());
            }
        } else {
            Appointment.setConsecutivo(oldDAppointment.getId());
            newAppointment.setId(Appointment.getConsecutivo() - 1);
            alert = new Alert(Alert.AlertType.NONE);
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Appointment Contains");
            alert.setHeaderText("There is no Patient with that id check the combobox options");
            alert.show();
        }
    }

    @FXML
    private void DoctorCommit(TableColumn.CellEditEvent<List<String>, String> event) {
        try {
            int DoctorID = Integer.parseInt(event.getRowValue().get(2));
            String strDatewithTime = event.getRowValue().get(3);
            LocalDateTime aLDT = LocalDateTime.parse(strDatewithTime);
            Appointment oldDAppointment = new Appointment(Integer.parseInt(event.getRowValue().get(1)), DoctorID, aLDT, event.getRowValue().get(4));
            Appointment newAppointment = new Appointment(Integer.parseInt(event.getRowValue().get(1)), Integer.parseInt(event.getNewValue()), aLDT, event.getRowValue().get(4));
            if (DoctorList.contains(new Doctor(Integer.parseInt(event.getNewValue()), "", "", "", "", "", null))) {
                if (!AppointmentList.contains(new Appointment(0, Integer.parseInt(event.getNewValue()), aLDT, ""))) {
                    this.AppointmentList.modificar(oldDAppointment, newAppointment);
                    Appointment.setConsecutivo(oldDAppointment.getId());
                    newAppointment.setId(Appointment.getConsecutivo() - 1);
                    try {
                        util.Data.setDataFile("appointment", AppointmentList);
                    } catch (QueueException | IOException ex) {
                        Logger.getLogger(FXMLCitasController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (this.AppointmentList != null && !this.AppointmentList.isEmpty()) {
                        this.TableView.setItems(getData());
                    }
                } else {
                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Add Appointment");
                    alert.setHeaderText("ERROR");
                    alert.setContentText("There is already an appointment registered on that date with that doctor");
                    alert.show();
                }
            } else {
                Appointment.setConsecutivo(oldDAppointment.getId());
                newAppointment.setId(Appointment.getConsecutivo() - 1);
                alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Appointment Contains");
                alert.setHeaderText("There is no doctor with that id check the combobox options");
                alert.show();

            }
        } catch (ListException ex) {
            Logger.getLogger(FXMLCitasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void TimeCommit(TableColumn.CellEditEvent<List<String>, String> event) {

    }

    @FXML
    private void RemarksCommit(TableColumn.CellEditEvent<List<String>, String> event) throws ListException {
        try {
            String remarks = event.getRowValue().get(4);
            String strDatewithTime = event.getRowValue().get(3);
            LocalDateTime aLDT = LocalDateTime.parse(strDatewithTime);
            Appointment oldDAppointment = new Appointment(Integer.parseInt(event.getRowValue().get(1)), Integer.parseInt(event.getRowValue().get(2)), aLDT, remarks);
            Appointment newAppointment = new Appointment(Integer.parseInt(event.getRowValue().get(1)), Integer.parseInt(event.getRowValue().get(2)), aLDT, event.getNewValue());
            this.AppointmentList.modificar(oldDAppointment, newAppointment);
            Appointment.setConsecutivo(oldDAppointment.getId());
            newAppointment.setId(Appointment.getConsecutivo() - 1);
            try {
                util.Data.setDataFile("appointment", AppointmentList);
            } catch (QueueException | IOException ex) {
                Logger.getLogger(FXMLCitasController.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (this.AppointmentList != null && !this.AppointmentList.isEmpty()) {
                this.TableView.setItems(getData());
            }
        } catch (ListException ex) {
            Logger.getLogger(FXMLCitasController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    private void ClearCode(ActionEvent event) {
        try {
            AppointmentList.clear();
            TableView.getItems().clear();
            util.Data.setDataFile("appointment", AppointmentList);
        } catch (QueueException | ListException | IOException ex) {
            Logger.getLogger(FXMLCitasController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
