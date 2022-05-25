/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
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
   
        private final String username = "proyectogrupo06.algoritmos@gmail.com";//
        private final String password = "contrasenagrupo06";
        
	public void sendEmail(String destinationEmail, String subjectEmail, String textEmail) throws AddressException, MessagingException{
          
            final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
            // Get a Properties object
            Properties props = System.getProperties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.port", "465");
            props.setProperty("mail.smtp.socketFactory.port", "465");
            props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
            props.put("mail.smtp.auth", "true");
            props.put("mail.debug", "true");
            props.put("mail.store.protocol", "pop3");
            props.put("mail.transport.protocol", "smtp");
            
            try {
                Session session = Session.getDefaultInstance(props,
                        new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

                // -- Create a new message --
                Message msg = new MimeMessage(session);

                // -- Set the FROM and TO fields --
                msg.setFrom(new InternetAddress(username));
                msg.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(destinationEmail, false));
                msg.setSubject(subjectEmail);
                msg.setText(textEmail);
                msg.setSentDate(new Date());
                Transport.send(msg);
                System.out.println("Message sent");
            } catch (MessagingException e) {
                System.out.println("Error al enviar el mensaje. Razon: " + e);
            }
            
	}
    
    
}
