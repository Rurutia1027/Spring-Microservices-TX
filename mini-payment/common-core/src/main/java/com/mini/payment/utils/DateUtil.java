/*
 * Copyright 2015-2102 RonCoo(http://www.roncoo.com) Group.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mini.payment.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {

    public static final String TIME_WITH_MINUTE_PATTERN = "HH:mm";

    // how many milliseconds in a day
    public static final long DAY_MILLI = 24 * 60 * 60 * 1000;

    public final static int LEFT_OPEN_RIGHT_OPEN = 1;
    public final static int LEFT_CLOSE_RIGHT_OPEN = 2;
    public final static int LEFT_OPEN_RIGHT_CLOSE = 3;
    public final static int LEFT_CLOSE_RIGHT_CLOSE = 4;
    public final static int COMP_MODEL_DATE = 1;
    public final static int COMP_MODEL_TIME = 2;
    public final static int COMP_MODEL_DATETIME = 3;

    private static Log logger = LogFactory.getLog(DateUtil.class);
    public static String DATE_FORMAT_DATEONLY = "yyyy-MM-dd";
    public static String DATE_FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static SimpleDateFormat sdfDateTime = new SimpleDateFormat(DateUtil.DATE_FORMAT_DATETIME);
    // Global SimpleDateFormat object
    public static SimpleDateFormat sdfDateOnly = new SimpleDateFormat(DateUtil.DATE_FORMAT_DATEONLY);
    public static final SimpleDateFormat SHORTDATEFORMAT = new SimpleDateFormat("yyyyMMdd");
    public static final SimpleDateFormat SHORT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat LONG_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat HMS_FORMAT = new SimpleDateFormat("HH:mm:ss");
    public static final SimpleDateFormat formatTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Date parseDate(String str, String parsePatterns) {
        try {
            return org.apache.commons.lang3.time.DateUtils.parseDate(str,
                    new String[]{parsePatterns});
        } catch (ParseException e) {
            logger.error(e);
            return null;
        }
    }

    /**
     * 根据单位字段比较两个日期
     *
     * @param date      date1
     * @param otherDate date2
     * @param withUnit  unit from Calendar
     * @return
     */
    public static int compareDate(Date date, Date otherDate, int withUnit) {
        Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(date);
        Calendar otherDateCal = Calendar.getInstance();
        otherDateCal.setTime(otherDate);

        switch (withUnit) {
            case Calendar.YEAR:
                dateCal.clear(Calendar.MONTH);
                otherDateCal.clear(Calendar.MONTH);
            case Calendar.MONTH:
                dateCal.set(Calendar.DATE, 1);
                otherDateCal.set(Calendar.DATE, 1);
            case Calendar.DATE:
                dateCal.set(Calendar.HOUR_OF_DAY, 0);
                otherDateCal.set(Calendar.HOUR_OF_DAY, 0);
            case Calendar.HOUR:
                dateCal.clear(Calendar.MINUTE);
                otherDateCal.clear(Calendar.MINUTE);
            case Calendar.MINUTE:
                dateCal.clear(Calendar.SECOND);
                otherDateCal.clear(Calendar.SECOND);
            case Calendar.SECOND:
                dateCal.clear(Calendar.MILLISECOND);
                otherDateCal.clear(Calendar.MILLISECOND);
            case Calendar.MILLISECOND:
                break;
            default:
                throw new IllegalArgumentException("withUnit 单位字段 " + withUnit + " 不合法！！");
        }
        return dateCal.compareTo(otherDateCal);
    }

    /**
     * @param date      date1
     * @param otherDate date2
     * @param withUnit  time unit from Calendar
     * @return
     */
    public static int compareTime(Date date, Date otherDate, int withUnit) {
        Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(date);
        Calendar otherDateCal = Calendar.getInstance();
        otherDateCal.setTime(otherDate);

        dateCal.clear(Calendar.YEAR);
        dateCal.clear(Calendar.MONTH);
        dateCal.set(Calendar.DATE, 1);
        otherDateCal.clear(Calendar.YEAR);
        otherDateCal.clear(Calendar.MONTH);
        otherDateCal.set(Calendar.DATE, 1);
        switch (withUnit) {
            case Calendar.HOUR:
                dateCal.clear(Calendar.MINUTE);
                otherDateCal.clear(Calendar.MINUTE);
            case Calendar.MINUTE:
                dateCal.clear(Calendar.SECOND);
                otherDateCal.clear(Calendar.SECOND);
            case Calendar.SECOND:
                dateCal.clear(Calendar.MILLISECOND);
                otherDateCal.clear(Calendar.MILLISECOND);
            case Calendar.MILLISECOND:
                break;
            default:
                throw new IllegalArgumentException("withUnit 单位字段 " + withUnit + " 不合法！！");
        }
        return dateCal.compareTo(otherDateCal);
    }

    /**
     * fetch current timestamp in milliseconds
     *
     * @return
     */
    public static long nowTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * fetch current timestamp
     *
     * @return
     */
    public static Timestamp nowTimeStamp() {
        return new Timestamp(nowTimeMillis());
    }

    /**
     * fetch current date in format yyyy-MM-dd
     */
    public static String getReqDate() {
        return SHORTDATEFORMAT.format(new Date());
    }

    /**
     * fetch given date timestamp in string value in format yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String getReqDate(Date date) {
        return SHORT_DATE_FORMAT.format(date);
    }

    /**
     * fetch given date timestamp in string value in format yyyyMMdd
     *
     * @param date
     * @return
     */
    public static String getReqDateyyyyMMdd(Date date) {
        return SHORTDATEFORMAT.format(date);
    }

    /**
     * fetch timestamp in string by given timestamp format as yyyy-MM-dd
     *
     * @param tmp
     * @return
     */
    public static String TimestampToDateStr(Timestamp tmp) {
        return SHORT_DATE_FORMAT.format(tmp);
    }

    /**
     * fetch current timestamp in format of HH:mm:ss
     *
     * @return
     */
    public static String getReqTime() {
        return HMS_FORMAT.format(new Date());
    }

    /**
     * fetch timestamp in default format
     *
     * @param date
     * @return
     */
    public static String getTimeStampStr(Date date) {
        return LONG_DATE_FORMAT.format(date);
    }

    /**
     * fetch date in long format
     *
     * @return
     */
    public static String getLongDateStr() {
        return LONG_DATE_FORMAT.format(new Date());
    }

    public static String getLongDateStr(Timestamp time) {
        return LONG_DATE_FORMAT.format(time);
    }

    /**
     * fetch date in short format
     *
     * @param date
     * @return
     */
    public static String getShortDateStr(Date date) {
        return SHORT_DATE_FORMAT.format(date);
    }

    public static String getShortDateStr() {
        return SHORT_DATE_FORMAT.format(new Date());
    }

    /**
     * calculate date value by given date and second value
     *
     * @param date
     * @param second
     * @return
     */
    public static Date addSecond(Date date, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * calculate date value by given date and minute value
     *
     * @param date
     * @param minute
     * @return
     */
    public static Date addMinute(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    /**
     * calculate date value by providing date and after given hour value
     *
     * @param date
     * @param hour
     * @return
     */
    public static Date addHour(Date date, int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hour);
        return calendar.getTime();
    }

    /**
     * get start timestamp value by given date value
     *
     * @param date
     * @return
     */
    public static Date getDayStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * get end timestamp value by given date value
     *
     * @param date
     * @return
     */
    public static Date getDayEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MILLISECOND, -1);
        return calendar.getTime();
    }

    /**
     * calculate date value after given days
     *
     * @param date
     * @param day
     * @return
     */
    public static Date addDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    /**
     * Get end date value of the given date's month
     *
     * @param date
     * @return
     */
    public static Date getMonthEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.MILLISECOND, -1);
        return calendar.getTime();
    }

    public static Date addYear(Date date, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, 365 * year);
        return calendar.getTime();
    }

    public static Timestamp strToTimestamp(String dateStr) {
        return Timestamp.valueOf(dateStr);
    }

    public static Timestamp strToTimestamp(Date date) {
        return Timestamp.valueOf(formatTimestamp.format(date));
    }

    public static Timestamp getCurTimestamp() {
        return Timestamp.valueOf(formatTimestamp.format(new Date()));
    }

    /**
     * Fetch day number by given [startTs, endTs]
     */
    public static long daysBetween(Timestamp startTs, Timestamp endTs) throws IllegalArgumentException {
        if (endTs.before(startTs)) {
            throw new IllegalArgumentException("endTs < starTs illegal argument.");
        }
        return (endTs.getTime() - startTs.getTime()) / DAY_MILLI;
    }

    /**
     * Return current timestamp's millisecond
     */
    public static Timestamp getSysDateTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * Converted String data in format of 'YYYY/MM/DD' to java.sql.Timestamp type.
     *
     * @param sDate Date string
     */
    public static Timestamp toSqlTimestamp(String sDate) {
        if (sDate == null) {
            return null;
        }
        if (sDate.length() != DateUtil.DATE_FORMAT_DATEONLY.length()
                && sDate.length() != DateUtil.DATE_FORMAT_DATETIME.length()) {
            return null;
        }
        return toSqlTimestamp(sDate,
                sDate.length() == DateUtil.DATE_FORMAT_DATEONLY.length()
                        ? DateUtil.DATE_FORMAT_DATEONLY
                        : DateUtil.DATE_FORMAT_DATETIME);

    }

    /**
     * Converted String that formated in YYYY/MM/DD hh::mm:ss into java.sql.Timestamp
     *
     * @param sDate Date string
     * @param sFmt  Date format DATE_FORMAT_DATEONLY/DATE_FORMAT_DATETIME
     */
    public static Timestamp toSqlTimestamp(String sDate, String sFmt) {
        String temp = null;
        if (sDate == null || sFmt == null) {
            return null;
        }
        if (sDate.length() != sFmt.length()) {
            return null;
        }
        if (sFmt.equals(DateUtil.DATE_FORMAT_DATETIME)) {
            temp = sDate.replace('/', '-');
            temp = temp + ".000000000";
        } else if (sFmt.equals(DateUtil.DATE_FORMAT_DATEONLY)) {
            temp = sDate.replace('/', '-');
            temp = temp + " 00:00:00.000000000";
        } else {
            return null;
        }
        return Timestamp.valueOf(temp);
    }

    /**
     * Return current system timestamp in String type, and return String type satisfy
     * pattern YYYY/MM/DD HH24:MI:SS
     */
    public static String getSysDateTimeString() {
        return toString(new Date(System.currentTimeMillis()), DateUtil.sdfDateTime);
    }

    /**
     * Converted given format pattern java.util.Date to String
     *
     * @param dt   java.util.Date instance
     * @param sFmt Date format , DATE_FORMAT_DATEONLY or DATE_FORMAT_DATETIME
     */
    public static String toString(Date dt, String sFmt) {
        if (dt == null || sFmt == null || "".equals(sFmt)) {
            return "";
        }
        return toString(dt, new SimpleDateFormat(sFmt));
    }

    /**
     * Convert a java.util.Date instance to a String using a specified SimpleDateFormat.
     *
     * @param dt        java.util.Date instance
     * @param formatter SimpleDateFormat Instance
     */
    private static String toString(Date dt, SimpleDateFormat formatter) {
        String sRet = null;

        try {
            sRet = formatter.format(dt).toString();
        } catch (Exception e) {
            logger.error(e);
            sRet = null;
        }

        return sRet;
    }

    /**
     * Convert java.sql.Timestamp into String with patter YYYY/MM/DD HH24:Mi
     *
     * @param dt java.sql.Timestamp instance
     * @return
     */
    public static String toSqlTimestampString2(Timestamp dt) {
        if (dt == null) {
            return null;
        }
        String temp = toSqlTimestampString(dt, DateUtil.DATE_FORMAT_DATETIME);
        return temp.substring(0, 16);
    }

    public static String toString(Timestamp dt) {
        return dt == null ? "" : toSqlTimestampString2(dt);
    }

    /**
     * Convert java.sql.Timestamp into String using specified format.
     *
     * @param dt   java.sql.Timestamp instance
     * @param sFmt Date 格式，DATE_FORMAT_DATEONLY/DATE_FORMAT_DATETIME/
     *             DATE_FORMAT_SESSION
     */
    public static String toSqlTimestampString(Timestamp dt, String sFmt) {
        String temp = null;
        String out = null;
        if (dt == null || sFmt == null) {
            return null;
        }
        temp = dt.toString();
        if (sFmt.equals(DateUtil.DATE_FORMAT_DATETIME) || // "YYYY/MM/DD
                // HH24:MI:SS"
                sFmt.equals(DateUtil.DATE_FORMAT_DATEONLY)) { // YYYY/MM/DD
            temp = temp.substring(0, sFmt.length());
            out = temp.replace('/', '-');
        }
        return out;
    }

    public static int getWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int w = cal.get(Calendar.DAY_OF_WEEK);
        return w;
    }

    /**
     * Convert given timestamp into yyyy-MM-dd format String type.
     *
     * @param timestamp
     * @return createTimeStr yyyy-MM-dd 时间
     */
    public static String timestampToStringYMD(Timestamp timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.DATE_FORMAT_DATEONLY);
        String createTimeStr = sdf.format(timestamp);
        return createTimeStr;
    }

    /**
     * Verify whether given timestamp locates in the same time zone
     *
     * @param now
     * @param start
     * @param end
     * @param model
     * @return boolean
     */
    public static boolean isBetween(Date now, Date start, Date end, int model) {
        return isBetween(now, start, end, model, LEFT_OPEN_RIGHT_OPEN);
    }

    /**
     * Verify given date is located in the given time range [start, end]
     *
     * @param date
     * @param start
     * @param end
     * @param interModel
     * @param compModel
     * @return
     */
    public static boolean isBetween(Date date, Date start, Date end, int interModel, int compModel) {
        if (date == null || start == null || end == null) {
            throw new IllegalArgumentException("parameters cannot be null");
        }
        SimpleDateFormat format = null;
        switch (compModel) {
            case COMP_MODEL_DATE: {
                format = new SimpleDateFormat("yyyyMMdd");
                break;
            }
            case COMP_MODEL_TIME: {
                format = new SimpleDateFormat("HHmmss");
                break;
            }
            case COMP_MODEL_DATETIME: {
                format = new SimpleDateFormat("yyyyMMddHHmmss");
                break;
            }
            default: {
                throw new IllegalArgumentException(String.format("date compare model [%d] " +
                                "illegal",
                        compModel));
            }
        }
        long dateNumber = Long.parseLong(format.format(date));
        long startNumber = Long.parseLong(format.format(start));
        long endNumber = Long.parseLong(format.format(end));
        switch (interModel) {
            case LEFT_OPEN_RIGHT_OPEN: {
                if (dateNumber <= startNumber || dateNumber >= endNumber) {
                    return false;
                } else {
                    return true;
                }
            }
            case LEFT_CLOSE_RIGHT_OPEN: {
                if (dateNumber < startNumber || dateNumber >= endNumber) {
                    return false;
                } else {
                    return true;
                }
            }
            case LEFT_OPEN_RIGHT_CLOSE: {
                if (dateNumber <= startNumber || dateNumber > endNumber) {
                    return false;
                } else {
                    return true;
                }
            }
            case LEFT_CLOSE_RIGHT_CLOSE: {
                if (dateNumber < startNumber || dateNumber > endNumber) {
                    return false;
                } else {
                    return true;
                }
            }
            default: {
                throw new IllegalArgumentException(String.format("date compare model [%d] " +
                                "illegal",
                        interModel));
            }
        }
    }

    /**
     * Fetch given date week's init timestamp.
     *
     * @param date
     * @return
     */
    public static Date getWeekStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.get(Calendar.WEEK_OF_YEAR);
        int firstDay = calendar.getFirstDayOfWeek();
        calendar.set(Calendar.DAY_OF_WEEK, firstDay);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * Fetch current week's end date.
     *
     * @param date
     * @return
     */
    public static Date getWeekEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.get(Calendar.WEEK_OF_YEAR);
        int firstDay = calendar.getFirstDayOfWeek();
        calendar.set(Calendar.DAY_OF_WEEK, 8 - firstDay);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * Fetch the first date of the given date's month.
     *
     * @param date
     * @return
     */
    public static Date getMonthStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * Fetch the start day of the given date's year
     *
     * @param date
     * @return
     */
    public static Date getYearStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * Fetch given date's year last day.
     *
     * @param date
     * @return
     */
    public static Date getYearEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * Fetch how many days in given date's month.
     *
     * @param date
     * @return
     */
    public static int getDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * Fetch the first day of the given date's month
     *
     * @param date
     * @return
     */
    public static Date getFirstDateOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    /**
     * Fetch given month last day
     *
     * @param date
     * @return
     */
    public static Date getLastDateOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    /**
     * Fetch given date's first day
     *
     * @param date
     * @return
     */
    public static Date getSeasonStart(Date date) {
        return getDayStart(getFirstDateOfMonth(getSeasonDate(date)[0]));
    }

    /**
     * Fetch given date's last day
     *
     * @param date
     * @return
     */
    public static Date getSeasonEnd(Date date) {
        return getDayEnd(getLastDateOfMonth(getSeasonDate(date)[2]));
    }

    /**
     * Fetch quarter month value in array.
     *
     * @param date
     * @return
     */
    public static Date[] getSeasonDate(Date date) {
        Date[] season = new Date[3];

        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int nSeason = getSeason(date);
        if (nSeason == 1) {// Quarter 1
            c.set(Calendar.MONTH, Calendar.JANUARY);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.FEBRUARY);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.MARCH);
            season[2] = c.getTime();
        } else if (nSeason == 2) {// Quarter 2
            c.set(Calendar.MONTH, Calendar.APRIL);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.MAY);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.JUNE);
            season[2] = c.getTime();
        } else if (nSeason == 3) {// Quarter 3
            c.set(Calendar.MONTH, Calendar.JULY);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.AUGUST);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.SEPTEMBER);
            season[2] = c.getTime();
        } else if (nSeason == 4) {// Quarter 4
            c.set(Calendar.MONTH, Calendar.OCTOBER);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.NOVEMBER);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.DECEMBER);
            season[2] = c.getTime();
        }
        return season;
    }

    /**
     * Return quarter value by the given date
     *
     * @param date
     * @return
     */
    public static int getSeason(Date date) {

        int season = 0;

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
                season = 1;
                break;
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                season = 2;
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                season = 3;
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                season = 4;
                break;
            default:
                break;
        }
        return season;
    }

    /**
     * Convert string to date
     *
     * @param dateString
     * @return
     */
    public static Date StringToDate(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            logger.error(e);
        }
        return date;
    }

    /**
     * Returns the day of the week for the given date, where Sunday is considered the first day of the week.
     *
     * <p>For example:
     * <ul>
     *   <li>Sunday → 1</li>
     *   <li>Monday → 2</li>
     *   <li>...</li>
     *   <li>Saturday → 7</li>
     * </ul>
     *
     * @param date The input date.
     * @return An integer from 1 (Sunday) to 7 (Saturday) representing the day of the week.
     */
    public static int getWeekIndex(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * Return date that given days before today timestamp,
     * and the date format will follow yyyy/MM/dd
     */
    public static Date subDays(int days) {
        Date date = addDay(new Date(), -days);
        String dateStr = getReqDate(date);
        Date date1 = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date1 = sdf.parse(dateStr);
        } catch (ParseException e) {
            logger.error(e);
        }
        return date1;
    }

    /**
     * Checks whether the start and end time exceed a specified number of days from the current time.
     *
     * <p>For example, if the limit is 90 days, this method ensures that both startTime and endTime
     * are within 90 days from now.</p>
     *
     * @param startDate start date
     * @param endDate   end date
     * @param interval  interval unit
     * @param dateUnit  given interval unit
     * @return
     */

    public static boolean isOverIntervalLimit(Date startDate, Date endDate, int interval, int dateUnit) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(dateUnit, interval * (-1));
        Date curDate = getDayStart(cal.getTime());
        if (getDayStart(startDate).compareTo(curDate) < 0 || getDayStart(endDate).compareTo(curDate) < 0) {
            return true;
        }
        return false;
    }

    /**
     * Verify start date and end date are located in the given interval values
     *
     * @param startDate start date
     * @param endDate   end date
     * @param interval  interval of days
     * @return
     */
    public static boolean isOverIntervalLimit(Date startDate, Date endDate, int interval) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, interval * (-1));
        Date curDate = getDayStart(cal.getTime());
        if (getDayStart(startDate).compareTo(curDate) < 0 || getDayStart(endDate).compareTo(curDate) < 0) {
            return true;
        }
        return false;
    }

    /**
     * Verify start date and end date are located in the given interval values
     *
     * @param startDateStr start date
     * @param endDateStr   end date
     * @param interval     interval
     * @return
     */
    public static boolean isOverIntervalLimit(String startDateStr, String endDateStr, int interval) {
        Date startDate = null;
        Date endDate = null;
        startDate = DateUtil.parseDate(startDateStr, DateUtil.DATE_FORMAT_DATEONLY);
        endDate = DateUtil.parseDate(endDateStr, DateUtil.DATE_FORMAT_DATEONLY);
        if (startDate == null || endDate == null) {
            return true;
        }

        return isOverIntervalLimit(startDate, endDate, interval);
    }

    /**
     * Passing timestamp string and format, return corresponding Date
     *
     * @param src     formatted timestamp in string
     * @param pattern format pattern
     * @return Date
     */
    public static Date getDateFromString(String src, String pattern) {
        SimpleDateFormat f = new SimpleDateFormat(pattern);
        try {
            return f.parse(src);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Fetch Quarter
     *
     * @param date
     * @return
     */
    @SuppressWarnings("deprecation")
    public static int getQuarter(Date date) {
        if (date.getMonth() == 0 || date.getMonth() == 1 || date.getMonth() == 2) {
            return 1;
        } else if (date.getMonth() == 3 || date.getMonth() == 4 || date.getMonth() == 5) {
            return 2;
        } else if (date.getMonth() == 6 || date.getMonth() == 7 || date.getMonth() == 8) {
            return 3;
        } else if (date.getMonth() == 9 || date.getMonth() == 10 || date.getMonth() == 11) {
            return 4;
        } else {
            return 0;

        }
    }

    /**
     * Fetch default timestamp format 'yyyy-MM-dd HH:mm:ss' by giving date
     *
     * @param date
     * @return String
     */
    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    /**
     * Fetch current timestamp in format of yyyy-MM-dd
     *
     * @param
     * @return String
     */
    public static String today() {
        return formatDate(new Date(), "yyyy-MM-dd");
    }

    /**
     * Fetch current timestamp in format of yyyyMMddhhmmssSSS
     *
     * @param
     * @return String
     */
    public static String currentTime() {
        return formatDate(new Date(), "yyyyMMddhhmmssSSS");
    }

    /**
     * Fetch given date's format by providing the format as parameter
     *
     * @param date
     * @param format
     * @return String
     */
    public static String formatDate(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    /**
     * Fetch yesterday's timestamp format
     *
     * @return Date
     */
    public static String getYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return formatDate(calendar.getTime(), "yyyy-MM-dd");
    }

    /**
     * Verify whether current timestamp is located in the given time range [startTime, endTime]
     *
     * @param startTime
     * @return boolean
     */
    public static boolean isInBetweenTimes(String startTime, String endTime) {
        Date nowTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String time = sdf.format(nowTime);
        if (time.compareTo(startTime) >= 0 && time.compareTo(endTime) <= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Convert string into date
     *
     * @param dateStr
     * @return
     */
    public static Date getDateByStr(String dateStr) {
        SimpleDateFormat formatter = null;
        if (dateStr == null) {
            return null;
        } else if (dateStr.length() == 10) {
            formatter = new SimpleDateFormat("yyyy-MM-dd");
        } else if (dateStr.length() == 16) {
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        } else if (dateStr.length() == 19) {
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else if (dateStr.length() > 19) {
            dateStr = dateStr.substring(0, 19);
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else {
            return null;
        }
        try {
            return formatter.parse(dateStr);
        } catch (ParseException e) {
            logger.error(e);
            return null;
        }
    }

    /**
     * Returns the date that is offset by the specified number of days from today.
     *
     * @param days The number of days to offset from the current date. Can be negative.
     * @return The resulting Date object.
     */
    public static Date getDate(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    /**
     * Calculate the maximum timestamp by the given date.
     *
     * @param dt
     * @return
     */
    public static Date getMaxTime(Date dt) {

        Date dt1 = null;
        Calendar ca = Calendar.getInstance();
        ca.setTime(dt);
        ca.add(Calendar.DAY_OF_MONTH, 1);
        dt1 = ca.getTime();
        dt1 = DateUtil.getMinTime(dt1);
        ca.setTime(dt1);
        ca.add(Calendar.SECOND, -1);
        dt1 = ca.getTime();
        return dt1;
    }

    /**
     * Calculate the minimum timestamp by the given date.
     *
     * @param dt
     * @return
     */
    public static Date getMinTime(Date dt) {
        Date dt1 = null;
        dt1 = DateUtil.getDateByStr(DateUtil.formatDate(dt, "yyyy-MM-dd"));
        return dt1;
    }

    /**
     * Get the last day of the given date's month.
     *
     * @param date
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Date getLastDayOfMonth(Date date) {
        Calendar cDay1 = Calendar.getInstance();
        cDay1.setTime(date);
        int lastDay = cDay1.getActualMaximum(Calendar.DAY_OF_MONTH);
        Date lastDate = cDay1.getTime();
        lastDate.setDate(lastDay);
        return lastDate;
    }

    /**
     * Calculate the first date in the given month.
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
        return calendar.getTime();
    }

    /**
     * Calculate the first date in the given month's previous month.
     *
     * @return
     */
    public static Date getPreviousMonthFirstDay() {
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1);
        lastDate.add(Calendar.MONTH, -1);
        return getMinTime(lastDate.getTime());
    }

    /**
     * Gets the last day of the previous month for the given date.
     *
     * @return
     */
    public static Date getPreviousMonthLastDay() {
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1);
        lastDate.add(Calendar.DATE, -1);
        return getMinTime(lastDate.getTime());
    }

    /**
     * Return how many days in given two dates.
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static long getDateDiff(String startDate, String endDate) {
        long diff = 0;
        try {
            Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
            Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);

            diff = (date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000) > 0 ?
                    (date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000)
                    : (date2.getTime() - date1.getTime()) / (24 * 60 * 60 * 1000);
        } catch (ParseException e) {
        }
        return diff;
    }

    /**
     * Return how many days in the given to dates
     *
     * @param date1
     * @param date2
     */
    public static long getDateDiff(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return 0L;
        }
        long diff = (date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000) > 0 ? (date1.getTime() - date2
                .getTime()) / (24 * 60 * 60 * 1000) : (date2.getTime() - date1.getTime()) / (24 * 60 * 60 * 1000);
        return diff;
    }

    /**
     * Calculates the difference in years between two dates.
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int getYearDiff(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return 0;
        }

        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        int year1 = calendar1.get(Calendar.YEAR);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        int year2 = calendar2.get(Calendar.YEAR);

        return Math.abs(year1 - year2);
    }

    /**
     * Fetch two given dates' milliseconds
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long getTimeDiff(Date date1, Date date2) {
        if (date1 == null || date1 == null) {
            return 0L;
        }
        long diff = (date1.getTime() - date2.getTime()) > 0 ? (date1.getTime() - date2
                .getTime()) : (date2.getTime() - date1.getTime());
        return diff;
    }

    public static boolean isSameWeekWithToday(Date date) {

        if (date == null) {
            return false;
        }

        Calendar todayCal = Calendar.getInstance();
        Calendar dateCal = Calendar.getInstance();

        todayCal.setTime(new Date());
        dateCal.setTime(date);
        int subYear = todayCal.get(Calendar.YEAR) - dateCal.get(Calendar.YEAR);
        // subYear==0, means locates in the same year
        if (subYear == 0) {
            if (todayCal.get(Calendar.WEEK_OF_YEAR) == dateCal.get(Calendar.WEEK_OF_YEAR))
                return true;
        } else if (subYear == 1 && dateCal.get(Calendar.MONTH) == 11 && todayCal.get(Calendar.MONTH) == 0) {
            if (todayCal.get(Calendar.WEEK_OF_YEAR) == dateCal.get(Calendar.WEEK_OF_YEAR))
                return true;
        } else if (subYear == -1 && todayCal.get(Calendar.MONTH) == 11 && dateCal.get(Calendar.MONTH) == 0) {
            if (todayCal.get(Calendar.WEEK_OF_YEAR) == dateCal.get(Calendar.WEEK_OF_YEAR))
                return true;
        }
        return false;
    }

    /**
     * getStrFormTime:
     *
     * @param format
     * @param date
     * @return
     */
    public static String getStrFormTime(String format, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 获取几天内日期 return 2014-5-4、2014-5-3
     */
    public static List<String> getLastDays(int countDay) {
        List<String> listDate = new ArrayList<String>();
        for (int i = 0; i < countDay; i++) {
            listDate.add(DateUtil.getReqDateyyyyMMdd(DateUtil.getDate(-i)));
        }
        return listDate;
    }

    /**
     * format given date
     *
     * @param date
     * @return
     */
    public static Date dateFormat(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date value = new Date();

        try {
            value = sdf.parse(sdf.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return value;

    }

    public static boolean isSameDayWithToday(Date date) {

        if (date == null) {
            return false;
        }

        Calendar todayCal = Calendar.getInstance();
        Calendar dateCal = Calendar.getInstance();

        todayCal.setTime(new Date());
        dateCal.setTime(date);
        int subYear = todayCal.get(Calendar.YEAR) - dateCal.get(Calendar.YEAR);
        int subMouth = todayCal.get(Calendar.MONTH) - dateCal.get(Calendar.MONTH);
        int subDay = todayCal.get(Calendar.DAY_OF_MONTH) - dateCal.get(Calendar.DAY_OF_MONTH);
        if (subYear == 0 && subMouth == 0 && subDay == 0) {
            return true;
        }
        return false;
    }

}
