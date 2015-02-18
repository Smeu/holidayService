package com.iquestgroup.homework;


/**
 * Service that handles holiday requests.
 *
 * @author Rares Smeu
 */
public interface HolidayService {

    /**
     * Sends the holiday request to a manager for review.
     *
     * @param holidayRequest the holiday request to be sent.
     */
    void send(HolidayRequest holidayRequest);

    /**
     * Accepts a holiday request by sending it to HR team.
     *
     * @param holidayRequest the holiday request to be accepted.
     */
    void accept(HolidayRequest holidayRequest);

    /**
     * Denies a holiday request and informs the requester.
     *
     * @param holidayRequest the holiday request to be denied.
     */
    void deny(HolidayRequest holidayRequest);
}
