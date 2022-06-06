/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *
 * @author Wilson Bonilla Mata
 */
public class MedicalCare {
    
   
    private int id;
    private int doctorID;
    private int patienteID;
    private LocalDateTime dateTime;
    private int sicknessID;
    private String annotations;

    public MedicalCare(int id, int doctorID, int patienteID, LocalDate date,LocalTime time, int sicknessID, String annotations) {
        this.id = id;
        this.doctorID = doctorID;
        this.patienteID = patienteID;
        this.dateTime = LocalDateTime.of(date, time);
        this.sicknessID = sicknessID;
        this.annotations = annotations;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
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
     * @return the patienteID
     */
    public int getPatienteID() {
        return patienteID;
    }

    /**
     * @param patienteID the patienteID to set
     */
    public void setPatienteID(int patienteID) {
        this.patienteID = patienteID;
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
     * @return the sicknessID
     */
    public int getSicknessID() {
        return sicknessID;
    }

    /**
     * @param sicknessID the sicknessID to set
     */
    public void setSicknessID(int sicknessID) {
        this.sicknessID = sicknessID;
    }

    /**
     * @return the annotations
     */
    public String getAnnotations() {
        return annotations;
    }

    /**
     * @param annotations the annotations to set
     */
    public void setAnnotations(String annotations) {
        this.annotations = annotations;
    }

    
    
    
}
