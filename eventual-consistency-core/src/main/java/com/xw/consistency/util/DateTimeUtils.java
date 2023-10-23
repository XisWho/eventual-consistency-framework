package com.xw.consistency.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {

    private static final String TIME_FORMAT_1 = "yyyy-MM-dd HH:mm:ss";

    public static String getNowDesc() {
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT_1);
        return sdf.format(new Date());
    }

    public static String getDateTimeDesc(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT_1);
        return sdf.format(date);
    }

    public static Date getDateTime(String desc) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT_1);
        return sdf.parse(desc);
    }

}
