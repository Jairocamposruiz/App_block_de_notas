package com.tokioschool.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static String localDateFormat(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static LocalDate localDateParse(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
