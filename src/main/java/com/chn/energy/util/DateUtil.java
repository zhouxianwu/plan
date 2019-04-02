package com.chn.energy.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhouxianwu on 2019/3/31.
 */
public class DateUtil {
    public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String date2Str(Date date){
        SimpleDateFormat format = new SimpleDateFormat(TIME_FORMAT);
        return format.format(date);
    }

    public static Date str2Date(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(TIME_FORMAT);
        return format.parse(date);
    }
}
