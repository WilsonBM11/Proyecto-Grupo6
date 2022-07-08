/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author Kevin Brenes
 */
public class Security {
    
    private String userAdmin;
    private String userPatient;
    private String passwordAdmin;
    private String passwordPatient;

    public Security(String userAdmin, String userPatient, String passwordAdmin, String passwordPatient) {
        this.userAdmin = userAdmin;
        this.userPatient = userPatient;
        this.passwordAdmin = passwordAdmin;
        this.passwordPatient = passwordPatient;
    }

    public String getUserAdmin() {
        return userAdmin;
    }

    public void setUserAdmin(String userAdmin) {
        this.userAdmin = userAdmin;
    }

    public String getUserPatient() {
        return userPatient;
    }

    public void setUserPatient(String userPatient) {
        this.userPatient = userPatient;
    }

    public String getPasswordAdmin() {
        return passwordAdmin;
    }

    public void setPasswordAdmin(String passwordAdmin) {
        this.passwordAdmin = passwordAdmin;
    }

    public String getPasswordPatient() {
        return passwordPatient;
    }

    public void setPasswordPatient(String passwordPatient) {
        this.passwordPatient = passwordPatient;
    }

    @Override
    public String toString() {
        return "Security{" + "userAdmin=" + userAdmin + ", userPatient=" + userPatient + ", passwordAdmin=" + passwordAdmin + ", passwordPatient=" + passwordPatient + '}';
    }

   
    
    
    
}
