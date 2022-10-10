package org.phonebook;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;
import java.util.regex.Pattern;

public class EmailSender {

    private static final String regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    private static String mainMessage = "List of all contacts:";
    private static Session newSession = null;
    private final static String username = AppProperties.propperties.getProperty("username");
    private final static String password = AppProperties.propperties.getProperty("password");

    // Put sender’s address
    static String sender = "phoneBook@phonebook.com";
    static String host = "smtp.mailtrap.io";
    private static MimeMessage mimeMessage;
     public static void sendEmail(String recipient) {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.port", "2525");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        newSession = Session.getDefaultInstance(properties,null);
        mimeMessage = new MimeMessage(newSession);
        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);
            // Set From: header field
            message.setFrom(new InternetAddress(sender));
            // Set To: header field
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipient));
            // Set Subject: header field
            message.setSubject(mainMessage);
            // Put the content of your message
            message.setText(PhoneBook.getAllContacts());
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isValid(String emailAddress) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
}
