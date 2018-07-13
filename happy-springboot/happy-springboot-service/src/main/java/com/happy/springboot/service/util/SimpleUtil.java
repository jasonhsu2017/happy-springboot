package com.happy.springboot.service.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;



public class SimpleUtil {

    public static boolean isNullOrNullString(String str){
        return isNull(str) || "null".equalsIgnoreCase(str);
    }

	public static boolean isNull(Object obj) {
		return obj == null ? true : false;
	}

	public static boolean isNotNull(Object obj) {
		return !isNull(obj);
	}

	public static boolean isEmpty(Collection<?> coll) {
		return (coll == null || coll.isEmpty());
	}

	public static boolean isNotEmpty(Collection<?> coll) {
		return !isEmpty(coll);
	}

	public static boolean isEmpty(Object[] arr) {
		return (arr == null || arr.length == 0);
	}

	public static boolean isNotEmpty(Object[] arr) {
		return !isEmpty(arr);
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String nstr) {
		if (nstr == null) {
			return true;
		} else {
			nstr = nstr.replace(" ", "");// 替换所有的空格
			if ("".equals(nstr) || (nstr).length() == 0) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * 判断字符串是否不为空(空格字符串也判断为空)
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String nstr) {
		return !isBlank(nstr);
	}

	/**
	 * 数据四舍五入
	 * 
	 * @param d
	 *            待四舍五入的数据
	 * @param seit精度位
	 * @return 返回double精度的数据 特别注意的是0.00时返回数据是0.0
	 */
	public static double getScale(String str, int seit) {
		String dStr = getScaleString(str, seit);
		return new Double(dStr);
	}

	/**
	 * 数据四舍五入
	 * 
	 * @param d
	 *            待四舍五入的数据
	 * @param seit精度位
	 * @return 返回全精度的 字符串
	 */
	public static String getScaleString(String str, int seit) {
		BigDecimal bd = new BigDecimal(str + "");
		BigDecimal bd1 = bd.setScale(seit, BigDecimal.ROUND_HALF_UP);
		return bd1.toString();
	}

	/**
	 * 连接列表值 通过逗号分隔
	 * 
	 * @param list
	 * @return
	 */
	public static String getBridgingStr(List<String> list) {
		StringBuilder builder = new StringBuilder("");
		if (list != null && !list.isEmpty()) {
			for (String str : list) {
				builder.append(str + ",");
			}
		}
		if (builder.length() > 0) {
			return builder.substring(0, builder.lastIndexOf(","));
		}else{
			return "";
		}
	}

	/**
	 * 连接列表值 通过逗号分隔
	 * 
	 * @param list
	 * @return
	 */
	public static String getBridgingStrObj(List<Object> list) {
		StringBuilder builder = new StringBuilder("");
		if (list != null && !list.isEmpty()) {
			for (Object str : list) {
				builder.append(str + ",");
			}
			builder.substring(0, builder.lastIndexOf(","));
		}
		return builder.toString();
	}

	/**
	 * 根据传入的格式,返回日期字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getDateString(Date date, String format) {
		if (date == null)
			return "";
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String dateString = formatter.format(date);
		return dateString;
	}

	/**
	 * 获取当年第一天日期
	 * 
	 * @param year
	 *            年份
	 * @return Date
	 */
	public static Date getCurrYearFirst() {
		int year = Integer.parseInt(getDateString(new Date(), "yyyy"));
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		Date currYearFirst = calendar.getTime();
		return getDate(getDateString(currYearFirst, "yyyy-MM-dd"));
	}

	/**
	 * 获取当年最后一天日期
	 * 
	 * @param year
	 *            年份
	 * @return Date
	 */
	public static Date getCurrYearLast() {
		int year = Integer.parseInt(getDateString(new Date(), "yyyy"));
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();
		return getDate(getDateString(currYearLast, "yyyy-MM-dd"));
	}

	/*
	 * 将字符串格式yyyy-MM-dd HH:mm:ss转让日期类型
	 * 
	 * @param dateStr
	 * 
	 * @return
	 */
	public static java.util.Date getDateAll(String dateStr) {
		java.util.Date ddd = new java.util.Date();
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// formatter.applyPattern("yyyy-MM-dd");
			ddd = formatter.parse(dateStr);
		} catch (Exception e) {
		}
		return ddd;
	}

