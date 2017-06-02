package com.zhjy.qzfwgz.util;

import java.math.BigDecimal;
//import java.math.MathContext;
//import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * һЩ���ù�����
 * 
 * @author xwtt
 * 
 */
public class CommonTools {

  private static final Log log = LogFactory.getLog(CommonTools.class);

  /**
   * �ж��Ƿ�Ϊ��
   * 
   * @param str
   * @return
   */
  public static boolean isNull(String str) {
    return str == null || str.length() == 0;
  }

  public static boolean isDate(String str) {
    return !CommonTools.isNull(str) && (str.length()) >= 10;
  }
  
  public static boolean isNumber(String str) {
    Pattern pattern = Pattern.compile("[0-9]*");
    Matcher isNum = pattern.matcher(str);
    if (!isNum.matches()) {
      return false;
    }
    return true;
  }
  /**
   * ����ԭʼ���������µ� Date().getTime()+"_"+fileName ����
   * 
   * @param fileName
   * @return
   * @throws IllegalArgumentException
   */
  public static String createFileName(String fileName)
      throws IllegalArgumentException {
    if (CommonTools.isNull(fileName)) {
      throw new IllegalArgumentException("���������µ��ļ���,�ļ�������Ϊ��fileName=="
          + fileName);
    }
    return new Date().getTime() + "_" + fileName;
  }

  /**
   * ����ϵͳ���ɵ� Date().getTime()+"_"+fileName ���� ��ԭ��ԭ������
   * 
   * @param fileName
   * @return
   * @throws IllegalArgumentException
   * @throws IndexOutOfBoundsException
   */
  public static String revertFileName(String fileName)
      throws IllegalArgumentException, IndexOutOfBoundsException {
    if (CommonTools.isNull(fileName)) {
      throw new IllegalArgumentException("���������µ��ļ���,�ļ�������Ϊ��fileName=="
          + fileName);
    }
    int ind = fileName.indexOf('_');

    if (ind < 1) {
      throw new IndexOutOfBoundsException("�ļ�����ʽ���� fileName == " + fileName
          + "--" + ind);
    }
    return fileName.substring(ind + 1);
  }

  public static String getToday() {
    Calendar cal = Calendar.getInstance();
    String month = (cal.get(Calendar.MONTH) + 1) < 10 ? "0"
        + (cal.get(Calendar.MONTH) + 1) : (cal.get(Calendar.MONTH) + 1) + "";
    String day = cal.get(Calendar.DATE) < 10 ? "0" + (cal.get(Calendar.DATE))
        : (cal.get(Calendar.DATE)) + "";
    String today = cal.get(Calendar.YEAR) + "-" + month + "-" + day;
    return today;
  }

  /**
   * �Ƚ�ʱ�� ����ʱ��ͽ����ʱ��Ƚ� �ȵ�ǰʱ�����Ϊfalse;
   * 
   * @param date
   * @return
   */
  public static boolean compareDate(String date) {
    if (CommonTools.isDate(date)) {
      int today = Integer
          .parseInt(CommonTools.replace(getToday(), "-", "", -1));
      int time = 0;
      time = Integer.parseInt(CommonTools.replace(date.substring(0, 10), "-",
          "", -1));
      if (log.isDebugEnabled()) {
        log.debug(today + "-" + time + "=" + (today - time));
      }
      return (today - time) >= 0;
    }
    return false;
  }

  public static String replace(String text, String repl, String with, int max) {
    if (text == null || CommonTools.isNull(repl) || with == null || max == 0) {
      return text;
    }

    StringBuffer buf = new StringBuffer(text.length());
    int start = 0, end = 0;
    while ((end = text.indexOf(repl, start)) != -1) {
      buf.append(text.substring(start, end)).append(with);
      start = end + repl.length();

      if (--max == 0) {
        break;
      }
    }
    buf.append(text.substring(start));
    return buf.toString();
  }

  public static String replace(String text, String repl) {
    if (CommonTools.isNull(text)) {
      return repl;
    }
    if (CommonTools.isNumber(text)&&Integer.parseInt(text) == 0) {
      return repl;
    }
    return text;
  }

  public static String percent(BigDecimal big) {
    String per = "";
    if ((big.abs().compareTo(new BigDecimal("1"))==1)) {   //>1
       per = big.setScale(1,BigDecimal.ROUND_HALF_UP).toString() + "��";
       
    }else if((big.abs().compareTo(new BigDecimal("1"))==0)){   //=1
    	per = big.setScale(1,BigDecimal.ROUND_HALF_UP).toString() + "��";
    }else if(big.abs().compareTo(new BigDecimal("0"))==1 && big.abs().compareTo(new BigDecimal("1"))==-1){   //>0 ,<1
      BigDecimal num100 = new BigDecimal("100");
      big = big.multiply(num100);
//      per = big.setScale(1,BigDecimal.ROUND_HALF_UP).toString() + "%";
      per = big.setScale(1,BigDecimal.ROUND_HALF_UP).toString();
    }
    else if(big.compareTo(new BigDecimal("0"))==0){   // =0
    	per = "";
    }	
    return per;
  }

  public static String signum(BigDecimal big) {
    String per = "";
    int i = 0;
    i = big.signum();
    big = big.abs();
    
    per = percent(big);
    if (i == -1) {
      per = "-" + per;
    }
    return per;
  }


}
