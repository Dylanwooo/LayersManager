package project;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import java.io.BufferedReader;  
import java.io.BufferedWriter;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.FileReader;  
import java.io.FileWriter;  
import java.io.IOException;  
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;  
import java.net.MalformedURLException;  
import java.net.URL;  
import java.net.URLConnection;  
import java.net.URLEncoder;  
import java.nio.charset.Charset;  
import java.util.Date;  
import java.util.HashMap;  
import java.util.Map; 
import net.sf.json.JSONArray;    
import net.sf.json.JSONObject; 
import net.sf.json.JSONException;


@WebServlet("/GetWeather")
public class GetWeather extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetWeather() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String baiduUrl = "http://api.map.baidu.com/telematics/v3/weather?location=北京&output=json&ak=m3Chubjkj2MedwGKxSxZ1eguQNwcpmm7";  
        StringBuffer strBuf; 
        //解决乱码问题
      	response.setContentType("text/html;charset=UTF-8"); 
        PrintWriter out = response.getWriter();
        String cityName = "武汉";
		try{
			baiduUrl = "http://api.map.baidu.com/telematics/v3/weather?location="+URLEncoder.encode(cityName, "utf-8")+"&output=json&ak=m3Chubjkj2MedwGKxSxZ1eguQNwcpmm7"; 
		}catch(UnsupportedEncodingException e1){
			e1.printStackTrace();     
		}
		
		strBuf = new StringBuffer(); 
		
		 try{  
             URL url = new URL(baiduUrl);  
             URLConnection conn = url.openConnection();  
             BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));//转码。  
             String line = null;  
             while ((line = reader.readLine()) != null)  
                 strBuf.append(line + " ");  
                 reader.close();  
         }catch(MalformedURLException e) {  
             e.printStackTrace();   
         }catch(IOException e){  
             e.printStackTrace();   
         } 
         out.println(strBuf);					 		 		 		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
