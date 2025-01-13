package com.wsda.bikegarage.Utils;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {

    public static void sendEmailStatus(String toAddress, String message, String targa) {
        try {
            // Imposta le proprietà SMTP
            Properties properties = new Properties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");

            // Disabilita la verifica del certificato
            properties.put("mail.smtp.ssl.trust", "*");

            // Crea un'autenticazione per il server SMTP
            Authenticator auth = new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("bikegageofficial@gmail.com", "ouob fdqy dhzq tmsk");
                }
            };

            // Crea una nuova sessione con l'autenticazione
            Session session = Session.getInstance(properties, auth);
            // Crea un nuovo messaggio email
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("bikegageofficial@gmail.com"));
            InternetAddress[] toAddresses = {new InternetAddress(toAddress)};
            msg.setRecipients(Message.RecipientType.TO, toAddresses);
            msg.setSubject("bikegageofficial@gmail.com");
            msg.setSentDate(new java.util.Date());
            if(message.equals("Completata")){
                msg.setText("Ciao caro Cliente\n la tua moto con targa: "+targa+" ha cambiato stato.\nAdesso è:" + message + "\n puoi venire a ritirarla quando desideri");
            } else {
                msg.setText("Ciao caro Cliente\n la tua moto con targa: "+targa+" ha cambiato stato.\nAdesso è:" + message);
            }
            Transport.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void sendEmailCode(String toAddress, String code) {
        try {
            // Imposta le proprietà SMTP
            Properties properties = new Properties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");

            // Disabilita la verifica del certificato
            properties.put("mail.smtp.ssl.trust", "*");

            // Crea un'autenticazione per il server SMTP
            Authenticator auth = new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("bikegageofficial@gmail.com", "ouob fdqy dhzq tmsk");
                }
            };

            // Crea una nuova sessione con l'autenticazione
            Session session = Session.getInstance(properties, auth);
            // Crea un nuovo messaggio email
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("bikegageofficial@gmail.com"));
            InternetAddress[] toAddresses = {new InternetAddress(toAddress)};
            msg.setRecipients(Message.RecipientType.TO, toAddresses);
            msg.setSubject("bikegageofficial@gmail.com");
            msg.setSentDate(new java.util.Date());
            msg.setText("Ciao impiegato, questo è il tuo codice di recupero della password: " + code);
            // Invia il messaggio email
            Transport.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}