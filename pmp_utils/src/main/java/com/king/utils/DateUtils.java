package com.king.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    // 日期转换为字符串
    public static String dateToString(Date date, String patten){
        SimpleDateFormat sdf = new SimpleDateFormat(patten);
        String format_date = sdf.format(date);
        return format_date;
    }

    // 字符串转换为日期
    public static Date stringToDate(String str, String patten) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(patten);
        Date date = sdf.parse(str);
        return date;
    }

}
