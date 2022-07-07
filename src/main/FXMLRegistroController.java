/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main;

import domain.BD;
import domain.Encoder;
import domain.Usuario;
import domain.ValidacionCorreo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javax.swing.ImageIcon;

/**
 * FXML Controller class
 *
 * @author Kevin Brenes
 */
public class FXMLRegistroController implements Initializable {

    private BD mBd;
    private Encoder mEncoder;
    private ValidacionCorreo mValidaciones;

    public FXMLRegistroController() {
        initComponents();
        mBd = new BD("login", "root", "");
        mEncoder = new Encoder();
        mValidaciones = new ValidacionCorreo();

    }
    
    
    @FXML
    private BorderPane bp;
    @FXML
    private TextField TF_CONTRASEÑA;
    @FXML
    private Button BTN_SIGNIN;
    @FXML
    private Button BTN_CLEAR;
    @FXML
    private TextField TF_USUARIO;
    @FXML
    private TextField TF_NOMBRE;
    @FXML
    private TextField TF_APELLIDO;
    @FXML
    private TextField TF_CONFIRMAR_CONTRASEÑA;
    @FXML
    private TextField TF_CORREO;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    
    

    @FXML
    private void SINGINCODE(ActionEvent event) {
        if (!TF_USUARIO.getText().trim().equals("") && !TF_NOMBRE.getText().trim().equals("") && !TF_APELLIDO.getText().trim().equals("")
                && !TF_CORREO.getText().trim().equals("") && !TF_CONTRASEÑA.getText().trim().equals("") && !TF_CONFIRMAR_CONTRASEÑA.getText().trim().equals("")) {
            if (mValidaciones.ValidarCorreo(TF_CORREO.getText().trim())) {
                if (TF_CONTRASEÑA.getText().trim().equals(TF_CONFIRMAR_CONTRASEÑA.getText().trim())) {

                    Usuario mUsuario = new Usuario();
                    mUsuario.setUsuario(TF_USUARIO.getText().trim());
                    mUsuario.setNombre(TF_NOMBRE.getText().trim());
                    mUsuario.setApellido(TF_APELLIDO.getText().trim());
                    mUsuario.setContraseña(mEncoder.ecnode(TF_CONTRASEÑA.getText().trim()));
                    mUsuario.setCorreo(TF_CORREO.getText().trim());

        
    
                }
            }
        }
    }

    @FXML
    private void CLEARCODE(ActionEvent event) {
    }

    private void initComponents() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
