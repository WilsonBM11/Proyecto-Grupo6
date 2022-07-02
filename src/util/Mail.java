/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import domain.BST;
import domain.Configurations;
import domain.QueueException;
import domain.TreeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import main.FXMLAddDoctorController;

/**
 *
 * @author Wilson Bonilla Mata
 */
public class Mail {

    private final String username = "proyectogrupo06.algoritmos@gmail.com";
    private final String password = "jbxcziuoiyefyodc";

    public void sendEmail(String destinationEmail, String subjectEmail, String textEmail) throws AddressException, MessagingException {

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

            BodyPart text = new MimeBodyPart();
            text.setContent(textEmail, "text/html");

            BodyPart image = new MimeBodyPart();
            image.setDataHandler((new DataHandler(new FileDataSource(getImage()))));
            image.setFileName("Clinica Grupo 06");

            MimeMultipart parts = new MimeMultipart();
            parts.addBodyPart(text);
            parts.addBodyPart(image);

            //msg.setText(textEmail);
            msg.setContent(parts);
            msg.setSentDate(new Date());
            Transport.send(msg);
            System.out.println("Message sent");
        } catch (MessagingException e) {
            System.out.println("Error sending mail. Reason: " + e);
        }

    }

    private BST getTree() {
        BST tree = new BST();
        if (util.Data.fileExists("configuration")) {
            try {
                tree = (BST) util.Data.getDataFile("configuration", tree);
            } catch (QueueException | IOException ex) {
                Logger.getLogger(FXMLAddDoctorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return tree;

    }
    private String getCorreo (){
        String correo = "";
    
        if (this.getTree() != null && !this.getTree().isEmpty()) {
            Configurations C = (Configurations) this.getTree().getRoot().data;
            correo = C.getCorreoElectronico();
    }
        return correo;

}
    public String getImage (){
        String Image = "";
    
        if (this.getTree() != null && !this.getTree().isEmpty()) {
            Configurations C = (Configurations) this.getTree().getRoot().data;
            Image = C.getImagenCorreo();
    }
        return Image;
    }
    public String getClinicName (){
        String correo = "";
    
        if (this.getTree() != null && !this.getTree().isEmpty()) {
            Configurations C = (Configurations) this.getTree().getRoot().data;
            correo = C.getClinicName();
    }
        return correo;

}
}
