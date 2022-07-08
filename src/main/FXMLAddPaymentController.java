/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import domain.Appointment;
import domain.DoublyLinkedList;
import domain.HeaderLinkedQueue;
import domain.ListException;
import domain.Payment;
import domain.QueueException;
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

/**
 * FXML Controller class
 *
 * @author Duran Family
 */
public class FXMLAddPaymentController implements Initializable {

    HeaderLinkedQueue paymentqueue;
    Alert alert;
    DoublyLinkedList AppointmentList;
    @FXML
    private Button registerButton;
    @FXML
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

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        try {
            AppointmentList = new DoublyLinkedList();

            if (util.Data.fileExists("appointment")) {
                try {
                    AppointmentList = (DoublyLinkedList) util.Data.getDataFile("appointment", AppointmentList);
                } catch (QueueException | IOException ex) {
                    Logger.getLogger(FXMLMantenimientoDoctoresController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            paymentqueue = new HeaderLinkedQueue();

            if (util.Data.fileExists("payments")) {
                try {
                    paymentqueue = (HeaderLinkedQueue) util.Data.getDataFile("payments", paymentqueue);
                } catch (QueueException | IOException ex) {
                    Logger.getLogger(FXMLMantenimientoDoctoresController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            for (int i = 1; i <= AppointmentList.size(); i++) {
                this.PatientComboBox.setItems(getDataAppointment());
            }
        } catch (ListException ex) {
            Logger.getLogger(FXMLAddPaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void registerOnAction(ActionEvent event) throws ParseException, IOException, ListException, QueueException {
        if (paymentqueue.isEmpty()) {
            if ("".equals(TF_BILLINGDATE.getText()) || "".equals(TF_PATIENTID.getText())
                    || "".equals(TF_PAYMENTMODE) || "".equals(TF_SERVICECHARGE.getText())
                    || "".equals(TF_TOTALCHARGE.getText())) {

                alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Payment Add");
                alert.setHeaderText("ERROR");
                alert.setContentText("Fill all the spaces");
                alert.show();

            } else {

                try {
                    Date date = util.Utility.stringToDate2(TF_BILLINGDATE.getText());

                    Payment payment = new Payment(Integer.parseInt(TF_PATIENTID.getText()), TF_PAYMENTMODE.getText(), Double.parseDouble(TF_SERVICECHARGE.getText()), date, Double.parseDouble(TF_TOTALCHARGE.getText()));
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
                    PatientComboBox.setValue("Patient");

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
                    || "".equals(TF_TOTALCHARGE.getText())) {

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

                    Payment payment = new Payment(Integer.parseInt(TF_PATIENTID.getText()), TF_PAYMENTMODE.getText(), Double.parseDouble(TF_SERVICECHARGE.getText()), date1, Double.parseDouble(TF_TOTALCHARGE.getText()));
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
                    PatientComboBox.setValue("Patient");

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
        String choicePatient = String.valueOf(PatientComboBox.getValue());
        String[] parts = choicePatient.split(",");
        String IdPatient = parts[0];
        TF_PATIENTID.setText(IdPatient.substring(1, IdPatient.length()));
        String choiceBilling = String.valueOf(PatientComboBox.getValue());
        String[] parts1 = choiceBilling.split(",");
        String billing = parts1[2];
        String date = billing.substring(0, billing.length() - 6);
        String replace = date.replace('-', '/');
        TF_BILLINGDATE.setText(replace);
    }

    private ObservableList<List<String>> getDataAppointment() {
        ObservableList<List<String>> data = FXCollections.observableArrayList();
        if (this.AppointmentList != null && !this.AppointmentList.isEmpty()) {
            try {
                for (int i = 1; i <= this.AppointmentList.size(); i++) {
                    Appointment A = (Appointment) this.AppointmentList.getNode(i).data;
                    List<String> arrayList = new ArrayList<>();
                    arrayList.add(String.valueOf(A.getPatientID()));
                    arrayList.add(String.valueOf(A.getDoctorID()));
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    arrayList.add(String.valueOf(A.getDateTime()));
                    arrayList.add(A.getRemarks());
                    data.add(arrayList);
                }
            } catch (ListException ex) {

            }
        }
        return data;
    }

}
