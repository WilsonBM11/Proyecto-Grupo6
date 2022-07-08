/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import static main.FXMLMainMenuController.loadPage;

/**
 * FXML Controller class
 *
 * @author Kevin Brenes
 */
public class FXMLWelcomeController implements Initializable {
    

    private BorderPane bp;
    @FXML
    private Button BTN_ADMIN;
    @FXML
    private Button BTN_PATIENT;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ADMINCODE(ActionEvent event) throws IOException  {
        
Parent root = FXMLLoader.load(getClass().getResource("FXMLLoginAdmin.fxml"));

        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.show();
        
    }

    @FXML
    private void PATIENTCODE(ActionEvent event) {
        
         loadPage(getClass().getResource("FXMLLoginPatien.fxml"), bp);
    }
    
}
