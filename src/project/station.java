package project;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import com.google.gson.Gson; 

import net.sf.json.JSONArray;

@WebServlet("/station")
public class station extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//JDBC驱动名及数据库URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
    static final String DB_URL = "jdbc:mysql://localhost:3306/mydata?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    
    static final String USER = "root";
	static final String PASS = "123456aa"; 
	
    public station() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		Statement stmt = null;
		response.setContentType("text/html;charset=UTF-8");
	    
		PrintWriter out = response.getWriter();
			
		try{
			//注册JDBC驱动
			Class.forName("com.mysql.jdbc.Driver");
			//打开连接
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			//执行SQL查询
			stmt = conn.createStatement();
			String sql;		
			sql = "SELECT STATIONNUM, STATIONNAME, ADMINCODE,PROVINCE,CITY,COUNTY,STATIONLEVL,STATIONTYPE,LATITUDE,LONGITUDE,ALTITUDE FROM stationIn";
			ResultSet rs = stmt.executeQuery(sql);
			out.println("[");    			  					
			while(rs.next()){
								
				String stationNum = rs.getString("STATIONNUM");
			    String name = rs.getString("STATIONNAME");
			    String code = rs.getString("ADMINCODE");
			    String province = rs.getString("PROVINCE");
			    String city = rs.getString("CITY");
			    String country = rs.getString("COUNTY");
			    String evl = rs.getString("STATIONLEVL");
			    String stationType = rs.getString("STATIONTYPE");
			    String lat = rs.getString("LATITUDE");
			    String longtitude = rs.getString("LONGITUDE");
			    String alt = rs.getString("ALTITUDE");
			    stationInfoClass sta = new stationInfoClass(stationNum,name,code,province,city,country,evl,stationType,lat,longtitude,alt);
			    Gson gson = new Gson();
			    //Java对象转成Json格式
			    String  json = gson.toJson(sta);
			    
			    out.write(json);
			    if(rs.getRow()<89)
			      {
			    	out.println(",");
			      }
			    out.flush();
		
			}			
			out.println("]");
			rs.close();
			stmt.close();
			conn.close();
		}
        catch(SQLException se){
        	out.println("JDBC error");
        	se.printStackTrace();
        }
		catch(Exception e){
			out.println("class.forName error");
			e.printStackTrace();
		}
		finally{
			try{
				if(stmt!=null)
					stmt.close();
			}
			catch(SQLException se2){			   
			}
			try{
				if(conn!=null)
				conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}		
	    //response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
