package cn.zhouzy.greatcate.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import android.util.Log;

// TODO: Auto-generated Javadoc

/**
 * 描述：日期处理类.
 *
 * @version v1.0 @date：2013-01-18 下午11:52:13
 */
public class DateUtil
{

	/** 时间日期格式化到年月日时分秒. */
	public static final String dateFormatYMDHMS = "yyyy-MM-dd HH:mm:ss";

	/** 时间日期格式化到年月日. */
	public static final String dateFormatYMD = "yyyy-MM-dd";

	/** 时间日期格式化到年月. */
	public static final String dateFormatYM = "yyyy-MM";

	/** 时间日期格式化到年月日时分. */
	public static final String dateFormatYMDHM = "yyyy-MM-dd HH:mm";

	/** 时间日期格式化到月日. */
	public static final String dateFormatMD = "MM/dd";

	/** 时分秒. */
	public static final String dateFormatHMS = "HH:mm:ss";

	/** 时分. */
	public static final String dateFormatHM = "HH:mm";

	/** 上午. */
	public static final String AM = "AM";

	/** 下午. */
	public static final String PM = "PM";

	/**
	 * 描述：String类型的日期时间转化为Date类型.
	 *
	 * @param strDate
	 *            String形式的日期时间
	 * @param format
	 *            格式化字符串，如："yyyy-MM-dd HH:mm:ss"
	 * @return Date Date类型日期时间
	 */
	public static Date getDateByFormat(String strDate, String format)
	{
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format, Locale.CHINA);
		Date date = null;
		try
		{
			date = mSimpleDateFormat.parse(strDate);
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 描述：获取偏移之后的Date.
	 * 
	 * @param date
	 *            日期时间
	 * @param calendarField
	 *            Calendar属性，对应offset的值，
	 *            如(Calendar.DATE,表示+offset天,Calendar.HOUR_OF_DAY,表示＋offset小时)
	 * @param offset
	 *            偏移(值大于0,表示+,值小于0,表示－)
	 * @return Date 偏移之后的日期时间
	 */
	public Date getDateByOffset(Date date, int calendarField, int offset)
	{
		Calendar c = new GregorianCalendar();
		try
		{
			c.setTime(date);
			c.add(calendarField, offset);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return c.getTime();
	}

	/**
	 * 描述：获取指定日期时间的字符串(可偏移).
	 *
	 * @param strDate
	 *            String形式的日期时间
	 * @param format
	 *            格式化字符串，如："yyyy-MM-dd HH:mm:ss"
	 * @param calendarField
	 *            Calendar属性，对应offset的值，
	 *            如(Calendar.DATE,表示+offset天,Calendar.HOUR_OF_DAY,表示＋offset小时)
	 * @param offset
	 *            偏移(值大于0,表示+,值小于0,表示－)
	 * @return String String类型的日期时间
	 */
	public static String getStringByOffset(String strDate, String format, int calendarField,
			int offset)
	{
		String mDateTime = null;
		try
		{
			Calendar c = new GregorianCalendar();
			SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format, Locale.CHINA);
			c.setTime(mSimpleDateFormat.parse(strDate));
			c.add(calendarField, offset);
			mDateTime = mSimpleDateFormat.format(c.getTime());
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		return mDateTime;
	}

	/**
	 * 描述：Date类型转化为String类型(可偏移).
	 *
	 * @param date
	 *            the date
	 * @param format
	 *            the format
	 * @param calendarField
	 *            the calendar field
	 * @param offset
	 *            the offset
	 * @return String String类型日期时间
	 */
	public static String getStringByOffset(Date date, String format, int calendarField, int offset)
	{
		String strDate = null;
		try
		{
			Calendar c = new GregorianCalendar();
			SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format, Locale.CHINA);
			c.setTime(date);
			c.add(calendarField, offset);
			strDate = mSimpleDateFormat.format(c.getTime());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return strDate;
	}

	/**
	 * 描述：Date类型转化为String类型.
	 *
	 * @param date
	 *            the date
	 * @param format
	 *            the format
	 * @return String String类型日期时间
	 */
	public static String getStringByFormat(Date date, String format)
	{
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format, Locale.CHINA);
		String strDate = null;
		try
		{
			strDate = mSimpleDateFormat.format(date);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return strDate;
	}

	/**
	 * 描述：获取指定日期时间的字符串,用于导出想要的格式. MMM dd,yyyy kk:mm:ss aa
	 * 
	 * @param date
	 *            the date
	 * @param format
	 *            the format
	 * @return String String类型日期时间
	 */
	public static String getStringByDateFormat(String strDate, String format)
	{
		String mDateTime = null;
		try
		{
			Calendar c = new GregorianCalendar();
			SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("MMM dd,yyyy kk:mm:ss aa",
					Locale.ENGLISH);
			c.setTime(mSimpleDateFormat.parse(strDate));
			SimpleDateFormat mSimpleDateFormat2 = new SimpleDateFormat(format, Locale.CHINA);
			mDateTime = mSimpleDateFormat2.format(c.getTime());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return mDateTime;
	}

	/**
	 * 描述：获取指定日期时间的字符串,用于导出想要的格式.
	 *
	 * @param strDate
	 *            String形式的日期时间，必须为yyyy-MM-dd HH:mm:ss格式
	 * @param format
	 *            输出格式化字符串，如："yyyy-MM-dd HH:mm:ss"
	 * @return String 转换后的String类型的日期时间
	 */
	public static String getStringByFormat(String strDate, String format)
	{
		String mDateTime = null;
		try
		{
			Calendar c = new GregorianCalendar();
			SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(dateFormatYMDHMS,
					Locale.CHINA);
			c.setTime(mSimpleDateFormat.parse(strDate));
			SimpleDateFormat mSimpleDateFormat2 = new SimpleDateFormat(format, Locale.CHINA);
			mDateTime = mSimpleDateFormat2.format(c.getTime());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return mDateTime;
	}

	/**
	 * 描述：获取milliseconds表示的日期时间的字符串.
	 *
	 * @param milliseconds
	 *            the milliseconds
	 * @param format
	 *            格式化字符串，如："yyyy-MM-dd HH:mm:ss"
	 * @return String 日期时间字符串
	 */
	public static String getStringByFormat(long milliseconds, String format)
	{
		String thisDateTime = null;
		try
		{
			SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format, Locale.CHINA);
			thisDateTime = mSimpleDateFormat.format(milliseconds);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return thisDateTime;
	}

	/**
	 * 描述：获取表示当前日期时间的字符串.
	 *
	 * @param format
	 *            格式化字符串，如："yyyy-MM-dd HH:mm:ss"
	 * @return String String类型的当前日期时间
	 */
	public static String getCurrentDate(String format)
	{
		String curDateTime = null;
		try
		{
			SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format, Locale.CHINA);
			Calendar c = new GregorianCalendar();
			curDateTime = mSimpleDateFormat.format(c.getTime());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return curDateTime;

	}

	/**
	 * 描述：获取表示当前日期时间的字符串(可偏移).
	 *
	 * @param format
	 *            格式化字符串，如："yyyy-MM-dd HH:mm:ss"
	 * @param calendarField
	 *            Calendar属性，对应offset的值，
	 *            如(Calendar.DATE,表示+offset天,Calendar.HOUR_OF_DAY,表示＋offset小时)
	 * @param offset
	 *            偏移(值大于0,表示+,值小于0,表示－)
	 * @return String String类型的日期时间
	 */
	public static String getCurrentDateByOffset(String format, int calendarField, int offset)
	{
		String mDateTime = null;
		try
		{
			SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format, Locale.CHINA);
			Calendar c = new GregorianCalendar();
			c.add(calendarField, offset);
			mDateTime = mSimpleDateFormat.format(c.getTime());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return mDateTime;

	}

	/**
	 * 描述：计算两个日期所差的天数.
	 *
	 * @param milliseconds1
	 *            the milliseconds1
	 * @param milliseconds2
	 *            the milliseconds2
	 * @return int 所差的天数
	 */
	public static int getOffectDay(long milliseconds1, long milliseconds2)
	{
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTimeInMillis(milliseconds1);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTimeInMillis(milliseconds2);
		// 先判断是否同年
		int y1 = calendar1.get(Calendar.YEAR);
		int y2 = calendar2.get(Calendar.YEAR);
		int d1 = calendar1.get(Calendar.DAY_OF_YEAR);
		int d2 = calendar2.get(Calendar.DAY_OF_YEAR);
		int maxDays = 0;
		int day = 0;
		if (y1 - y2 > 0)
		{
			maxDays = calendar2.getActualMaximum(Calendar.DAY_OF_YEAR);
			day = d1 - d2 + maxDays;
		} else if (y1 - y2 < 0)
		{
			maxDays = calendar1.getActualMaximum(Calendar.DAY_OF_YEAR);
			day = d1 - d2 - maxDays;
		} else
		{
			day = d1 - d2;
		}
		return day;
	}

	/**
	 * 描述：计算两个日期所差的小时数.
	 *
	 * @param date1
	 *            第一个时间的毫秒表示
	 * @param date2
	 *            第二个时间的毫秒表示
	 * @return int 所差的小时数
	 */
	public static int getOffectHour(long date1, long date2)
	{
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTimeInMillis(date1);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTimeInMillis(date2);
		int h1 = calendar1.get(Calendar.HOUR_OF_DAY);
		int h2 = calendar2.get(Calendar.HOUR_OF_DAY);
		int h = 0;
		int day = getOffectDay(date1, date2);
		h = h1 - h2 + day * 24;
		return h;
	}

	/**
	 * 描述：计算两个日期所差的分钟数.
	 *
	 * @param date1
	 *            第一个时间的毫秒表示
	 * @param date2
	 *            第二个时间的毫秒表示
	 * @return int 所差的分钟数
	 */
	public static int getOffectMinutes(long date1, long date2)
	{
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTimeInMillis(date1);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTimeInMillis(date2);
		int m1 = calendar1.get(Calendar.MINUTE);
		int m2 = calendar2.get(Calendar.MINUTE);
		int h = getOffectHour(date1, date2);
		int m = 0;
		m = m1 - m2 + h * 60;
		return m;
	}

	/**
	 * 描述：获取本周一.
	 *
	 * @param format
	 *            the format
	 * @return String String类型日期时间
	 */
	public static String getFirstDayOfWeek(String format)
	{
		return getDayOfWeek(format, Calendar.MONDAY);
	}

	/**
	 * 描述：获取本周日.
	 *
	 * @param format
	 *            the format
	 * @return String String类型日期时间
	 */
	public static String getLastDayOfWeek(String format)
	{
		return getDayOfWeek(format, Calendar.SUNDAY);
	}

	/**
	 * 描述：获取本周的某一天.
	 *
	 * @param format
	 *            the format
	 * @param calendarField
	 *            the calendar field
	 * @return String String类型日期时间
	 */
	private static String getDayOfWeek(String format, int calendarField)
	{
		String strDate = null;
		try
		{
			Calendar c = new GregorianCalendar();
			SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format, Locale.CHINA);
			int week = c.get(Calendar.DAY_OF_WEEK);
			if (week == calendarField)
			{
				strDate = mSimpleDateFormat.format(c.getTime());
			} else
			{
				int offectDay = calendarField - week;
				if (calendarField == Calendar.SUNDAY)
				{
					offectDay = 7 - Math.abs(offectDay);
				}
				c.add(Calendar.DATE, offectDay);
				strDate = mSimpleDateFormat.format(c.getTime());
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return strDate;
	}

	/**
	 * 描述：获取本月第一天.
	 *
	 * @param format
	 *            the format
	 * @return String String类型日期时间
	 */
	public static String getFirstDayOfMonth(String format)
	{
		String strDate = null;
		try
		{
			Calendar c = new GregorianCalendar();
			SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format, Locale.CHINA);
			// 当前月的第一天
			c.set(GregorianCalendar.DAY_OF_MONTH, 1);
			strDate = mSimpleDateFormat.format(c.getTime());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return strDate;

	}

	/**
	 * 描述：获取本月最后一天.
	 *
	 * @param format
	 *            the format
	 * @return String String类型日期时间
	 */
	public static String getLastDayOfMonth(String format)
	{
		String strDate = null;
		try
		{
			Calendar c = new GregorianCalendar();
			SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format, Locale.CHINA);
			// 当前月的最后一天
			c.set(Calendar.DATE, 1);
			c.roll(Calendar.DATE, -1);
			strDate = mSimpleDateFormat.format(c.getTime());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return strDate;
	}

	/**
	 * 描述：获取表示当前日期的0点时间毫秒数.
	 *
	 * @return the first time of day
	 */
	public static long getFirstTimeOfDay()
	{
		Date date = null;
		try
		{
			String currentDate = getCurrentDate(dateFormatYMD);
			date = getDateByFormat(currentDate + " 00:00:00", dateFormatYMDHMS);
			return date.getTime();
		} catch (Exception e)
		{
		}
		return -1;
	}

	/**
	 * 描述：获取表示当前日期24点时间毫秒数.
	 *
	 * @return the last time of day
	 */
	public static long getLastTimeOfDay()
	{
		Date date = null;
		try
		{
			String currentDate = getCurrentDate(dateFormatYMD);
			date = getDateByFormat(currentDate + " 24:00:00", dateFormatYMDHMS);
			return date.getTime();
		} catch (Exception e)
		{
		}
		return -1;
	}

	/**
	 * 描述：判断是否是闰年()
	 * <p>
	 * (year能被4整除 并且 不能被100整除) 或者 year能被400整除,则该年为闰年.
	 *
	 * @param year
	 *            年代（如2012）
	 * @return boolean 是否为闰年
	 */
	public static boolean isLeapYear(int year)
	{
		if ((year % 4 == 0 && year % 400 != 0) || year % 400 == 0)
		{
			return true;
		} else
		{
			return false;
		}
	}

	/**
	 * 取指定日期为星期几.
	 *
	 * @param strDate
	 *            指定日期
	 * @param inFormat
	 *            指定日期格式
	 * @return String 星期几
	 */
	public static String getWeekNumber(String strDate, String inFormat)
	{
		String week = "星期日";
		Calendar calendar = new GregorianCalendar();
		DateFormat df = new SimpleDateFormat(inFormat, Locale.CHINA);
		try
		{
			calendar.setTime(df.parse(strDate));
		} catch (Exception e)
		{
			return "错误";
		}
		int intTemp = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		switch (intTemp)
		{
		case 0:
			week = "星期日";
			break;
		case 1:
			week = "星期一";
			break;
		case 2:
			week = "星期二";
			break;
		case 3:
			week = "星期三";
			break;
		case 4:
			week = "星期四";
			break;
		case 5:
			week = "星期五";
			break;
		case 6:
			week = "星期六";
			break;
		}
		return week;
	}

	/**
	 * 根据给定的毫秒数算得时间的描述.
	 *
	 * @param milliseconds
	 *            the milliseconds
	 * @return the time description
	 */
	public static String getTimeDescription(long milliseconds)
	{
		if (milliseconds > 1000)
		{
			// 大于一分
			if (milliseconds / 1000 / 60 > 1)
			{
				long minute = milliseconds / 1000 / 60;
				long second = milliseconds / 1000 % 60;
				return minute + "分" + second + "秒";
			} else
			{
				// 显示秒
				return milliseconds / 1000 + "秒";
			}
		} else
		{
			return milliseconds + "毫秒";
		}
	}

	/**
	 * 将指定的日期格式字符串转成另一种指定的日期格式字符串
	 * 
	 * @param datestr
	 *            日期字符串
	 * @param frmtstr1
	 *            日期格式如：yyyy-MM-dd HH:mm:ss
	 * @param frmtstr2
	 *            另一种日期格式如：yyyy-MM-dd HH:mm:ss
	 * @return frmtstr2 指定的日期字符串
	 */
	public static String StrToStr(String datestr, String frmtstr1, String frmtstr2)
	{
		return DateToStr(StrToDate(datestr, frmtstr1), frmtstr2);
	}

	/**
	 * 将指定的日期格式字符串转成Date日期格式
	 * 
	 * @param datestr
	 *            日期字符串
	 * @param frmtstr
	 *            日期格式如：yyyy-MM-dd HH:mm:ss
	 * @return Date日期格式
	 */
	public static Date StrToDate(String datestr, String frmtstr)
	{
		Date date = null;
		try
		{
			if (datestr != null && !"".equals(datestr) && frmtstr != null && !"".equals(frmtstr))
			{
				SimpleDateFormat sdf = new SimpleDateFormat(frmtstr, Locale.CHINA);
				date = sdf.parse(datestr);
			}
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 将指定的日期格式字符串转成Date日期格式
	 * 
	 * @param date
	 *            Date日期格式
	 * @param frmtstr
	 *            日期格式如：yyyy-MM-dd HH:mm:ss
	 * @return frmtstr日期格式
	 */
	public static String DateToStr(Date date, String frmtstr)
	{
		if (date == null)
			return "";
		SimpleDateFormat dd = new SimpleDateFormat(frmtstr, Locale.CHINA);
		String date1 = dd.format(date);
		return date1;
	}

	/**
	 * 判断是不是今天之后的第几天
	 * 
	 * @param datestr
	 *            日期字符串
	 * @param frmtstr
	 *            日期的格式如：yyyy-MM-dd HH:mm:ss
	 * @param index
	 *            今天之后的第几天
	 * @return boolean类型，如果是返回true，否则false
	 */
	private static boolean isToday(String datestr, String frmtstr, int index)
	{
		Date now_date;
		try
		{
			now_date = StrToDate(datestr, frmtstr);
			Calendar nowCalendar = Calendar.getInstance();
			nowCalendar.add(Calendar.DAY_OF_YEAR, index);
			nowCalendar.set(Calendar.HOUR_OF_DAY, 0);
			nowCalendar.set(Calendar.MINUTE, 0);
			nowCalendar.set(Calendar.SECOND, 0);
			nowCalendar.set(Calendar.MILLISECOND, 0);

			if (now_date.equals(nowCalendar.getTime()))
			{
				return true;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public static boolean isToday(Date datestr, String frmtstr, int index)
	{
		String now_date;
		try
		{
			now_date = DateUtil.DateToStr(datestr, "yyyy-M-dd");
			Calendar nowCalendar = Calendar.getInstance();
			nowCalendar.add(Calendar.DAY_OF_YEAR, index);
			nowCalendar.set(Calendar.HOUR_OF_DAY, 0);
			nowCalendar.set(Calendar.MINUTE, 0);
			nowCalendar.set(Calendar.SECOND, 0);
			nowCalendar.set(Calendar.MILLISECOND, 0);
			if (now_date.equals(nowCalendar.get(Calendar.YEAR) + "-" + (nowCalendar.get(
					Calendar.MONTH) + 1) + "-" + nowCalendar.get(Calendar.DAY_OF_MONTH)))
			{
				return true;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 判断是不是今天
	 * 
	 * @param datestr
	 * @param frmtstr
	 * @return
	 */
	public static boolean isToday(String datestr, String frmtstr)
	{
		return isToday(datestr, frmtstr, 0);
	}

	/**
	 * 判断是不是昨天
	 * 
	 * @param datestr
	 * @param frmtstr
	 * @return
	 */
	public static boolean isVorgestern(String datestr, String frmtstr)
	{
		return isToday(datestr, frmtstr, -1);
	}

	/**
	 * 判断是不是明天
	 * 
	 * @param datestr
	 * @param frmtstr
	 * @return
	 */
	public static boolean isTomorrow(String datestr, String frmtstr)
	{
		return isToday(datestr, frmtstr, 1);
	}

	/**
	 * 判断是不是后天
	 * 
	 * @param datestr
	 * @param frmtstr
	 * @return
	 */
	public static boolean isAfterTomorrow(String datestr, String frmtstr)
	{
		return isToday(datestr, frmtstr, 2);
	}

	/**
	 * 今年的日子：显示 月-日 非今年的日子：年-月-日
	 * 
	 * @param calendar
	 * @return
	 */
	public static String getStDate(String calendar)
	{
		Calendar nowCalendar = Calendar.getInstance();
		int year_temp = nowCalendar.get(Calendar.YEAR);// 当前年份
		Date dat = StrToDate(calendar, "yyyy-MM-dd");
		nowCalendar.setTime(dat);
		int year = nowCalendar.get(Calendar.YEAR);
		if (year == year_temp)
		{
			return StrToStr(calendar, "yyyy-MM-dd", "MM-dd");
		} else
		{
			return calendar;
		}
	}

	/**
	 * 比较两个日期时间的大小
	 * 
	 * @param sDataFormat
	 *            时间格式 "yyyy-MM-dd hh:mm" "yyyy-MM-dd"
	 * @param data1
	 *            时间1
	 * @param data2
	 *            时间2
	 * @return <br>
	 *         1 时间1在时间2的后面 <br>
	 *         -1 时间1在时间2的前面 <br>
	 *         0 同一时刻 <br>
	 *         -2 发生异常
	 */
	public static int compare_date(String sDataFormat, String data1, String data2)
	{
		DateFormat df = new SimpleDateFormat(sDataFormat, Locale.CHINA);
		try
		{
			Date dt1 = df.parse(data1);
			Date dt2 = df.parse(data2);
			if (dt1.getTime() > dt2.getTime())
			{
				return 1;
			} else if (dt1.getTime() < dt2.getTime())
			{
				return -1;
			} else
			{
				return 0;
			}
		} catch (Exception exception)
		{
			Log.e("error", "转化时间出错 " + exception.toString());
			return -2;
		}
	}

	/**
	 * <p>
	 * 在工具类中经常使用到工具类的格式化描述，这个主要是一个日期的操作类，所以日志格式主要使用 SimpleDateFormat的定义格式.
	 * </p>
	 * 格式的意义如下： 日期和时间模式 <br>
	 * <p>
	 * 日期和时间格式由日期和时间模式字符串指定。在日期和时间模式字符串中，未加引号的字母 'A' 到 'Z' 和 'a' 到 'z'
	 * 被解释为模式字母，用来表示日期或时间字符串元素。文本可以使用单引号 (') 引起来，以免进行解释。"''"
	 * 表示单引号。所有其他字符均不解释；只是在格式化时将它们简单复制到输出字符串，或者在分析时与输入字符串进行匹配。
	 * </p>
	 * 定义了以下模式字母（所有其他字符 'A' 到 'Z' 和 'a' 到 'z' 都被保留）： <br>
	 * <table border="1" cellspacing="1" cellpadding="1" summary="Chart shows
	 * pattern letters, date/time component, presentation, and examples.">
	 * <tr>
	 * <th align="left">字母</th>
	 * <th align="left">日期或时间元素</th>
	 * <th align="left">表示</th>
	 * <th align="left">示例</th>
	 * </tr>
	 * <tr>
	 * <td><code>G</code></td>
	 * <td>Era 标志符</td>
	 * <td>Text</td>
	 * <td><code>AD</code></td>
	 * </tr>
	 * <tr>
	 * <td><code>y</code></td>
	 * <td>年</td>
	 * <td>Year</td>
	 * <td><code>1996</code>; <code>96</code></td>
	 * </tr>
	 * <tr>
	 * <td><code>M</code></td>
	 * <td>年中的月份</td>
	 * <td>Month</td>
	 * <td><code>July</code>; <code>Jul</code>; <code>07</code></td>
	 * </tr>
	 * <tr>
	 * <td><code>w</code></td>
	 * <td>年中的周数</td>
	 * <td>Number</td>
	 * <td><code>27</code></td>
	 * </tr>
	 * <tr>
	 * <td><code>W</code></td>
	 * <td>月份中的周数</td>
	 * <td>Number</td>
	 * <td><code>2</code></td>
	 * </tr>
	 * <tr>
	 * <td><code>D</code></td>
	 * <td>年中的天数</td>
	 * <td>Number</td>
	 * <td><code>189</code></td>
	 * </tr>
	 * <tr>
	 * <td><code>d</code></td>
	 * <td>月份中的天数</td>
	 * <td>Number</td>
	 * <td><code>10</code></td>
	 * </tr>
	 * <tr>
	 * <td><code>F</code></td>
	 * <td>月份中的星期</td>
	 * <td>Number</td>
	 * <td><code>2</code></td>
	 * </tr>
	 * <tr>
	 * <td><code>E</code></td>
	 * <td>星期中的天数</td>
	 * <td>Text</td>
	 * <td><code>Tuesday</code>; <code>Tue</code></td>
	 * </tr>
	 * <tr>
	 * <td><code>a</code></td>
	 * <td>Am/pm 标记</td>
	 * <td>Text</td>
	 * <td><code>PM</code></td>
	 * </tr>
	 * <tr>
	 * <td><code>H</code></td>
	 * <td>一天中的小时数（0-23）</td>
	 * <td>Number</td>
	 * <td><code>0</code></td>
	 * </tr>
	 * <tr>
	 * <td><code>k</code></td>
	 * <td>一天中的小时数（1-24）</td>
	 * <td>Number</td>
	 * <td><code>24</code></td>
	 * </tr>
	 * <tr>
	 * <td><code>K</code></td>
	 * <td>am/pm 中的小时数（0-11）</td>
	 * <td>Number</td>
	 * <td><code>0</code></td>
	 * </tr>
	 * <tr>
	 * <td><code>h</code></td>
	 * <td>am/pm 中的小时数（1-12）</td>
	 * <td>Number</td>
	 * <td><code>12</code></td>
	 * </tr>
	 * <tr>
	 * <td><code>m</code></td>
	 * <td>小时中的分钟数</td>
	 * <td>Number</td>
	 * <td><code>30</code></td>
	 * </tr>
	 * <tr>
	 * <td><code>s</code></td>
	 * <td>分钟中的秒数</td>
	 * <td>Number</td>
	 * <td><code>55</code></td>
	 * </tr>
	 * <tr>
	 * <td><code>S</code></td>
	 * <td>毫秒数</td>
	 * <td>Number</td>
	 * <td><code>978</code></td>
	 * </tr>
	 * <tr>
	 * <td><code>z</code></td>
	 * <td>时区</td>
	 * <td>General time zone</td>
	 * <td><code>Pacific Standard Time</code>; <code>PST</code>;
	 * <code>GMT-08:00</code></td>
	 * </tr>
	 * <tr>
	 * <td><code>Z</code></td>
	 * <td>时区</td>
	 * <td>RFC 822 time zone</td>
	 * <td><code>-0800</code></td>
	 * </tr>
	 * </table>
	 * 
	 * <pre>
	*                          HH:mm    15:44
	*                         h:mm a    3:44 下午
	*                        HH:mm z    15:44 CST
	*                        HH:mm Z    15:44 +0800
	*                     HH:mm zzzz    15:44 中国标准时间
	*                       HH:mm:ss    15:44:40
	*                     yyyy-MM-dd    2016-08-12
	*               yyyy-MM-dd HH:mm    2016-08-12 15:44
	*            yyyy-MM-dd HH:mm:ss    2016-08-12 15:44:40
	*       yyyy-MM-dd HH:mm:ss zzzz    2016-08-12 15:44:40 中国标准时间
	*  EEEE yyyy-MM-dd HH:mm:ss zzzz    星期五 2016-08-12 15:44:40 中国标准时间
	*       yyyy-MM-dd HH:mm:ss.SSSZ    2016-08-12 15:44:40.461+0800
	*     yyyy-MM-dd'T'HH:mm:ss.SSSZ    2016-08-12T15:44:40.461+0800
	*   yyyy.MM.dd G 'at' HH:mm:ss z    2016.08.12 公元 at 15:44:40 CST
	*                         K:mm a    3:44 下午
	*               EEE, MMM d, ''yy    星期五, 八月 12, '16
	*          hh 'o''clock' a, zzzz    03 o'clock 下午, 中国标准时间
	*   yyyyy.MMMMM.dd GGG hh:mm aaa    02016.八月.12 公元 03:44 下午
	*     EEE, d MMM yyyy HH:mm:ss Z    星期五, 12 八月 2016 15:44:40 +0800
	*                  yyMMddHHmmssZ    160812154440+0800
	*     yyyy-MM-dd'T'HH:mm:ss.SSSZ    2016-08-12T15:44:40.461+0800
	* EEEE 'DATE('yyyy-MM-dd')' 'TIME('HH:mm:ss')' zzzz    星期五 DATE(2016-08-12) TIME(15:44:40) 中国标准时间
	 * </pre>
	 */
	public static final SimpleDateFormat DEFAULT_SDF = new SimpleDateFormat("yyyy-MM-dd", Locale
			.getDefault());

	/**
	 * 将时间戳转为时间字符串
	 * <p>
	 * 格式为yyyy-MM-dd HH:mm:ss
	 * </p>
	 *
	 * @param milliseconds
	 *            毫秒时间戳
	 * @return 时间字符串
	 */
	public static String milliseconds2String(long milliseconds)
	{
		return milliseconds2String(milliseconds, DEFAULT_SDF);
	}

	/**
	 * 将时间戳转为时间字符串
	 * <p>
	 * 格式为用户自定义
	 * </p>
	 *
	 * @param milliseconds
	 *            毫秒时间戳
	 * @param format
	 *            时间格式
	 * @return 时间字符串
	 */
	public static String milliseconds2String(long milliseconds, SimpleDateFormat format)
	{
		return format.format(new Date(milliseconds));
	}

	/**
	 * 将时间字符串转为时间戳
	 * <p>
	 * 格式为yyyy-MM-dd HH:mm:ss
	 * </p>
	 *
	 * @param time
	 *            时间字符串
	 * @return 毫秒时间戳
	 */
	public static long string2Milliseconds(String time)
	{
		return string2Milliseconds(time, DEFAULT_SDF);
	}

	/**
	 * 将时间字符串转为时间戳
	 * <p>
	 * 格式为用户自定义
	 * </p>
	 *
	 * @param time
	 *            时间字符串
	 * @param format
	 *            时间格式
	 * @return 毫秒时间戳
	 */
	public static long string2Milliseconds(String time, SimpleDateFormat format)
	{
		try
		{
			return format.parse(time).getTime();
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * 将时间字符串转为Date类型
	 * <p>
	 * 格式为yyyy-MM-dd HH:mm:ss
	 * </p>
	 *
	 * @param time
	 *            时间字符串
	 * @return Date类型
	 */
	public static Date string2Date(String time)
	{
		return string2Date(time, DEFAULT_SDF);
	}

	/**
	 * 将时间字符串转为Date类型
	 * <p>
	 * 格式为用户自定义
	 * </p>
	 *
	 * @param time
	 *            时间字符串
	 * @param format
	 *            时间格式
	 * @return Date类型
	 */
	public static Date string2Date(String time, SimpleDateFormat format)
	{
		return new Date(string2Milliseconds(time, format));
	}

	/**
	 * 将Date类型转为时间字符串
	 * <p>
	 * 格式为yyyy-MM-dd
	 * </p>
	 *
	 * @param time
	 *            Date类型时间
	 * @return 时间字符串
	 */
	public static String date2String(Date time)
	{
		return date2String(time, DEFAULT_SDF);
	}

	/**
	 * 将Date类型转为时间字符串
	 * <p>
	 * 格式为用户自定义
	 * </p>
	 *
	 * @param time
	 *            Date类型时间
	 * @param format
	 *            时间格式
	 * @return 时间字符串
	 */
	public static String date2String(Date time, SimpleDateFormat format)
	{
		return format.format(time);
	}

	/**
	 * 将Date类型转为时间戳
	 *
	 * @param time
	 *            Date类型时间
	 * @return 毫秒时间戳
	 */
	public static long date2Milliseconds(Date time)
	{
		return time.getTime();
	}

	/**
	 * 将时间戳转为Date类型
	 *
	 * @param milliseconds
	 *            毫秒时间戳
	 * @return Date类型时间
	 */
	public static Date milliseconds2Date(long milliseconds)
	{
		return new Date(milliseconds);
	}

	/**
	 * 获取当前时间
	 *
	 * @return 毫秒时间戳
	 */
	public static long getCurTimeMills()
	{
		return System.currentTimeMillis();
	}

	/**
	 * 获取当前时间
	 * <p>
	 * 格式为yyyy-MM-dd HH:mm:ss
	 * </p>
	 *
	 * @return 时间字符串
	 */
	public static String getCurTimeString()
	{
		return date2String(new Date());
	}

	/**
	 * 获取当前时间
	 * <p>
	 * 格式为用户自定义
	 * </p>
	 *
	 * @param format
	 *            时间格式
	 * @return 时间字符串
	 */
	public static String getCurTimeString(SimpleDateFormat format)
	{
		return date2String(new Date(), format);
	}

	/**
	 * 获取当前时间
	 * <p>
	 * Date类型
	 * </p>
	 *
	 * @return Date类型时间
	 */
	public static Date getCurTimeDate()
	{
		return new Date();
	}

}
