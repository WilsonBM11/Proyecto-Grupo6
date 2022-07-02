/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package main;

import domain.BST;
import domain.Configurations;
import domain.QueueException;
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
    @FXML
    private ImageView LogoImage;
    @FXML
    private Label TXTCorreo;
    @FXML
    private Label TXTTelefono;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tree = new BST();
        if (util.Data.fileExists("configuration")) {
            try {
                tree = (BST) util.Data.getDataFile("configuration", tree);
            } catch (QueueException | IOException ex) {
                Logger.getLogger(FXMLAddDoctorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.txtMessage.setText(getClinicName());
        this.image = new Image(getClinicImage());
        this.TXTTelefono.setText(getPhone());
        this.TXTCorreo.setText(getMail());
        LogoImage.setImage(image);
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
          tree = new BST();
        if (util.Data.fileExists("configuration")) {
            try {
                tree = (BST) util.Data.getDataFile("configuration", tree);
            } catch (QueueException ex) {
                Logger.getLogger(FXMLMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FXMLMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.bp.setCenter(ap);
        this.txtMessage.setText(getClinicName());
        this.image = new Image(getClinicImage());
        LogoImage.setImage(image);
        this.TXTTelefono.setText(getPhone());
        this.TXTCorreo.setText(getMail());
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

    @FXML
    private void MedicalCareCode(ActionEvent event) {
        loadPage(getClass().getResource("FXMLMedicalCare.fxml"), bp);
    }

    @FXML
    private void ReportsCode(ActionEvent event) {
        loadPage(getClass().getResource("FXMLReportes.fxml"), bp);
    }
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

    @FXML
    private void ConfigurationCode(ActionEvent event) {
        loadPage(getClass().getResource("FXMLAddConfiguration.fxml"), bp);
    }

   
    
}
