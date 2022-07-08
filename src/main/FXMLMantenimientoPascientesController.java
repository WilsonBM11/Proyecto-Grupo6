/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main;

import domain.CircularLinkedList;
import domain.ListException;
import domain.Patient;
import domain.QueueException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javax.mail.MessagingException;
import util.Mail;

/**
 * FXML Controller class
 *
 * @author Duran Family
 */
public class FXMLMantenimientoPascientesController implements Initializable {

    Alert alert;
    CircularLinkedList PatientList;
    @FXML
    private TableColumn<List<String>, String> IDcolumn;
    @FXML
    private TableColumn<List<String>, String> LastNameColumn;
    @FXML
    private TableColumn<List<String>, String> FirstNameColumn;
    @FXML
    private TableColumn<List<String>, String> PhoneNumberColumn;
    @FXML
    private TableColumn<List<String>, String> EmailColumn;
    @FXML
    private TableView<List<String>> TableView;
    @FXML
    private TableColumn<List<String>, String> AddressColumn;
    @FXML
    private TableColumn<List<String>, String> AgeColumn;
    @FXML
    private BorderPane bp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PatientList = new CircularLinkedList();
        if (util.Data.fileExists("patients")) {
            try {
                PatientList = (CircularLinkedList) util.Data.getDataFile("patients", PatientList);
            } catch (QueueException | IOException ex) {
                Logger.getLogger(FXMLMantenimientoDoctoresController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        this.IDcolumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyObjectWrapper<>(data.getValue().get(0));
            }
        });

