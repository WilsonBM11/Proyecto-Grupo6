/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main;

import java.net.URL;
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
 * @author Duran Family
 */
public class FXMLCitasController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private TableView<?> TableView;
    @FXML
    private TableColumn<?, ?> DOCTORIDCOLUMN;
    @FXML
    private TableColumn<?, ?> PATIENTIDCOLUMN;
    @FXML
    private TableColumn<?, ?> APPOINTMENTCOLUMN;
    @FXML
    private TableColumn<?, ?> SICKNESSCOLUMN;
    @FXML
    private TableColumn<?, ?> ANNOTATIONSCOLUMN;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    private void AddCode(ActionEvent event) {
        FXMLMainMenuController.loadPage(getClass().getResource("FXMLAddCITAS.fxml"), bp);
    }

    @FXML
    private void DeleteCode(ActionEvent event) {
    }

    @FXML
    private void ContainsCode(ActionEvent event) {
    }
    
}
