package com.github.infrastructure.helper;

import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * @author EalenXie Created on 2019/6/5 10:42.
 * 日期工具类
 */
public enum DateUtils {

    ;

    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    private static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final ZoneId DEFAULT_ZONE_ID = ZoneId.systemDefault();
    private static final DateTimeFormatter DEFAULT_DATE_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);
    private static final DateTimeFormatter DEFAULT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT);
    /**
     * S 秒
     */
    private static final int S = 1000;
    /**
     * MIN 分钟
     */
    private static final int MIN = S * 60;
    /**
     * H 小时
     */
    private static final int H = MIN * 60;
    /**
     * D 天
     */
    private static final int D = H * 24;

    /**
     * 默认 字符串日期格式 yyyy-MM-dd HH:mm:ss
     */
    public static Date ofDate(String date) {
        try {
            return ofDate(date, DEFAULT_ZONE_ID, DEFAULT_DATE_TIME_FORMATTER);
        } catch (DateTimeParseException exception) {
            return ofDate(date, DEFAULT_ZONE_ID, DEFAULT_DATE_FORMATTER);
        }
    }


    /**
     * @param date   日期
     * @param format 日期格式 (至少要精确到天)
     */
    public static Date ofDate(String date, String format) {
        return ofDate(date, DEFAULT_ZONE_ID, DateTimeFormatter.ofPattern(format));
    }

    public static Date ofDate(String date, ZoneId zoneId, DateTimeFormatter formatter) {
        return Date.from(LocalDate.parse(date, formatter).atStartOfDay().atZone(zoneId).toInstant());
    }

    public static String formatDate(@NonNull Date date) {
        return format(date, DEFAULT_ZONE_ID, DEFAULT_DATE_FORMATTER);
    }

    public static String formatDateTime(@NonNull Date date) {
        return format(date, DEFAULT_ZONE_ID, DEFAULT_DATE_TIME_FORMATTER);
    }

    /**
     * 日期类型按自定义格式进行字符串化
     */
    public static String format(@NonNull Date date, @NonNull String format) {
        return format(date, DEFAULT_ZONE_ID, DateTimeFormatter.ofPattern(format));
    }

    public static String format(@NonNull Date date, ZoneId zoneId, DateTimeFormatter formatter) {
        return LocalDateTime.ofInstant(date.toInstant(), zoneId).format(formatter);
    }

    /**
     * 获得某天最大时间 23:59:59:999
     */
    public static Date getEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        setCalendarTime(calendar, 23, 59, 59, 999);
        return calendar.getTime();
    }

    /**
     * 获得某天最小时间  2019-04-10 00:00:00
     */
    public static Date getStartOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        setCalendarTime(calendar, 0, 0, 0, 0);
        return calendar.getTime();
    }

    /**
     * 获取当月最后一天
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 获取当月第一天
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 日期是否是今天
     */
    public static boolean isCurrentDate(@NonNull Date date) {
        return date.after(Date.from(LocalDate.now().atStartOfDay(DEFAULT_ZONE_ID).toInstant())) &&
                date.before(Date.from(LocalDate.now().plusDays(1).atStartOfDay(DEFAULT_ZONE_ID).toInstant()));
    }

    /**
     * 获取任意时间 前后天的时间
     *
     * @param calendarEnum 日期枚举 比如 Calendar.MONTH
     * @param count        任意加减天数
     * @return 获取任意时间
     */
    public static String anyTimeByDay(Date date, int calendarEnum, int count) {
        return LocalDateTime.ofInstant(getYourCalendar(date, calendarEnum, count).toInstant(), DEFAULT_ZONE_ID).format(DEFAULT_DATE_TIME_FORMATTER);
    }

    /**
     * 获取任意时间 前后天的时间
     *
     * @param calendarEnum 日期枚举 比如 Calendar.MONTH
     * @param count        任意加减天数
     * @return 获取任意时间
     */
    public static String anyTimeByCurrentDay(int calendarEnum, int count) {
        return anyTimeByDay(new Date(), calendarEnum, count);
    }

    /**
     * @param calendarEnum 日期枚举 比如 Calendar.MONTH Calendar.DAY
     * @param count        任意加减天数
     * @return 获取任意日期
     */
    public static Date anyDateByCurrentDay(int calendarEnum, int count) {
        return anyDateByDay(new Date(), calendarEnum, count);
    }

    /**
     * @param calendarEnum 日期枚举 比如 Calendar.MONTH
     * @param count        任意加减天数
     * @return 获取任意日期
     */
    public static Date anyDateByDay(Date date, int calendarEnum, int count) {
        return Date.from(getYourCalendar(date, calendarEnum, count).toInstant());
    }

    /**
     * @param costTime 消耗的毫秒数
     * @return 将毫秒数换算成消耗的时间 如 : 90061001 换算成 1day 1hour 1min 1second 1ms
     */
    public static String costTimeByMs(long costTime) {
        int yearDay = 365;
        int monthDay = 30;
        long day = costTime / D;
        long hour = (costTime - day * D) / H;
        long minute = (costTime - day * D - hour * H) / MIN;
        long second = (costTime - day * D - hour * H - minute * MIN) / S;
        long milliSecond = costTime - day * D - hour * H - minute * MIN - second * S;
        StringBuilder result = new StringBuilder();
        if (day != 0) {
            //每年按平均365天算
            if (day >= yearDay) {
                long year = day / yearDay;
                result.append(year).append("year ");
                day = day - year * yearDay;
            }
            //每月按平均30天算
            if (day < yearDay && day >= monthDay) {
                long month = day / monthDay;
                result.append(month).append("month ");
                day = day - month * monthDay;
            }
            if (day != 0 && day < monthDay) result.append(day).append("day ");
        }
        if (hour != 0) result.append(hour).append("hour ");
        if (minute != 0) result.append(minute).append("min ");
        if (second != 0) result.append(second).append("second ");
        if (milliSecond != 0) result.append(milliSecond).append("ms ");
        return result.toString();
    }

    /**
     * @param startTime 起始时间
     * @param endTime   终止时间
     * @return 将毫秒数换算成消耗的时间 如 : 90061001 换算成 1day 1hour 1min 1second 1ms
     */
    public static String costTimeByMs(long startTime, long endTime) {
        return costTimeByMs(endTime - startTime);
    }

    /**
     * @param date  日期
     * @param field 域值
     * @param value 附加值
     * @return 获取你想要的Calendar
     */
    private static Calendar getYourCalendar(Date date, int field, int value) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(field, calendar.get(field) + value);
        return calendar;
    }


    /**
     * 设置calender 时间
     *
     * @param calendar    calender对象
     * @param hour        小时
     * @param minute      分钟
     * @param second      秒
     * @param millisecond 毫秒
     */
    private static void setCalendarTime(Calendar calendar, int hour, int minute, int second, int millisecond) {
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, millisecond);
    }
}
