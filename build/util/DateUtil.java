/*****************************************************************************
 * Copyright (C) ZHJY Company. All rights reserved.                          *
 * ------------------------------------------------------------------------- *
 *                ZHJY��˾��Ȩ����                                            *
 *                http://www.zhongguancun.com.cn                                    *
 *****************************************************************************/

package com.zhjy.qzfwgz.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>Title: DateUtil.java</p>
 * <p>Description: ���ڴ�������</p>
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
     * ���Ĭ�ϵ� date pattern
     */
    public static String getDatePattern() {
        return defaultDatePattern;
    }

    /**
     * ����Ԥ��Format�ĵ�ǰ�����ַ���
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
     * ʹ��Ԥ��Format��ʽ��Date���ַ���
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
     * ʹ�ò���Format��ʽ��Date���ַ���
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
     * ʹ��Ԥ���ʽ���ַ���תΪDate
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
     * ʹ�ò���Format���ַ���תΪDate
     */
    public static Date parse(String strDate, String pattern) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.parse(strDate);
    }
    
    

}

