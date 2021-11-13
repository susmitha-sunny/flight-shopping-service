package com.gotravel.flightshoppingservice.util;

import java.time.LocalDate;

public class DateUtil {

    public static int convertToDay(final LocalDate date) {
        return date.getDayOfWeek().getValue();
    }
}
