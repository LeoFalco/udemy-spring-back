package com.nelioalves.cursomc;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.lang.reflect.Array;
import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class Calendars {

    private static final String DATE_PATTERN = "dd/MM/yyyy HH:mm";
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);

    public static Calendar toCalendar(String data) {
        try {

            Date date = simpleDateFormat.parse(data);

            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, date.getYear());
            c.set(Calendar.MONTH, date.getMonth());
            c.set(Calendar.DAY_OF_MONTH, date.getDay());
            c.set(Calendar.HOUR_OF_DAY, date.getHours());
            c.set(Calendar.MINUTE, date.getMinutes());

            return c;
        } catch (ParseException e) {
            throw new InvalidParameterException("data fora do formato: " + DATE_PATTERN);
        }
    }
}
