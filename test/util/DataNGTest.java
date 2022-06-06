/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import domain.ListException;
import domain.MedicalCare;
import domain.Patient;
import domain.QueueException;
import domain.SinglyLinkedList;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author Wilson Bonilla Mata
 */
public class DataNGTest {

    public DataNGTest() {
    }

    @Test
    public void testSomeMethod() throws QueueException, ListException, IOException {
        SinglyLinkedList s = new SinglyLinkedList();
        if (util.Data.fileExists("medicalcare")) {
            s = (SinglyLinkedList) util.Data.getDataFile("medicalcare", s);
        }

        s.add(new MedicalCare(3020, 30680, LocalDate.of(2022, 6, 6), LocalTime.of(11, 30), 1, "Nada que agregar"));
        s.add(new MedicalCare(3045, 30102, LocalDate.of(2022, 6, 6), LocalTime.of(10, 30), 2, "Nada que agregar"));
        
        System.out.println(s.toString());
        util.Data.setDataFile("medicalcare", s);
    }

}
