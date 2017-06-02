package com.zhjy.qzfwgz.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import com.zhjy.qzfwgz.vo.ToEvalStateInfoVO;
import com.zhjy.qzfwgz.vo.ToEventVO;
import com.zhjy.qzfwgz.util.StringUtil;


  public class WriteEvalStateInfoXml{
  	public String getNewsByteArray(ToEvalStateInfoVO vo) throws IOException{
  		ByteArrayOutputStream byteOPStream = null;
  		// 先建立xml文档
  	
  		Element root = new Element("STATINFO");
  		Document doc = new Document(root);
      Element eventidELT = new Element("EVENTID");
      eventidELT.addContent(StringUtil.dealNull(vo.getEventId()));
  		root.addContent(eventidELT);
  		Element detailnumELT = new Element("DETAILNUM");
  		detailnumELT.addContent(StringUtil.dealNull(vo.getDetailNum()));
  		root.addContent(detailnumELT);
  		Element tasknumELT = new Element("TASKNUM");
  		tasknumELT.addContent(StringUtil.dealNull(vo.getTaskNum()));
  		root.addContent(tasknumELT);
  		Element createtimeELT = new Element("CREATETIME");
  		createtimeELT.addContent(StringUtil.dealNull(vo.getCreateTime()));
  		root.addContent(createtimeELT);
  		Element insttimeELT = new Element("INSTTIME");
  		insttimeELT.addContent(StringUtil.dealNull(vo.getInstTime()));
  		root.addContent(insttimeELT);
  		Element recdispnumELT = new Element("RECDISPNUM");
  		recdispnumELT.addContent(StringUtil.dealNull(vo.getRecDispNum()));
  		root.addContent(recdispnumELT);
  		Element dispatchtimeELT = new Element("DISPATCHTIME");
  		dispatchtimeELT.addContent(StringUtil.dealNull(vo.getDispatchTime()));
  		root.addContent(dispatchtimeELT);
  		Element unitidELT = new Element ("UNITID");
  		unitidELT.addContent(StringUtil.dealNull(vo.getUnitId()));
  		root.addContent(unitidELT);
  		Element unitstandardidELT = new Element ("UNITSTANDARDID");
  		unitstandardidELT.addContent(StringUtil.dealNull(vo.getUnitStandardId()));
  		root.addContent(unitstandardidELT);
  		Element unitnameELT = new Element ("UNITNAME");
  		unitnameELT.addContent(StringUtil.dealNull(vo.getUnitName()));
  		root.addContent(unitnameELT);
  		Element  unittypeidELT = new Element ("UNITTYPEID");
  		unittypeidELT.addContent(StringUtil.dealNull(vo.getUnitTypeId()));
  		root.addContent(unittypeidELT);
  		Element endtimeELT = new Element ("ENDTIME");
  		endtimeELT.addContent(StringUtil.dealNull(vo.getEndTime()));
  		root.addContent(endtimeELT);
  		Element disposetimeELT = new Element ("DISPOSETIME");
  		disposetimeELT.addContent(StringUtil.dealNull(vo.getDisposeTime()));
  		root.addContent(disposetimeELT);
  		Element needdisposeELT = new Element ("NEEDDISPOSE");
  		needdisposeELT.addContent(StringUtil.dealNull(vo.getNeedDispose()));
  		root.addContent(needdisposeELT);
  		Element needarchiveELT = new Element ("NEEDARCHIVE");
  		needarchiveELT.addContent(StringUtil.dealNull(vo.getNeedArchive()));
  		root.addContent(needarchiveELT);
  		Element isarchivedELT = new Element ("ISARCHIVED");
  		isarchivedELT.addContent(StringUtil.dealNull(vo.getIsArchived()));
  		root.addContent(isarchivedELT);
  		Element isarchiveintimeELT = new Element ("ISARCHIVEINTIME");
  		isarchiveintimeELT.addContent(StringUtil.dealNull(vo.getIsArchiveinTime()));
  		root.addContent(isarchiveintimeELT);
  		Element overtimescoreELT = new Element ("OVERTIMESCORE");
  		overtimescoreELT.addContent(StringUtil.dealNull(vo.getOverTimeScore()));
  		root.addContent(overtimescoreELT);
  		Element wrongarchiveELT = new Element ("WRONGARCHIVE");
  		wrongarchiveELT.addContent(StringUtil.dealNull(vo.getWrongArchive()));
  		root.addContent(wrongarchiveELT);
  		Element multireportELT = new Element ("MULTIREPORT");
  		multireportELT.addContent(StringUtil.dealNull(vo.getMultiReport()));
  		root.addContent(multireportELT);
  		Element overtimescoredescELT = new Element ("OVERTIMESCOREDESC");
  		overtimescoredescELT.addContent(StringUtil.dealNull(vo.getOverTimeScoreDesc()));
  		root.addContent(overtimescoredescELT);
  		Element wrongarchivedescELT = new Element ("WRONGARCHIVEDESC");
  		wrongarchivedescELT.addContent(StringUtil.dealNull(vo.getWrongArchiveDesc()));
  		root.addContent(wrongarchivedescELT);
  		Element multireportdescELT = new Element ("MULTIREPORTDESC");
  		multireportdescELT.addContent(StringUtil.dealNull(vo.getMultiReportDesc()));
  		root.addContent(multireportdescELT);
  		Element eventsrcidELT = new Element ("EVENTSRCID");
  		eventsrcidELT.addContent(StringUtil.dealNull(vo.getEventSrcId()));
  		root.addContent(eventsrcidELT);
  		Element eventsrcnameELT = new Element ("EVENTSRCNAME");
  		eventsrcnameELT.addContent(StringUtil.dealNull(vo.getEventSrcName()));
  		root.addContent(eventsrcnameELT);
  		Element eventtypenameELT = new Element ("EVENTTYPENAME");
  		eventtypenameELT.addContent(StringUtil.dealNull(vo.getEventTypename()));
  		root.addContent(eventtypenameELT);
  		Element subtypenameELT = new Element ("SUBTYPENAME");
  		subtypenameELT.addContent(StringUtil.dealNull(vo.getSubTypeName()));
  		root.addContent(subtypenameELT);
  		Element eventtypeidELT = new Element ("EVENTTYPEID");
  		eventtypeidELT.addContent(StringUtil.dealNull(vo.getEventTypeId()));
  		root.addContent(eventtypeidELT);
  		Element subtypeidELT = new Element ("SUBTYPEID");
  		subtypeidELT.addContent(StringUtil.dealNull(vo.getSubTypeId()));
  		root.addContent(subtypeidELT);
  		Element maintypeidELT = new Element ("MAINTYPEID");
  		maintypeidELT.addContent(StringUtil.dealNull(vo.getMainTypeId()));
  		root.addContent(maintypeidELT);
  		Element maintypenameELT = new Element ("MAINTYPENAME");
  		maintypenameELT.addContent(StringUtil.dealNull(vo.getMainTypeName()));
  		root.addContent(maintypenameELT);
  		Element isarchiveovertimeELT = new Element ("ISARCHIVEOVERTIME");
  		isarchiveovertimeELT.addContent(StringUtil.dealNull(vo.getIsArchiveOverTime()));
  		root.addContent(isarchiveovertimeELT);
  		Element archivetimeELT = new Element ("ARCHIVETIME");
  		archivetimeELT.addContent(StringUtil.dealNull(vo.getArchiveTime()));
  		root.addContent(archivetimeELT);
  		
  		XMLOutputter outputter = new XMLOutputter();
  		byteOPStream = new ByteArrayOutputStream();
  		outputter.output(doc, byteOPStream);
  		byteOPStream.close();
  		return new String(byteOPStream.toByteArray(),"UTF-8");
  		
  	}
  	public static void main(String[] arg) {
  		WriteEvalStateInfoXml writeEvalStateInfoXml = new WriteEvalStateInfoXml();
  		try{
  			ToEvalStateInfoVO vo = new ToEvalStateInfoVO();
  			String xml = writeEvalStateInfoXml.getNewsByteArray(vo);
  			System.out.print(xml);
  		}catch (Exception e){
  			e.printStackTrace();
  		}
  	}
  	
  }