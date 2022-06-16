/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

/**
 *
 * @author Profesor Gilberth Chaves A <gchavesav@ucr.ac.cr>
 */
public class FXMLMainMenuController implements Initializable {
    
    private Label label;
    @FXML
    private BorderPane bp;
    @FXML
    private Button btnHome;
    @FXML
    private AnchorPane ap;
    @FXML
    private Button btnExit;
    @FXML
    private Text txtMessage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    public static void loadPage(URL ui, BorderPane bp){
        Parent root = null;
        try {
            root = FXMLLoader.load(ui); 
        } catch (IOException ex) {
            Logger.getLogger(FXMLMainMenuController.class.getName());
        }
        //cleaning nodes
        bp.setTop(null); bp.setCenter(null); bp.setBottom(null); 
        //bp.setLeft(null);
        bp.setRight(null);
        bp.setCenter(root);
    }


    @FXML
    private void Home(MouseEvent event) {
        this.txtMessage.setText("Proyecto Clinica de Salud Mental");
        this.bp.setCenter(ap);
    }

    @FXML
    private void Exit(MouseEvent event) {
        System.exit(0); //FORMA VALIDA
   
    }

    @FXML
    private void PascientesCode(ActionEvent event) {
        loadPage(getClass().getResource("FXMLMantenimientoPascientes.fxml"), bp);
    }

    @FXML
    private void DoctoresCode(ActionEvent event) {
        loadPage(getClass().getResource("FXMLMantenimientoDoctores.fxml"), bp);
    }

    @FXML
    private void EnfermedadesCode(ActionEvent event) {
        loadPage(getClass().getResource("FXMLMantenimientoEnfermedades.fxml"), bp);
    }

    @FXML
    private void CitasCode(ActionEvent event) {
        loadPage(getClass().getResource("FXMLCitas.fxml"), bp);
    }
   
    
}
