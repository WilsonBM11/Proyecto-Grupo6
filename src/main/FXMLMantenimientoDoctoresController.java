/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main;

import domain.CircularDoublyLinkedList;
import domain.Doctor;
import domain.ListException;
import java.net.URL;
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
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

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
                return new ReadOnlyObjectWrapper<>(data.getValue().get(1));
            }
        });

        this.FirstNameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyObjectWrapper<>(data.getValue().get(2));
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
            DoctorList.remove(new Doctor(Integer.parseInt(id.get()), "", "", "", "", "", null));
            util.Utility.setCircularDoublyLinkedList(DoctorList);
            TableView.getItems().clear();
            TableView.getItems().addAll(getData());
            alert = new Alert(Alert.AlertType.NONE);
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setTitle("Doctor - Remove");
            alert.setHeaderText("The element was removed");
            alert.show();
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
    private void ModifyCode(ActionEvent event) {
    }

}
