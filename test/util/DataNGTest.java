/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import domain.BST;
import domain.CircularDoublyLinkedList;
import domain.LinkedQueue;
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
        LinkedQueue bst = new LinkedQueue();
        if (util.Data.fileExists("numbers")) {
            bst = (LinkedQueue) util.Data.getDataFile("numbers", bst);
        }

        for (int i = 0; i < 5; i++) {
            bst.enQueue(util.Utility.random(25));
        }
        
        System.out.println(bst.toString());
        util.Data.setDataFile("numbers", bst);
    }

}
