/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javafx.util.converter.LocalDateTimeStringConverter;

/**
 *
 * @author Wilson Bonilla Mata
 */
public class Appointment {
   
    private int id;
    private static int consecutivo = 1;
    private int patientID;
    private int doctorID;
    private LocalDateTime dateTime;
    private String remarks;

    public Appointment(int patientID, int doctorID, LocalDateTime dateTime, String remarks) {
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.dateTime = dateTime;
        this.remarks = remarks;
        this.id = consecutivo ++;
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
     * @return the doctorID
     */
    public int getDoctorID() {
        return doctorID;
    }

    /**
     * @param doctorID the doctorID to set
     */
    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

   
    
    
    /**
     * @return the remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks the remarks to set
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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
    
    public boolean compareDoctorId(int id){
        return util.Utility.equals(this.doctorID, id);
    }
    
    @Override
    public String toString() {
        return "Appointment " + "id = " + id + ", patientID = " + patientID + ", doctorID = " + doctorID + ", dateTime = " + dateTime + ", remarks = " + remarks;
    }
    
    
    
    
    
}
