/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main;

import domain.Encoder;
import domain.ListException;
import domain.Patient;
import domain.QueueException;
import domain.Security;
import domain.SinglyLinkedList;
import domain.TreeException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Duran Family
 */
public class FXMLLoginController implements Initializable {

    Alert alert;
    SinglyLinkedList user;
    @FXML
    private Button registerButton;
    @FXML
    private TextField TF_USER;
    @FXML
    private TextField TF_PASSWORD;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        user = new SinglyLinkedList();
        if (util.Data.fileExists("users")) {
            try {
                user = (SinglyLinkedList) util.Data.getDataFile("users", user);
            } catch (QueueException | IOException ex) {
                Logger.getLogger(FXMLMantenimientoEnfermedadesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void registerOnAction(ActionEvent event) throws IOException {
        if (!TF_PASSWORD.equals("") || !TF_USER.equals("")) {
            if (TF_USER.getText().equals("hola") && TF_PASSWORD.getText().equals("124")) {
                Parent root = FXMLLoader.load(getClass().getResource("FXMLMainMenu.fxml"));
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();

                Scene scene = new Scene(root);
                Stage stage2 = new Stage();

                String css = mainFX.class.getResource("MyStyle.css").toExternalForm();
                scene.getStylesheets().add(css);
                stage2.setScene(scene);
                stage.setTitle("Proyecto Clinica Salud Mental");
                stage.setResizable(false);
                stage.show();
                stage2.show();
            } else {
                alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Login");
                alert.setHeaderText("Username or password incorrect");
                alert.setContentText("Please correct it or fill in all the blanks");
                alert.show();
            }

        }

    }

    private ObservableList<List<String>> getData() {
        ObservableList<List<String>> data = FXCollections.observableArrayList();
        if (this.user != null && !this.user.isEmpty()) {
            try {
                for (int i = 1; i <= this.user.size(); i++) {
                    Security S = (Security) this.user.getNode(i).data;
                    List<String> arrayList = new ArrayList<>();
                    arrayList.add(S.getUser());
                    arrayList.add(S.getPassword());
                    arrayList.add(S.getType());
                    data.add(arrayList);
                }
            } catch (ListException ex) {

            }
        }
        return data;
    }

    private String getUser() {
        String name1 = "";
        String name = String.valueOf(getData());
        String[] parts = name.split(",");
        String name2 = parts[0];
        name1 = name2.substring(1, name.length() - 2);
        return name1;
    }

    private String getPassword() {
        String name1 = "";
        String name = String.valueOf(getData());
        String[] parts = name.split(",");
        String name2 = parts[1];
        name1 = name2.substring(0, name.length() - 2);
        return name1;
    }

    private String getType() {
        String name1 = "";
        String name = String.valueOf(getData());
        String[] parts = name.split(",");
        String name2 = parts[2];
        name1 = name2.substring(0, name.length() - 2);
        return name1;
    }

}
