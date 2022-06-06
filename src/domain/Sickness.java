/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

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

    @Override
    public String toString() {
        return "Sickness " + "id = " + id + ", description = " + description;
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
    
    
    
}
