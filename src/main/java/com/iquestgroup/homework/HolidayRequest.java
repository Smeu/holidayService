package com.iquestgroup.homework;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Model for a holiday request.
 *
 * @author Rares Smeu
 */
public class HolidayRequest {

    public String employeeName;

    public String employeeEmail;

    public String managerEmail;

    public Date from;

    public Date to;

    private static final String HR_EMAIL = "HR_EMAIL@ADDRESS.RO";

    private static final DateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy");

    /**
     * Sends the holiday request to a manager for review.
     */
    public void send() {
        Notification notification = new EmailNotification();
        notification.senderName = employeeName;
        notification.from = employeeEmail;
        notification.to = managerEmail;
        notification.subject = "Holiday request " + employeeName;
        notification.message = getMessage();
        notification.send();
    }

    /**
     * Accepts a holiday request by sending it to HR team.
     */
    public void accept() {
        Notification notification = new EmailNotification();
        notification.from = managerEmail;
        notification.to = HR_EMAIL;
        notification.subject = "Holiday request " + employeeName;
        notification.message = getMessage();
        notification.send();
    }

    /**
     * Denies a holiday request and informs the requester.
     */
    public void deny() {
        Notification notification = new EmailNotification();
        notification.from = managerEmail;
        notification.to = employeeEmail;
        notification.subject = "Holiday request denied";
        notification.message = getMessage();
        notification.send();
    }

    private String getMessage() {
        return "Holiday request for " + employeeName
                + " from " + dateFormat.format(from)
                + " to " + dateFormat.format(to);
    }


}
