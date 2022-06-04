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
public class Doctor {

    //Doctor (id(int), lastname(String), firstname(String), birthday(java.util.Date),
    //phoneNumber(String), email(String), address(String))
    private int id;
    private String firstName;
    private String lasName;
    private String phoneNumber;
    private String email;
    private String address;
    private Date birthday;

    public Doctor(int id, String firstName, String lasName, String phoneNumber, String email, String address, Date birthday) {
        this.id = id;
        this.firstName = firstName;
        this.lasName = lasName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLasName() {
        return lasName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLasName(String lasName) {
        this.lasName = lasName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return util.Utility.getAge(birthday);

    }

    @Override
    public String toString() {
        return "Doctor " + "id = " + id + ", firstName = " + firstName + ", lasName = " + lasName + ", phoneNumber = " + phoneNumber + ", email = " + email + ", address = " + address + ", birthday = " + birthday;
    }

}
