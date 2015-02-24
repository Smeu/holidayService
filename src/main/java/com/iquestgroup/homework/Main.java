package com.iquestgroup.homework;


import java.util.Calendar;


public class Main {

    public static void main(String[] args) {
        HolidayRequest holidayRequest = new HolidayRequest();
        holidayRequest.employeeName = "Rares Smeu";
        holidayRequest.employeeEmail = "rares.smeu@iquestgroup.com";
        holidayRequest.managerEmail = "raressmeu@yahoo.com";
        holidayRequest.from = Calendar.getInstance().getTime();
        holidayRequest.to = Calendar.getInstance().getTime();
        holidayRequest.send();
        holidayRequest.deny();
    }
}
