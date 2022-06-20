package com.example.demo.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StringTimeNow {

    private static StringTimeNow stringTimeNow = new StringTimeNow();

    private StringTimeNow() {}

    public static StringTimeNow getInstance() {
        return stringTimeNow;
    }

    public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";

    public static String stringTimeNow() {
        String timeNow;
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        timeNow = sdf.format(cal.getTime());
        return timeNow.replace ( " " , "T" );
    }
}
