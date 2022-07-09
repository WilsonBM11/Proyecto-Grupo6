/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import static com.itextpdf.text.pdf.PdfName.D;
import domain.Appointment;
import domain.Doctor;
import domain.HeaderLinkedQueue;
import domain.ListException;
import domain.Payment;
import domain.PriorityLinkedQueue;
import domain.QueueException;
import domain.Security;
import domain.Sickness;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Wilson Bonilla Mata
 */
public class FXMLPagoConsultaController implements Initializable {

    HeaderLinkedQueue paymentqueue;
    Alert alert;

    @FXML
    private BorderPane bp;
    @FXML
    private TableView<List<String>> TableView;
    @FXML
    private TableColumn<List<String>, String> patientColumn;
    @FXML
    private TableColumn<List<String>, String> paymentModeColumn;
    @FXML
    private TableColumn<List<String>, String> serviceChargeColumn;
    @FXML
    private TableColumn<List<String>, String> billingDateColumn;
    @FXML
    private TableColumn<List<String>, String> totalChargeColumn;
    private Security currentUser;
    @FXML
    private Button addBTN;
    @FXML
    private Button deleteBTN;
    @FXML
    private Button containsBTN;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("");
        currentUser = util.Utility.getCurrentUser();
        if(currentUser.getType().equalsIgnoreCase("Patient")){
            addBTN.setVisible(false);
            deleteBTN.setVisible(false);
            containsBTN.setVisible(false);
        }
        paymentqueue = new HeaderLinkedQueue();
        if (util.Data.fileExists("payments")) {
            try {
                paymentqueue = (HeaderLinkedQueue) util.Data.getDataFile("payments", paymentqueue);
            } catch (QueueException | IOException ex) {
                Logger.getLogger(FXMLMantenimientoDoctoresController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        this.patientColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyObjectWrapper<>(data.getValue().get(0));
            }
        });

