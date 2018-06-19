package cn.fk.te.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * 线程安全的日期格式化工具
 * Created by tanxianlin@lakala.com at 2016/12/14
 */

public class DateUtil {
	private static final HashMap<String,ThreadLocal<SimpleDateFormat>> map=new HashMap<String, ThreadLocal<SimpleDateFormat>>();
	private static String SHORT="yyyy-MM-dd";
	private static String SHORT_1="yyyyMMdd";

	private static String LONG="yyyy-MM-dd HH:mm:ss";
	private static String EQUAL="yyyy-MM-dd HH:mm:ss.SSS";

	static {
		map.put(SHORT, ThreadLocal.withInitial(() -> new SimpleDateFormat(SHORT)));
		map.put(SHORT_1,new ThreadLocal<SimpleDateFormat>(){
			@Override
			public SimpleDateFormat initialValue(){
				return new SimpleDateFormat(SHORT_1);
			}
		});
		map.put(LONG,new ThreadLocal<SimpleDateFormat>(){
			@Override
			public SimpleDateFormat initialValue(){
				return new SimpleDateFormat(LONG);
			}
		});
		map.put(EQUAL,new ThreadLocal<SimpleDateFormat>(){
			@Override
			public SimpleDateFormat initialValue(){
				return new SimpleDateFormat(EQUAL);
			}
		});

		map.put(EQUAL,new ThreadLocal<SimpleDateFormat>(){
			@Override
			public SimpleDateFormat initialValue(){
				return new SimpleDateFormat(EQUAL);
			}
		});
	}

	public static String toShort(Date date){
		return map.get(SHORT).get().format(date);
	}
	public static Date parseShort(String dateString)  {
		try {
			return map.get(SHORT).get().parse(dateString);
		} catch (ParseException e) {
			throw new IllegalArgumentException("日期格式不正确",e);
		}
	}
	public static String toLong(Date date){
		return map.get(LONG).get().format(date);
	}
	public static Date parseLong(String dateString) {
		try {
			return map.get(LONG).get().parse(dateString);
		} catch (ParseException e) {
			throw new IllegalArgumentException("日期格式不正确",e);
		}
	}
	public static String toEqual(Date date) {
		return map.get(EQUAL).get().format(date);
	}
	public static Date parseEqual(String dateString){
		try {
			return map.get(EQUAL).get().parse(dateString);
		} catch (ParseException e) {
			throw new IllegalArgumentException("日期格式不正确",e);
		}
	}

	public static String getYesterday(){
		Calendar c=Calendar.getInstance();
		c.add(Calendar.DATE,-1);
		return  map.get(SHORT_1).get().format(c.getTime());
	}

	public  Date parse(String strDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.parse(strDate);
	}

	//由出生日期获得年龄
	public  static int getAge(Date birthDay) throws Exception {
		Calendar cal = Calendar.getInstance();

		if (cal.before(birthDay)) {
			throw new IllegalArgumentException(
					"The birthDay is before Now.It's unbelievable!");
		}
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH);
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(birthDay);

		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth) age--;
			}else{
				age--;
			}
		}
		return age;
	}

	//获得生日
	public static int getAgeByBirth(Date birthday) {
		int age = 0;
		try {
			Calendar now = Calendar.getInstance();
			now.setTime(new Date());// 当前时间

			Calendar birth = Calendar.getInstance();
			birth.setTime(birthday);

			if (birth.after(now)) {//如果传入的时间，在当前时间的后面，返回0岁
				age = 0;
			} else {
				age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
				if (now.get(Calendar.DAY_OF_YEAR) > birth.get(Calendar.DAY_OF_YEAR)) {
					age += 1;
				}
			}
			return age;
		} catch (Exception e) {//兼容性更强,异常后返回数据
			return 0;
		}
	}

	//获取当前系统前一天日期
	public static Date getPreDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		return date;
	}
}
