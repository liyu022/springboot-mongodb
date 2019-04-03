package com.jolin.util;

import java.time.*;
import java.util.Date;

public  class DateConvert {
    // 05. java.time.LocalDate --> java.util.Date
    public Date LocalDateToUdate(LocalDate localDate) {
        // LocalDate localDate = LocalDate.now();
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        Date date = Date.from(instant);
        return date;
    }

    public Date LocalDateTimeToUdate(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        Date date = Date.from(zdt.toInstant());
        return date;
    }

    public static boolean sameDate(Date d1, Date d2) {
        LocalDateTime localDate1 = d1.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime localDate2 = d2.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return localDate1.isEqual(localDate2);
    }
    public static boolean isAfterDate(Date d1, Date d2) {
        LocalDateTime localDate1 = d1.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime localDate2 = d2.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return localDate1.isAfter(localDate2);
    }
}
