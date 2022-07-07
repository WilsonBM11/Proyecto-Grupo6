/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import domain.BST;
import domain.CircularDoublyLinkedList;
import domain.CircularLinkedList;
import domain.Configurations;
import domain.Doctor;
import domain.ListException;
import domain.Patient;
import domain.QueueException;
import domain.Sickness;
import domain.SinglyLinkedList;
import domain.TreeException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Duran Family
 */
public class FXMLReportsController implements Initializable {

    BST tree;
    CircularLinkedList patientList;
    CircularDoublyLinkedList DoctorList;
    SinglyLinkedList sicknesslist;
    PdfPCell id = new PdfPCell(new Phrase("ID"));
    PdfPCell firstName = new PdfPCell(new Phrase("First Name"));
    PdfPCell lastName = new PdfPCell(new Phrase("Last Name"));
    PdfPCell PhoneNumber = new PdfPCell(new Phrase("Phone Number"));
    PdfPCell Email = new PdfPCell(new Phrase("Email"));
    PdfPCell Address = new PdfPCell(new Phrase("Address"));
    PdfPCell Birthday = new PdfPCell(new Phrase("Birthday"));

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
        patientList = new CircularLinkedList();
        if (util.Data.fileExists("patients")) {
            try {
                patientList = (CircularLinkedList) util.Data.getDataFile("patients", patientList);
            } catch (QueueException | IOException ex) {
                Logger.getLogger(FXMLPatientAddController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        DoctorList = new CircularDoublyLinkedList();
        if (util.Data.fileExists("doctors")) {
            try {
                DoctorList = (CircularDoublyLinkedList) util.Data.getDataFile("doctors", DoctorList);
            } catch (QueueException | IOException ex) {
                Logger.getLogger(FXMLMantenimientoDoctoresController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        sicknesslist = new SinglyLinkedList();
        if (util.Data.fileExists("sickness")) {
            try {
                sicknesslist = (SinglyLinkedList) util.Data.getDataFile("sickness", sicknesslist);
            } catch (QueueException | IOException ex) {
                Logger.getLogger(FXMLMantenimientoDoctoresController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void btnPatients(ActionEvent event) throws FileNotFoundException, ListException {
        Document documento = new Document();
        Paragraph titulo = new Paragraph(getClinicName());

        FileOutputStream archivo = new FileOutputStream("Patients.pdf");

        try {
            PdfWriter.getInstance(documento, archivo);
            documento.open();
            titulo.setAlignment(1);
            Image image = Image.getInstance(getClinicImage());
            image.scaleAbsolute(150, 100);
            image.setAbsolutePosition(415, 750);
            documento.add(image);
            documento.add(titulo);
            documento.add(new Paragraph("Patients"));
            documento.add(Chunk.NEWLINE);
            PdfPTable tabla = new PdfPTable(7);
            tabla.setWidthPercentage(100);
            id.setBackgroundColor(BaseColor.CYAN);
            firstName.setBackgroundColor(BaseColor.CYAN);
            lastName.setBackgroundColor(BaseColor.CYAN);
            PhoneNumber.setBackgroundColor(BaseColor.CYAN);
            firstName.setBackgroundColor(BaseColor.CYAN);
            lastName.setBackgroundColor(BaseColor.CYAN);
            PhoneNumber.setBackgroundColor(BaseColor.CYAN);
            Email.setBackgroundColor(BaseColor.CYAN);
            Address.setBackgroundColor(BaseColor.CYAN);
            Birthday.setBackgroundColor(BaseColor.CYAN);
            
            tabla.addCell(id);
            tabla.addCell(firstName);
            tabla.addCell(lastName);
            tabla.addCell(PhoneNumber);
            tabla.addCell(Email);
            tabla.addCell(Address);
            tabla.addCell(Birthday);
            for (int i = 1; i <= this.patientList.size(); i++) {
                Patient D = (Patient) this.patientList.getNode(i).data;
                List<String> arrayList = new ArrayList<>();
                tabla.addCell(String.valueOf(D.getId()));
                tabla.addCell(D.getFirstname());
                tabla.addCell(D.getLastname());
                tabla.addCell(D.getPhoneNumber());
                tabla.addCell(D.getEmail());
                tabla.addCell(D.getAddress());
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                tabla.addCell(String.valueOf(format.format(D.getBirthday())));
            }
            documento.add(tabla);

            documento.close();

        } catch (DocumentException | IOException ex) {
            Logger.getLogger(FXMLReportsController.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    @FXML
    private void btnDoctors(ActionEvent event) throws DocumentException, BadElementException {
        FileOutputStream archivo = null;
        try {
            Document documento = new Document();
            Paragraph titulo = new Paragraph(getClinicName());
            archivo = new FileOutputStream("Doctors.pdf");
            PdfWriter.getInstance(documento, archivo);
            documento.open();
            titulo.setAlignment(1);
            Image image = Image.getInstance(getClinicImage());
            image.scaleAbsolute(150, 100);
            image.setAbsolutePosition(415, 750);
            documento.add(image);
            documento.add(titulo);
            documento.add(new Paragraph("Doctors"));
            documento.add(Chunk.NEWLINE);
            PdfPTable tabla = new PdfPTable(7);
            tabla.setWidthPercentage(100);
            id.setBackgroundColor(BaseColor.CYAN);
            firstName.setBackgroundColor(BaseColor.CYAN);
            lastName.setBackgroundColor(BaseColor.CYAN);
            PhoneNumber.setBackgroundColor(BaseColor.CYAN);
            Email.setBackgroundColor(BaseColor.CYAN);
            Address.setBackgroundColor(BaseColor.CYAN);
            Birthday.setBackgroundColor(BaseColor.CYAN);
            tabla.addCell(id);
            tabla.addCell(firstName);
            tabla.addCell(lastName);
            tabla.addCell(PhoneNumber);
            tabla.addCell(Email);
            tabla.addCell(Address);
            tabla.addCell(Birthday);

            for (int i = 1; i <= this.DoctorList.size(); i++) {
                Doctor D = (Doctor) this.DoctorList.getNode(i).data;
                List<String> arrayList = new ArrayList<>();
                tabla.addCell(String.valueOf(D.getId()));
                tabla.addCell(D.getFirstName());
                tabla.addCell(D.getLastName());
                tabla.addCell(D.getPhoneNumber());
                tabla.addCell(D.getEmail());
                tabla.addCell(D.getAddress());
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                tabla.addCell(String.valueOf(format.format(D.getBirthday())));
            }
            documento.add(tabla);

            documento.close();
        } catch (ListException | DocumentException | IOException ex) {
            Logger.getLogger(FXMLReportsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnSickness(ActionEvent event) {
        FileOutputStream archivo = null;
        try {
            Document documento = new Document();
            Paragraph titulo = new Paragraph(getClinicName());
            archivo = new FileOutputStream("sickness.pdf");
            PdfWriter.getInstance(documento, archivo);
            documento.open();
            titulo.setAlignment(1);
            Image image = Image.getInstance(getClinicImage());
            image.scaleAbsolute(150, 100);
            image.setAbsolutePosition(415, 750);
            documento.add(image);
            documento.add(titulo);
            documento.add(new Paragraph("Sickness"));
            documento.add(Chunk.NEWLINE);
            PdfPTable tabla = new PdfPTable(2);
            tabla.setWidthPercentage(100);
            id.setBackgroundColor(BaseColor.CYAN);
            PdfPCell description = new PdfPCell(new Phrase("Description"));
            description.setBackgroundColor(BaseColor.CYAN);
            tabla.addCell(id);
            tabla.addCell(description);

            for (int i = 1; i <= this.sicknesslist.size(); i++) {
                Sickness D = (Sickness) this.sicknesslist.getNode(i).data;
                List<String> arrayList = new ArrayList<>();
                tabla.addCell(String.valueOf(D.getId()));
                tabla.addCell(D.getDescription());
            }
            documento.add(tabla);

            documento.close();
        } catch (ListException | DocumentException | IOException ex) {
            Logger.getLogger(FXMLReportsController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private String getClinicName() {
        String name1 = "";
        try {
            String ClinicImage = String.valueOf(getData());
            String[] parts = ClinicImage.split(",");
            String name = parts[0];
            name1 = name.substring(2, name.length());
        } catch (TreeException ex) {
            Logger.getLogger(FXMLMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name1;
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
}
