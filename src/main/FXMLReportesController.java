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
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;




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
              
              
        }catch(SQLException e){
            
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
        } catch (SQLException e) {
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
             
             labelStatus.setText("Registro eliminado");
        } catch (SQLException e) {
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

        } catch (SQLException e) {
            
        }
    }
    
    private void btnGenerar (ActionEvent event){
        
        Document document = new Document ();
        
        try {
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(document, new FileOutputStream(ruta+ "/Desktop/ Reporte.pdf"));// Aqui se ve donde guarda el documento y el nombre que tendra 
            document.open();
            
            PdfPTable tabla =  new PdfPTable(3);
            tabla.addCell("Codigo");
            tabla.addCell("Nombre");
            tabla.addCell("Departamento");
            
            try {
                 Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/reportes", "root", "");
                 PreparedStatement pst = cn.prepareStatement ("select * from doctores/especialistas");
                 
                 
                 ResultSet rs = pst.executeQuery();
                 
                 if (rs.next()) {
                     do {                         
                         tabla.addCell(rs.getString(1));//Coloca datos en la columna de id
                         tabla.addCell(rs.getString(2));//Coloca datos en la columna de nombre
                         tabla.addCell(rs.getString(3));//Coloca datos en la columna de departamento
                     } while (rs.next());
                     document.add(tabla);
                } 
            } catch (DocumentException | SQLException e) {
            }
            document.close();
            
            //Agregar mensaje que se creo el reporte
            
        } catch (DocumentException | FileNotFoundException e) {
        }
        
        
    }
    
    
}
