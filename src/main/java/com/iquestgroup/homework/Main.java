package com.iquestgroup.homework;


import java.util.Calendar;


public class Main {

    public static void main(String[] args) {
        HolidayService holidayService = new HolidayServiceImpl();
        HolidayRequest holidayRequest = new HolidayRequest();
        holidayRequest.setEmployeeName("Rares Smeu");
        holidayRequest.setEmployeeEmail("rares.smeu@iquestgroup.com");
        holidayRequest.setManagerEmail("raressmeu@yahoo.com");
        holidayRequest.setFrom(Calendar.getInstance().getTime());
        holidayRequest.setTo(Calendar.getInstance().getTime());
        holidayService.send(holidayRequest);
        holidayService.deny(holidayRequest);
    }
}
