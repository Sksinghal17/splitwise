package com.test.splitwise.utills;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateUtils {

  final static long hoursInMillis = 60L * 60L * 1000;
  final static long minutesInMillis = 60L * 1000;
  public final static long serverOverlapTime = 5 * hoursInMillis + (30 * minutesInMillis);

  public static Date addHours(Date date, int hours) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.HOUR_OF_DAY, hours);
    return calendar.getTime();
  }


  public static Date addMinutes(Date date, int hours) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.MINUTE, hours);
    return calendar.getTime();
  }


  public static boolean isDateAfter(Date from, Date to) {
    Calendar cal1 = Calendar.getInstance();
    Calendar cal2 = Calendar.getInstance();
    cal1.setTime(from);
    cal2.setTime(to);
    return cal1.after(cal2);
  }

  public static SimpleDateFormat dateFormatter() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    return sdf;
  }

  public static SimpleDateFormat dateTimeFormatter() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    return sdf;
  }

  public static String dateFormatSort(Date date) {
    if (date == null) {
      return null;
    }
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    return sdf.format(date);
  }

  public static String dateFormatSort(Date date, String pattern) {
    if (date == null) {
      return null;
    }
    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    return sdf.format(date);
  }

  public static String convertDateToDateTimeFormat(Date date) {
    SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM, yyyy");
    String strDate = formatter.format(date);
    return strDate;
  }

  public static Date getDate(String date) throws ParseException {
    try {
      return dateFormatter().parse(date);
    } catch (ParseException e) {
      throw e;
    }
  }

  public static Date getDateWithoutException(String date) {
    try {
      SimpleDateFormat simpleDateFormat = dateFormatter();
      simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
      return simpleDateFormat.parse(date);
    } catch (ParseException e) {
      log.info("Cannot parse DateString: {}. Returning null", date);
      return null;
    }
  }

  public static Date getCurrentDate() {
    String date = dateFormatter().format(new Date());
    try {
      return dateFormatter().parse(date);
    } catch (ParseException e) {
      return new Date();
    }
  }

  public static String getCurrentDateTimeStr() {
    String dateTime = dateTimeFormatter().format(new Date());
    return dateTime;
  }

  public static Date getCurrentDateTime() {
    String date = dateTimeFormatter().format(new Date());
    try {
      return dateFormatter().parse(date);
    } catch (ParseException e) {
      log.error("Cannot parse Date: {}. Returning null", date);
      return new Date();
    }
  }

  public static String getCurrentISTDateStr() {
    Date serverDate = new Date(); // oldDate == current time
    Date localDate = new Date(serverDate.getTime() + serverOverlapTime); // Adds
    // 5
    // hours
    // 32
    // minutes
    String deliveryDate = new SimpleDateFormat("yyyy-MM-dd").format(localDate);
    return deliveryDate;
  }

  public static Date getCurrentISTDate() {
    Date serverDate = new Date(); // oldDate == current time
    Date localDate = new Date(serverDate.getTime() + serverOverlapTime); // Adds
    // 5
    // hours
    // 32
    // minutes
    return localDate;
  }

  public static long getCurrentEpochTime() {
    return System.currentTimeMillis();
  }
}
