package com.zhjy.qzfwgz.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;
import com.zhjy.qzfwgz.vo.ToEventVO;
import com.zhjy.qzfwgz.util.StringUtil;

public class WriteEventXml {

	
	public String getNewsByteArray(ToEventVO vo) throws IOException {
		ByteArrayOutputStream byteOPStream = null;
		// 先建立xml文档
	
		Element root = new Element("EVENTINFO");
		Document doc = new Document(root);
    Element id = new Element("ID");
		id.addContent(StringUtil.dealNull(vo.getId()));
		root.addContent(id);
		Element eventid = new Element("EVENTID");
		eventid.addContent(StringUtil.dealNull(vo.getEventid()));
		root.addContent(eventid);
		Element relid = new Element("RELID");
		relid.addContent(StringUtil.dealNull(vo.getRelid()));
		root.addContent(relid);
		Element tasknum = new Element("TASKNUM");
		tasknum.addContent(StringUtil.dealNull(vo.getTasknum()));
		root.addContent(tasknum);
		Element recdispnum = new Element("RECDISPNUM");
		recdispnum.addContent(StringUtil.dealNull(vo.getRecdispnum()));
		root.addContent(recdispnum);
		Element systemid = new Element("SYSTEMID");
		systemid.addContent(StringUtil.dealNull(vo.getSystemid()));
		root.addContent(systemid);
		Element systemname = new Element("SYSTEMNAME");
		systemname.addContent(StringUtil.dealNull(vo.getSystemname()));
		root.addContent(systemname);
		Element eventsrcid = new Element ("EVENTSRCID");
		eventsrcid.addContent(StringUtil.dealNull(vo.getEventsrcid()));
		root.addContent(eventsrcid);
		Element eventsrcname = new Element ("EVENTSRCNAME");
		eventsrcname.addContent(StringUtil.dealNull(vo.getEventsrcname()));
		root.addContent(eventsrcname);
		Element subeventsrcid = new Element ("SUBEVENTSRCID");//新加
		subeventsrcid.addContent(StringUtil.dealNull(vo.getSubEventsrcid()));
		root.addContent(subeventsrcid);
		Element  subEventsrcNameELT = new Element ("SUBEVENTSRCNAME");
		subEventsrcNameELT.addContent(StringUtil.dealNull(vo.getSubEventSrcName()));
		root.addContent(subEventsrcNameELT);
		Element eventstateid = new Element ("EVENTSTATEID");
		eventstateid.addContent(StringUtil.dealNull(vo.getEventstateid()));
		root.addContent(eventstateid);
		Element eventstatename = new Element ("EVENTSTATENAME");
		eventstatename.addContent(StringUtil.dealNull(vo.getEventstatename()));
		root.addContent(eventstatename);
		Element eventtypeid = new Element ("EVENTTYPEID");
		eventtypeid.addContent(StringUtil.dealNull(vo.getEventTypeID()));
		root.addContent(eventtypeid);
		Element eventtypename = new Element ("EVENTTYPENAME");
		eventtypename.addContent(StringUtil.dealNull(vo.getEventTypeName()));
		root.addContent(eventtypename);
		Element maintypeid = new Element ("MAINTYPEID");
		maintypeid.addContent(StringUtil.dealNull(vo.getMainTypeID()));
		root.addContent(maintypeid);
		Element maintypename = new Element ("MAINTYPENAME");
		maintypename.addContent(StringUtil.dealNull(vo.getMainTypeName()));
		root.addContent(maintypename);
		Element subtypeid = new Element ("SUBTYPEID");
		subtypeid.addContent(StringUtil.dealNull(vo.getSubTypeID()));
		root.addContent(subtypeid);
		Element subtypename = new Element ("SUBTYPENAME");
		subtypename.addContent(StringUtil.dealNull(vo.getSubTypeName()));
		root.addContent(subtypename);
		Element partcode = new Element ("PARTCODE");
		partcode.addContent(StringUtil.dealNull(vo.getPartcode()));
		root.addContent(partcode);
		Element damagegradeid = new Element ("DAMAGEGRADEID");
		damagegradeid.addContent(StringUtil.dealNull(vo.getDamagegradeid()));
		root.addContent(damagegradeid);
		Element damagegradename = new Element ("DAMAGEGRADENAME");
		damagegradename.addContent(StringUtil.dealNull(vo.getDamagegradename()));
		root.addContent(damagegradename);
		Element appealtheme = new Element ("APPEALTHEME");
		appealtheme.addContent(StringUtil.dealNull(vo.getAppealtheme()));
		root.addContent(appealtheme);
		Element eventdesc = new Element ("EVENTDESC");
		eventdesc.addContent(StringUtil.dealNull(vo.getEventdesc()));
		root.addContent(eventdesc);
		Element address = new Element ("ADDRESS");
		address.addContent(StringUtil.dealNull(vo.getAddress()));
		root.addContent(address);		
		Element dutyrgncode = new Element ("DUTYRGNCODE");
		dutyrgncode.addContent(StringUtil.dealNull(vo.getDutyrgncode()));
		root.addContent(dutyrgncode);
		Element createtime = new Element ("CREATETIME");
		createtime.addContent(StringUtil.dealNull(vo.getCreatetime()));
		root.addContent(createtime);
		Element operatorid = new Element ("OPERATORID");
		operatorid.addContent(StringUtil.dealNull(vo.getOperatorid()));
		root.addContent(operatorid);
		Element operatorname = new Element ("OPERATORNAME");
		operatorname.addContent(StringUtil.dealNull(vo.getOperatorname()));
		root.addContent(operatorname);
		Element patrolcardid = new Element ("PATROLCARDID");
		patrolcardid.addContent(StringUtil.dealNull(vo.getPatrolcardid()));
		root.addContent(patrolcardid);
		Element patrolcardname = new Element ("PATROLNAME");
		patrolcardname.addContent(StringUtil.dealNull(vo.getPatrolname()));
		root.addContent(patrolcardname);
		Element patroltelmobile = new Element ("PATROLTELMOBILE");
		patroltelmobile.addContent(StringUtil.dealNull(vo.getPatroltelmobile()));
		root.addContent(patroltelmobile);
		Element reportname = new Element ("REPORTNAME");
		reportname.addContent(StringUtil.dealNull(vo.getReportname()));
		root.addContent(reportname);
		Element reporttelmobile = new Element ("REPORTTELMOBILE");
		reporttelmobile.addContent(StringUtil.dealNull(vo.getReporttelmobile()));
		root.addContent(reporttelmobile);
		Element retutnvistitflag = new Element ("RETURNVISITFLAG");
		retutnvistitflag.addContent(StringUtil.dealNull(vo.getReturnvisitflag()));
		root.addContent(retutnvistitflag);
		Element returnvisittype = new Element ("RETURNVISITTYPE");
		returnvisittype.addContent(StringUtil.dealNull(vo.getReturnvisittype()));
		root.addContent(returnvisittype);
		Element returnvisitobject = new Element ("RETURNVISITOBJECT");
		returnvisitobject.addContent(StringUtil.dealNull(vo.getReturnvisitobject()));
		root.addContent(returnvisitobject);
		Element insttime = new Element ("INSTTIME");
		insttime.addContent(StringUtil.dealNull(vo.getInsttime()));
		root.addContent(insttime);
		Element archivedate = new Element ("ARCHIVEDATE");
		archivedate.addContent(StringUtil.dealNull(vo.getArchivedate()));
		root.addContent(archivedate);
		Element publishdate = new Element ("PUBLISHDATE");
		publishdate.addContent(StringUtil.dealNull(vo.getPublishdate()));
		root.addContent(publishdate);
		Element publishflag = new Element ("PUBLISHFLAG");
		publishflag.addContent(StringUtil.dealNull(vo.getPublishflag()));
		root.addContent(publishflag);
		/*Element dispatchcount = new Element ("DISPATCHCOUNT");
		dispatchcount.addContent(StringUtil.dealNull(vo.getDispatchcount()));
		root.addContent(dispatchcount);*/
		/*Element curunitid = new Element ("CURUNITID");
		curunitid.addContent(StringUtil.dealNull(vo.getCurunitid()));
		root.addContent(curunitid);
		Element acttimesstateid = new Element ("ACTTIMESSTATEID");
		acttimesstateid.addContent(StringUtil.dealNull(vo.getCurunitid()));
		root.addContent(acttimesstateid);
		Element reworkcount = new Element ("REWORKCOUNT");
		reworkcount.addContent(StringUtil.dealNull(vo.getReworkcount()));
		root.addContent(reworkcount);
		Element transbackcount = new Element ("TRANSBACKCOUNT");
		transbackcount.addContent(StringUtil.dealNull(vo.getTransbackcount()));
		root.addContent(transbackcount);*/
		
		Element  eventGradeidELT = new Element ("EVENTGRADEID");
		eventGradeidELT.addContent(StringUtil.dealNull(vo.getEventGradeid()));
		root.addContent(eventGradeidELT);
		Element  eventGradeNameELT = new Element ("EVENTGRADENAME");
		eventGradeNameELT.addContent(StringUtil.dealNull(vo.getEventGradeName()));
		root.addContent(eventGradeNameELT);
		Element  streetIdELT = new Element ("STREETCODE");
		streetIdELT.addContent(StringUtil.dealNull(vo.getStreetCode()));
		root.addContent(streetIdELT);
		Element  streetNameELT = new Element ("STREETNAME");
		streetNameELT.addContent(StringUtil.dealNull(vo.getStreetName()));
		root.addContent(streetNameELT);//COMMUNITYNAME
		Element  communityIdELT = new Element ("COMMUNITYCODE");
		communityIdELT.addContent(StringUtil.dealNull(vo.getCommunityCode()));
		root.addContent(communityIdELT);
		Element  communityNameELT = new Element ("COMMUNITYNAME");
		communityNameELT.addContent(StringUtil.dealNull(vo.getCommunityName()));
		root.addContent(communityNameELT);
		Element  cellIdELT = new Element ("CELLCODE");
		cellIdELT.addContent(StringUtil.dealNull(vo.getCellCode()));
		root.addContent(cellIdELT);
		Element  cellNameELT = new Element ("CELLNAME");
		cellNameELT.addContent(StringUtil.dealNull(vo.getCellName()));
		root.addContent(cellNameELT);
		Element  coordinateXELT = new Element ("COORDINATEX");
		coordinateXELT.addContent(StringUtil.dealNull(vo.getCoordinateX()));
		root.addContent(coordinateXELT);
		Element  coordinateYELT = new Element ("COORDINATEY");
		coordinateYELT.addContent(StringUtil.dealNull(vo.getCoordinateY()));
		root.addContent(coordinateYELT);
		
		
		Element  returnVisitNameELT = new Element ("RETURNVISITNAME");
		returnVisitNameELT.addContent(StringUtil.dealNull(vo.getReturnVisitName()));
		root.addContent(returnVisitNameELT);
		Element  reportGenderidELT = new Element ("REPORTGENDERID");
		reportGenderidELT.addContent(StringUtil.dealNull(vo.getReportGenderid()));
		root.addContent(reportGenderidELT);
		Element  dealTypeELT = new Element ("DEALTYPE");
		dealTypeELT.addContent(StringUtil.dealNull(vo.getDealType()));
		root.addContent(dealTypeELT);
		Element  signFlagELT = new Element ("SIGNFLAG");
		signFlagELT.addContent(StringUtil.dealNull(vo.getSignFlag()));
		root.addContent(signFlagELT);
		Element  dispatchELT = new Element ("DISPATCHFLAG");
		dispatchELT.addContent(StringUtil.dealNull(vo.getDispatchFlag()));
		root.addContent(dispatchELT);
		Element  similaridELT = new Element ("SIMILARID");
		similaridELT.addContent(StringUtil.dealNull(vo.getSimilarId()));
		root.addContent(similaridELT);

		XMLOutputter outputter = new XMLOutputter();
		byteOPStream = new ByteArrayOutputStream();
		outputter.output(doc, byteOPStream);
		byteOPStream.close();
		return new String(byteOPStream.toByteArray(),"UTF-8");
	}
	public static void main(String[] arg) {
		WriteEventXml writeXML=new WriteEventXml();
		try {
			ToEventVO vo=new ToEventVO();
			vo.setAddress("房管局");
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
		}
	}
}
