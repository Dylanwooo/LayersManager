package com.zhjy.qzfwgz.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.List;

public class CreateDateTableBySql{
	public void CreateDateTableBySql(Calendar calendarStart,Calendar calendarEnd) throws Exception{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			//加载配置文件
			 Class.forName(LoadCalendarProperties.getInstance().getProperty("drivers"));
				String url=LoadCalendarProperties.getInstance().getProperty("url");
				String user=LoadCalendarProperties.getInstance().getProperty("username");
				String pass=LoadCalendarProperties.getInstance().getProperty("password");
				conn=DriverManager.getConnection(url,user,pass);
				
				stmt=conn.createStatement();
				
				
				while(!calendarEnd.equals(calendarStart)) {
					
					//先检查库中是否有这条数据
					String getSqlString =dealDateSql.checkDateSql(calendarStart);
					rs= stmt.executeQuery(getSqlString);
					if(rs.next()){
						System.out.println("数据库中已存在此条数�?:"+calendarStart.getTime());
					}
					else{
					String insertSqlString=dealDateSql.createInsertSql(calendarStart);//根据日期，产生一条insert
					stmt.execute(insertSqlString);
					calendarStart.add(Calendar.DAY_OF_MONTH,1);
					}
				}
				
				
				
		}
		catch(Exception Ex){
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
	}
	public static void main(String args[]) throws Exception{
		Calendar calendarStart =Calendar.getInstance();
		calendarStart.set(2200, 0, 1);
		Calendar calendarEnd = Calendar.getInstance();
		calendarEnd.set(2202, 0,1);
		CreateDateTableBySql createDateTableBySql = new CreateDateTableBySql();
		createDateTableBySql.CreateDateTableBySql(calendarStart, calendarEnd);

	}
}