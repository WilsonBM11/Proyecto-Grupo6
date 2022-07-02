/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main;

import domain.Appointment;
import domain.DoublyLinkedList;
import domain.ListException;
import domain.QueueException;
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
        PATIENTIDCOLUMN.setCellFactory(TextFieldTableCell.forTableColumn());
        DOCTORIDCOLUMN.setCellFactory(TextFieldTableCell.forTableColumn());
        APPOINTMENTCOLUMN.setCellFactory(TextFieldTableCell.forTableColumn());
        RemarksCOLUMN.setCellFactory(TextFieldTableCell.forTableColumn());

        try {
            if (this.AppointmentList != null && !this.AppointmentList.isEmpty()) {
                for (int i = 1; i <= AppointmentList.size(); i++) {
                    this.TableView.setItems(getData());
                }
            }
        } catch (ListException ex) {
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
            dialog.setTitle("Doctor Remove");
            dialog.setHeaderText("Enter the ID:");
            Optional<String> id = dialog.showAndWait();

            if (!id.isPresent() || id.get().compareTo("") == 0) {
                alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Doctor Remove");
                alert.setHeaderText("The list dont have the element");
                alert.show();

            }
            if (AppointmentList.size() == 1) {
                if (AppointmentList.contains(new Appointment(Integer.parseInt(id.get()), 0, null, ""))) {
                    AppointmentList.clear();
                    TableView.getItems().clear();
                    util.Data.setDataFile("appointment", AppointmentList);
                } else {
                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Doctor Remove");
                    alert.setHeaderText("The list dont have the element");
                    alert.show();
                }
            } else {
                if (AppointmentList.contains(new Appointment(Integer.parseInt(id.get()), 0, null, ""))) {
                    AppointmentList.remove(new Appointment(Integer.parseInt(id.get()), 0, null, ""));
                    util.Data.setDataFile("appointment", AppointmentList);
                    TableView.getItems().clear();
                    TableView.getItems().addAll(getData());
                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setTitle("Doctor - Remove");
                    alert.setHeaderText("The element was removed");
                    alert.show();
                } else {
                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Doctor Remove");
                    alert.setHeaderText("The list dont have the element");
                    alert.show();

                }
            }
        } catch (ListException ex) {
            alert = new Alert(Alert.AlertType.NONE);
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Doctor - Remove");
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
            dialog.setTitle("Doctor Position Contains");
            dialog.setHeaderText("Enter the id: ");
            Optional<String> id = dialog.showAndWait();
            Appointment appointment = new Appointment(Integer.parseInt(id.get()), 0, null, "");

            if (!id.isPresent() || id.get().compareTo("") == 0) {
                alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Appointment Postiion Contains");
                alert.setHeaderText("The list doesn't contains the element");
                alert.show();

            } else {
                if (AppointmentList.contains(appointment)) {
                    Object foundElement = AppointmentList.getNodeById(appointment);
                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setTitle("Job Position Contains");
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

                    //Agrego el arrayList a ObservableList<List<String>> data
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
        this.AppointmentList.modificar(oldDAppointment, newAppointment);
        System.out.println(AppointmentList.toString());
        try {
            util.Data.setDataFile("appointment", AppointmentList);
        } catch (QueueException ex) {
            Logger.getLogger(FXMLCitasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ListException ex) {
            Logger.getLogger(FXMLCitasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLCitasController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (this.AppointmentList != null && !this.AppointmentList.isEmpty()) {
            this.TableView.setItems(getData());
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
            this.AppointmentList.modificar(oldDAppointment, newAppointment);
            try {
                util.Data.setDataFile("appointment", AppointmentList);
            } catch (QueueException ex) {
                Logger.getLogger(FXMLCitasController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FXMLCitasController.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (this.AppointmentList != null && !this.AppointmentList.isEmpty()) {
                this.TableView.setItems(getData());
            }
        } catch (ListException ex) {
            Logger.getLogger(FXMLCitasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void TimeCommit(TableColumn.CellEditEvent<List<String>, String> event) {

    }

    @FXML
    private void RemarksCommit(TableColumn.CellEditEvent<List<String>, String> event) {
        try {
            String remarks = event.getRowValue().get(4);
            String strDatewithTime = event.getRowValue().get(3);
            LocalDateTime aLDT = LocalDateTime.parse(strDatewithTime);
            Appointment oldDAppointment = new Appointment(Integer.parseInt(event.getRowValue().get(1)), Integer.parseInt(event.getRowValue().get(2)), aLDT, remarks);
            Appointment newAppointment = new Appointment(Integer.parseInt(event.getRowValue().get(1)), Integer.parseInt(event.getRowValue().get(2)), aLDT, event.getNewValue());
            this.AppointmentList.modificar(oldDAppointment, newAppointment);
            System.out.println(AppointmentList.toString());
            try {
                util.Data.setDataFile("appointment", AppointmentList);
            } catch (QueueException ex) {
                Logger.getLogger(FXMLCitasController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FXMLCitasController.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (this.AppointmentList != null && !this.AppointmentList.isEmpty()) {
                this.TableView.setItems(getData());
            }
        } catch (ListException ex) {
            Logger.getLogger(FXMLCitasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
