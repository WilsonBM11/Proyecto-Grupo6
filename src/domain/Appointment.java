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
    private int patientID;
    private int doctorID;
    private LocalDateTime dateTime;
    private String remarks;

    public Appointment(int id, int patientID, int doctorID, LocalDate date,LocalTime time, String remarks) {
        this.id = id;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.dateTime = LocalDateTime.of(date,time);
        this.remarks = remarks;
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

    /**
     * @return the dateTime
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * @param dateTime the dateTime to set
     */
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
    
    
    
    
}
