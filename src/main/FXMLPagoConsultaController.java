/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Wilson Bonilla Mata
 */
public class FXMLPagoConsultaController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private TableView<?> TableView;
    @FXML
    private TableColumn<?, ?> patientColumn;
    @FXML
    private TableColumn<?, ?> paymentModeColumn;
    @FXML
    private TableColumn<?, ?> serviceChargeColumn;
    @FXML
    private TableColumn<?, ?> billingDateColumn;
    @FXML
    private TableColumn<?, ?> totalChargeColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    

    @FXML
    private void AddCode(ActionEvent event) {
    }

    @FXML
    private void DeleteCode(ActionEvent event) {
    }

    @FXML
    private void ContainsCode(ActionEvent event) {
    }

    @FXML
    private void patientColumnOnEditCommit(TableColumn.CellEditEvent<List<String>, String> event) {
    }

    @FXML
    private void paymentModeColumnOnEditCommit(TableColumn.CellEditEvent<List<String>, String> event) {
    }

    @FXML
    private void serviceChargeColumnOnEditCommit(TableColumn.CellEditEvent<List<String>, String> event) {
    }

    @FXML
    private void billingDateColumnOnEditCommit(TableColumn.CellEditEvent<List<String>, String> event) {
    }

    @FXML
    private void totalChargeColumnOnEditCommit(TableColumn.CellEditEvent<List<String>, String>  event) {
    }
    
}
