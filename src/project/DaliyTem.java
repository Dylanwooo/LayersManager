package project;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class DaliyTem
 */
@WebServlet("/DaliyTem")
public class DaliyTem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
    static final String DB_URL = "jdbc:mysql://localhost:3306/mydata?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    
    static final String USER = "root";
	static final String PASS = "123456";
	
    public DaliyTem() {
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
		String selectDate = request.getParameter("oDate");
		String sTime = request.getParameter("oStartTime");
		String eTime = request.getParameter("oEndTime");	
		
		try{
			//注册JDBC驱动			
			Class.forName("com.mysql.jdbc.Driver");			
			//打开连接
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			//执行SQL查询
			stmt = conn.createStatement();
				String sql;		
				String temp = sTime;				
				sql = "SELECT Station_Name,Lat,Lon,TEM,TEM_Max,TEM_Min FROM tab".concat(selectDate);
				ResultSet rs = stmt.executeQuery(sql);	
				out.println("[");    			  					
				while(rs.next()){							
				    Gson gson = new Gson();
				    //Java对象转成Json格式
				    //String  json = gson.toJson(tempreture);
				    //out.write(json);

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

	private int parseInt(String eTime) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
