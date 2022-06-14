/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main;

import domain.CircularDoublyLinkedList;
import domain.Doctor;
import domain.LinkedQueue;
import domain.ListException;
import domain.Queue;
import domain.QueueException;
import domain.SinglyLinkedList;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
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
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TableCell;

/**
 * FXML Controller class
 *
 * @author Duran Family
 */
public class FXMLMantenimientoDoctoresController implements Initializable {

    Alert alert;
    CircularDoublyLinkedList DoctorList;
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
        this.DoctorList = util.Utility.getCircularDoublyLinkedList();

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
            if (this.DoctorList != null && !this.DoctorList.isEmpty()) {
                for (int i = 1; i <= DoctorList.size(); i++) {
                    this.TableView.setItems(getData());
                }
            }
        } catch (ListException ex) {
        }
    }

    private ObservableList<List<String>> getData() {
        ObservableList<List<String>> data = FXCollections.observableArrayList();
        if (this.DoctorList != null && !this.DoctorList.isEmpty()) {
            try {
                for (int i = 1; i <= this.DoctorList.size(); i++) {
                    Doctor D = (Doctor) this.DoctorList.getNode(i).data;
                    List<String> arrayList = new ArrayList<>();
                    arrayList.add(String.valueOf(D.getId()));
                    arrayList.add(D.getFirstName());
                    arrayList.add(D.getLasName());
                    arrayList.add(D.getPhoneNumber());
                    arrayList.add(D.getEmail());
                    arrayList.add(D.getAddress());
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    arrayList.add(String.valueOf(D.getBirthday()));

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
        FXMLMainMenuController.loadPage(getClass().getResource("FXMLAddDoctor.fxml"), bp);
    }

    @FXML
    private void DeleteCode(ActionEvent event) {
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

        try {
            if(DoctorList.size()==1){
                DoctorList.clear();
                TableView.getItems().clear();
            } else {
            DoctorList.remove(new Doctor(Integer.parseInt(id.get()), "", "", "", "", "", null));
            util.Utility.setCircularDoublyLinkedList(DoctorList);
            TableView.getItems().clear();
            TableView.getItems().addAll(getData());
            alert = new Alert(Alert.AlertType.NONE);
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setTitle("Doctor - Remove");
            alert.setHeaderText("The element was removed");
            alert.show();
            }
        } catch (ListException ex) {
            alert = new Alert(Alert.AlertType.NONE);
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Doctor - Remove");
            alert.setHeaderText(ex.getMessage());
            alert.show();
        }
    }

    @FXML
    private void ContainsCode(ActionEvent event) throws ListException {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Doctor Position Contains");
        dialog.setHeaderText("Enter the id: ");
        Optional<String> id = dialog.showAndWait();

        if (!id.isPresent() || id.get().compareTo("") == 0) {
            alert = new Alert(Alert.AlertType.NONE);
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Doctor Postiion Contains");
            alert.setHeaderText("The list doesn't contains the element");
            alert.show();

        } else {

            try {
                if (util.Utility.getCircularDoublyLinkedList().contains(new Doctor(Integer.parseInt(id.get()), "", "", "", "", "", null))) {
                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setTitle("Job Position Contains");
                    alert.setHeaderText("The list contains: ");
                    alert.setContentText(String.valueOf((new Doctor(Integer.parseInt(id.get()), "", "", "", "", "", null))));
                    alert.show();
                } else {
                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Doctor Position Contains");
                    alert.setHeaderText("The list dont have the element");
                    alert.show();

                }
            } catch (ListException ex) {
                alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Doctor Position Contains");
                alert.setHeaderText(ex.getMessage());
                alert.show();
            }
        }
    }

    @FXML
    private void FirstNameCommit(TableColumn.CellEditEvent<List<String>, String> event) throws ListException {
        try {
            DoctorList = util.Utility.getCircularDoublyLinkedList();
            String FirstName = event.getRowValue().get(1);
            String date = event.getRowValue().get(6);
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            Doctor oldDoctor = new Doctor(Integer.parseInt(event.getRowValue().get(0)), FirstName, event.getRowValue().get(2), event.getRowValue().get(3), event.getRowValue().get(4), event.getRowValue().get(5), date1);
            Doctor newDoctor = new Doctor(Integer.parseInt(event.getRowValue().get(0)), event.getNewValue(), event.getRowValue().get(2), event.getRowValue().get(3), event.getRowValue().get(4), event.getRowValue().get(5), date1);
            this.DoctorList.modificar(oldDoctor, newDoctor);
            util.Utility.setCircularDoublyLinkedList(DoctorList);
            if(this.DoctorList!=null && !this.DoctorList.isEmpty()){
                this.TableView.setItems(getData());
            }
        }catch (ParseException ex) {
            System.err.println("ERROR " + ex);
        }
    }
    @FXML
    private void LastNameCommit(TableColumn.CellEditEvent<List<String>, String> event) throws ListException {
       DoctorList = util.Utility.getCircularDoublyLinkedList();
        String lastName = event.getRowValue().get(2);
        Doctor oldDoctor = new Doctor(Integer.parseInt(event.getRowValue().get(0)), event.getRowValue().get(1), lastName, event.getRowValue().get(3), event.getRowValue().get(4), event.getRowValue().get(5), null);
        Doctor newDoctor = new Doctor(Integer.parseInt(event.getRowValue().get(0)), event.getRowValue().get(1), event.getNewValue(), event.getRowValue().get(3), event.getRowValue().get(4), event.getRowValue().get(5), null);
        this.DoctorList.modificar(oldDoctor, newDoctor);
        util.Utility.setCircularDoublyLinkedList(DoctorList);
        System.out.println(DoctorList.toString());
        //String actualizar = String.valueOf(event.getNewValue());
        //String set = event.getRowValue().set(1, actualizar);
        if(this.DoctorList!=null && !this.DoctorList.isEmpty()){
           this.TableView.setItems(getData());
       }
    }

    @FXML
    private void PhoneNumberCommit(TableColumn.CellEditEvent<List<String>, String> event) throws ListException{
    
       DoctorList = util.Utility.getCircularDoublyLinkedList();
        String PhoneNumber = event.getRowValue().get(3);
        Doctor oldDoctor = new Doctor(Integer.parseInt(event.getRowValue().get(0)), event.getRowValue().get(1), event.getRowValue().get(2), PhoneNumber, event.getRowValue().get(4), event.getRowValue().get(5), null);
        Doctor newDoctor = new Doctor(Integer.parseInt(event.getRowValue().get(0)), event.getRowValue().get(1), event.getRowValue().get(2), event.getNewValue(), event.getRowValue().get(4), event.getRowValue().get(5), null);
        this.DoctorList.modificar(oldDoctor, newDoctor);
        util.Utility.setCircularDoublyLinkedList(DoctorList);
        System.out.println(DoctorList.toString());
        //String actualizar = String.valueOf(event.getNewValue());
        //String set = event.getRowValue().set(1, actualizar);
        if(this.DoctorList!=null && !this.DoctorList.isEmpty()){
           this.TableView.setItems(getData());
       }
    }
    @FXML
    private void EmailCommit(TableColumn.CellEditEvent<List<String>, String> event) throws ListException {
     DoctorList = util.Utility.getCircularDoublyLinkedList();
        String Email = event.getRowValue().get(4);
        Doctor oldDoctor = new Doctor(Integer.parseInt(event.getRowValue().get(0)), event.getRowValue().get(1), event.getRowValue().get(2), event.getRowValue().get(3), Email, event.getRowValue().get(5), null);
        Doctor newDoctor = new Doctor(Integer.parseInt(event.getRowValue().get(0)), event.getRowValue().get(1), event.getRowValue().get(2), event.getRowValue().get(3), event.getNewValue(), event.getRowValue().get(5), null);
        this.DoctorList.modificar(oldDoctor, newDoctor);
        util.Utility.setCircularDoublyLinkedList(DoctorList);
        System.out.println(DoctorList.toString());
        //String actualizar = String.valueOf(event.getNewValue());
        //String set = event.getRowValue().set(1, actualizar);
        if(this.DoctorList!=null && !this.DoctorList.isEmpty()){
           this.TableView.setItems(getData());
       }
    }
    @FXML
    private void AddressCommit(TableColumn.CellEditEvent<List<String>, String> event) throws ListException {
    
        DoctorList = util.Utility.getCircularDoublyLinkedList();
        String Address = event.getRowValue().get(5);
        Doctor oldDoctor = new Doctor(Integer.parseInt(event.getRowValue().get(0)), event.getRowValue().get(1), event.getRowValue().get(2), event.getRowValue().get(3), event.getRowValue().get(4), Address, null);
        Doctor newDoctor = new Doctor(Integer.parseInt(event.getRowValue().get(0)), event.getRowValue().get(1), event.getRowValue().get(2), event.getRowValue().get(3), event.getRowValue().get(4), event.getNewValue(), null);
        this.DoctorList.modificar(oldDoctor, newDoctor);
        util.Utility.setCircularDoublyLinkedList(DoctorList);
        System.out.println(DoctorList.toString());
        //String actualizar = String.valueOf(event.getNewValue());
        //String set = event.getRowValue().set(1, actualizar);
        if(this.DoctorList!=null && !this.DoctorList.isEmpty()){
           this.TableView.setItems(getData());
       }
    }
   
}
