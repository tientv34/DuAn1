/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class XDate {

    static SimpleDateFormat formater = new SimpleDateFormat();

    public static Date toDate(String date, String pattern) {
        try {
            formater.applyPattern(pattern);
            return formater.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    public static String toString(Date date, String... pattern) {
        formater.applyPattern(pattern[0]);
        return formater.format(date);

    }

    public static Date addDays(Date date, long days) {
        date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
        return date;
    }

    public static java.sql.Date now() {
        java.sql.Date date = java.sql.Date.valueOf(LocalDate.now());
        return date;
    }

    public static java.sql.Date getBirthDay(String birthDay) {
        try {
            java.sql.Date date = java.sql.Date.valueOf(LocalDate.parse(birthDay));
            return date;
        } catch (Exception e) {
            return null;
        }
    }

    public static String getGio() {
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String formatted = current.format(formatter);
        return formatted;
    }
}
