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

/**
 * Servlet implementation class Data
 */
@WebServlet("/Data")
public class Data extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//JDBC驱动名及数据库URL
		static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
	    static final String DB_URL = "jdbc:mysql://localhost:3306/mydata?useUnicode=true&characterEncoding=utf-8&useSSL=false";
	    
	    static final String USER = "root";
		static final String PASS = "123456aa";
		
    public Data() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		Statement stmt = null;
		//解决乱码问题
		response.setContentType("text/html;charset=UTF-8");               
	    
		PrintWriter out = response.getWriter();
		//获得表的标题
		String title = request.getParameter("mtitle");
	    try{
			//注册JDBC驱动			
			Class.forName("com.mysql.jdbc.Driver");			
			//打开连接
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			//执行SQL查询
			stmt = conn.createStatement();
			String sql;	
			
			sql = "SELECT TEM_Max,TEM_Min,Station_Name,Lat,Lon,TEM,TEM_Max,TEM_Min,RHU,PRE_1h,PRS,WIN_D_Avg_10mi,WIN_S_Avg_10mi,Station_Name FROM tab".concat(title);
			ResultSet rs = stmt.executeQuery(sql);	
			out.println("[");    			  					
			while(rs.next()){			
				String lati = rs.getString("Lat");
			    String longti = rs.getString("Lon");
			    String tem = rs.getString("TEM");
			    String tem_max = rs.getString("TEM_Max");
			    String tem_min = rs.getString("TEM_Min");
			    String humid = rs.getString("RHU");
			    String rain = rs.getString("PRE_1h");
			    String pressure = rs.getString("PRS");
			    String windDirection = rs.getString("WIN_D_Avg_10mi");
			    String wind = rs.getString("WIN_S_Avg_10mi");
			    String staName = rs.getString("Station_Name");
			    String tem_Max = rs.getString("TEM_Max");
			    String tem_Min = rs.getString("TEM_Min");
			    Tempre tempreture = new Tempre(lati,longti,tem,tem_max,tem_min,humid,rain,pressure,windDirection,wind,staName,tem_Max,tem_Min);
			    Gson gson = new Gson();
			    //Java对象转成Json格式
			    String  json = gson.toJson(tempreture);
			    out.write(json);

			    if(!rs.isLast())
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
        	se.printStackTrace();
        	out.println("JDBC error");
        }
		catch(Exception e){			
			e.printStackTrace();
			out.println("class.forName error");
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
