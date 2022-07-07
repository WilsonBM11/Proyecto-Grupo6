/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package domain;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author Duran Family
 */
public class BSTNGTest {
    
    public BSTNGTest() {
    }

    @Test
    public void testSomeMethod() {
        BST tree = new BST();
        for (int i = 0 ; i <=10 ; i++){
            
        
        tree.add(i);
        }
        System.out.println(tree.toString());
        try {
            util.Data.setDataFile("Week", tree);
        } catch (QueueException | ListException | IOException ex) {
            Logger.getLogger(BSTNGTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
