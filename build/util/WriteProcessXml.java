package com.zhjy.qzfwgz.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import com.zhjy.qzfwgz.vo.ToProcessVO;
import com.zhjy.qzfwgz.util.DateUtil;
import com.zhjy.qzfwgz.util.StringUtil;

public class WriteProcessXml {

	
	public String getNewsByteArray(ToProcessVO vo) throws IOException {
		ByteArrayOutputStream byteOPStream = null;
		// 先建立xml文档
		Element root = new Element("PROCESSINFO");
		Document doc = new Document(root);
        Element id = new Element("ID");
		id.addContent(StringUtil.dealNull(vo.getId()));
		root.addContent(id);
		Element eventstateid = new Element("EVENTSTATEID");
		eventstateid.addContent(StringUtil.dealNull(vo.getEventstateid()));
		root.addContent(eventstateid);
		Element eventstatename = new Element("EVENTSTATENAME");
		eventstatename.addContent(StringUtil.dealNull(vo.getEventstatename()));
		root.addContent(eventstatename);
		Element humanid = new Element("HUMANID");
		humanid.addContent(StringUtil.dealNull(vo.getHumanid()));
		root.addContent(humanid);
		Element humanname = new Element("HUMANNAME");
		humanname.addContent(StringUtil.dealNull(vo.getHumanname()));
		root.addContent(humanname);
		Element unitid = new Element("UNITID");
		unitid.addContent(StringUtil.dealNull(vo.getUnitid()));
		root.addContent(unitid);
		Element unitname = new Element("UNITNAME");
		unitname.addContent(StringUtil.dealNull(vo.getUnitname()));
		root.addContent(unitname);
		Element startdate = new Element ("STARTDATE");
		startdate.addContent(StringUtil.dealNull(vo.getStartdate()));
		root.addContent(startdate);
		Element enddate = new Element ("ENDDATE");
		enddate.addContent(StringUtil.dealNull(vo.getEnddate()));
		root.addContent(enddate);
		Element itemmemo = new Element ("ITEMMEMO");
		itemmemo.addContent(StringUtil.dealNull(vo.getItemmemo()));
		root.addContent(itemmemo);
		Element testin = new Element ("TESTIN");
		testin.addContent(StringUtil.dealNull(vo.getTestin()));
		root.addContent(testin);
		
		
		
		
		
		XMLOutputter outputter = new XMLOutputter();
		byteOPStream = new ByteArrayOutputStream();
		outputter.output(doc, byteOPStream);
		byteOPStream.close();
		return (new String(byteOPStream.toByteArray(),"UTF-8"));
	}
	public static void main(String[] arg) {
		WriteProcessXml writeXML=new WriteProcessXml();
		try {
			ToProcessVO vo=new ToProcessVO();
			//vo.setItemmemo("333");
			//vo.setId(new Long((long)444));
			writeXML.getNewsByteArray(vo);
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}
}
