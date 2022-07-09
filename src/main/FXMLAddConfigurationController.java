/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main;

import domain.Appointment;
import domain.BST;
import domain.BTree;
import domain.CircularDoublyLinkedList;
import domain.Configurations;
import domain.ListException;
import domain.QueueException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import util.Data;

/**
 * FXML Controller class
 *
 * @author Duran Family
 */
public class FXMLAddConfigurationController implements Initializable {

    BST tree;
    Alert alert;
    BST treeHours;

    @FXML
    private TextField TF_Email;
    @FXML
    private TextField idClinicName;
    @FXML
    private TextField TF_PhoneNumber;
    @FXML
    private Button registerButton;

    @FXML
    private AnchorPane pane;
    @FXML
    private TextField TF_image;
    @FXML
    private TextField TF_image1;
    @FXML
    private TextField TF_Star;
    @FXML
    private TextField TF_Departure;
    @FXML
    private ComboBox DaysCombobox;
    String[] days = {"Monday to Friday", "Saturday"};

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//Inicializa los arboles y listas y se le asigna al archivo txt correspondiente
        tree = new BST();
        if (util.Data.fileExists("configuration")) {
            try {
                tree = (BST) util.Data.getDataFile("configuration", tree);
            } catch (QueueException | IOException ex) {
                Logger.getLogger(FXMLAddDoctorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        treeHours = new BST();
        if (util.Data.fileExists("Week")) {
            try {
                treeHours = (BST) Data.getDataFile("Week", treeHours);
            } catch (QueueException | IOException ex) {
                Logger.getLogger(FXMLAddConfigurationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        treeHours = new BST();
        if (util.Data.fileExists("Saturday")) {
            try {
                treeHours = (BST) Data.getDataFile("Saturday", treeHours);
            } catch (QueueException | IOException ex) {
                Logger.getLogger(FXMLAddConfigurationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //Se le agrega al combobox las opciones de dias que se trabajan
        DaysCombobox.getItems().addAll(days);
    }

    @FXML
    private void registerOnAction(ActionEvent event) {
        if (tree != null) {
            //Si hay un elemento diferente de nulo entonces revisa si los campos esten llenos
            if ("".equals(TF_Email.getText())
                    || "".equals(TF_PhoneNumber) || "".equals(TF_image.getText())
                    || "".equals(idClinicName.getText())) {
                alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Configuration");
                alert.setHeaderText("ERROR");
                alert.setContentText("Fill all the spaces");
                alert.show();

            } else {
                try {
                    //Verifica si el email que se escribe es correcto dentro de los parametros 
                    Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
                    Matcher mather = pattern.matcher(TF_Email.getText());
                    if (mather.find() == true) {
                        //Si es asi guarda los datos dentro de un objeto de configuracion y este se agrega al arbol
                        Configurations configurations = new Configurations(idClinicName.getText(), TF_PhoneNumber.getText(), TF_Email.getText(), TF_image.getText(), TF_image1.getText());
                        //Como el arbol es de un solo nodo entonces cada ves que mete un nuevo elemento elimina el anterior
                        tree.clear();
                        tree.add(configurations);
                        try {
                            //Se agrega a los archivos 
                            util.Data.setDataFile("configuration", tree);
                        } catch (QueueException | ListException | IOException ex) {
                            Logger.getLogger(FXMLAddConfigurationController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            util.Data.setDataFile("configuration", tree);
                        } catch (QueueException | ListException | IOException ex) {
                            Logger.getLogger(FXMLAddCITASController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        //Se limpian los campos de textos

                        idClinicName.setText("");
                        TF_image.setText("");
                        TF_PhoneNumber.setText("");
                        TF_Email.setText("");
                        TF_image1.setText("");
                        //Se envia una alerta exitosa

                        alert = new Alert(Alert.AlertType.NONE);
                        alert.setAlertType(Alert.AlertType.INFORMATION);
                        alert.setTitle("Configurations");
                        alert.setContentText("The changes were made successfully");
                        alert.show();
                    } else {
                        alert = new Alert(Alert.AlertType.NONE);
                        alert.setAlertType(Alert.AlertType.ERROR);
                        alert.setTitle("Doctor");
                        alert.setHeaderText("ERROR");
                        alert.setContentText("Invalid Email");
                        alert.show();
                    }
                    //Number format exception en caso de que exista un fallo 
                } catch (NumberFormatException ex) {
                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Configurations");
                    alert.setHeaderText("ERROR");
                    alert.setContentText("Wrong character, fill again the space");
                    alert.show();
                }
            }
        }
    }
    //De aqui se obtiene la ruta para cambiar el logo de la imagen

    @FXML
    private void ChangeLogoCode(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            TF_image.setText(selectedFile.toURI().toString());
            TF_image1.setText(selectedFile.getAbsolutePath());
        } else {
            TF_image.setText("Is not valid");
        }
    }
//Se cambian las horas
    @FXML
    private void RegisterTimeCode(ActionEvent event) {
        try {
            if (treeHours != null) {
                if ("".equals(TF_Departure.getText())
                        || "".equals(TF_Star.getText())) {
                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Configuration");
                    alert.setHeaderText("ERROR");
                    alert.setContentText("Fill all the spaces");
                    alert.show();
                } else {
//Se deben de seguir los siguientes parametros para poder cambiar las horas
                    treeHours = new BST();
                    int inicio = Integer.parseInt(TF_Star.getText());
                    int finalH = Integer.parseInt(TF_Departure.getText());
                    if (inicio > 24 || inicio < 0 || finalH > 24 || finalH < 0) {
                        alert = new Alert(Alert.AlertType.NONE);
                        alert.setAlertType(Alert.AlertType.ERROR);
                        alert.setTitle("Configurations");
                        alert.setHeaderText("ERROR");
                        alert.setContentText("The hours entered have to be between 0 and 24 and the star time has to be less");
                        alert.show();

                    } else {
                        //Se agrega al archivo de lunes a viernes un for que recorre las horas desde el inicio hasta el final
                        if (DaysCombobox.getValue().equals("Monday to Friday")) {
                            for (int i = inicio; i <= finalH; i++) {

                                treeHours.add(i);
                                try {
                                    util.Data.setDataFile("Week", treeHours);

                                } catch (QueueException | ListException | IOException ex) {
                                    Logger.getLogger(FXMLAddConfigurationController.class.getName()).log(Level.SEVERE, null, ex);

                                }

                            }
                            alert = new Alert(Alert.AlertType.NONE);
                            alert.setAlertType(Alert.AlertType.INFORMATION);
                            alert.setTitle("Configurations");
                            alert.setContentText("The changes were made successfully");
                            alert.show();
                            TF_Star.setText("");
                            TF_Departure.setText("");
                            File file = new File("Week");
                            
//Se realiza lo mismo pero para el dia sabado 
                        } else if (DaysCombobox.getValue().equals("Saturday")) {
                            for (int i = inicio; i <= finalH; i++) {

                                treeHours.add(i);
                                try {
                                    util.Data.setDataFile("Saturday", treeHours);
                                } catch (QueueException | ListException | IOException ex) {
                                    Logger.getLogger(FXMLAddConfigurationController.class.getName()).log(Level.SEVERE, null, ex);

                                }
                            }
                        }
                        alert = new Alert(Alert.AlertType.NONE);
                        alert.setAlertType(Alert.AlertType.INFORMATION);
                        alert.setTitle("Configurations");
                        alert.setContentText("The changes were made successfully");
                        alert.show();
                        TF_Star.setText("");
                        TF_Departure.setText("");

                    }

                }
            }

        } catch (NumberFormatException ex) {
            alert = new Alert(Alert.AlertType.NONE);
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Configurations");
            alert.setHeaderText("ERROR");
            alert.setContentText("Wrong character, fill again the space");
            alert.show();

        }
    }
}
