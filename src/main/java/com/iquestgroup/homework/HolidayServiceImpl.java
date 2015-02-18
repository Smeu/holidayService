package com.iquestgroup.homework;


import java.text.DateFormat;
import java.text.SimpleDateFormat;


/**
 * Implementation with emails for communication.
 *
 * @author Rares Smeu
 */
public class HolidayServiceImpl implements HolidayService {

    private static final String HR_EMAIL = "HR_EMAIL@ADDRESS.RO";

    private static final DateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy");

    private EmailService emailService = new EmailService();

    @Override
    public void send(HolidayRequest holidayRequest) {
        Email email = new Email();
        email.setSenderName(holidayRequest.getEmployeeName());
        email.setFrom(holidayRequest.getEmployeeEmail());
        email.setTo(holidayRequest.getManagerEmail());
        email.setSubject("Holiday request " + holidayRequest.getEmployeeName());
        email.setMessage(getEmailMessage(holidayRequest));
        emailService.send(email);
    }

    @Override
    public void accept(HolidayRequest holidayRequest) {
        Email email = new Email();
        email.setFrom(holidayRequest.getManagerEmail());
        email.setTo(HR_EMAIL);
        email.setSubject("Holiday request " + holidayRequest.getEmployeeName());
        email.setMessage(getEmailMessage(holidayRequest));
        emailService.send(email);
    }

    @Override
    public void deny(HolidayRequest holidayRequest) {
        Email email = new Email();
        email.setFrom(holidayRequest.getManagerEmail());
        email.setTo(holidayRequest.getEmployeeEmail());
        email.setSubject("Holiday request denied");
        email.setMessage(getEmailMessage(holidayRequest));
        emailService.send(email);
    }

    private String getEmailMessage(HolidayRequest holidayRequest) {
        StringBuilder message = new StringBuilder();
        message.append("Holiday request for ");
        message.append(holidayRequest.getEmployeeName());
        message.append(" from ");
        message.append(dateFormat.format(holidayRequest.getFrom()));
        message.append(" to ");
        message.append(dateFormat.format(holidayRequest.getTo()));
        return message.toString();
    }

}
