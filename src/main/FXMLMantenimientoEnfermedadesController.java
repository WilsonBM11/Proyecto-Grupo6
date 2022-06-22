/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main;

import domain.CircularDoublyLinkedList;
import domain.Doctor;
import domain.ListException;
import domain.QueueException;
import domain.Sickness;
import domain.SinglyLinkedList;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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
public class FXMLMantenimientoEnfermedadesController implements Initializable {
    SinglyLinkedList SicknessList;
    Alert alert;
    @FXML
    private BorderPane bp;
     @FXML
    private TableColumn<List<String>, String> IDcolumn;
    @FXML
    private TableColumn<List<String>, String> DescriptionColumn;
    @FXML
    private TableView<List<String>>  TableView;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SicknessList = new SinglyLinkedList();
        if(util.Data.fileExists("sickness")){
            try {
                SicknessList = (SinglyLinkedList) util.Data.getDataFile("sickness", SicknessList);
            } catch (QueueException | IOException ex) {
                Logger.getLogger(FXMLMantenimientoEnfermedadesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        

        this.IDcolumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyObjectWrapper<>(data.getValue().get(0));
            }
        });
        this.DescriptionColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyObjectWrapper<>(data.getValue().get(1));
            }

        });
        this.TableView.setEditable(true);
        DescriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        try {
            if (this.SicknessList != null && !this.SicknessList.isEmpty()) {
                for (int i = 1; i <= SicknessList.size(); i++) {
                    this.TableView.setItems(getData());
                }
            }
        } catch (ListException ex) {
        }
    }

    private ObservableList<List<String>> getData() {
        ObservableList<List<String>> data = FXCollections.observableArrayList();
        if (this.SicknessList != null && !this.SicknessList.isEmpty()) {
            try {
                for (int i = 1; i <= this.SicknessList.size(); i++) {
                    Sickness D = (Sickness) this.SicknessList.getNode(i).data;
                    List<String> arrayList = new ArrayList<>();
                    arrayList.add(String.valueOf(D.getId()));
                    arrayList.add(D.getDescription());

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
        FXMLMainMenuController.loadPage(getClass().getResource("FXMLAddSick.fxml"), bp);
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

        } else{

        try {
            SicknessList.remove(new Sickness(id.get()));
            try {
                util.Data.setDataFile("sickness", SicknessList);
            } catch (QueueException ex) {
                Logger.getLogger(FXMLMantenimientoEnfermedadesController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FXMLMantenimientoEnfermedadesController.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        
        
    }

    @FXML
    private void ContainsCode(ActionEvent event) throws ListException {
         TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Sickness Contains");
        dialog.setHeaderText("Enter the description: ");
        Optional<String> description = dialog.showAndWait();
            
            
        if(!description.isPresent()||description.get().compareTo("") == 0) {
                 alert = new Alert(Alert.AlertType.NONE);
                 alert.setAlertType(Alert.AlertType.ERROR);
                 alert.setTitle("Sickness Contains");
                 alert.setHeaderText("The list doesn't contains the element");
                 alert.show();
                      
        }else{
                 
             try {
                 if(SicknessList.contains(new Sickness(description.get()))){
                   alert = new Alert(Alert.AlertType.NONE);
                     alert.setAlertType(Alert.AlertType.INFORMATION);
                     alert.setTitle("Sickness Contains");
                     alert.setHeaderText("The list contains: ");
                     alert.setContentText(((new Sickness(description.get())).toString()));
                     alert.show();
                 }else{
                     alert = new Alert(Alert.AlertType.NONE);
                     alert.setAlertType(Alert.AlertType.ERROR);
                     alert.setTitle("Job Position Contains");
                     alert.setHeaderText("The list dont have the element");
                     alert.show();

                 }
             } catch (ListException ex) {
                 alert = new Alert(Alert.AlertType.NONE);
                 alert.setAlertType(Alert.AlertType.ERROR);
                 alert.setTitle("Job Position Contains");
                 alert.setHeaderText(ex.getMessage());
                 alert.show();
             }
             }
    }
   
      

    @FXML
    private void DescriptionCommit(TableColumn.CellEditEvent<List<String>, String> event)throws ListException {
        
        SicknessList = util.Utility.getSinglyLinkedList();
        String Description = event.getRowValue().get(1);
        Sickness oldSick = new Sickness(Description);
        int id = oldSick.getId();
        Sickness newSick = new Sickness(event.getNewValue());
        newSick.setId(id-1);
        Sickness.setConsecutivo(id);
        this.SicknessList.modificar(oldSick, newSick);
        util.Utility.setSinglyLinkedList(SicknessList);
        System.out.println(SicknessList.toString());
       }
    }
