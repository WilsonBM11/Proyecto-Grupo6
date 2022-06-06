/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import domain.Appointment;
import domain.Doctor;
import domain.Queue;
import domain.List;
import domain.ListException;
import domain.MedicalCare;
import domain.Patient;
import domain.Payment;
import domain.QueueException;
import domain.Sickness;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Wilson Bonilla Mata
 */
public class Data {
    
    public static boolean fileExists(String fileName) {
        File file = new File(fileName + ".txt");
        return file.exists();
    }
    
    private static PrintStream getPrintStream(String fileName) {
        PrintStream ps = null;
        //"operations.txt"
        File file = new File(fileName + ".txt");
        try {

            FileOutputStream fos = new FileOutputStream(file, false);
            ps = new PrintStream(fos);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex);
        }
        return ps;
    }

    private static BufferedReader getBufferedReader(String fileName) {
        File file = new File(fileName + ".txt");
        //"operations.txt"
        BufferedReader bfr = null; //Se debe declarar el metodo de forma global cuando se vaya a retornar
        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            bfr = new BufferedReader(isr);

        } catch (FileNotFoundException ex) {
            System.out.println("Error ");
        }
        return bfr;
    }


    public static void setDataFile(String fileName, Object TDA) throws QueueException, ListException, IOException {
        PrintStream ps = getPrintStream(fileName);
        switch (instanceOf(TDA)) {
            case "Queue":
                Queue queue = (Queue) TDA;
                while (!queue.isEmpty()) {
                    ps.println(getString(fileName, queue.deQueue()));
                }
            case "List":
                List list = (List) TDA;
                for (int i = 1; i <= list.size(); i++) {
                    ps.println(getString(fileName, list.getNode(i).data));
                }
        }
    }

    public static Object getDataFile(String fileName, Object TDA) throws QueueException, IOException {

        BufferedReader br = getBufferedReader(fileName);
        String lineRegister = "";

        switch (instanceOf(TDA)) {
            case "Queue":
                Queue queue = (Queue) TDA;
                lineRegister = br.readLine();
                while (lineRegister != null) {
                    queue.enQueue(getObject(fileName, lineRegister));
                    lineRegister = br.readLine();
                }
                return queue;
            case "List":
                List list = (List) TDA;
                lineRegister = br.readLine();
                while (lineRegister != null) {
                    list.add(getObject(fileName, lineRegister));
                    lineRegister = br.readLine();
                }
                return list;
        }

        return null;
    }

    private static String instanceOf(Object a) {
        if (a instanceof Queue) {
            return "Queue";
        }
        if (a instanceof List) {
            return "List";
        }
        return "unknown";
    }

    private static Object getObject(String fileName, String lineRegister) {
        ArrayList<String> aux = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(lineRegister, ";");
        while (st.hasMoreTokens()) {
            aux.add(st.nextToken());
        }
        //Se debe guardar los date con dateFormat(Date value)
        //El LocalDate y LocalTime se guarda con LocalDate.toString y  LocalTime.toString
        switch (fileName) {
            case "patients":
                return new Patient(Integer.parseInt(aux.get(0)), aux.get(1), aux.get(2),
                        util.Utility.stringToDate(aux.get(3)), aux.get(4), aux.get(5), aux.get(6));
            case "doctors":
                return new Doctor(Integer.parseInt(aux.get(0)), aux.get(1), aux.get(2), aux.get(3),
                        aux.get(4), aux.get(5), util.Utility.stringToDate(aux.get(6)));
            case "appointment":
                Appointment a = new Appointment(Integer.parseInt(aux.get(1)),
                            Integer.parseInt(aux.get(2)), LocalDate.parse(aux.get(3)), LocalTime.parse(aux.get(4)), aux.get(5));
                a.setId(Integer.parseInt(aux.get(0)));
                Appointment.setConsecutivo(a.getId()+1);
                return a;
            case "sickness":
                Sickness s = new Sickness(aux.get(1));
                s.setId(Integer.parseInt(aux.get(0)));
                Sickness.setConsecutivo(s.getId()+1);
                return s;
            case "medicalcare":
                MedicalCare mc = new MedicalCare(Integer.parseInt(aux.get(1)),
                        Integer.parseInt(aux.get(2)), LocalDate.parse(aux.get(3)), LocalTime.parse(aux.get(4)),
                        Integer.parseInt(aux.get(5)), aux.get(6));
                mc.setId(Integer.parseInt(aux.get(0)));
                MedicalCare.setConsecutivo(mc.getId()+1);
                return mc;
            case "payments":
                Payment pay = new Payment(Integer.parseInt(aux.get(1)), aux.get(2), Double.parseDouble(aux.get(3)),
                        util.Utility.stringToDate(aux.get(4)), Double.parseDouble(aux.get(5)));
                pay.setId(Integer.parseInt(aux.get(0)));
                Payment.setConsecutivo(pay.getId()+1);
                return pay;
        }
        return null;
    }

    private static String getString(String fileName, Object data) {
        
        switch (fileName) {
            case "patients":
                Patient p = (Patient) data;
                return p.getId()+";"+p.getLastname()+";"+p.getFirstname()+";"+util.Utility.dateFormat(p.getBirthday())+";"+p.getPhoneNumber()+";"+p.getEmail()+";"+p.getAddress();
            case "doctors":
                Doctor d = (Doctor) data;
                return d.getId()+";"+d.getFirstName()+";"+d.getLasName()+";"+d.getPhoneNumber()+";"+d.getEmail()+";"+d.getAddress()+";"+util.Utility.dateFormat(d.getBirthday());
            case "appointment":
                Appointment a = (Appointment) data;
                return a.getId()+";"+a.getPatientID()+";"+a.getDoctorID()+";"+a.getDateTime().toLocalDate().toString()+";"+
                a.getDateTime().toLocalTime().toString()+";"+a.getRemarks();
            case "sickness":
                Sickness s = (Sickness) data;
                return s.getId()+";"+s.getDescription();
            case "medicalcare":
                MedicalCare mc = (MedicalCare) data;
                return mc.getId()+";"+mc.getDoctorID()+";"+mc.getPatientID()+";"+mc.getDateTime().toLocalDate().toString()+";"+
                       mc.getDateTime().toLocalTime().toString()+";"+mc.getSicknessID()+";"+mc.getAnnotations();
            case "payments":
                Payment pay = (Payment) data;
                return pay.getId()+";"+pay.getPatientID()+";"+pay.getPaymentMode()+";"+pay.getServiceCharge()+";"+
                       util.Utility.dateFormat(pay.getBillingDate())+";"+pay.getTotalCharge();
        }
        return null;
    }
}
