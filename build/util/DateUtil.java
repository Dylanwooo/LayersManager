/*****************************************************************************
 * Copyright (C) ZHJY Company. All rights reserved.                          *
 * ------------------------------------------------------------------------- *
 *                ZHJY公司版权所有                                            *
 *                http://www.zhongguancun.com.cn                                    *
 *****************************************************************************/

package com.zhjy.qzfwgz.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>Title: DateUtil.java</p>
 * <p>Description: 日期处理工具类</p>
 * <p>Copyright: ZHJY'S Copyright (c) 2006</p>
 * <p>Company: ZHJY</p>
 * @since 2006-7-26
 * @author jwzh_project_team
 * @version 1.0
 */
public class DateUtil {
	public static String defaultDatePattern = "yyyy-MM-dd";
	public static String defaultDatePattern1 = "yyyy-MM-dd HH:mm:ss";
	public static String defaultDatePattern2 = "yyyyMMdd";
    /**
     * 获得默认的 date pattern
     */
    public static String getDatePattern() {
        return defaultDatePattern;
    }

    /**
     * 返回预设Format的当前日期字符串
     */
    public static String getToday() {
        Date today = new Date();
        return format(today);
    }
    public static String getAllToday() {
      Date today = new Date();
      return formatAllDate(today);
    }
    public static String getNotFlagToday() {
      Date today = new Date();
      return formatNotFlagDate(today);
    }
    /**
     * 使用预设Format格式化Date成字符串
     */
    public static String format(Date date) {
        return format(date, getDatePattern());
    }
    public static String formatAllDate(Date date) {
      return format(date, defaultDatePattern1);
    }
    public static String formatNotFlagDate(Date date) {
      return format(date, defaultDatePattern2);
    }
    /**
     * 使用参数Format格式化Date成字符串
     */
    public static String format(Date date, String pattern) {
        String returnValue = "";

        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            returnValue = df.format(date);
        }

        return (returnValue);
    }

    /**
     * 使用预设格式将字符串转为Date
     */
    public static Date parse(String strDate) throws ParseException {
        return parse(strDate, getDatePattern());
    }
    public static String parseString(String strDate) throws ParseException {
    	if(strDate==null || "".equals(strDate)){
    		return null;
    	}
    	if(strDate.length()>19){
    		strDate=strDate.substring(0,19);
    	}
    	Date bufferDate=parse(strDate, defaultDatePattern1);
      return format(bufferDate);
     }
    
    public static Date parseAll(String strDate) throws ParseException {
    	if(strDate==null || "".equals(strDate)){
    		return null;
    	}
        return parse(strDate, defaultDatePattern1);
    }

    /**
     * 使用参数Format将字符串转为Date
     */
    public static Date parse(String strDate, String pattern) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.parse(strDate);
    }
    
    

}

