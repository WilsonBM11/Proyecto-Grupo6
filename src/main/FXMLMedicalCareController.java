/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domain.Appointment;
import domain.BTree;
import domain.BTreeNode;
import domain.CircularDoublyLinkedList;
import domain.CircularLinkedList;
import domain.Doctor;
import domain.DoublyLinkedList;
import domain.ListException;
import domain.MedicalCare;
import domain.Patient;
import domain.QueueException;
import domain.Sickness;
import domain.SinglyLinkedList;
import domain.TreeException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import util.Data;


/**
 * FXML Controller class
 *
 * @author Wilson Bonilla Mata
 */
public class FXMLMedicalCareController implements Initializable {
    
    private BTree medicalCareList;
    private CircularDoublyLinkedList doctorsList;
    private DoublyLinkedList appoimentsList;
    private CircularLinkedList patientsList;
    private SinglyLinkedList sicknessList;
    private ObservableList<List<String>> tableViewData;
    @FXML
    private ComboBox<String> doctorsComboBox;
    @FXML
    private ComboBox<String> patientsComboBox;
    @FXML
    private ComboBox<String> sicknessComboBox;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox<String> timeComboBox;
    @FXML
    private TableView<List<String>> tableViewMedicalCare;
    @FXML
    private TableColumn<List<String>, String> doctorColumn;
    @FXML
    private TableColumn<List<String>, String> timeColumn;
    @FXML
    private TableColumn<List<String>, String> sicknessColumn;
    @FXML
    private TableColumn<List<String>, String> annotationsColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
//        LocalTime time = LocalTime.of(10, 50);
        medicalCareList = new BTree();
        doctorsList = new CircularDoublyLinkedList();
        appoimentsList = new DoublyLinkedList();
        patientsList = new CircularLinkedList();
        sicknessList = new SinglyLinkedList();
        try {
            if (util.Data.fileExists("medicalcare")) 
                medicalCareList = (BTree) Data.getDataFile("medicalcare", medicalCareList);
            if (util.Data.fileExists("doctors")) 
                doctorsList = (CircularDoublyLinkedList) Data.getDataFile("doctors", doctorsList);
            if(util.Data.fileExists("appointment"))
                appoimentsList = (DoublyLinkedList) Data.getDataFile("appointment", appoimentsList);
            if(util.Data.fileExists("patients"))
                patientsList = (CircularLinkedList) Data.getDataFile("patients", patientsList);
            if(util.Data.fileExists("sickness"))
                sicknessList = (SinglyLinkedList) Data.getDataFile("sickness", sicknessList);
            
        } catch (QueueException | IOException ex) {
            Logger.getLogger(FXMLMedicalCareController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        this.doctorColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyObjectWrapper<>(data.getValue().get(0));
            }
        });
        this.timeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyObjectWrapper<>(data.getValue().get(1));
            }
        });
        this.sicknessColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyObjectWrapper<>(data.getValue().get(2));
            }
        });
        this.annotationsColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyObjectWrapper<>(data.getValue().get(3));
            }
        });
        
        if (this.doctorsList != null && !this.doctorsList.isEmpty()){
            try {
                this.doctorsComboBox.setItems(getComboBoxData("doctors",0));
            } catch (ListException ex) {
                Logger.getLogger(FXMLMedicalCareController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (medicalCareList != null && !medicalCareList.isEmpty()) {
            try {
                getTreeData(medicalCareList.getRoot(),305380944);
                this.tableViewMedicalCare.setItems(tableViewData);
            } catch (ListException ex) {
                Logger.getLogger(FXMLMedicalCareController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        

    }    

    private ObservableList<String> getComboBoxData(String identifier, int id) throws ListException {
        ObservableList<String> data = FXCollections.observableArrayList();
        
        switch (identifier) {
            case "doctors":
                for (int i = 1; i <= doctorsList.size(); i++) {
                    Doctor D = (Doctor) doctorsList.getNode(i).data;
                    data.add(D.getFirstName() + " " + D.getLastName()+"-"+D.getId());
                }
            break;
            case "patients":
                for (int i = 1; i <= appoimentsList.size(); i++) {
                    Appointment A = (Appointment) appoimentsList.getNode(i).data;
                    if(A.compareDoctorId(id)){
                        Patient aux = (Patient) patientsList.getNodeById(new Patient(A.getPatientID(), "", "", "", "", "", new Date()));
                        data.add(aux.getFirstname()+" "+aux.getLastname()+"-"+aux.getId());
                    }
                }
            break;
            case "sickness":
                for (int i = 1; i <= sicknessList.size(); i++) {
                    Sickness S = (Sickness) sicknessList.getNode(i).data;
                    data.add(S.getDescription()+"-"+S.getId());
                }
        }
        return data;
    }

    @FXML
    private void selectDoctorOnAction(ActionEvent event) throws ListException {
        int doctorID = getId(String.valueOf(doctorsComboBox.getValue()));
        patientsComboBox.setDisable(false);
        sicknessComboBox.setDisable(false);
        patientsComboBox.setItems(getComboBoxData("patients", doctorID));
        sicknessComboBox.setItems(getComboBoxData("sickness", 0));
        
    }
    
    public int getId(String selectedItem){
        ArrayList<String> aux = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(selectedItem, "-");
        while (st.hasMoreTokens()) {
            aux.add(st.nextToken());
        }
        return Integer.parseInt(aux.get(1));
    }
    
//    private ObservableList<List<String>> getData(int medicalCareId) {
//        ObservableList<List<String>> data = FXCollections.observableArrayList();
//        if (medicalCareList != null && !medicalCareList.isEmpty()) {
//            try {
//                getTreeData(medicalCareList.getRoot(),medicalCareId);
//                data = tableViewData;
//            } catch (ListException ex) {
//                Logger.getLogger(FXMLMedicalCareController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return data;
//    }
    
    private void getTreeData(BTreeNode node, int id) throws ListException {
        if(node != null){
            MedicalCare A = (MedicalCare) node.data;
            if (util.Utility.equals(A.getPatientID(), id)) {
                List<String> arrayList = new ArrayList<>();
                Doctor d = (Doctor) doctorsList.getNodeById(A.getDoctorID());
                Sickness s = (Sickness) sicknessList.getNodeById(A.getSicknessID());
                arrayList.add(d.getFirstName() + " " + d.getLastName());
                arrayList.add(A.getDateTime().toString());
                arrayList.add(s.getDescription());
                arrayList.add(A.getAnnotations());
                tableViewData.add(arrayList);
            }
            getTreeData(node.left, id);
            getTreeData(node.right, id);
        }
    }
    
}
