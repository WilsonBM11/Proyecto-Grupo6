/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main;

import domain.ListException;
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

/**
 * FXML Controller class
 *
 * @author Duran Family
 */
public class FXMLAddSickController implements Initializable {

    SinglyLinkedList sicknesslist;
    Alert alert;

    @FXML
    private Button registerButton;
    @FXML
    private TextArea TF_Description;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void registerOnAction(ActionEvent event) throws ListException {
        if (sicknesslist == null && util.Utility.getSinglyLinkedList().isEmpty()) {
            if (TF_Description.getText().equals("")) {

                alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Add Sick");
                alert.setHeaderText("ERROR");
                alert.setContentText("Fill all the spaces");
                alert.show();

            } else {

                Sickness sick = new Sickness(TF_Description.getText());
                sicknesslist = new SinglyLinkedList();
                sicknesslist.add(sick);
                util.Utility.setSinglyLinkedList(sicknesslist);
                TF_Description.setText("");

                alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setTitle("Sick added");
                alert.show();

            }
        } else {
            if ("".equals(TF_Description.getText())) {

                alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Sick Employee");
                alert.setHeaderText("ERROR");
                alert.setContentText("Fill all the spaces");
                alert.show();

            } else {

                Sickness S = new Sickness(TF_Description.getText());
                util.Utility.getSinglyLinkedList().add(S);
                util.Utility.setSinglyLinkedList(util.Utility.getSinglyLinkedList());
                TF_Description.setText("");

                alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setTitle("Add Sick");
                alert.setHeaderText("Sickness added");
                alert.show();

            }
        }
    }
}
