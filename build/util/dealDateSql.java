package com.zhjy.qzfwgz.util;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.zhjy.qzfwgz.coding.YearMonthDay;

public class dealDateSql{
	public static String createInsertSql(Calendar calendarStart){
		String insertString = null;
		int startYear = calendarStart.get(Calendar.YEAR);
		int startMonth = calendarStart.get(Calendar.MONTH)+1;
		int startDay = calendarStart.get(Calendar.DAY_OF_MONTH);
		int weekIsWork = calendarStart.get(Calendar.DAY_OF_WEEK);
		if(weekIsWork!=Calendar.SATURDAY && weekIsWork!=Calendar.SUNDAY){
			weekIsWork = YearMonthDay.ISWORK;		
		}
		else{
			weekIsWork = YearMonthDay.ISNOTWORK;
		}	
		insertString = "insert into all_date values("+startYear+","+startMonth+","+startDay+","+weekIsWork +")";
		return insertString;
		
	}
	
	public static String  checkDateSql(Calendar calendarStart){
		String checkString = null;
		int startYear = calendarStart.get(Calendar.YEAR);
		int startMonth = calendarStart.get(Calendar.MONTH)+1;
		int startDay = calendarStart.get(Calendar.DAY_OF_MONTH);
		
		checkString = "select * from all_date where ALL_DATE_YEAR="+startYear+"and ALL_DATE_MONTH="+startMonth+
		"and ALL_DATE_Day="+startDay;
		return checkString;
	}
	
	public static String getHolidaySql(Calendar calendarStart){
		int startYear = calendarStart.get(Calendar.YEAR);
		int startMonth = calendarStart.get(Calendar.MONTH)+1;
		System.out.print(startYear+"年"+startMonth);
		String getHolidayString = "select all_date_day from all_date where ALL_DATE_YEAR="+startYear+"and ALL_DATE_MONTH="+startMonth+
		"and DAY_IS_WORK = "+YearMonthDay.ISNOTWORK;
		return getHolidayString;
	}
	
	public static String updateToWorkDay(Calendar calendarStart){
		int startYear = calendarStart.get(Calendar.YEAR);
		int startMonth = calendarStart.get(Calendar.MONTH)+1;
		int startDay = calendarStart.get(Calendar.DAY_OF_MONTH);
		
		String updateToHoliday = "update all_date tt set day_is_work = 1 where ALL_DATE_YEAR = " +startYear
		+"and ALL_DATE_MONTH = " + startMonth +"and all_date_day = " +startDay;
		return updateToHoliday;

	}
	
	public static String updateToHoliday(Calendar calendarStart){
		int startYear = calendarStart.get(Calendar.YEAR);
		int startMonth = calendarStart.get(Calendar.MONTH)+1;
		int startDay = calendarStart.get(Calendar.DAY_OF_MONTH);
		
		String updateToHoliday = "update all_date tt set day_is_work = 0 where ALL_DATE_YEAR = " +startYear
		+"and ALL_DATE_MONTH = " + startMonth +"and all_date_day = " +startDay;
		return updateToHoliday;

	}
	public static String getIsWork(Calendar calendarStart){
		int startYear = calendarStart.get(Calendar.YEAR);
		int startMonth = calendarStart.get(Calendar.MONTH)+1;
		int startDay = calendarStart.get(Calendar.DAY_OF_MONTH);
		System.out.print(startYear+"年"+startMonth+"月");
	//	System.out.print(startYear+"年"+	startMonth+"月"+	startDay+"日");
		String getIsWork = "select DAY_IS_WORK from all_date where ALL_DATE_YEAR = " + startYear +" and ALL_DATE_MONTH = "
		+ startMonth +" and ALL_DATE_day  =  "+ startDay;
		return getIsWork;
		
	}
	
	public static String queryEndingDateSql(Calendar calendarStart,int number){
		java.util.Date date = calendarStart.getTime();
        Timestamp time=new Timestamp(date.getTime());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String dateString=sdf.format(time);
		String EndingDateString = "select to_date(all_date_year||'-'||all_date_month||'-'||all_date_day,'yyyy-mm-dd') as curr_date" +
				" from(" +
		"select rownum as order_num ,b.* from(" +
		"select a.* from all_date a where (a.all_date_year||'-'||(case when all_date_month<10 then '0' end)" +
		"||all_date_month||'-'||" +
		"(case when all_date_day<10 then '0' end)||all_date_day)>" + "'" +
		dateString  +"'" + 
		"and a.day_is_work=1 order by all_date_year,all_date_month,all_date_day) b" +
				")where order_num="+number;
		return EndingDateString;
		
	}
	
	public static void main(String args[]){
		Calendar today = Calendar.getInstance();
		java.util.Date date = today.getTime();
        Timestamp time=new Timestamp(date.getTime());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String dateString=sdf.format(time);
		//java.sql.Date time1 = (java.sql.Date) new Date(date.getTime());
		String sqlString =  "select * from all_date where ALL_DATE_YEAR  =  " + 2010 + " and ALL_DATE_MONTH = "+3+
		" and ALL_DATE_Day = "+4;
		String test =    "select * from(" +
		"select rownum as order_num ,b.* from(" +
		"select a.* from all_date a where (a.all_date_year||'-'||(case when all_date_month<10 then '0' end)" +
		"||all_date_month||'-'||" +
		"(case when all_date_day<10 then '0' end)||all_date_day)>" + "'" +
		dateString  +"'" + 
		
		"and a.day_is_work=1 order by all_date_year,all_date_month,all_date_day) b" +
		")where order_num=50";
		System.out.println(test);
		/*Calendar calendarStart = Calendar.getInstance();
		calendarStart.set(2010,2,4);
		int number =5;
		dealDateSql dealDateSql1 = new dealDateSql();
		dealDateSql1.queryEndingDateSql(calendarStart, number);*/
	}
}