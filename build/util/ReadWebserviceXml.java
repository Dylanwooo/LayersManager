package  com.zhjy.qzfwgz.util;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.xml.sax.InputSource;

import com.zhjy.qzfwgz.vo.WebserviceVO;



public class ReadWebserviceXml{
	private static Long parselong(String pl){
		long buffer=0;
		try{
			 buffer=Long.parseLong(pl);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			return null;	
		} 
		return new Long(buffer);
		
	}
	

	public static WebserviceVO xmlElements(String xmlDoc) {
		//System.out.println("xmlDoc____________________________"+xmlDoc);
		WebserviceVO webserviceVO = new WebserviceVO();
		// 创建一个新的字符串
		StringReader read = new StringReader(xmlDoc);
		// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
		InputSource source = new InputSource(read);
		// 创建一个新的SAXBuilder
		SAXBuilder sb = new SAXBuilder();
		List result = null;
		try {
			result = new ArrayList();   
			// 通过输入源构造一个Document
			Document doc = sb.build(source);
			// 取的根元素
			Element root = doc.getRootElement();
			//System.out.println(root.getName());// 输出根元素的名称（测试）
			
			if(root.getChild("ID")!=null) 
			webserviceVO.setId(parselong(root.getChild("ID").getText()));
			if(root.getChild("ITEMID")!=null) 
				webserviceVO.setItemId(parselong(root.getChild("ITEMID").getText()));
			if(root.getChild("RESULTINFO")!=null) 
			webserviceVO.setResuleInfo(root.getChild("RESULTINFO").getText());
			if(root.getChild("RESULTTYPE")!=null) 
			webserviceVO.setResultType(parselong(root.getChild("RESULTTYPE").getText()));
		}	catch (Exception e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			} 
			return webserviceVO;
	}
	
	public static String createReturnString(WebserviceVO vo) {
		ByteArrayOutputStream byteOPStream = null;
		// 先建立xml文档
		Element root = new Element("RESULT");
		Document doc = new Document(root);
        Element id = new Element("ID");
		id.addContent(StringUtil.dealNull(vo.getId()));
		root.addContent(id);
    Element resultTypeElt = new Element("RESULTTYPE");
    resultTypeElt.addContent(StringUtil.dealNull(vo.getResultType()));
		root.addContent(resultTypeElt);
	  Element itemIdElt = new Element("ITEMID");
	  itemIdElt.addContent(StringUtil.dealNull(vo.getItemId()));
		root.addContent(itemIdElt);
		Element resuleInfoElt = new Element("RESULTINFO");
		resuleInfoElt.addContent(StringUtil.dealNull(vo.getResuleInfo()));
		root.addContent(resuleInfoElt);
		
		XMLOutputter outputter = new XMLOutputter();
		byteOPStream = new ByteArrayOutputStream();
		String returnString="";
		try {
			outputter.output(doc, byteOPStream);
		  byteOPStream.close();
		  returnString=new String(byteOPStream.toByteArray(),"UTF-8");
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return returnString;
			
	}
	
	
	public static void main(String[] args) {
		ReadWebserviceXml readXml = new ReadWebserviceXml();
		String xml ="<?xml version='1.0' encoding='UTF-8'?>" +
		"<RESULT>" +
		"<ID>2345</ID>" +
		"<ITEMID>2346</ITEMID>" +
		"<DISPATCHID>123123</DISPATCHID>" +
		"<RESULTTYPE>0</RESULTTYPE>" +
		"<RESULTINFO>成功或失败的说明</RESULTINFO>" +
		"</RESULT>";
		System.out.println(readXml.xmlElements(xml));		
	}
}