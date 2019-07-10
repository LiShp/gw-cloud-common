package com.gw.cloud.common.base.util;


import com.gw.cloud.common.base.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author cgb
 */
public class DateUtil {

    private static final Log logger = LogFactory.getLog(DateUtils.class);

    /**
     * 默认时区类型：东八区
     */
    public static final String DEFAULT_TIME_ZONE_TYPE = "GMT+8";
    /**
     * 默认日期和时间格式
     */
    public static final String DEFAULT_FORMAT_PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";
    /**
     * 默认日期格式
     */
    public static final String DEFAULT_FORMAT_PATTERN_DATE = "yyyy-MM-dd";
    /**
     * 默认时间格式
     */
    public static final String DEFAULT_FORMAT_PATTERN_TIME = "HH:mm:ss";
    /**
     * 默认时间格式
     */
    public static final String DEFAULT_FORMAT_PATTERN_TIME_TWO = "HHmmss";

    /**
     * 日期和时间格式一："yyyyMMddHHmmss"
     */
    public static final String FORMAT_PATTERN_DATETIME_ONE = "yyyyMMddHHmmss";
    /**
     * 日期和时间格式二："yyyy/M/d HH:mm"
     */
    public static final String FORMAT_PATTERN_DATETIME_TWO = "yyyy/M/d HH:mm";
    /**
     * 日期格式一："yyyyMMdd"
     */
    public static final String FORMAT_PATTERN_DATE_ONE = "yyyyMMdd";

    public static Date currentDate() {
        return new Date();
    }

    public static String formatCurrentDate(String pattern) {
        if (pattern == null || pattern.trim().equals("")) {
            pattern = DEFAULT_FORMAT_PATTERN_DATE;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(currentDate());
    }

    /**
     * 日期相差多少天
     *
     * @param start
     * @param end
     * @return
     */
    public static long datePeriod(Date start, Date end) {
        Date startDay = day(start);
        Date endDay = day(end);
        if (startDay == null || endDay == null) {
            return -1;
        }
        return Math.abs((startDay.getTime() - endDay.getTime()) / (1000L * 60L * 60L * 24L));
    }

    /**
     * 返回天
     *
     * @param date
     * @return
     */
    public static Date day(Date date) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(dateFormat.format(date));
        } catch (ParseException e) {
            logger.error("DateUtils day() parse failed", e);
            throw new BusinessException("日期格式转换异常", null, false, false);
        }
    }

    /**
     * 计算日期后多少天
     *
     * @param date
     * @param days
     * @return
     */
    public static Date after(Date date, int days) {
        Calendar temCalendar = initCalendar(date);
        temCalendar.add(Calendar.DAY_OF_YEAR, days);
        return temCalendar.getTime();
    }

    /**
     * 计算周第几天,如果在date前,向前推进
     *
     * @param date
     * @param dayOfWeek
     * @return
     */
    public static Date dayOfWeek(Date date, int dayOfWeek, boolean isForward) {
        Calendar temCalendar = initCalendar(date);
        temCalendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        if (isForward && temCalendar.getTime().before(date)) {
            temCalendar.add(Calendar.DAY_OF_WEEK, 7);
        }
        return temCalendar.getTime();
    }

    public static Date dayOfPreviousWeek(Date date, int dayOfWeek) {
        Calendar temCalendar = initCalendar(date);
        temCalendar.add(Calendar.DAY_OF_WEEK, -7);
        return dayOfWeek(temCalendar.getTime(), dayOfWeek, false);
    }


    /**
     * 计算月底
     *
     * @param date
     * @return
     */
    public static Date endOfMonth(Date date) {
        Calendar temCalendar = initCalendar(date);
        temCalendar.set(Calendar.DAY_OF_MONTH,
                temCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return temCalendar.getTime();
    }

    /**
     * 计算月第几天
     *
     * @param date
     * @param dayOfMonth
     * @return
     */
    public static Date dayOfMonth(Date date, int dayOfMonth, boolean isForward) {
        Calendar temCalendar = initCalendar(date);
        int maxDayOfMonth = temCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int minDayOfMonth = temCalendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        if (dayOfMonth > maxDayOfMonth) {
            dayOfMonth = maxDayOfMonth;
        } else if (dayOfMonth < minDayOfMonth) {
            dayOfMonth = minDayOfMonth;
        }
        temCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        if (isForward && temCalendar.getTime().before(date)) {
            temCalendar.set(Calendar.DAY_OF_MONTH, maxDayOfMonth);
            temCalendar.add(Calendar.DAY_OF_MONTH, dayOfMonth);
        }

        return temCalendar.getTime();
    }

    public static Date dayOfPreviousMonth(Date date, int dayOfMonth) {
        Calendar temCalendar = initCalendar(date);
        temCalendar.add(Calendar.MONTH, -1);
        return dayOfMonth(temCalendar.getTime(), dayOfMonth, false);
    }

    private static Calendar initCalendar(Date date) {
        Calendar temCalendar = Calendar.getInstance();
        temCalendar.setTime(date);
        return temCalendar;
    }


    /**
     * 当前时间加？分钟后的时间String
     *
     * @param minute
     * @return
     */
    public static String getDateStrForAddMinute(int minute) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, minute);
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT_PATTERN_DATETIME);
        return sdf.format(now.getTimeInMillis());
    }

    /**
     * 计算时间加多少秒后的时间Date
     *
     * @param date
     * @param second
     * @return
     */
    public static Date afterSecond(Date date, int second) {
        Calendar temCalendar = initCalendar(date);
        temCalendar.add(Calendar.SECOND, second);
        return temCalendar.getTime();
    }

    /**
     * 将日期格式的字符串转换为长整型
     *
     * @param date
     * @return
     */
    public static long dateStrToLong(String date) throws ParseException {
        if (StringUtils.isNotBlank(date)) {
            SimpleDateFormat sf = new SimpleDateFormat(DEFAULT_FORMAT_PATTERN_DATETIME);
            return sf.parse(date).getTime();
        }
        return 0L;
    }

    /**
     * 将字符串转换为日期
     *
     * @param str 字符串
     * @param dateFormat 日期格式
     * @return 日期
     */
    public static Date stringToDate(String str, String dateFormat) {

        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
        }
        return date;
    }

    /**
     * 将日期转换为字符串
     *
     * @param date 日期
     * @param dateFormat 日期格式
     * @return 字符串
     */
    public static String dateToString(Date date, String dateFormat) {

        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        return format.format(date);
    }

    /**
     * 获取两个时间的间隔
     *
     * @param start 开始时间
     * @param end 结束时间
     * @return 返回相差毫秒数
     */
    public static long getTimePeriod(Date start, Date end) {
        return end.getTime() - start.getTime();
    }
}