        this.paymentModeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyObjectWrapper<>(data.getValue().get(1));
            }
        });

        this.serviceChargeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyObjectWrapper<>(data.getValue().get(2));
            }
        });

        this.billingDateColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyObjectWrapper<>(data.getValue().get(3));
            }
        });

        this.totalChargeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyObjectWrapper<>(data.getValue().get(4));
            }
        });
        this.TableView.setEditable(true);
        serviceChargeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        paymentModeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        totalChargeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        if (this.paymentqueue != null && !this.paymentqueue.isEmpty()) {
            for (int i = 1; i <= paymentqueue.size(); i++) {
                this.TableView.setItems(getData());
            }
        }
    }

    @FXML
    private void AddCode(ActionEvent event) {
        FXMLMainMenuController.loadPage(getClass().getResource("FXMLAddPayment.fxml"), bp);

    }

    @FXML
    private void DeleteCode(ActionEvent event) throws ParseException {
        try {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Appointment Remove");
            dialog.setHeaderText("Enter the ID Patient:");
            Optional<String> id = dialog.showAndWait();
            TextInputDialog dialog2 = new TextInputDialog();
            dialog2.setTitle("Appointment Remove");
            dialog2.setHeaderText("Enter the Date of the appointment:");
            Optional<String> id2 = dialog2.showAndWait();

            if ((!id.isPresent() || id.get().compareTo("") == 0 && !id2.isPresent() || id2.get().compareTo("") == 0)) {
                alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Payment Remove");
                alert.setHeaderText("The list dont have the element");
                alert.show();

            }
            if (paymentqueue.size() == 1) {
                String strDatewithTime = id2.get();
                Date date1 = new SimpleDateFormat("yyyy/MM/dd").parse(strDatewithTime);

                if (paymentqueue.contains(new Payment(Integer.parseInt(id.get()), "", 0.0, date1, 0.0))) {
                    paymentqueue.clear();
                    TableView.getItems().clear();
                    util.Data.setDataFile("payments", paymentqueue);
                } else {
                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Payment Remove");
                    alert.setHeaderText("The list dont have the element");
                    alert.show();
                }
            } else {
                String strDatewithTime = id2.get();
                Date date1 = new SimpleDateFormat("yyyy/MM/dd").parse(strDatewithTime);
                if (paymentqueue.contains(new Payment(Integer.parseInt(id.get()), "", 0.0, date1, 0.0))) {
                    paymentqueue.remove(new Payment(Integer.parseInt(id.get()), "", 0.0, date1, 0.0));
                    util.Data.setDataFile("payments", paymentqueue);
                    TableView.getItems().clear();
                    TableView.getItems().addAll(getData());
                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setTitle("Payment - Remove");
                    alert.setHeaderText("The element was removed");
                    alert.show();
                } else {
                    alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Payment Remove");
                    alert.setHeaderText("The list dont have the element");
                    alert.show();

                }
            }
        } catch (ListException ex) {
            alert = new Alert(Alert.AlertType.NONE);
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Appointment - Remove");
            alert.setHeaderText(ex.getMessage());
            alert.show();
        } catch (QueueException | IOException ex) {
            Logger.getLogger(FXMLMantenimientoDoctoresController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchElementException ex) {
            System.err.println("ERROR " + ex);
        }

    }

    @FXML
    private void ContainsCode(ActionEvent event) {
    }
    
    @FXML
    private void paymentModeColumnOnEditCommit(TableColumn.CellEditEvent<List<String>, String> event) throws ParseException {
        try {
            String paymentColumn = event.getRowValue().get(1);
            String sDate1 = event.getRowValue().get(3);
            Date date1 = new SimpleDateFormat("yyyy/MM/dd").parse(sDate1);
            Payment oldPayment = new Payment(Integer.parseInt(event.getRowValue().get(0)), paymentColumn, Double.parseDouble(event.getRowValue().get(2)), date1, Double.parseDouble(event.getRowValue().get(4)));
            Payment newPayment = new Payment(Integer.parseInt(event.getRowValue().get(0)), event.getNewValue(), Double.parseDouble(event.getRowValue().get(2)), date1, Double.parseDouble(event.getRowValue().get(4)));
            paymentqueue.replace(paymentqueue, oldPayment, newPayment);
            System.out.println(paymentqueue.toString());
            Payment.setConsecutivo(oldPayment.getId() - 1);
            newPayment.setId(Payment.getConsecutivo());

            util.Data.setDataFile("payments", paymentqueue);
        } catch (QueueException | ListException | IOException ex) {
            Logger.getLogger(FXMLPagoConsultaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void serviceChargeColumnOnEditCommit(TableColumn.CellEditEvent<List<String>, String> event) {
        try {
            String serviceCharge = event.getRowValue().get(2);
            String sDate1 = event.getRowValue().get(3);
            Date date1 = new SimpleDateFormat("yyyy/MM/dd").parse(sDate1);
            Payment oldPayment = new Payment(Integer.parseInt(event.getRowValue().get(0)), event.getRowValue().get(1), Double.parseDouble(serviceCharge), date1, Double.parseDouble(event.getRowValue().get(4)));
            double newTotalCharge = Double.parseDouble(event.getNewValue()) * (1 + 0.3);
            Payment newPayment = new Payment(Integer.parseInt(event.getRowValue().get(0)), event.getRowValue().get(1), Double.parseDouble(event.getNewValue()), date1, newTotalCharge);
            paymentqueue.replace(paymentqueue, oldPayment, newPayment);
            System.out.println(paymentqueue.toString());
            Payment.setConsecutivo(oldPayment.getId() - 1);
            newPayment.setId(Payment.getConsecutivo());
            this.TableView.setItems(getData());
            util.Data.setDataFile("payments", paymentqueue);
        } catch (ParseException | QueueException | ListException | IOException ex) {
            Logger.getLogger(FXMLPagoConsultaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ObservableList<List<String>> getData() {
        ObservableList<List<String>> data = FXCollections.observableArrayList();
        if (this.paymentqueue != null && !this.paymentqueue.isEmpty()) {
            try {
                HeaderLinkedQueue aux = new HeaderLinkedQueue();
                while (!paymentqueue.isEmpty()) {
                    Payment P = (Payment) paymentqueue.front();
                    if (currentUser.getType().equalsIgnoreCase("Patient")) {
                        if (currentUser.getUser().equals(String.valueOf(P.getPatientID()))) {
                            List<String> arrayList = new ArrayList<>();
                            arrayList.add(String.valueOf(P.getPatientID()));
                            arrayList.add(P.getPaymentMode());
                            arrayList.add(String.valueOf(P.getServiceCharge()));
                            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                            arrayList.add(String.valueOf(format.format(P.getBillingDate())));
                            arrayList.add(String.valueOf(P.getTotalCharge()));
                            data.add(arrayList);
                            aux.enQueue(paymentqueue.deQueue());
                        }
                    }else{
                        List<String> arrayList = new ArrayList<>();
                        arrayList.add(String.valueOf(P.getPatientID()));
                        arrayList.add(P.getPaymentMode());
                        arrayList.add(String.valueOf(P.getServiceCharge()));
                        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                        arrayList.add(String.valueOf(format.format(P.getBillingDate())));
                        arrayList.add(String.valueOf(P.getTotalCharge()));
                        data.add(arrayList);
                        aux.enQueue(paymentqueue.deQueue());
                    }
                    
                }
                //al final dejamos la cola en su estado original
                while (!aux.isEmpty()) {
                    Payment p = (Payment) aux.front();
                    paymentqueue.enQueue(aux.deQueue());
                }
            } catch (QueueException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return data;
    }

   
    

}
