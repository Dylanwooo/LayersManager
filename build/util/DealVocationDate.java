package com.zhjy.qzfwgz.util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import com.zhjy.qzfwgz.coding.YearMonthDay;



public class DealVocationDate{
	
	public static Object[] readVocationDate(String yearNum,String monNum) throws Exception{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List holidayList = new ArrayList();
		Object[] holidays=null;
		Calendar calendarStart = Calendar.getInstance();
		try{
			//加载配置文件
			 Class.forName(LoadCalendarProperties.getInstance().getProperty("drivers"));
			 
				String url=LoadCalendarProperties.getInstance().getProperty("url");
				String user=LoadCalendarProperties.getInstance().getProperty("username");
				String pass=LoadCalendarProperties.getInstance().getProperty("password");
				conn=DriverManager.getConnection(url,user,pass);
				stmt=conn.createStatement();
				//把是节假日是0的取出来
				System.out.println(yearNum+"年"+monNum+"月");
				calendarStart.set(Calendar.YEAR,Integer.parseInt(yearNum));
				calendarStart.set(Calendar.MONTH,Integer.parseInt(monNum)-1);
				System.out.println(calendarStart.get(Calendar.YEAR)+"年"+calendarStart.get(Calendar.MONTH));
				String getHolidayString =dealDateSql.getHolidaySql(calendarStart);
				rs= stmt.executeQuery(getHolidayString);
				while(rs.next()){
					
					int holiday = rs.getInt(1);
					holidayList.add(new Integer(holiday));
				
				}
				holidays=holidayList.toArray();
				System.out.println(holidayList);
				 
				
				 
		}catch(Exception Ex){
			Ex.printStackTrace();
			conn.rollback();
		}
		
		
		
		finally {
			if(stmt!=null){
				stmt.close();
			}
			if(conn!=null){
				conn.close();
			}
			if(rs!=null){
				rs.close();
			}
		}
		return holidays;

			
			
	}
	
	
	public static void modifyHolidays(String yearNum,String monNum,String dayNum) throws Exception{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Calendar calendarStart = Calendar.getInstance();
		
		try{
			//加载配置文件
			 Class.forName(LoadCalendarProperties.getInstance().getProperty("drivers"));
			 
				String url=LoadCalendarProperties.getInstance().getProperty("url");
				String user=LoadCalendarProperties.getInstance().getProperty("username");
				String pass=LoadCalendarProperties.getInstance().getProperty("password");
				conn=DriverManager.getConnection(url,user,pass);
				stmt=conn.createStatement();
				
				calendarStart.set(Calendar.YEAR, Integer.parseInt(yearNum));
				calendarStart.set(Calendar.MONTH, Integer.parseInt(monNum)-1);
				System.out.print(calendarStart.get(Calendar.MONTH)+"月");
				calendarStart.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dayNum));
				String getIsWork = dealDateSql.getIsWork(calendarStart);
				rs=stmt.executeQuery(getIsWork);//查询此天是否工作�?
				while(rs.next()){
					int isWork = rs.getInt(1);
					System.out.println(isWork);
					changeHolidayString(isWork, stmt, calendarStart);//更改是否节假日标志位
					
				}
		}catch(Exception Ex){
			Ex.printStackTrace();
			conn.rollback();
		}finally {
			if(stmt!=null){
				stmt.close();
			}
			if(conn!=null){
				conn.close();
			}
			if(rs!=null){
				rs.close();
			}
		}
		
	}
	
	public static void changeHolidayString(int isWork,Statement stmt, Calendar calendarStart) throws SQLException{
		if(isWork==YearMonthDay.ISWORK){//此天是工作日
			String updateToHoliday = dealDateSql.updateToHoliday(calendarStart)	;
			stmt.executeUpdate(updateToHoliday);
		}else if(isWork==YearMonthDay.ISNOTWORK){//此天是节假日
			String updateToWorkDay = dealDateSql.updateToWorkDay(calendarStart);
			stmt.executeUpdate(updateToWorkDay);
		}else{
			String updateToHoliday = dealDateSql.updateToHoliday(calendarStart)	;
			stmt.executeUpdate(updateToHoliday);
		}

		
	}
	
	public static Date queryEndingDate (Calendar startDate,int number) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List list = new ArrayList();
		Date returnDate=null;
		try{
			//加载配置文件
			 Class.forName(LoadCalendarProperties.getInstance().getProperty("drivers"));
			 
				String url=LoadCalendarProperties.getInstance().getProperty("url");
				String user=LoadCalendarProperties.getInstance().getProperty("username");
				String pass=LoadCalendarProperties.getInstance().getProperty("password");
				conn=DriverManager.getConnection(url,user,pass);
				stmt=conn.createStatement();
				String queryEndingDate = dealDateSql.queryEndingDateSql(startDate,number);
				System.out.println("____________queryEndingDate__________________");
				System.out.println(queryEndingDate);
				rs=stmt.executeQuery(queryEndingDate);
				while(rs.next()){
					returnDate=rs.getDate("curr_date");

				}
		}catch(Exception Ex){
			Ex.printStackTrace();
			conn.rollback();
		}
		finally {
			if(stmt!=null){
				stmt.close();
			}
			if(conn!=null){
				conn.close();
			}
			if(rs!=null){
				rs.close();
			}
			
		}
		return returnDate;
		
	}
	


	   

	public static void main(String args[]) throws Exception{
		Date d1=new Date();
		Calendar calendarStart = Calendar.getInstance();
		String  year = "2010";
		String  month = "12";
		String day = "22";
		DealVocationDate.modifyHolidays(year,month,day);
	}
}