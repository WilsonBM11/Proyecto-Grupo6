/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main;

import domain.CircularDoublyLinkedList;
import domain.Doctor;
import domain.ListException;
import domain.Sickness;
import domain.SinglyLinkedList;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Duran Family
 */
public class FXMLMantenimientodeEnfermedadesPadecimientosController implements Initializable {
    Alert alert;
    SinglyLinkedList SicknessList;

    @FXML
    private BorderPane bp;
    @FXML
    private TableView<List<String>>  TableView;
    @FXML
    private TableColumn<List<String>, String> IDcolumn;
    @FXML
    private TableColumn<List<String>, String> DescriptionColumn;

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
    }    

    @FXML
    private void AddCode(ActionEvent event) {
        FXMLMainMenuController.loadPage(getClass().getResource("FXMLMantemimientodeEnfermedadesPadecimientos.fxml"), bp);
    }

    @FXML
    private void DeleteCode(ActionEvent event) {
    }

    @FXML
    private void ContainsCode(ActionEvent event) {
    }

    @FXML
    private void ModifyCode(ActionEvent event) {
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
                Logger.getLogger(FXMLMantenimientodeEnfermedadesPadecimientosController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return data;
    }

}
