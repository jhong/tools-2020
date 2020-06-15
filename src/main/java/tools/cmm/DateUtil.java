package tools.cmm;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil {
	static Logger logger = LoggerFactory.getLogger(DateUtil.class);

	public final static String DATE_FORMAT = "yyyy-MM-dd";
	public final static String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 현재 시간(Date) 반환
	 * @return
	 * @throws Exception
	 */
	public static Date getCurrentDate() throws Exception {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}
	
	/**
	 * 현재 시간 반환 (자동생성 파일명 뒤에 사용하기 위한 포맷)
	 * @return
	 * @throws Exception
	 */
	public static String getCurrTimePostfixStr() throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
		Calendar cal = Calendar.getInstance();
		String today = null;
		today = formatter.format(cal.getTime());
		Timestamp ts = Timestamp.valueOf(today);
		return ts.toString().replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
	}

	/**
	 * 현재 시간을 포매팅하여 반환한다.
	 * @param dtFormat
	 * @return 날짜 스트링
	 */
	public static String getNowString(String dtFormat) {
		Calendar today = Calendar.getInstance();
		String szResult = "";
		if (dtFormat != null) {
//			szResult = new SimpleDateFormat(dtFormat).format(today.getTime());
			szResult = new SimpleDateFormat(dtFormat, Locale.ENGLISH).format(today.getTime());
		}
		return szResult;
	}
	
	public static Calendar str2cal(String str, String format) {
		return str2cal(str, format, Locale.KOREA);
	}
	
	public static Calendar str2cal(String str, String format, Locale locale) {
		Calendar cal = null;
		if (StringUtils.isEmpty(str)) return cal;
		
		try {
//			DateFormat formatter = new SimpleDateFormat(format);
			DateFormat formatter = new SimpleDateFormat(format, locale);
			Date date = (Date)formatter.parse(str); 
			
			cal = Calendar.getInstance();
			cal.setTime(date);
		} catch (NullPointerException e) {
			logger.error("fail in str2cal() str="+str+", format="+format, e);
		} catch (IllegalArgumentException e) {
			logger.error("fail in str2cal() str="+str+", format="+format, e);
		} catch (Exception e) {
			logger.error("fail in str2cal() str="+str+", format="+format, e);
		}
		return cal;
	}
	
	public static String date2str(Date date, String format) {
		if (date == null || StringUtils.isEmpty(format)) return null;
		return new SimpleDateFormat(format).format(date);
	}
	
	 /**
	  * 표준포멧에 의한 날짜 형식을 가져온다.
	  * @param dt
	  * @param dtFormat
	  * @return 날짜 스트링
	  */
	 public static String getDateString(java.util.Date dt, String dtFormat) {
	     String szResult = "";
	     if (dt != null && dtFormat != null) {
	    	 szResult = new SimpleDateFormat(dtFormat).format(dt);

	     }
	     return szResult;
	 }
	
}
