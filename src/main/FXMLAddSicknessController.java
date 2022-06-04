/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main;

import domain.Sickness;
import domain.SinglyLinkedList;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Duran Family
 */
public class FXMLAddSicknessController implements Initializable {

    private SinglyLinkedList sicknesslist;
    Alert alert;
    @FXML
    private BorderPane bp;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnClose;
    @FXML
    private TextArea TA_Description;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnAddOnAction(ActionEvent event) {
        if (sicknesslist == null && util.Utility.getSinglyLinkedList().isEmpty()) {
            if (TA_Description.getText().equals("")) {

                alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Add Doctor");
                alert.setHeaderText("ERROR");
                alert.setContentText("Fill all the spaces");
                alert.show();

            } else {
                Sickness sickness = new Sickness(TA_Description.getText());
                sicknesslist = new SinglyLinkedList();
                sicknesslist.add(sickness);
                util.Utility.setSinglyLinkedList(sicknesslist);
                TA_Description.setText("");

                alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setTitle("Sickness added");
                alert.show();

            }
        } else {
            if (TA_Description.getText().equals("")) {

                alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Add Sickness");
                alert.setHeaderText("ERROR");
                alert.setContentText("Fill all the spaces");
                alert.show();

            } else {

                Sickness sickness = new Sickness(TA_Description.getText());
                sicknesslist = new SinglyLinkedList();
                sicknesslist.add(sickness);
                util.Utility.setSinglyLinkedList(sicknesslist);
                TA_Description.setText("");

                alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setTitle("Add Sickness");
                alert.setHeaderText("Sickness added Succesfully");
                alert.show();

            }
        }
    }

    @FXML
    private void bntCloseOnAction(ActionEvent event) {
        FXMLMainMenuController.loadPage(getClass().getResource("FXMLMantemimientodeEnfermedadesPadecimientos.fxml"), bp);
    }

}
