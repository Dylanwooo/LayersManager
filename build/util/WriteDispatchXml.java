package com.zhjy.qzfwgz.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import com.zhjy.qzfwgz.vo.ToDispatchVO;
import com.zhjy.qzfwgz.vo.ToEventVO;
import com.zhjy.qzfwgz.util.DateUtil;
import com.zhjy.qzfwgz.util.StringUtil;

public class WriteDispatchXml {

	
	public String getNewsByteArray(ToDispatchVO vo) throws IOException {
		ByteArrayOutputStream byteOPStream = null;
		// 先建立xml文档
	
		Element root = new Element("DISPATCHINFO");
		Document doc = new Document(root);
    Element idElt = new Element("ID");
    idElt.addContent(StringUtil.dealNull(vo.getId()));
		root.addContent(idElt);
		Element dispatchidElt = new Element("DISPATCHID");
		dispatchidElt.addContent(StringUtil.dealNull(vo.getDispatchId()));
		root.addContent(dispatchidElt);
		Element curunitidElt = new Element("CURUNITID");
		curunitidElt.addContent(StringUtil.dealNull(vo.getCurunitId()));
		root.addContent(curunitidElt);
		Element acttimesstateidElt = new Element("ACTTIMESSTATEID");
		acttimesstateidElt.addContent(StringUtil.dealNull(vo.getActTimesStateId()));
		root.addContent(acttimesstateidElt);
		Element dispatchdateElt = new Element("DISPATCHDATE");
		dispatchdateElt.addContent(StringUtil.dealNull(vo.getDispatchDate()));
		root.addContent(dispatchdateElt);
		Element eventstateidElt = new Element("EVENTSTATEID");
		eventstateidElt.addContent(StringUtil.dealNull(vo.getEventStateId()));
		root.addContent(eventstateidElt);
		Element archivedateElt = new Element("ARCHIVEDATE");
		archivedateElt.addContent(StringUtil.dealNull(vo.getArchiveDate()));
		root.addContent(archivedateElt);
		Element dispatchcountElt = new Element("DISPATCHCOUNT");
		dispatchcountElt.addContent(StringUtil.dealNull(vo.getDispatchCount()));
		root.addContent(dispatchcountElt);
		Element reworkcountElt = new Element("REWORKCOUNT");
		reworkcountElt.addContent(StringUtil.dealNull(vo.getReworkCount()));
		root.addContent(reworkcountElt);
		Element transbackcountElt = new Element("TRANSBACKCOUNT");
		transbackcountElt.addContent(StringUtil.dealNull(vo.getTransbackCount()));
		root.addContent(transbackcountElt);
		
		
		XMLOutputter outputter = new XMLOutputter();
		byteOPStream = new ByteArrayOutputStream();
		outputter.output(doc, byteOPStream);
		byteOPStream.close();
		return new String(byteOPStream.toByteArray(),"UTF-8");
			
	}
	
	public static void main(String[] arg) {
		/*WriteDispatchXml writeXML=new WriteDispatchXml();
		try {
			ToDispatchVO vo=new ToDispatchVO();
			//vo.setAddress("房管局");
			//vo.setRelid((long)333);
			//vo.setTasknum("YH3333");
			//vo.setRecdispnum("333");
			//vo.setSystemid((long)444);
			//vo.setSystemname("系统服务");
			writeXML.getNewsByteArray(vo);
			String xml = writeXML.getNewsByteArray(vo);
			System.out.println(xml);	
			
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}*/
		
	String link="http://10.165.153.16:8001/qzfwzw//qzfwgz/sjfx/sjfxIndex.jsp?searchId=7704&searchSysId=6025&loginId=@userId@&pwd=@pwd@&isOpen=1&search__TRAN_DID__2__string=gsj&search__APPEAL_DATE__0__date=#appeal_date__0__date#&search__APPEAL_DATE__1__date=#appeal_date__1__date#&&search__BACK_FLAG__2__number=0";
	link=link.replace("#appeal_date__1__date#","2010-06-01 00:00:00");
	System.out.println(link);
	
	}
}