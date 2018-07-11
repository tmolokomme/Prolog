package com.wealth.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
  private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
  private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

	public static Date getDate(String dateString) {
		if (StringUtil.isEmpty(dateString)) {
			return null;
		}
		try {
			Date parsed = dateFormat.parse(dateString);
			return parsed;
		} catch (ParseException pe) {
			return null;
		}
	}

  public static String getDateStr(Date date) {
    if (date == null) {
      return null;
    }
    try {
      String dateStr = dateFormat.format(date);
      return dateStr;
    } catch (Exception pe) {
      return null;
    }
  }

  public static String getTimeStr(Date date) {
    if (date == null) {
      return null;
    }
    try {
      String dateStr = timeFormat.format(date);
      return dateStr;
    } catch (Exception pe) {
      return null;
    }
  }

	public static Date setEndOfDay(Date date) {
		if (date != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
      cal.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
      cal.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
      cal.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));
			return cal.getTime();
		}
		return null;
	}

	public static boolean valid_from_to_dates(Date from, Date to,
			boolean allowNulls) {
		if (from == null && to == null) {
			return allowNulls;
		}
		if (from == null || to == null) {
			return false;
		}
		return from.compareTo(to) <= 0;
	}

	public static boolean isMonthEnd(Date monthEndDate) {
		if (monthEndDate == null) {
			return false;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(monthEndDate);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH) == cal
				.get(Calendar.DAY_OF_MONTH);
	}
}
