package com.iquestgroup.homework;


import com.dumbster.smtp.SimpleSmtpServer;
import com.dumbster.smtp.SmtpMessage;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertTrue;


public class EmailNotificationTest {

    private static final String SENDER_NAME = "senderName";

    private static final String SUBJECT = "subject";

    private static final String EMAIL_MESSAGE = "email message";

    private static final String FROM = "from@email.com";

    private static final String TO = "to@email.com";

    @Test
    public void testSend() throws Exception {
        SimpleSmtpServer server = SimpleSmtpServer.start();

        Notification notification = new EmailNotification();
        notification.senderName = SENDER_NAME;
        notification.subject = SUBJECT;
        notification.message = EMAIL_MESSAGE;
        notification.from = FROM;
        notification.to = TO;
        notification.send();

        server.stop();
        assertTrue(server.getReceivedEmailSize() == 1);
        Iterator emailIterator = server.getReceivedEmail();
        SmtpMessage email = (SmtpMessage) emailIterator.next();
        assertTrue(email.getHeaderValue("Subject").equals(SUBJECT));
        assertTrue(email.getBody().equals(EMAIL_MESSAGE));
    }
}
