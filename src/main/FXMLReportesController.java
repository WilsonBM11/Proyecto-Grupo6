/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main;

import java.sql.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Kevin Brenes
 */
public class FXMLReportesController implements Initializable {

    
    @FXML
    private BorderPane bp;
    @FXML
    private Button btnBuscar;
    @FXML
    private Label labelStatus;
    
    private javax.swing.JTextField txt_nombre;
    
    private javax.swing.JTextField txt_departamento;
    
    private javax.swing.JTextField txt_buscar;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void txt_nombre(ActionEvent event) {
    }

    @FXML
    private void txt_departamento(ActionEvent event) {
    }

    @FXML
    private void btnRegistrar(ActionEvent event) {
        try{
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/reportes", "root", "");
            PreparedStatement pst = cn.prepareStatement("Insert into doctores/especialistas values (?,?,?)");//pst nombre del objeto
            
            pst.setString(1, "0");
             pst.setString(2, txt_nombre.getText().trim()); //.trim quita espacios al inicio y al final
              pst.setString(3, txt_departamento.getText().trim());
              pst.executeUpdate();//ejecuta las instrucciones de la base de datos
              
              txt_nombre.setText("");//Limpiar txt
              txt_departamento.setText("");//Limpiar txt
              labelStatus.setText("Registro exitoso");
              
              
        }catch(Exception e){
            
        }
        
    }

    @FXML
    private void btnModificar(ActionEvent event) {
        
        try {
            String ID = txt_buscar.getText().trim();
             Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/reportes", "root", "");
             PreparedStatement pst = cn.prepareStatement("update doctores/especialistas set Nombre = ?, Departamento = ? where ID = " + ID);
             
             pst.setString(1, txt_nombre.getText().trim());
             pst.setString(1, txt_departamento.getText().trim());
             
             pst.executeUpdate();
             
             labelStatus.setText("Modificacion exitosa");
        } catch (Exception e) {
        }
    
    
    }

    @FXML
    private void btnEliminar(ActionEvent event) {
    
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/reportes", "root", "");
             PreparedStatement pst = cn.prepareStatement("delete from doctores/especialistas where ID =?");
             
             pst.setString(1, txt_buscar.getText().trim());
             pst.executeUpdate();
             
             txt_nombre.setText("");
             txt_departamento.setText("");
             txt_buscar.setText("");
             
             labelStatus.setText("Regsitro eliminado");
        } catch (Exception e) {
        }
    
    
    } 
    
     @FXML
    private void btnBuscar(ActionEvent event) {
        
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/reportes", "root", "");
            PreparedStatement pst = cn.prepareStatement("Select * from doctores/especialistas where ID = ? ");
            
            pst.setString(1, txt_buscar.getText().trim());
            
            ResultSet rs = pst.executeQuery(); //obtener el resultado 
           
            if (rs.next()) {
                txt_nombre.setText(rs.getString("Nombre" ));
                txt_departamento.setText(rs.getString("Departamento"));
            } else {
                // Mensaje   "No se ha registrado" 
            }

        } catch (Exception e) {
            
        }
    }
    
}
