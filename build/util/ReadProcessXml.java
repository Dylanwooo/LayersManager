package  com.zhjy.qzfwgz.util;


import java.io.StringReader;
import com.zhjy.qzfwgz.util.DateUtil;
import com.zhjy.qzfwgz.vo.ToProcessVO;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;


import org.xml.sax.InputSource;



public class ReadProcessXml{
	
	private Long parselong(String pl){
		long buffer=0;
		try{
			 buffer=Long.parseLong(pl);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			return null;	
		} 
		return new Long(buffer);
		
	}
	public ToProcessVO xmlElements(String xmlDoc) {
		
		ToProcessVO toprocessvoinfo = new ToProcessVO();
		// 创建一个新的字符串
		StringReader read = new StringReader(xmlDoc);
		// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
		InputSource source = new InputSource(read);
		// 创建一个新的SAXBuilder
		SAXBuilder sb = new SAXBuilder();
		
		try {
			  
			// 通过输入源构造一个Document
			Document doc = sb.build(source);
			// 取的根元素
			Element root = doc.getRootElement();
			//System.out.println(root.getName());// 输出根元素的名称（测试）
			
			
			   if(root.getChild("ID").getText()!=null){
			   toprocessvoinfo.setId(parselong(root.getChild("ID").getText()));                                        
		     toprocessvoinfo.setEventstateid(parselong(root.getChild("EVENTSTATEID").getText()));                           
		     toprocessvoinfo.setEventstatename(root.getChild("EVENTSTATENAME").getText());                                
		     toprocessvoinfo.setHumanid(parselong(root.getChild("HUMANID").getText()));                         
		     toprocessvoinfo.setHumanname(root.getChild("HUMANNAME").getText());                 
		     toprocessvoinfo.setUnitid(parselong(root.getChild("UNITID").getText()));                        
		     toprocessvoinfo.setUnitname(root.getChild("UNITNAME").getText());                 
		     toprocessvoinfo.setStartdate(DateUtil.parseAll(root.getChild("STARTDATE").getText()));                  
		     toprocessvoinfo.setEnddate(DateUtil.parseAll(root.getChild("ENDDATE").getText()));           
		     toprocessvoinfo.setItemmemo(root.getChild("ITEMMEMO").getText());               
			   }
		         
			
			
			System.out.println(toprocessvoinfo);
			
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} 
		return toprocessvoinfo;
	}
	public static void main(String[] args) {
		ReadProcessXml readprocessxml = new ReadProcessXml();
		String xml ="<?xml version='1.0' encoding='UTF-8'?>" +
				"" +
				"<PROCESSINFO><ID></ID><EVENTSTATEID></EVENTSTATEID><EVENTSTATENAME></EVENTSTATENAME><HUMANID></HUMANID><HUMANNAME></HUMANNAME><UNITID></UNITID><UNITNAME></UNITNAME><STARTDATE></STARTDATE><ENDDATE></ENDDATE><ITEMMEMO></ITEMMEMO></PROCESSINFO>";
		
		readprocessxml.xmlElements(xml);
		
		
		
	}
}