package com.zhjy.qzfwgz.util;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;
import com.zhjy.qzfwgz.vo.WebserviceDispatchVO;



public class ReadDispatchWebserviceXml{
	private static Long parselong(String pl){
		long buffer=0;
		try{
			 buffer=Long.parseLong(pl);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			return null;	
		} 
		return new Long(buffer);
		
	}
	public static WebserviceDispatchVO xmlElements(String xmlDoc) {
		//System.out.println("xmlDoc____________________________"+xmlDoc);
		WebserviceDispatchVO webserviceDispatchVO = new WebserviceDispatchVO();
		// ����һ���µ��ַ���
		StringReader read = new StringReader(xmlDoc);
		// �����µ�����ԴSAX ��������ʹ�� InputSource ������ȷ����ζ�ȡ XML ����
		InputSource source = new InputSource(read);
		// ����һ���µ�SAXBuilder
		SAXBuilder sb = new SAXBuilder();
		List result = null;
		try {
			result = new ArrayList();   
			// ͨ������Դ����һ��Document
			Document doc = sb.build(source);
			// ȡ�ĸ�Ԫ��
			Element root = doc.getRootElement();
			//System.out.println(root.getName());// �����Ԫ�ص����ƣ����ԣ�
			
			if(root.getChild("ID")!=null) 
			webserviceDispatchVO.setId(parselong(root.getChild("ID").getText()));
			if(root.getChild("DISPATCHID")!=null)
			webserviceDispatchVO.setDispatchId(parselong(root.getChild("DISPATCHID").getText()));
			if(root.getChild("RESULTINFO")!=null) 
			webserviceDispatchVO.setResuleInfo(root.getChild("RESULTINFO").getText());
			if(root.getChild("RESULTTYPE")!=null) 
			webserviceDispatchVO.setResultType(parselong(root.getChild("RESULTTYPE").getText()));
		}	catch (Exception e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			} 
			return webserviceDispatchVO;
	}
	public static void main(String[] args) {
		ReadDispatchWebserviceXml readXml = new ReadDispatchWebserviceXml();
		String xml ="<?xml version='1.0' encoding='UTF-8'?>" +
		"<RESULT>" +
		"<ID>2345</ID>" +
		"<DISPATCHID>123213</DISPATCHID>" +
		"<ITEMID>2346</ITEMID>" +
		"<RESULTTYPE>0</RESULTTYPE>" +
		"<RESULTINFO>�ɹ���ʧ�ܵ�˵��</RESULTINFO>" +
		"</RESULT>";
		System.out.println(readXml.xmlElements(xml));		
	}
}