	/**
	 * 将字符串格式yyyy-MM-dd转让日期类型
	 * 
	 * @param dateStr
	 * @return
	 */
	public static java.util.Date getDate(String dateStr) {
		java.util.Date ddd = new java.util.Date();
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			// formatter.applyPattern("yyyy-MM-dd");
			ddd = formatter.parse(dateStr);
		} catch (Exception e) {
		}
		return ddd;
	}

	/**
	 * 将字符串格式yyyyMMdd转让日期类型
	 * 
	 * @param dateStr
	 * @return
	 */
	public static java.util.Date getDateyyyyMMdd(String dateStr) {
		java.util.Date ddd = new java.util.Date();
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			ddd = formatter.parse(dateStr);
		} catch (Exception e) {
		}
		return ddd;
	}

	/**
	 * 将日期类型转字符串格式yyyyMMdd
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String getDateFormate(Date date) {
		String str = "";
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			str = formatter.format(date);
		} catch (Exception e) {
		}
		return str;
	}

	/**
	 * 计算未来年
	 * 
	 * @param oldDate
	 *            传入时间
	 * @param years
	 *            年
	 * @return
	 */
	public static Date getNewDate(Date oldDate, long years) {
		int moth = (int) (years * 12);
		return getNewDateByMoth(oldDate, moth);

	}

	/**
	 * 计算未来年
	 * 
	 * @param oldDate
	 *            传入时间
	 * @param years
	 *            年
	 * @return
	 */
	public static Date getNewDateByMoth(Date oldDate, int moth) {
		/*
		 * long afterTime = (oldDate.getTime() / 1000) + 60 * 60 * 24 * 30 *
		 * moth; Date temp = new Date(); temp.setTime(afterTime * 1000); return
		 * temp;
		 */
		Calendar calender = Calendar.getInstance();
		calender.setTime(oldDate);
		calender.add(Calendar.MONTH, moth);
		return calender.getTime();
	}

	/**
	 * 计算过去年
	 * 
	 * @param oldDate
	 *            传入时间
	 * @param years
	 *            年
	 * @return
	 */
	public static Date getOldDateByMoth(Date newDate, int moth) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(newDate);
		calender.add(Calendar.MONTH, -moth);
		return calender.getTime();

	}

	/**
	 * 获取两日期之间的年差
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static Long getYears(Date start, Date end) {
		Long value = end.getTime() - start.getTime();
		if (value > 0) {
			double value1 = value;
			double del = 1000 * 3600 * 24 * 365l;
			Double year = value1/del;
			return year.longValue();
		} else {
			return 0l;
		}
	}



	/****
	 * 获取superClassGenricType,基于superClass操作,操作Type[0]
	 * 
	 * @param clazz
	 * @return
	 * 
	 */
	public static Class<?> getSuperClassGenricType(Class<?> clazz) {
		return getSuperClassGenricType(clazz, 0);
	}

	/****
	 * 获取superClassGenricType,基于superClass操作
	 * 
	 * @param clazz
	 * @return
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public static Class<?> getSuperClassGenricType(Class<?> clazz, int index) {
		Type genType = clazz.getGenericSuperclass();

		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if ((index >= params.length) || (index < 0)) {
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class) params[index];
	}

	/**
	 * 字符串处理
	 * 
	 * @param str
	 * @return
	 */
	public static String stringToEmpty(String str) {
		String temp = "";
		if (str != null) {
			temp = str.trim();
		}
		return temp;
	}

	/**
	 * 数组转化为List（Arrays.asList不支持remove/clear方法 ）
	 * 
	 * @param <T>
	 * 
	 * @param list
	 * @return
	 */
	public static <T> List<T> ArrayToList(T[] str) {
		List<T> list = new ArrayList<T>();
		if (str != null && str.length > 0) {
			for (T obj : str) {
				list.add(obj);
			}
		}
		return list;
	}

	/**
	 * 如果是起始日期，时分秒设为0 如果是终止日期，时分秒设为23:59:59
	 * 
	 * @param d
	 * @param isBegin
	 * @return
	 */
	public static Date getQueryDate(Date d, boolean isBegin) {
		Calendar c = Calendar.getInstance();
		if (isBegin) {

			c.setTime(d);
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
		} else {
			c.setTime(d);
			c.set(Calendar.HOUR_OF_DAY, 23);
			c.set(Calendar.MINUTE, 59);
			c.set(Calendar.SECOND, 59);
		}

		return c.getTime();
	}

	/**
	 * @Title: trim
	 * @Description: 将String类型进行trim操作
	 * @param str
	 * @return String 返回类型
	 * @throws
	 * @author xuguoping
	 */
	public static String trim(String str) {
		String temp = "";
		if (str != null) {
			temp = str.trim();
		}
		return temp;
	}

	/**
	 * @Title: getCurrentDate
	 * @Description: 获取当前服务器日期字符串(yyyy-MM-dd)
	 * @return String 返回类型
	 * @throws
	 * @author xuguoping
	 */
	public static String getCurrentDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(new Date());
	}
	
	/**
	 * 获取当前日期 ， 格式yyyyMMdd
	 * @return
	 */
	public static Date getCurrentDateyyyyMMdd(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		try {
			return dateFormat.parse(dateFormat.format(new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Convert hex string to byte[]
	 * 
	 * @param hexString
	 *            the hex string
	 * @return byte[]
	 */
	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	/**
	 * Convert char to byte
	 * 
	 * @param c
	 *            char
	 * @return byte
	 */
	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	/**
	 * @Title: objToStrTrim
	 * @Description: 将String类型进行trim操作
	 * @param str
	 * @return String 返回类型
	 * @throws
	 */
	public static String objToStrTrim(Object obj) {
		String temp = "";
		if (obj != null) {
			String str = String.valueOf(obj);
			temp = str.trim();
		}
		return temp;
	}
	
	/**
	 * @Description: 检查投资者交易经验是否满两年
	 * @param start
	 * @param end
	 * @return int
	 * @throws
	 */
	public static boolean checkInvestorsExperienceDate(Date start, Date end) {
		try {
			int bigY = Integer.valueOf(getDateFormate(end));// (yyyyMMdd)
			int smallY = Integer.valueOf(getDateFormate(start));// (yyyyMMdd)
			if (bigY - smallY < 20000) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	* @Title: trimMap
	* @Description: 将Map中的String类型进行trim操作
	* @param map
	* @return 
	* @return  Map<String,Object>    返回类型
	* @throws
	*/ 
	public static Map<String,Object> trimMap(Map<String,Object> map){
		for(Entry<String,Object> entry : map.entrySet()){
			if(entry.getValue() instanceof String){
				map.put(entry.getKey(), trim(entry.getValue().toString()));
			}else if(SimpleUtil.isNull(entry.getValue())){
				map.put(entry.getKey(), "");
			}
			
		}
		return map; 
	}
	
	/**
	* @Title: cast
	* @Description: 强制类型转换，把Object类型转换成特定的类型
	* @param @param o
	* @param @return    设定文件
	* @return T    返回类型
	* @throws
	 */
    @SuppressWarnings("unchecked")
    public static <T> T cast(Object o) {
        return (T) o;
    }
    
    /**
     * @Title: nullToEmpty
     * @Description: 将空指针的null值或者"null"值转换成空的字符串
     * @param @param src
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
      */
     public static String nullToEmpty(String src){
     	if (src == null || "null".equalsIgnoreCase(src)) {
 			return "";
 		}else{
 			return src;
 		}
     }
	
     
 	public static String lpad(Object src, int maxLen, char append) {
		String str = String.valueOf(src);
		int appendLen = maxLen - str.length();
		if (appendLen > 0) {
			StringBuilder sb = new StringBuilder(maxLen);
			for (int i = 0; i < appendLen; i++) {
				sb.append(append);
			}
			sb.append(str);
			return sb.toString();
		}
		return str;
	}

	public static String investmentSuffix(long lenderid, int tableNums, int length, int start) {
		return lpad(lenderid % tableNums, length, '0');
	}
}