        this.LastNameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyObjectWrapper<>(data.getValue().get(2));
            }
        });

        this.FirstNameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyObjectWrapper<>(data.getValue().get(1));
            }
        });

        this.PhoneNumberColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyObjectWrapper<>(data.getValue().get(3));
            }
        });

        this.EmailColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyObjectWrapper<>(data.getValue().get(4));
            }
        });
        this.AddressColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyObjectWrapper<>(data.getValue().get(5));
            }

        });
        this.AgeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyObjectWrapper<>(data.getValue().get(6));
            }

        });
        this.TableView.setEditable(true);
        LastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        FirstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        AgeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        PhoneNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        EmailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        AddressColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        try {
            if (this.PatientList != null && !this.PatientList.isEmpty()) {
                for (int i = 1; i <= PatientList.size(); i++) {
                    this.TableView.setItems(getData());
                }
            }
        } catch (ListException ex) {
        }
    }

    private ObservableList<List<String>> getData() {
        ObservableList<List<String>> data = FXCollections.observableArrayList();
        if (this.PatientList != null && !this.PatientList.isEmpty()) {
            try {
                for (int i = 1; i <= this.PatientList.size(); i++) {
                    Patient D = (Patient) this.PatientList.getNode(i).data;
                    List<String> arrayList = new ArrayList<>();
                    arrayList.add(String.valueOf(D.getId()));
                    arrayList.add(D.getFirstname());
                    arrayList.add(D.getLastname());
                    arrayList.add(D.getPhoneNumber());
                    arrayList.add(D.getEmail());
                    arrayList.add(D.getAddress());
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    arrayList.add(String.valueOf(format.format(D.getBirthday())));

                    //Agrego el arrayList a ObservableList<List<String>> data
                    data.add(arrayList);
                }
            } catch (ListException ex) {

            }
        }
        return data;
    }

    @FXML
    private void AddCode(ActionEvent event) {
        FXMLMainMenuController.loadPage(getClass().getResource("FXMLPatientAdd.fxml"), bp);
    }

    @FXML
    private void DeleteCode(ActionEvent event) throws ListException {
try {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Patient Remove");
        dialog.setHeaderText("Enter the ID:");
        Optional<String> id = dialog.showAndWait();

        if (!id.isPresent() || id.get().compareTo("") == 0) {
            alert = new Alert(Alert.AlertType.NONE);
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Patient Remove");
            alert.setHeaderText("The list dont have the element");
            alert.show();

        }
            if (PatientList.size() == 1) {
                if (PatientList.contains(new Patient(Integer.parseInt(id.get()), "", "", "", "", "", null))) {
                    PatientList.clear();
                    TableView.getItems().clear();
                    try {
                        util.Data.setDataFile("patients", PatientList);
                    } catch (QueueException ex) {
                        Logger.getLogger(FXMLMantenimientoPascientesController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLMantenimientoPascientesController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Patient Remove");
                    alert.setHeaderText("The list dont have the element");
                    alert.show();

                }
            } else {
                if (PatientList.contains(new Patient(Integer.parseInt(id.get()), "", "", "", "", "", null))) {
                    PatientList.remove(new Patient(Integer.parseInt(id.get()), "", "", "", "", "", null));
                    try {
                        util.Data.setDataFile("patients", PatientList);
                    } catch (QueueException ex) {
                        Logger.getLogger(FXMLMantenimientoPascientesController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLMantenimientoPascientesController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    TableView.getItems().clear();
                    TableView.getItems().addAll(getData());
                    try {
                        util.Data.setDataFile("patients", PatientList);
                    } catch (QueueException | IOException ex) {
                        Logger.getLogger(FXMLMantenimientoPascientesController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setTitle("Patient - Remove");
                    alert.setHeaderText("The Patient was removed");
                    alert.show();
                } else {
                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Patient Remove");
                    alert.setHeaderText("The list dont have the Patient");
                    alert.show();

                }
            }
        } catch (ListException ex)  {
            Logger.getLogger(FXMLMantenimientoPascientesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchElementException ex){
            System.err.println("ERROR " + ex);
            
    }
    }

    @FXML
    private void ContainsCode(ActionEvent event) throws ListException {
       try {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Patient Position Contains");
        dialog.setHeaderText("Enter the id: ");
        Optional<String> id = dialog.showAndWait();
        Patient patient = new Patient(Integer.parseInt(id.get()), "", "", "", "", "", null);
        if (!id.isPresent() || id.get().compareTo("") == 0) {
            alert = new Alert(Alert.AlertType.NONE);
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Patient Position Contains");
            alert.setHeaderText("The list doesn't contains the element");
            alert.show();
        }else {
                if (PatientList.contains(patient)) {
                    Object foundElement = PatientList.getNodeById(patient);
                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setTitle("Patient Contains");
                    alert.setHeaderText("The list contains: ");
                    alert.setContentText(foundElement.toString());
                    alert.show();
                } else {
                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Patient Position Contains");
                    alert.setHeaderText("The list dont have the element");
                    alert.show();

                }
            }
       }catch (ListException ex) {
                alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Doctor Position Contains");
                alert.setHeaderText(ex.getMessage());
                alert.show();
        } catch (NoSuchElementException ex){
            System.err.println("ERROR " + ex);
        }
       }
    
      @FXML
    private void FirstNameCommit(TableColumn.CellEditEvent<List<String>, String> event) throws ListException {
        try {
            String FirstName = event.getRowValue().get(1);
            String sDate1 = event.getRowValue().get(6);
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
            Patient oldPatient = new Patient(Integer.parseInt(event.getRowValue().get(0)), FirstName, event.getRowValue().get(2), event.getRowValue().get(3), event.getRowValue().get(4), event.getRowValue().get(5), date1);
            Patient newPatient = new Patient(Integer.parseInt(event.getRowValue().get(0)), event.getNewValue(), event.getRowValue().get(2), event.getRowValue().get(3), event.getRowValue().get(4), event.getRowValue().get(5), date1);
            this.PatientList.modificar(oldPatient, newPatient);
            try {
                util.Data.setDataFile("patients", PatientList);
            } catch (QueueException ex) {
                Logger.getLogger(FXMLMantenimientoPascientesController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FXMLMantenimientoPascientesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(PatientList.toString());
            if (this.PatientList != null && !this.PatientList.isEmpty()) {
                this.TableView.setItems(getData());
            }
        } catch (ParseException ex) {
            Logger.getLogger(FXMLMantenimientoPascientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      @FXML

    private void LastNameCommit(TableColumn.CellEditEvent<List<String>, String> event) throws ListException {
        try {
            String lastName = event.getRowValue().get(2);
            String sDate1 = event.getRowValue().get(6);
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
            Patient oldPatient = new Patient(Integer.parseInt(event.getRowValue().get(0)), event.getRowValue().get(1), lastName, event.getRowValue().get(3), event.getRowValue().get(4), event.getRowValue().get(5), date1);
            Patient newPatient = new Patient(Integer.parseInt(event.getRowValue().get(0)), event.getRowValue().get(1), event.getNewValue(), event.getRowValue().get(3), event.getRowValue().get(4), event.getRowValue().get(5), date1);
            this.PatientList.modificar(oldPatient, newPatient);
            try {
                util.Data.setDataFile("patients", PatientList);
            } catch (QueueException ex) {
                Logger.getLogger(FXMLMantenimientoPascientesController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FXMLMantenimientoPascientesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (this.PatientList != null && !this.PatientList.isEmpty()) {
                this.TableView.setItems(getData());
            }
        } catch (ParseException ex) {
            Logger.getLogger(FXMLMantenimientoPascientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      @FXML

    private void PhoneNumberCommit(TableColumn.CellEditEvent<List<String>, String> event) throws ListException {

        try {
            String PhoneNumber = event.getRowValue().get(3);
            String sDate1 = event.getRowValue().get(6);
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
            Patient oldPatient = new Patient(Integer.parseInt(event.getRowValue().get(0)), event.getRowValue().get(1), event.getRowValue().get(2), PhoneNumber, event.getRowValue().get(4), event.getRowValue().get(5), date1);
            Patient newPatient = new Patient(Integer.parseInt(event.getRowValue().get(0)), event.getRowValue().get(1), event.getRowValue().get(2), event.getNewValue(), event.getRowValue().get(4), event.getRowValue().get(5), date1);
            this.PatientList.modificar(oldPatient, newPatient);
            try {
                util.Data.setDataFile("patients", PatientList);
            } catch (QueueException ex) {
                Logger.getLogger(FXMLMantenimientoPascientesController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FXMLMantenimientoPascientesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (this.PatientList != null && !this.PatientList.isEmpty()) {
                this.TableView.setItems(getData());
            }
        } catch (ParseException ex) {
            Logger.getLogger(FXMLMantenimientoPascientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  @FXML
    private void EmailCommit(TableColumn.CellEditEvent<List<String>, String> event) throws ListException {
        try {
            Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
            Matcher mather = pattern.matcher(event.getNewValue());
            if (mather.find() == true) {
                String Email = event.getRowValue().get(4);
                String sDate1 = event.getRowValue().get(6);
                Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
                Patient oldPatient = new Patient(Integer.parseInt(event.getRowValue().get(0)), event.getRowValue().get(1), event.getRowValue().get(2), event.getRowValue().get(3), Email, event.getRowValue().get(5), date1);
                Patient newPatient = new Patient(Integer.parseInt(event.getRowValue().get(0)), event.getRowValue().get(1), event.getRowValue().get(2), event.getRowValue().get(3), event.getNewValue(), event.getRowValue().get(5), date1);
                this.PatientList.modificar(oldPatient, newPatient);
                try {
                    util.Data.setDataFile("patients", PatientList);
                } catch (QueueException ex) {
                    Logger.getLogger(FXMLMantenimientoPascientesController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(FXMLMantenimientoPascientesController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Mail m = new Mail();
                try {
                    m.sendEmail(newPatient.getEmail(), "Registro de Usuario - " + m.getClinicName(),
                            "¡Se registro con exito su usario!<br><br>"
                            + "A continuación se muestran los datos de su registro:<br><br>"
                            + "Cédula: " + newPatient.getId() + "<br><br>"
                            + "Nombre: " + newPatient.getFirstname() + " " + newPatient.getLastname() + "<br><br>"
                            + "Teléfono: " + newPatient.getPhoneNumber() + "<br><br>"
                            + "Dirección: " + newPatient.getAddress() + "<br><br>"
                            + "Email: " + newPatient.getEmail() + "<br><br>"
                            + "Su usuario de ingreso al sistema es: " + newPatient.getId() + "<br><br>"
                            + "Su contraseña temporal de ingreso al sistema es: " + util.Utility.random(0, 10000) + "<br><br>"
                            + "¡Gracias por poner su salud en nuestras manos!");

                } catch (MessagingException ex) {
                    Logger.getLogger(FXMLMantenimientoPascientesController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Doctor");
                alert.setHeaderText("ERROR");
                alert.setContentText("Invalid Email");
                alert.show();
            }
            if (this.PatientList != null && !this.PatientList.isEmpty()) {
                this.TableView.setItems(getData());
            }
        } catch (ParseException ex) {
            Logger.getLogger(FXMLMantenimientoPascientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void AddressCommit(TableColumn.CellEditEvent<List<String>, String> event) throws ListException {

        try {
            String Address = event.getRowValue().get(5);
            String sDate1 = event.getRowValue().get(6);
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
            Patient oldPatient = new Patient(Integer.parseInt(event.getRowValue().get(0)), event.getRowValue().get(1), event.getRowValue().get(2), event.getRowValue().get(3), event.getRowValue().get(4), Address, date1);
            Patient newPatient = new Patient(Integer.parseInt(event.getRowValue().get(0)), event.getRowValue().get(1), event.getRowValue().get(2), event.getRowValue().get(3), event.getRowValue().get(4), event.getNewValue(), date1);
            this.PatientList.modificar(oldPatient, newPatient);
            try {
                util.Data.setDataFile("patients", PatientList);
            } catch (QueueException | IOException ex) {
                Logger.getLogger(FXMLMantenimientoPascientesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (this.PatientList != null && !this.PatientList.isEmpty()) {
                this.TableView.setItems(getData());
            }
        } catch (ParseException ex) {
            Logger.getLogger(FXMLMantenimientoPascientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML

    private void BirthdayCommit(TableColumn.CellEditEvent<List<String>, String> event) {
        try {
            String sDate1 = event.getRowValue().get(6);
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
            Patient oldPatient = new Patient(Integer.parseInt(event.getRowValue().get(0)), event.getRowValue().get(1), event.getRowValue().get(2), event.getRowValue().get(3), event.getRowValue().get(4), event.getRowValue().get(5), date1);
            Patient newPatient = new Patient(Integer.parseInt(event.getRowValue().get(0)), event.getRowValue().get(1), event.getRowValue().get(2), event.getRowValue().get(3), event.getRowValue().get(4), event.getRowValue().get(5), new SimpleDateFormat("dd/MM/yyyy").parse(event.getNewValue()));
            try {
                this.PatientList.modificar(oldPatient, newPatient);
            } catch (ListException ex) {
                Logger.getLogger(FXMLMantenimientoPascientesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                try {
                    util.Data.setDataFile("patients", PatientList);
                } catch (ListException ex) {
                    Logger.getLogger(FXMLMantenimientoPascientesController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (QueueException | IOException ex) {
                Logger.getLogger(FXMLMantenimientoDoctoresController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (this.PatientList != null && !this.PatientList.isEmpty()) {
                this.TableView.setItems(getData());
            }
        } catch (ParseException ex) {
            Logger.getLogger(FXMLMantenimientoDoctoresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    
}
