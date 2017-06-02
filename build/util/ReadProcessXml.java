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
			// TODO �Զ����� catch ��
			return null;	
		} 
		return new Long(buffer);
		
	}
	public ToProcessVO xmlElements(String xmlDoc) {
		
		ToProcessVO toprocessvoinfo = new ToProcessVO();
		// ����һ���µ��ַ���
		StringReader read = new StringReader(xmlDoc);
		// �����µ�����ԴSAX ��������ʹ�� InputSource ������ȷ����ζ�ȡ XML ����
		InputSource source = new InputSource(read);
		// ����һ���µ�SAXBuilder
		SAXBuilder sb = new SAXBuilder();
		
		try {
			  
			// ͨ������Դ����һ��Document
			Document doc = sb.build(source);
			// ȡ�ĸ�Ԫ��
			Element root = doc.getRootElement();
			//System.out.println(root.getName());// �����Ԫ�ص����ƣ����ԣ�
			
			
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
			// TODO �Զ����� catch ��
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