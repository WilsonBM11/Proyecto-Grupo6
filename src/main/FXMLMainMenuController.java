/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package main;

import domain.BST;
import domain.Configurations;
import domain.ListException;
import domain.QueueException;
import domain.SinglyLinkedList;
import domain.TreeException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

/**
 *
 * @author Profesor Gilberth Chaves A <gchavesav@ucr.ac.cr>
 */
public class FXMLMainMenuController implements Initializable {
    BST tree;
    Image image;
    SinglyLinkedList type;
    
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
    private Button btnDoctor;
    @FXML
    private Text txtMessage;
    @FXML
    private ImageView LogoImage;
    @FXML
    private Label TXTCorreo;
    @FXML
    private Label TXTTelefono;
    @FXML
    private Button btnPatient;
    @FXML
    private Button btnSickness;
    @FXML
    private Button btnAppointment;
    @FXML
    private Button btnMedicalCare;
    @FXML
    private Button btnPayment;
    @FXML
    private Button btnReports;
    @FXML
    private Button btnConfiguration;
    
    @Override
    //Se inicializa un arbol que trae informacion del archivo de configuration
    public void initialize(URL url, ResourceBundle rb) {
        tree = new BST();
        if (util.Data.fileExists("configuration")) {
            try {
                tree = (BST) util.Data.getDataFile("configuration", tree);
            } catch (QueueException | IOException ex) {
                Logger.getLogger(FXMLAddDoctorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
          type = util.Utility.getAdmin();
        
                
        try {
            if(getType1().equals("patient")){//Se desabilitan los botones a los que el paciente no puede ingresar
                btnDoctor.setVisible(false);
                btnConfiguration.setVisible(false);
                btnPayment.setVisible(false);
                btnReports.setVisible(false);
                btnSickness.setVisible(false);
            }
        } catch (ListException ex) {
            Logger.getLogger(FXMLMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
             
        
     
        
        //Se modifica el menu como el nombre de la cilinica, image, email o numero telefonico con los valores
        //Que se asignaron en configuration
        
        this.txtMessage.setText(getClinicName());
        this.image = new Image(getClinicImage());
        this.TXTTelefono.setText("Phone Number: " + getPhone());
        this.TXTCorreo.setText("Email: " + getMail());
        LogoImage.setImage(image);
    }   
    
    //Metodo para cargar fxml dentro del border pane del menu 
    
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
    //Boton home 
    private void Home(MouseEvent event) {
          tree = new BST();
        if (util.Data.fileExists("configuration")) {
            try {
                tree = (BST) util.Data.getDataFile("configuration", tree);
            } catch (QueueException | IOException ex) {
                Logger.getLogger(FXMLMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //Al igual que cuando se inicializa la interfaz 
        this.bp.setCenter(ap);
        this.txtMessage.setText(getClinicName());
        this.image = new Image(getClinicImage());
        LogoImage.setImage(image);
         this.TXTTelefono.setText("Phone Number: " + getPhone());
        this.TXTCorreo.setText("Email:" + getMail());
    }

    @FXML
    private void Exit(MouseEvent event) {
        System.exit(0); //FORMA VALIDA
   
    }
    //Aqui se carga la interfaz las interfaces de acuerdo a los botones en el menu 

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

    @FXML
    private void MedicalCareCode(ActionEvent event) {
        loadPage(getClass().getResource("FXMLMedicalCare.fxml"), bp);
    }

    @FXML
    private void ReportsCode(ActionEvent event) {
        loadPage(getClass().getResource("FXMLReports.fxml"), bp);
    }
      @FXML
    private void ConfigurationCode(ActionEvent event) {
        loadPage(getClass().getResource("FXMLAddConfiguration.fxml"), bp);
    }

    @FXML
    private void PaymentCode(ActionEvent event) {
             loadPage(getClass().getResource("FXMLPagoConsulta.fxml"), bp);
    }
    
    //Se obtiene el valor la informacion de las configuraciones y esto se mete dentro del arbol 
    
     private ObservableList<List<String>> getData() throws TreeException {
        ObservableList<List<String>> data = FXCollections.observableArrayList();
        if (this.tree != null && !this.tree.isEmpty()) {
            Configurations C = (Configurations) this.tree.getRoot().data;
            List<String> arrayList = new ArrayList<>();
            arrayList.add(String.valueOf(C.getClinicName()));
            arrayList.add(C.getCorreoElectronico());
            arrayList.add(C.getImagen());
            arrayList.add(C.getTelefono());
            data.add(arrayList);
        }
        return data;
    }
     //Metodo para obtener el valor del nombre de la clinica utiliza un split para separar los valores del arraylist

    public String getClinicName() {
        String name1 = "";
        try {
            String ClinicName = String.valueOf(getData());
            String[] parts = ClinicName.split(",");
            String name = parts[0];
            name1 = name.substring(2, name.length());
        } catch (TreeException ex) {
            Logger.getLogger(FXMLMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name1;
    }
    //Metodo para obtener el valor de la imagen de la clinica utiliza un split para separar los valores del arraylist

    private String getClinicImage() {
        String name1 = "";
        try {
            String ClinicImage = String.valueOf(getData());
            String[] parts = ClinicImage.split(",");
            String name = parts[2];
            name1 = name.substring(1, name.length());
        } catch (TreeException ex) {
            Logger.getLogger(FXMLMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name1;
    }
     //Metodo para obtener el valor del correo de la clinica utiliza un split para separar los valores del arraylist
    private String getMail() {
        String name1 = "";
        try {
            String ClinicName = String.valueOf(getData());
            String[] parts = ClinicName.split(",");
            String name = parts[1];
            name1 = name.substring(0, name.length());
        } catch (TreeException ex) {
            Logger.getLogger(FXMLMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name1;
    }
     //Metodo para obtener el valor del telefono de la clinica utiliza un split para separar los valores del arraylist
      private String getPhone() {
        String name1 = "";
        try {
            String ClinicName = String.valueOf(getData());
            String[] parts = ClinicName.split(",");
            String name = parts[3];
            name1 = name.substring(0, name.length()-2);
        } catch (TreeException ex) {
            Logger.getLogger(FXMLMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name1;
    }
       private ObservableList<String> getType() throws ListException {
        ObservableList<String> data = FXCollections.observableArrayList();

      
                for (int i = 1; i <= type.size(); i++) {
                    String Administrador = (String) type.getNode(i).data;
                    data.add(Administrador);
                }
                return data;
           }
        private String getType1() throws ListException {
        String name1 = "";
        String ClinicName = String.valueOf(getType());
        String[] parts = ClinicName.split(",");
        String name = parts[0];
        name1 = name.substring(1, name.length()-1);
        return name1;
    }
           
}
