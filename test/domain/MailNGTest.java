/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author Wilson Bonilla Mata
 */
public class MailNGTest {
    
    public MailNGTest() {
    }

    @Test
    public void testSomeMethod() {
        try {
            Mail m = new Mail();
            m.sendEmail("wilsonbm11@gmail.com");
        } catch (MessagingException ex) {
            Logger.getLogger(MailNGTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
