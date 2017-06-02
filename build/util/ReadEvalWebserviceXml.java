package com.zhjy.qzfwgz.util;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

import com.zhjy.qzfwgz.vo.WebserviceEvalVO;

	public class ReadEvalWebserviceXml {
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
		
		public static WebserviceEvalVO  xmlElements(String xmlDoc) {
			//System.out.println("xmlDoc____________________________"+xmlDoc);
			WebserviceEvalVO webserviceEvalVO = new WebserviceEvalVO();
//		 创建一个新的字符串
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
				
				if(root.getChild("RESULTINFO")!=null) 
					webserviceEvalVO.setResuleInfo(root.getChild("RESULTINFO").getText());
				if(root.getChild("RESULTTYPE")!=null) 
					webserviceEvalVO.setResultType(parselong(root.getChild("RESULTTYPE").getText()));
			}	catch (Exception e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				} 
				return webserviceEvalVO;
		}
			
		public static void main(String[] args) {
			ReadEvalWebserviceXml readXml = new ReadEvalWebserviceXml();
			String xml ="<?xml version='1.0' encoding='UTF-8'?>" +
			"<RESULT>" +
			"<RESULTTYPE>0</RESULTTYPE>" +
			"<RESULTINFO>成功或失败的说明</RESULTINFO>" +
			"</RESULT>";
			System.out.println(readXml.xmlElements(xml));		
		}
	}