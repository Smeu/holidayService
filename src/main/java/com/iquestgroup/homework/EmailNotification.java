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
 * Email notification system for sending emails using the gmail smtp.
 *
 * @author Rares Smeu
 */
public class EmailNotification extends Notification {

    private static final String EMAIL_PROPERTIES_FILE = "email.properties";

    private static final String EMAIL_USERNAME = "mail.username";

    private static final String EMAIL_PASSWORD = "mail.password";

    private static Session session;

    public EmailNotification() {
        if (session == null) {
            initSession();
        }
    }

    private void initSession() {
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
            input = getClass().getClassLoader().getResourceAsStream(EMAIL_PROPERTIES_FILE);
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

    @Override
    void send() {
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(getSenderAddress());
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msg.setSubject(subject);
            msg.setText(message);
            Transport.send(msg);

        }
        catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("Mail couldn't be sent.");
        }
    }

    private InternetAddress getSenderAddress() throws AddressException {
        try {
            return new InternetAddress(from, senderName);
        }
        catch (UnsupportedEncodingException e) {
            return new InternetAddress(from);
        }
    }
}
