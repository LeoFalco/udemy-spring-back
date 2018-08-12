package com.nelioalves.cursomc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class Dates {

    private static final String DD_MM_YYYY_HH_MM = "dd/MM/yyyy HH:mm";

    public static Date toDate(String data) {
        return toDate(data, DD_MM_YYYY_HH_MM);
    }

    public static Date toDate(String pattern, String data) {

        Date date = null;
        try {
            date = new SimpleDateFormat(pattern).parse(data);
            System.out.println("teste");
        } catch (ParseException ignore) {
            System.out.println("erro");
        }

        return null;
        /*
        return Optional.ofNullable(date)
                .orElseThrow(() -> new IllegalArgumentException("impossível usar o formato " + pattern + " para converter a data " + data));
                */
    }
}
