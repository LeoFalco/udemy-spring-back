package com.nelioalves.cursomc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dates {
    private static final Logger logger = Logger.getLogger(Dates.class.getName());

    private static final String DD_MM_YYYY_HH_MM = "dd/MM/yyyy HH:mm";

    public static Date toDate(String data) {
        return toDate(data, DD_MM_YYYY_HH_MM);
    }

    public static Date toDate(String pattern, String data) {

        Date date = null;
        try {
            date = new SimpleDateFormat(pattern).parse(data);
        } catch (ParseException ex) {
            logger.log(Level.WARNING, ex.getMessage());
        }
        return date;
    }
}
