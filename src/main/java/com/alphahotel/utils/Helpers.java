package com.alphahotel.utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by ValdoR on 2019-12-20.
 */
public class Helpers {
    static final String DEFAULT_PATTERN = "dd-MM-yyyy HH:mm";
    public static long diffDatesInDays(Date startDate, Date endDate){
        return TimeUnit.DAYS.convert(Math.abs(endDate.getTime() - startDate.getTime()), TimeUnit.MILLISECONDS);
    }

    public static boolean isValidEmail(String email){
        if(email == null || email.trim().length() < 5 ){
            return false;
        }
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public static Date formatDateOrFail(String date_string) throws ParseException {
        DateFormat formatter = new SimpleDateFormat(DEFAULT_PATTERN);
        return formatter.parse(date_string);
    }
    public static String actualDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DEFAULT_PATTERN);
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
