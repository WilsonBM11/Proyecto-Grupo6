/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main;

import domain.Doctor;
import domain.ListException;
import domain.Sickness;
import domain.SinglyLinkedList;
import java.net.URL;
import java.util.ArrayList;
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
         this.SicknessList = util.Utility.getSinglyLinkedList();

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
            util.Utility.setSinglyLinkedList(SicknessList);
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
        dialog.setTitle("Job Position Contains");
        dialog.setHeaderText("Enter the description: ");
        Optional<String> description = dialog.showAndWait();
            
            
        if(!description.isPresent()||description.get().compareTo("") == 0) {
                 alert = new Alert(Alert.AlertType.NONE);
                 alert.setAlertType(Alert.AlertType.ERROR);
                 alert.setTitle("Job Postiion Contains");
                 alert.setHeaderText("The list doesn't contains the element");
                 alert.show();
                      
        }else{
                 
             try {
                 if(util.Utility.getSinglyLinkedList().contains(new Sickness(description.get()))){
                   alert = new Alert(Alert.AlertType.NONE);
                     alert.setAlertType(Alert.AlertType.INFORMATION);
                     alert.setTitle("Job Position Contains");
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
   
      private ObservableList<List<String>> getData() {
        ObservableList<List<String>> data = FXCollections.observableArrayList();
        if (this.SicknessList != null && !this.SicknessList.isEmpty()) {
            try {
                for (int i = 1; i <= this.SicknessList.size(); i++) {
                    Sickness S = (Sickness) this.SicknessList.getNode(i).data;
                    List<String> arrayList = new ArrayList<>();
                    arrayList.add(String.valueOf(S.getId()));
                    arrayList.add(S.getDescription());

                    //Agrego el arrayList a ObservableList<List<String>> data
                    data.add(arrayList);
                }
            } catch (ListException ex) {

            }
        }
        return data;
    }

    @FXML
    private void DescriptionCommit(TableColumn.CellEditEvent<List<String>, String> event)throws ListException {
        
        SicknessList = util.Utility.getSinglyLinkedList();
        String Description = event.getRowValue().get(1);
        Sickness oldSick = new Sickness(Description);
        Sickness newSick = new Sickness(event.getNewValue());
        newSick.setId(Integer.parseInt(event.getRowValue().get(0)));
        this.SicknessList.modificar(oldSick, newSick);
        util.Utility.setSinglyLinkedList(SicknessList);
        System.out.println(SicknessList.toString());
       }
    }
