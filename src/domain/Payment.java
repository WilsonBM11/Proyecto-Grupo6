/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Date;

/**
 *
 * @author Wilson Bonilla Mata
 */
public class Payment {
    
    private int id;
    private static int consecutivo = 1;
    private int patientID;
    private String paymentMode;
    private double serviceCharge;
    private Date billingDate;
    private double totalCharge;

    public Payment(int patientID, String paymentMode, double serviceCharge, Date billingDate, double totalCharge) {
        this.id = consecutivo++;
        this.patientID = patientID;
        this.paymentMode = paymentMode;
        this.serviceCharge = serviceCharge;
        this.billingDate = billingDate;
        this.totalCharge = totalCharge;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the patientID
     */
    public int getPatientID() {
        return patientID;
    }

    /**
     * @param patientID the patientID to set
     */
    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    /**
     * @return the paymentMode
     */
    public String getPaymentMode() {
        return paymentMode;
    }

    /**
     * @param paymentMode the paymentMode to set
     */
    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    /**
     * @return the serviceCharge
     */
    public double getServiceCharge() {
        return serviceCharge;
    }

    /**
     * @param serviceCharge the serviceCharge to set
     */
    public void setServiceCharge(double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    /**
     * @return the billingDate
     */
    public Date getBillingDate() {
        return billingDate;
    }

    /**
     * @param billingDate the billingDate to set
     */
    public void setBillingDate(Date billingDate) {
        this.billingDate = billingDate;
    }

    /**
     * @return the totalCharge
     */
    public double getTotalCharge() {
        return totalCharge;
    }

    /**
     * @param totalCharge the totalCharge to set
     */
    public void setTotalCharge(double totalCharge) {
        this.totalCharge = totalCharge;
    }

    /**
     * @return the consecutivo
     */
    public static int getConsecutivo() {
        return consecutivo;
    }

    /**
     * @param aConsecutivo the consecutivo to set
     */
    public static void setConsecutivo(int aConsecutivo) {
        consecutivo = aConsecutivo;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Payment" + "id = " + id + ", patientID = " + patientID + ", paymentMode = " + paymentMode + ", serviceCharge = " + serviceCharge + ", billingDate = " + billingDate + ", totalCharge = " + totalCharge;
    }
    
    
    
    
}
