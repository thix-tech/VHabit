package com.sqchen.vhabit.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2017/3/15.
 */

public class DateStrUtil {
    public static SimpleDateFormat DAY_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static SimpleDateFormat MIN_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static SimpleDateFormat SEC_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static Date mDate = null;

    /**
     * 将日期抓换为字符串
     * @param format
     * @param dateStr
     * @return
     */
    public static Date changeStrToDate(SimpleDateFormat format,String dateStr) {
        try {
            mDate = format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return mDate;
    }

    public static String changeDateToStr(SimpleDateFormat format,Date date) {
        return format.format(date);
    }

    public static int getWeek(Date date) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTime(date);
        int week = mCalendar.get(Calendar.DAY_OF_WEEK) - 1;
        if(week < 0)
            week = 0;
        return week;
    }

}
