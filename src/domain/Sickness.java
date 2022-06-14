/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.util.Date;

/**
 *
 * @author Duran Family
 */
public class Sickness {

    
    private int id;
    private String description;
    private static int consecutivo = 1;
    
    public Sickness(String description) {
       this.id = consecutivo++;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static int getConsecutivo() {
        return consecutivo;
    }

    public static void setConsecutivo(int consecutivo) {
        Sickness.consecutivo = consecutivo;
    }

    @Override
    public String toString() {
        return "Sickness " + "id = " + id + ", description = " + description;
    }

  

  

    
   

}
