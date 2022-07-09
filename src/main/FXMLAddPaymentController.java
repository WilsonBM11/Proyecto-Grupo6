/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import domain.Appointment;
import domain.BTree;
import domain.BTreeNode;
import domain.CircularLinkedList;
import domain.DoublyLinkedList;
import domain.HeaderLinkedQueue;
import domain.ListException;
import domain.MedicalCare;
import domain.Patient;
import domain.Payment;
import domain.QueueException;
import domain.Sickness;
import domain.TreeException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javax.mail.MessagingException;
import util.Data;
import util.Mail;

/**
 * FXML Controller class
 *
 * @author Duran Family
 */
public class FXMLAddPaymentController implements Initializable {

    HeaderLinkedQueue paymentqueue;
    Alert alert;
    BTree medicalCareList;
    @FXML
    private Button registerButton;
    
    private ComboBox PatientComboBox;
    @FXML
    private Button GETDATABUTTON;
    @FXML
    private TextField TF_PATIENTID;
    @FXML
    private TextField TF_BILLINGDATE;
    @FXML
    private TextField TF_PAYMENTMODE;
    @FXML
    private TextField TF_SERVICECHARGE;
    @FXML
    private TextField TF_TOTALCHARGE;
    String[] metodosdePago = {"Tarjeta", "Efectivo"};
    @FXML
    private TextField TF_ReceivedService;
    @FXML
    private Button sendBillMail;
    private Payment currentPayment;
    private CircularLinkedList PatientList;
    private ObservableList<List<String>> MedicalCareComboBoxData = FXCollections.observableArrayList();
    @FXML
    private ComboBox MedicalCareComboBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            MedicalCareComboBoxData = FXCollections.observableArrayList();
            medicalCareList = new BTree();
            if (util.Data.fileExists("medicalcare")) {
                medicalCareList = (BTree) Data.getDataFile("medicalcare", medicalCareList);
            }
            
            paymentqueue = new HeaderLinkedQueue();
            if (util.Data.fileExists("payments")) {
                    paymentqueue = (HeaderLinkedQueue) util.Data.getDataFile("payments", paymentqueue);
            }
            
