package com.iquestgroup.homework;


/**
 * Model for a notification.
 *
 * @author Rares Smeu
 */
public abstract class Notification {

    public String senderName;

    public String to;

    public String from;

    public String subject;

    public String message;

    /**
     * Sends the notification.
     */
    abstract void send();
}
