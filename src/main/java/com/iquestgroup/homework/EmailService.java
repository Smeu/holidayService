package com.iquestgroup.homework;


import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;


/**
 * Email service for sending emails using the gmail smtp.
 *
 * @author Rares Smeu
 */
public class EmailService {

    private static final String EMAIL_PROPERTIES_FILE = "email.properties";

    private static final String EMAIL_USERNAME = "mail.username";

    private static final String EMAIL_PASSWORD = "mail.password";

    private Session session;

    public EmailService() {
        Properties props = getProperties();
        final String username = props.getProperty(EMAIL_USERNAME);
        final String password = props.getProperty(EMAIL_PASSWORD);
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };

        session = Session.getDefaultInstance(props, authenticator);
    }

    private Properties getProperties() {
        Properties props = new Properties();
        InputStream input = null;

        try {
            input = EmailService.class.getClassLoader().getResourceAsStream(EMAIL_PROPERTIES_FILE);
            props.load(input);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            if (input != null) {
                try {
                    input.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return props;
    }

    /**
     * Sends the email.
     *
     * @param email email to be sent.
     */
    public void send(Email email) {
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(getSenderAddress(email));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getTo()));
            msg.setSubject(email.getSubject());
            msg.setText(email.getMessage());
            Transport.send(msg);

        }
        catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("Mail couldn't be sent.");
        }
    }

    private InternetAddress getSenderAddress(Email email) throws AddressException {
        try {
            return new InternetAddress(email.getFrom(), email.getSenderName());
        }
        catch (UnsupportedEncodingException e) {
            return new InternetAddress(email.getFrom());
        }
    }
}
