/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main;


import domain.CircularDoublyLinkedList;
import domain.Encoder;
import domain.QueueException;
import domain.SinglyLinkedList;
import domain.Usuario;
import domain.ValidacionCorreo;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import static main.FXMLMainMenuController.loadPage;


/**
 * FXML Controller class
 *
 * @author Kevin Brenes
 */
public class FXMLRegistroController implements Initializable {

    private Alert alert;
    private SinglyLinkedList adminList;
    private final Encoder mEncoder;
    private final ValidacionCorreo mValidaciones;

    public FXMLRegistroController() {
        initComponents();
        mEncoder = new Encoder();
        mValidaciones = new ValidacionCorreo();

    }

    private JCheckBox CB_Mostrar;
    private JCheckBox CB_MostrarConfirm;
    @FXML
    private BorderPane bp;
    @FXML
    private JPasswordField PF_CONTRASEÑA;
    @FXML
    private JPasswordField PF_CONFIRMAR_CONTRASEÑA;
    @FXML
    private Button BTN_SIGNIN;
    @FXML
    private Button BTN_REGRESAR;
    @FXML
    private TextField TF_USUARIO;
    @FXML
    private TextField TF_NOMBRE;
    @FXML
    private TextField TF_APELLIDO;
    @FXML
    private TextField TF_CORREO;


    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        adminList = new SinglyLinkedList();
        if (util.Data.fileExists("admin")) {
            try {
                adminList = (SinglyLinkedList) util.Data.getDataFile("admin", adminList);
            } catch (QueueException | IOException ex) {
                Logger.getLogger(FXMLAddDoctorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void SINGINCODE(ActionEvent event) {

        if (!TF_USUARIO.getText().trim().equals("") && !TF_NOMBRE.getText().trim().equals("")
                && !TF_APELLIDO.getText().trim().equals("")
                && !TF_CORREO.getText().trim().equals("") && !PF_CONTRASEÑA.getText().trim().equals("")
                && !PF_CONFIRMAR_CONTRASEÑA.getText().trim().equals("")) {

        } else {
            alert = new Alert(Alert.AlertType.NONE);
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Sign in");
            alert.setHeaderText("ERROR");
            alert.setContentText("Fill all the spaces");
            alert.show();
        }

        if (mValidaciones.ValidarCorreo(TF_CORREO.getText().trim())) {

        } else {
            alert = new Alert(Alert.AlertType.NONE);
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Sign in");
            alert.setHeaderText("ERROR");
            alert.setContentText("Invalid Email");
            alert.show();
        }

        if (PF_CONTRASEÑA.getText().trim().equals(PF_CONFIRMAR_CONTRASEÑA.getText().trim())) {
            
            Usuario mUsuario = new Usuario();
            mUsuario.setUsuario(TF_USUARIO.getText().trim());
            mUsuario.setNombre(TF_NOMBRE.getText().trim());
            mUsuario.setApellido(TF_APELLIDO.getText().trim());
            mUsuario.setContraseña(mEncoder.ecnode(PF_CONTRASEÑA.getText().trim()));
            mUsuario.setCorreo(TF_CORREO.getText().trim());
        } else {
            alert = new Alert(Alert.AlertType.NONE);
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Sign in");
            alert.setHeaderText("ERROR");
            alert.setContentText("Las Contraseñas no coinciden");
            alert.show();
        }
    }

    private void cbMostrarActionPerformed(java.awt.event.ActionEvent evt) {
        if (CB_Mostrar.isSelected()) {
            // TF_CONTRASEÑA.setEchoChar((char) 0); //password = JPasswordField
        } else {
            //   TF_CONTRASEÑA.setEchoChar('*');
        }
    }

    private void cbMostrarConfirmActionPerformed(java.awt.event.ActionEvent evt) {
        if (CB_MostrarConfirm.isSelected()) {
            //   TF_CONFIRMAR_CONTRASEÑA.setEchoChar((char) 0); //password = JPasswordField
        } else {
            //TF_CONFIRMAR_CONTRASEÑA.setEchoChar('*');
        }
    }

    @FXML
    private void REGRESARCODE(ActionEvent event) {
        loadPage(getClass().getResource("FXMLLoginAdmin.fxml"), bp);

    }

    private void initComponents() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