            PatientList = new CircularLinkedList();
            if (util.Data.fileExists("patients")) {
                PatientList = (CircularLinkedList) util.Data.getDataFile("patients", PatientList);
            }
//            
            this.MedicalCareComboBox.getItems().clear();
            if (medicalCareList != null && !medicalCareList.isEmpty()) {
                getComboBoxData(medicalCareList.getRoot());
                this.MedicalCareComboBox.setItems(MedicalCareComboBoxData);
            }
            
        } catch (QueueException | IOException   ex) {
            Logger.getLogger(FXMLAddPaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }     

    }

    @FXML
    private void registerOnAction(ActionEvent event) throws ParseException, IOException, ListException, QueueException {
        if (paymentqueue.isEmpty()) {
            if ("".equals(TF_BILLINGDATE.getText()) || "".equals(TF_PATIENTID.getText())
                    || "".equals(TF_PAYMENTMODE) || "".equals(TF_SERVICECHARGE.getText())
                    || "".equals(TF_ReceivedService.getText())) {

                alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Payment Add");
                alert.setHeaderText("ERROR");
                alert.setContentText("Fill all the spaces");
                alert.show();

            } else {

                try {
                    Date date = util.Utility.stringToDate2(TF_BILLINGDATE.getText());
                    
                    double totalCharge = Double.parseDouble(TF_SERVICECHARGE.getText()) * (1 + 0.3);
                    Payment payment = new Payment(Integer.parseInt(TF_PATIENTID.getText()), TF_PAYMENTMODE.getText(), Double.parseDouble(TF_SERVICECHARGE.getText()), date, totalCharge);
                    currentPayment = payment;
                    sendBillMail.setDisable(false);
                    try {
                        paymentqueue.enQueue(payment);
                    } catch (QueueException ex) {
                        Logger.getLogger(FXMLAddPaymentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    util.Data.setDataFile("payments", paymentqueue);

                    TF_BILLINGDATE.setText("");
                    TF_PATIENTID.setText("");
                    TF_PAYMENTMODE.setText("");
                    TF_SERVICECHARGE.setText("");
                    TF_TOTALCHARGE.setText("");
                    MedicalCareComboBox.setValue("Medical Care");
                    TF_ReceivedService.setText("");

                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setTitle("Payment added");
                    alert.show();

                } catch (NumberFormatException ex) {
                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Payment");
                    alert.setHeaderText("ERROR");
                    alert.setContentText("Wrong character, fill again the space");
                    alert.show();
                }
            }

        } else {
            if ("".equals(TF_BILLINGDATE.getText()) || "".equals(TF_PATIENTID.getText())
                    || "".equals(TF_PAYMENTMODE) || "".equals(TF_SERVICECHARGE.getText())
                    || "".equals(TF_ReceivedService.getText())) {

                alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Payment Add");
                alert.setHeaderText("ERROR");
                alert.setContentText("Fill all the spaces");
                alert.show();

            } else {

                try {
                    String sDate1 = TF_BILLINGDATE.getText();
                    Date date1 = new SimpleDateFormat("yyyy/MM/dd").parse(sDate1);
                    
                    double totalCharge = Double.parseDouble(TF_SERVICECHARGE.getText()) * (1 + 0.3);
                    Payment payment = new Payment(Integer.parseInt(TF_PATIENTID.getText()), TF_PAYMENTMODE.getText(), Double.parseDouble(TF_SERVICECHARGE.getText()), date1, totalCharge);
                    currentPayment = payment;
                    sendBillMail.setDisable(false);
                    try {
                        paymentqueue.enQueue(payment);
                    } catch (QueueException ex) {
                        Logger.getLogger(FXMLAddPaymentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    util.Data.setDataFile("payments", paymentqueue);

                    TF_BILLINGDATE.setText("");
                    TF_PATIENTID.setText("");
                    TF_PAYMENTMODE.setText("");
                    TF_SERVICECHARGE.setText("");
                    TF_TOTALCHARGE.setText("");
                    MedicalCareComboBox.setValue("Medical Care");

                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setTitle("Payment added");
                    alert.show();
                } catch (NumberFormatException ex) {
                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Payment");
                    alert.setHeaderText("ERROR");
                    alert.setContentText("Wrong character, fill again the space");
                    alert.show();
                }
            }

        }
    }

    @FXML
    private void GETDATAOnAction(ActionEvent event) {
        String choicePatient = String.valueOf(MedicalCareComboBox.getValue());
        String[] parts = choicePatient.split(",");
        String IdPatient = parts[0];
        TF_PATIENTID.setText(IdPatient.substring(1, IdPatient.length()));
        String choiceBilling = String.valueOf(MedicalCareComboBox.getValue());
        String[] parts1 = choiceBilling.split(",");
        String billing = parts1[2];
        String date = billing.substring(0, billing.length() - 14);
        String replace = date.replace('-', '/');
        TF_BILLINGDATE.setText(replace);
    }


    
     private void getComboBoxData(BTreeNode node){
         if(node != null){
             MedicalCare mc = (MedicalCare) node.data;
             List<String> arrayList = new ArrayList<>();
             arrayList.add(String.valueOf(mc.getPatientID()));
             arrayList.add(String.valueOf(mc.getDoctorID()));
             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
             arrayList.add(String.valueOf(mc.getDateTime()));
             MedicalCareComboBoxData.add(arrayList);
             getComboBoxData(node.left);
             getComboBoxData(node.right);
         }
     }
    
    @FXML
    private void TF_SERVICECHARGEOnAction(ActionEvent event) {
        if (!"".equals(TF_SERVICECHARGE.getText())) {
            double totalCharge = Double.parseDouble(TF_SERVICECHARGE.getText()) * (1 + 0.3);
            TF_TOTALCHARGE.setText(String.valueOf(totalCharge));
        }else{
            TF_TOTALCHARGE.setText("");
        }
    }

    @FXML
    private void sendBillMailOnAction(ActionEvent event) throws FileNotFoundException, ListException, MessagingException {
        try {
            Payment payment = currentPayment;
            Patient p = (Patient) PatientList.getNodeById(new Patient(payment.getPatientID(), "", "", "", "", "", new Date()));
            Mail m = new Mail();
            Document documento = new Document();
            Paragraph titulo = new Paragraph(m.getClinicName() + " - Factura No. " + payment.getId());
            String documentPath = m.getClinicName() + " - Factura No. " + payment.getId() + ".pdf";
            FileOutputStream archivo = new FileOutputStream(documentPath);
            
            PdfWriter.getInstance(documento, archivo);
            documento.open();
            
            titulo.setAlignment(1);
            Image image = Image.getInstance(m.getImage());
            image.scaleAbsolute(150, 100);
            image.setAbsolutePosition(415, 750);
            documento.add(image);
            documento.add(titulo);
            documento.add(new Paragraph("Cliente:"));
            documento.add(Chunk.NEWLINE);
            documento.add(new Paragraph("Nombre: " + p.getFirstname() + " " + p.getLastname() +"\n"
                                        +"Cedula: " + payment.getPatientID()));
            documento.add(Chunk.NEWLINE);
            PdfPTable tabla = new PdfPTable(5);
            tabla.setWidthPercentage(100);
            
            PdfPCell description = new PdfPCell(new Phrase("Servicio"));
            PdfPCell billingDate = new PdfPCell(new Phrase("Fecha de Facturacion"));
            PdfPCell paymentMode = new PdfPCell(new Phrase("Metodo de Pago"));
            PdfPCell serviceCharges = new PdfPCell(new Phrase("Cargos por Servicio"));
            PdfPCell totalCharges = new PdfPCell(new Phrase("Cargos Totales"));
            
            description.setBackgroundColor(BaseColor.ORANGE);
            billingDate.setBackgroundColor(BaseColor.ORANGE);
            paymentMode.setBackgroundColor(BaseColor.ORANGE);
            serviceCharges.setBackgroundColor(BaseColor.ORANGE);
            totalCharges.setBackgroundColor(BaseColor.ORANGE);
            
            tabla.addCell(description);
            tabla.addCell(billingDate);
            tabla.addCell(paymentMode);
            tabla.addCell(serviceCharges);
            tabla.addCell(totalCharges);
            
            //Se añade la informacion de la factura
            tabla.addCell(TF_ReceivedService.getText());
            tabla.addCell(util.Utility.dateFormat(payment.getBillingDate()));
            tabla.addCell(payment.getPaymentMode());
            tabla.addCell(String.valueOf(payment.getServiceCharge()));
            tabla.addCell(String.valueOf(payment.getTotalCharge()));
            
            documento.add(tabla);
            
            documento.add(new Paragraph("¡Gracias por poner su salud en nuestras manos!"));
            documento.add(Chunk.NEWLINE);
            
            documento.close();
            
            m.sendEmail(p.getEmail(), m.getClinicName() + " - Factura No. " + payment.getId(), 
                    "Se adjunta la factura correspondiente a la atención medica recibida<br><br>"
                  + "¡Gracias por poner su salud en nuestras manos!<br><br>",documentPath);
        } catch (DocumentException | IOException ex) {
            Logger.getLogger(FXMLAddPaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
