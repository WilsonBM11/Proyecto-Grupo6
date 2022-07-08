/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main;

import domain.SinglyLinkedList;
import domain.Usuario;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Duran Family
 */
public class FXMLLoginAdminController implements Initializable {
    

   
    Alert alert;
    

    @FXML
    private TextField TF_USUARIO;
    @FXML
    private TextField TF_CONTRASEÑA;
    @FXML
    private Button BTN_LOGIN;
    @FXML
    private Button BTN_CLEAR;
    @FXML
    private BorderPane bp;
    @FXML
    private Button BTN_SIGNIN;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void LOGINCODE(ActionEvent event) {
        String usuario = usuario();
        String password = password();
        //Si el usuario y contraseña son correctos se mostrara la calculadora y se ocultara el login
        if (usuario.equals(TF_USUARIO.getText()) && password.equals(TF_CONTRASEÑA.getText())) {
            FXMLMainMenuController.loadPage(getClass().getResource("FXMLMainMenu.fxml"), bp);
        } else {
            alert = new Alert(Alert.AlertType.NONE);
                 alert.setAlertType(Alert.AlertType.ERROR);
                 alert.setTitle("Login");
                 alert.setHeaderText("Usuario y/o contraseña incorrectas");
                 alert.show();
        }
        //End if
    }


    @FXML
    private void CLEARCODE(ActionEvent event) {
        TF_USUARIO.setText("");
        TF_CONTRASEÑA.setText("");
    }

    public String usuario() {
        //Se crea un metodo que va a retornar el String de Usuario
        File contra = new File("passwords.txt");
        //Un string que va a leer linea por linea
        String lineRegisterFile = "";
        //Un arreglo de string para el metodo split
        String fila[];
        //Un String que tomara la parte del usuario
        String part1 = "";
        try {

            FileInputStream fis = new FileInputStream(contra); //Se crea un input String que recibe los datos del archivo
            InputStreamReader isr = new InputStreamReader(fis); //Este metodo se encargaa de leerlo 
            BufferedReader br = new BufferedReader(isr); //Este metodo se encarga de transformar de idioma maquina a normal  
            //Mientras sea diferente de null va a reccorer el archivo
            while (lineRegisterFile != null) {
                //El line Register va a utilizar el read line para leer la linea
                lineRegisterFile = br.readLine();

                if (lineRegisterFile != null) {
                    //El fila usara el split para separar el String cuando este el caracter ;
                    fila = lineRegisterFile.split(";");
                    //Tomara la primera parte antes del caracter ;
                    part1 = fila[0];

                }
            }
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null, "No se encontro el archivo");
        } catch (IOException ex) { //Catch por si no se pudo leer el archivo
            JOptionPane.showMessageDialog(null, "No se pudo leer ");
        }
        return part1;
    }
    //Se hace el mismo metodo que el usuario pero este tomara la segunda parte la del password 

    public String password() {
        //Se crea un metodo que va a retornar el String de Usuario
        File contra = new File("passwords.txt");
        //Un string que va a leer linea por linea
        String lineRegisterFile = "";
        //Un arreglo de string para el metodo split
        String fila[];
        //Un String que tomara la parte del password
        String part2 = "";
        try {
            FileInputStream fis = new FileInputStream(contra); //Se crea un input Stream que recibe los datos del archivo
            InputStreamReader isr = new InputStreamReader(fis); //Este metodo se encargaa de leerlo 
            BufferedReader br = new BufferedReader(isr); //Este metodo se encarga de transformar de idioma maquina a normal
            while (lineRegisterFile != null) {

                lineRegisterFile = br.readLine();
                if (lineRegisterFile != null) {
                    fila = lineRegisterFile.split(";");
                    part2 = fila[1];

                }
            }
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null, "No se encontro el archivo");
        } catch (IOException ex) { //Catch por si no se pudo leer el archivo
            JOptionPane.showMessageDialog(null, "No se pudo leer ");
        }
        return part2;
    }

}