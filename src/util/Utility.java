/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import domain.Appointment;
import domain.CircularDoublyLinkedList;
import domain.CircularLinkedList;
import domain.Doctor;
import domain.DoublyLinkedList;
import domain.Patient;
import domain.Sickness;
import domain.SinglyLinkedList;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Profesor Lic. Gilberth Chaves Avila
 */
public class Utility {

    private static SinglyLinkedList singlyLinkedList = new SinglyLinkedList();
    private static DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
    private static CircularLinkedList circularLinkedList = new CircularLinkedList();
    private static CircularDoublyLinkedList circularDoublyLinkedList = new CircularDoublyLinkedList();

    private static String criterioOrdenamiento;

    public static String getCriterioOrdenamiento() {
        return criterioOrdenamiento;
    }

    public static void setCriterioOrdenamiento(String criterio) {
        Utility.criterioOrdenamiento = criterio;
    }

    public static SinglyLinkedList getSinglyLinkedList() {
        return singlyLinkedList;
    }

    public static void setSinglyLinkedList(SinglyLinkedList singlyLinkedList) {
        Utility.singlyLinkedList = singlyLinkedList;
    }

    public static DoublyLinkedList getDoublyLinkedList() {
        return doublyLinkedList;
    }

    public static void setDoublyLinkedList(DoublyLinkedList doublyLinkedList) {
        Utility.doublyLinkedList = doublyLinkedList;
    }

    public static CircularLinkedList getCircularLinkedList() {
        return circularLinkedList;
    }

    public static void setCircularLinkedList(CircularLinkedList circularLinkedList) {
        Utility.circularLinkedList = circularLinkedList;
    }

    public static CircularDoublyLinkedList getCircularDoublyLinkedList() {
        return circularDoublyLinkedList;
    }

    public static void setCircularDoublyLinkedList(CircularDoublyLinkedList circularDoublyLinkedList) {
        Utility.circularDoublyLinkedList = circularDoublyLinkedList;
    }

    private CircularLinkedList patientsList;

    public static int random() {
        return 1 + (int) Math.floor(Math.random() * 99);
    }

    public static int random(int bound) {
        //return 1+random.nextInt(bound);
        return 1 + (int) Math.floor(Math.random() * bound);
    }

    public static int random(int lowBound, int highBound) {
        return lowBound + (int) Math.floor(Math.random() * highBound);
    }

    public static String format(double value) {
        return new DecimalFormat("###,###,###,###.##")
                .format(value);
    }

    public static String $format(double value) {
        return new DecimalFormat("$###,###,###,###.##")
                .format(value);
    }

    public static String perFormat(double value) {
        //#,##0.00 '%'
        return new DecimalFormat("#,##0.00'%'")
                .format(value);
    }

    public static void fill(int a[]) {
        Random random = new Random();
        for (int i = 0; i < a.length; i++) {
            a[i] = random.nextInt(99) + 1;
        }
    }

    public static void bubbleSort(int a[]) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[i]) {
                    int aux = a[i];
                    a[i] = a[j];
                    a[j] = aux;
                }//if
            }//for j
        }//for i
    }

    public static String show(int[] a) {
        String result = "";
        int counter = 0;
        for (int i = 0; i < a.length; i++) {
            if (counter++ >= 50) {
                result += "\n";
                counter = 0;
            }
            result += a[i] + " ";
        }
        return result;
    }

      private static String instanceOf(Object a, Object b) {
        if(a instanceof Integer && b instanceof Integer) return "Integer";
        if(a instanceof String && b instanceof String) return "String";
        if(a instanceof Character && b instanceof Character) return "Character";
        if(a instanceof Doctor && b instanceof Doctor) return "Doctor";
        if(a instanceof Sickness  && b instanceof Sickness)return "Sickness";
         if(a instanceof Patient  && b instanceof Patient)return "Patient";
          if(a instanceof Appointment  && b instanceof Appointment)return "Appointment";
        return "unknown";
    }

    public static boolean equals(Object a, Object b) {
        switch (instanceOf(a, b)) {
            case "Integer":
                Integer a1 = (Integer) a;
                Integer b1 = (Integer) b;
                //return x==y;
                return a1.equals(b1);
            case "String":
                String a2 = (String) a;
                String b2 = (String) b;
                return a2.equalsIgnoreCase(a2);
            case "Character":
                Character a3 = (Character) a;
                Character b3 = (Character) b;
                return a3.compareTo(a3) == 0;
            case "Doctor":
                Doctor a5 = (Doctor) a;
                Doctor b5 = (Doctor) b;
                return a5.getId() == b5.getId();
            case "Sickness":
                Sickness a6 = (Sickness) a;
                Sickness b6 = (Sickness) b;
                 return a6.getDescription().equalsIgnoreCase(b6.getDescription());
           case "Patient":
                Patient a7 = (Patient) a;
                Patient b7 = (Patient) b;
                return a7.getId() == b7.getId();
           case "Appointment":
               Appointment a8 = (Appointment)a;
               Appointment b8 = (Appointment)b;
               return a8.getPatientID() == b8.getPatientID();
        }
        return false;
    }

    //less than (menorQ)
    public static boolean lessT(Object a, Object b) {
        switch (instanceOf(a, b)) {
            case "Integer":
                Integer a1 = (Integer) a;
                Integer b1 = (Integer) b;
                return a1 < b1;
            case "String":
                String a2 = (String) a;
                String b2 = (String) b;
                return a2.compareToIgnoreCase(b2) < 0;
            case "Character":
                Character a3 = (Character) a;
                Character b3 = (Character) b;
                return a3.compareTo(a3) < 0;

        }
        return false;
    }
    //greater than (mayorQ)

    public static boolean greaterT(Object a, Object b) {
        switch (instanceOf(a, b)) {
            case "Integer":
                Integer a1 = (Integer) a;
                Integer b1 = (Integer) b;
                return a1 > b1;
            case "String":
                String a2 = (String) a;
                String b2 = (String) b;
                return a2.compareToIgnoreCase(b2) > 0;
            case "Character":
                Character a3 = (Character) a;
                Character b3 = (Character) b;
                return a3.compareTo(a3) > 0;
        }

        return false;
    }

    public static boolean isLetter(char value) {
        return Character.isLetter(value);
    }

    public static boolean isNumber(char value) {
        return Character.isDigit(value);
    }

    /**
     * @return the patientsList
     */
    public CircularLinkedList getPatientsList() {
        return patientsList;
    }

    /**
     * @param patientsList the patientsList to set
     */
    public void setPatientsList(CircularLinkedList patientsList) {
        this.patientsList = patientsList;
    }

    public static int getAge(Date birthday) {
        LocalDate fecha = LocalDate.now();
        LocalDate fechaNacimiento = birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long edad = ChronoUnit.YEARS.between(fechaNacimiento, fecha);
        return (int) edad;
    }

    public static String dateFormat(Date value) {
        return new SimpleDateFormat("dd/MM/yyyy").format(value);
    }
    
    public static Date stringToDate(String value) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return format.parse(value);
        } catch (ParseException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
