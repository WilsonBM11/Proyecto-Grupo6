/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Wilson Bonilla Mata
 */
public class Mail {
   
        private Properties properties = new Properties();
	
        private String email = "proyectogrupo06.algoritmos@gmail.com";
	private String password = "laqodsemtcijbmbw";
 
	private Session session;
 
	private void init() {
            
            properties.setProperty("mai.smtp.host", "smtp.gmail.com");
            properties.setProperty("mai.smtp.starttls.enable", "true");
            properties.setProperty("mai.smtp.port", "587");
            properties.setProperty("mai.smtp.auth", "true");
            
            session = Session.getDefaultInstance(properties);
            
	}
 
	public void sendEmail(String destinationEmail) throws AddressException, MessagingException{
            
            init();
            MimeMessage mail = new MimeMessage(session);
            mail.setFrom(new InternetAddress(email));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(destinationEmail));
            mail.setSubject("Prueba Java");
            mail.setText("Hola Wilson");
            
            Transport transport = session.getTransport("smtp");
            transport.connect(email,password);
            transport.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
            transport.close();
            
            
	}
    
    
}
