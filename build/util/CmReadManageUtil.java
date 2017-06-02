package com.zhjy.qzfwgz.util;

import java.io.StringReader;
import java.util.Date;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

import com.zhjy.qzfwgz.coding.CmEventCoding;
import com.zhjy.qzfwgz.vo.CmAccessoriesFile;
import com.zhjy.qzfwgz.vo.CmEventInfo;
import com.zhjy.qzfwgz.vo.CmEventInfoAffVO;
import com.zhjy.qzfwgz.vo.CmManageEventInfo;


public class CmReadManageUtil {
	public static CmManageEventInfo dealEventInfoXml(String operationCode,
			String eventInfoXml) {
		CmManageEventInfo cmManageEventInfo = new CmManageEventInfo();
		if (CmEventCoding.INCEPT_OPERATION_CODE_TH.equals(operationCode)) {
			cmManageEventInfo = dealTHElementsXml(eventInfoXml);
		} else if (CmEventCoding.INCEPT_OPERATION_CODE_NOT.equals(operationCode)) {
			cmManageEventInfo = dealNOTElementsXml(eventInfoXml);
		} else if (CmEventCoding.INCEPT_OPERATION_CODE_JA.equals(operationCode)) {
			cmManageEventInfo = dealJAElementsXml(eventInfoXml);
		}
		return cmManageEventInfo;
	}

	public static CmManageEventInfo dealTHElementsXml(String eventInfoXml) {
		CmManageEventInfo cmManageEventInfo = new CmManageEventInfo();

		// 创建一个新的字符串
		StringReader read = new StringReader(eventInfoXml);
		// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
		InputSource source = new InputSource(read);
		// 创建一个新的SAXBuilder
		SAXBuilder sb = new SAXBuilder();
		try {
			// 通过输入源构造一个Document
			Document doc = sb.build(source);
			// 取的根元素
			Element root = doc.getRootElement();
			System.out.println(root.getName());// 输出根元素的名称（测试）
			if(root.getChild("ID")!=null)
			cmManageEventInfo.setAppealId(QzfwgzUtil.stringPassLong(root.getChild(
					"ID").getText()));
			if(root.getChild("TRANSBACKOPINION")!=null)
			cmManageEventInfo.setDealIdea(QzfwgzUtil.getNull(root.getChild(
					"TRANSBACKOPINION").getText()));
			if(root.getChild("TRANSBACKOPINION")!=null)
			cmManageEventInfo.setJieanIdea(QzfwgzUtil.getNull(root.getChild("TRANSBACKOPINION").getText()));
			if(root.getChild("TRANSBACKDATE")!=null)
			cmManageEventInfo.setDealTime(QzfwgzUtil.getNull(root.getChild(
					"TRANSBACKDATE").getText()));
			if(root.getChild("TRANSBACKDATE")!=null)
			cmManageEventInfo.setOperatorName(QzfwgzUtil.getNull(root.getChild(
					"TRANSBACKDATE").getText()));
			cmManageEventInfo.setInceptType(CmEventCoding.CM_EVENT_STATE_TH);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cmManageEventInfo;

	}

	public static CmManageEventInfo dealNOTElementsXml(String eventInfoXml) {
		CmManageEventInfo cmManageEventInfo = new CmManageEventInfo();
		// 创建一个新的字符串
		StringReader read = new StringReader(eventInfoXml);
		// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
		InputSource source = new InputSource(read);
		// 创建一个新的SAXBuilder
		SAXBuilder sb = new SAXBuilder();
		try {
			// 通过输入源构造一个Document
			Document doc = sb.build(source);
			// 取的根元素
			Element root = doc.getRootElement();
			System.out.println(root.getName());// 输出根元素的名称（测试）
			if(root.getChild("ID")!=null)
			cmManageEventInfo.setAppealId(QzfwgzUtil.stringPassLong(root.getChild(
					"ID").getText()));
			if(root.getChild("CANCELOPINION")!=null)
			cmManageEventInfo.setDealIdea(QzfwgzUtil.getNull(root.getChild(
					"CANCELOPINION").getText()));
			if(root.getChild("CANCELOPINION")!=null)
			cmManageEventInfo.setJieanIdea(QzfwgzUtil.getNull(root.getChild("CANCELOPINION").getText()));
			if(root.getChild("CANCELDATE")!=null)
			cmManageEventInfo.setDealTime(QzfwgzUtil.getNull(root.getChild(
					"CANCELDATE").getText()));
			if(root.getChild("CANCELHUMAN")!=null)
			cmManageEventInfo.setOperatorName(QzfwgzUtil.getNull(root.getChild(
					"CANCELHUMAN").getText()));
			cmManageEventInfo.setInceptType(CmEventCoding.CM_EVENT_STATE_NOT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cmManageEventInfo;
	}
	public static CmManageEventInfo dealJAElementsXml(String eventInfoXml){
		CmManageEventInfo cmManageEventInfo=new CmManageEventInfo();
//	创建一个新的字符串
		StringReader read = new StringReader(eventInfoXml);
		// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
		InputSource source = new InputSource(read);
		// 创建一个新的SAXBuilder
		SAXBuilder sb = new SAXBuilder();
		try {
			// 通过输入源构造一个Document
			Document doc = sb.build(source);
			// 取的根元素
			Element root = doc.getRootElement();
			System.out.println(root.getName());// 输出根元素的名称（测试）
			if(root.getChild("ID")!=null)
			cmManageEventInfo.setAppealId(QzfwgzUtil.stringPassLong(root.getChild("ID").getText()));
			if(root.getChild("DEALDESC")!=null)
			cmManageEventInfo.setDealIdea(QzfwgzUtil.getNull(root.getChild("DEALDESC").getText()));
			if(root.getChild("DEALUNIT")!=null)
			cmManageEventInfo.setDealOrg(QzfwgzUtil.getNull(root.getChild("DEALUNIT").getText()));
			if(root.getChild("DEALDATE")!=null)
			cmManageEventInfo.setDealTime(QzfwgzUtil.getNull(root.getChild("DEALDATE").getText()));
			if(root.getChild("ARCHIVEOPINION")!=null)
			cmManageEventInfo.setJieanIdea(QzfwgzUtil.getNull(root.getChild("ARCHIVEOPINION").getText()));
			if(root.getChild("ARCHIVEDATE")!=null)
			cmManageEventInfo.setJieanTime(QzfwgzUtil.getNull(root.getChild("ARCHIVEDATE").getText()));
			if(root.getChild("ARCHIVEHUMAN")!=null)
			cmManageEventInfo.setOperatorName(QzfwgzUtil.getNull(root.getChild("ARCHIVEHUMAN").getText()));
			cmManageEventInfo.setInceptType(CmEventCoding.CM_EVENT_STATE_JA);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cmManageEventInfo;
		
	}
	public static CmEventInfo dealCmEventInfoXml(String eventInfoXml){
		CmEventInfo cmEventInfo=new CmEventInfo();
		CmEventInfoAffVO cmEventInfoAffVO =new CmEventInfoAffVO();
		
//	创建一个新的字符串
		StringReader read = new StringReader(eventInfoXml);
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
			if(root.getChild("ID")!=null)
			cmEventInfo.setEventId(QzfwgzUtil.stringPassLong(root.getChild("ID").getText()));	
			if(root.getChild("SYSTEMID")!=null)
			cmEventInfo.setSystemid(QzfwgzUtil.stringPassInteger(root.getChild("SYSTEMID").getText()));
			if(root.getChild("EVENTDESC")!=null)
			cmEventInfo.setEventdesc(QzfwgzUtil.getNull(root.getChild("EVENTDESC").getText()));
			if(root.getChild("ADDRESS")!=null)
			cmEventInfo.setAddress(QzfwgzUtil.getNull(root.getChild("ADDRESS").getText()));
			if(root.getChild("CREATETIME")!=null)
			cmEventInfo.setCreatetime(QzfwgzUtil.getNull(root.getChild("CREATETIME").getText()));
			if(root.getChild("OPERATORNAME")!=null)
			cmEventInfo.setOperatorname(QzfwgzUtil.getNull(root.getChild("OPERATORNAME").getText()));
			if(root.getChild("DEALOPINION")!=null)
			cmEventInfo.setDealopinion(QzfwgzUtil.getNull(root.getChild("DEALOPINION").getText()));
			if(root.getChild("DISPATCHHUMAN")!=null)
			cmEventInfo.setDispatchhuman(QzfwgzUtil.getNull(root.getChild("DISPATCHHUMAN").getText()));
			cmEventInfo.setCmType(CmEventCoding.CM_TYPE_INCEPT);
			cmEventInfo.setState(CmEventCoding.CM_EVENT_STATE_JS);
			//审批意见
			if(root.getChild("APPROVEOPINION")!=null)
			cmEventInfo.setApproveopinion(QzfwgzUtil.getNull(root.getChild("APPROVEOPINION").getText()));
			if(root.getChild("APPROVEHUMAN")!=null)
			cmEventInfo.setApprovehuman(QzfwgzUtil.getNull(root.getChild("APPROVEHUMAN").getText()));
			if(root.getChild("APPROVEHUMAN")!=null)
			cmEventInfo.setApprovehuman(QzfwgzUtil.getNull(root.getChild("APPROVEHUMAN").getText()));
			if(root.getChild("REPLYCONTENT")!=null)
				cmEventInfo.setReplycontent(QzfwgzUtil.getNull(root.getChild("REPLYCONTENT").getText()));
			//新添加
			dealCmEventAffInfo(root, cmEventInfoAffVO);
			cmEventInfo.setCmEventInfoAffVO(cmEventInfoAffVO);
			
			if(root.getChild("APPEALTHEME")!=null)
				cmEventInfo.setAppealtheme(QzfwgzUtil.getNull(root.getChild("APPEALTHEME").getText()));
			if(root.getChild("SIGNFLAG")!=null)
				cmEventInfo.setSignflag(QzfwgzUtil.getNull(root.getChild("SIGNFLAG").getText()));
			if(root.getChild("REPORTNAME")!=null)
				cmEventInfo.setReportname(QzfwgzUtil.getNull(root.getChild("REPORTNAME").getText()));
			if(root.getChild("REPORTTELMOBILE")!=null)
				cmEventInfo.setReporttelmobile(QzfwgzUtil.getNull(root.getChild("REPORTTELMOBILE").getText()));
			if(root.getChild("REPORTGENDERID")!=null)
				cmEventInfo.setReportgenderid(QzfwgzUtil.getNull(root.getChild("REPORTGENDERID").getText()));
			if(root.getChild("RETURNVISITFLAG")!=null)
				cmEventInfo.setReturnvisitflag(QzfwgzUtil.getNull(root.getChild("RETURNVISITFLAG").getText()));
			if(root.getChild("RETURNVISITTYPE")!=null)
				cmEventInfo.setReturnvisittype(QzfwgzUtil.getNull(root.getChild("RETURNVISITTYPE").getText()));
			if(root.getChild("RETURNVISITNAME")!=null)
				cmEventInfo.setReturnvisitname(QzfwgzUtil.getNull(root.getChild("RETURNVISITNAME").getText()));
			if(root.getChild("RETURNVISITOBJECT")!=null)
				cmEventInfo.setReturnvisitobject(QzfwgzUtil.getNull(root.getChild("RETURNVISITOBJECT").getText()));
			if(root.getChild("DEALTYPE")!=null)
				cmEventInfo.setDealtype(QzfwgzUtil.getNull(root.getChild("DEALTYPE").getText()));
			if(root.getChild("DEALFLAG")!=null)
				cmEventInfo.setDealFlag(QzfwgzUtil.getNull(root.getChild("DEALFLAG").getText()));
			Element adjunctElt = (Element) root.getChild("ADJUNCT");
	    if(adjunctElt!=null){
      	List itemList = adjunctElt.getChildren();
      	if(itemList!=null && itemList.size()>0){
      		for(int i=0;i<itemList.size();i++){
      			Element itemElt=(Element)itemList.get(i);
      			CmAccessoriesFile cmAccessoriesFile=new CmAccessoriesFile();
      			cmAccessoriesFile.setAppealType(QzfwgzUtil.stringPassInteger(itemElt.getChild("TYPE").getText()));
      			String fileName=itemElt.getChild("FILENAME").getText();
      			String fileContent=itemElt.getChild("CONTENT").getText();
      			if(!QzfwgzUtil.isNull(fileContent) && !QzfwgzUtil.isNull(fileName)){
      				fileName=(new Date()).getTime()+"_"+fileName;
      				FileUtil fileUtil=new FileUtil();
							fileUtil.save(Base64.decode(fileContent), CmEventCoding.SRCPATH, fileName);
							cmAccessoriesFile.setFileName(fileName);
      			}
      			cmEventInfo.getMenageList().add(cmAccessoriesFile);
      			
      		}
      		
      		
      	}
	    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cmEventInfo;
		
	}
	public static CmEventInfoAffVO dealCmEventAffInfo(Element root ,CmEventInfoAffVO cmEventInfoAffVO){
		//System.out.println(root);
		
/*		if(root.getChild("RelID")!=null)
			cmEventInfoAffVO.setRelId(QzfwgzUtil.stringPassLong(root.getChild("RelID").getText()));*/
		if(root.getChild("EVENTTYPEID")!=null)
			cmEventInfoAffVO.setEventTypeid(QzfwgzUtil.stringPassLong(root.getChild("EVENTTYPEID").getText()));
			
		if(root.getChild("MAINTYPEID")!=null)
			cmEventInfoAffVO.setMainTypeid(QzfwgzUtil.stringPassLong(root.getChild("MAINTYPEID").getText()));
		if(root.getChild("SUBTYPEID")!=null)
			cmEventInfoAffVO.setSubTypeid(QzfwgzUtil.stringPassLong(root.getChild("SUBTYPEID").getText()));
		if(root.getChild("STREETCODE")!=null)
			cmEventInfoAffVO.setStreetCode(QzfwgzUtil.stringPassLong(root.getChild("STREETCODE").getText()));
		if(root.getChild("COMMUNITYCODE")!=null)
			cmEventInfoAffVO.setCommunityCode(QzfwgzUtil.stringPassLong(root.getChild("COMMUNITYCODE").getText()));
		if(root.getChild("CELLCODE")!=null)
			cmEventInfoAffVO.setCellCode(QzfwgzUtil.stringPassLong(root.getChild("CELLCODE").getText()));
		if(root.getChild("COORDINATEX")!=null)
			cmEventInfoAffVO.setCoordinateX((QzfwgzUtil.stringPassDouble(root.getChild("COORDINATEX").getText())));
		if(root.getChild("COORDINATEY")!=null)
			cmEventInfoAffVO.setCoordinateY((QzfwgzUtil.stringPassDouble(root.getChild("COORDINATEY").getText())));
		if(root.getChild("RELRECDISPNUM")!=null)
			cmEventInfoAffVO.setRelRecdispnum(root.getChild("RELRECDISPNUM").getText());
		if(root.getChild("SUBEVENTSRCID")!=null)
			cmEventInfoAffVO.setSubEventsrcid(QzfwgzUtil.stringPassLong(root.getChild("SUBEVENTSRCID").getText()));
		if(root.getChild("EVENTGRADEID")!=null)
			cmEventInfoAffVO.setEventGradeid(QzfwgzUtil.stringPassLong(root.getChild("EVENTGRADEID").getText()));
		if(root.getChild("SIMILARID")!=null)
			cmEventInfoAffVO.setSimilarId(QzfwgzUtil.stringPassLong(root.getChild("SIMILARID").getText()));
		/*2010-09-07黄基强增加
		if(root.getChild("REPORTNAME")!=null)
			cmEventInfoAffVO.setReportname(QzfwgzUtil.getNull(root.getChild("REPORTNAME").getText()));
		if(root.getChild("REPORTTELMOBILE")!=null)
			cmEventInfoAffVO.setReporttelmobile(QzfwgzUtil.getNull(root.getChild("REPORTTELMOBILE").getText()));
		if(root.getChild("RETURNVISITFLAG")!=null)
			cmEventInfoAffVO.setReturnvisitflag(QzfwgzUtil.getNull(root.getChild("RETURNVISITFLAG").getText()));
		if(root.getChild("RETURNVISITTYPE")!=null)
			cmEventInfoAffVO.setReturnvisittype(QzfwgzUtil.getNull(root.getChild("RETURNVISITTYPE").getText()));
		if(root.getChild("RETURNVISITOBJECT")!=null)
			cmEventInfoAffVO.setReturnvisitobject(QzfwgzUtil.getNull(root.getChild("RETURNVISITOBJECT").getText()));
			*/
		return cmEventInfoAffVO;
		
	}
	public static void main(String[] args) {
		String eventInfoXml = "<EVENTINFO>" +
				"<ID>17</ID>" +
				"<SYSTEMID>0</SYSTEMID>" +
				"<RelID>2</RelID>" +
				"<EVENTDESC>问题描述</EVENTDESC>" +
				"<ADDRESS>地址</ADDRESS>" +
				"<CREATETIME>受理(登记)时间</CREATETIME>" +
				"<OPERATORNAME>登记人名称</OPERATORNAME>" +
				"<DEALOPINION>处理意见</DEALOPINION>" +
				"<DISPATCHHUMAN>派遣人名称</DISPATCHHUMAN>" +
				"<APPROVEOPINION>审批意见</APPROVEOPINION>" +
				"<APPROVEHUMAN>审批人名称</APPROVEHUMAN>" +
				"<EVENTTYPEID>11</EVENTTYPEID>"+
				"<MAINTYPEID>11</MAINTYPEID>"+
				"<SUBTYPEID>11</SUBTYPEID>"+
				"<STREETID>11</STREETID>"+
				"<COMMUNITYID>11</COMMUNITYID>"+
				"<CELLID>11</CELLID>"+
				"<COORDINATEX>11.1</COORDINATEX>"+
				"<COORDINATEY>11.1</COORDINATEY>"+
				"<RELRECDISPNUM>空旷旷</RELRECDISPNUM>"+
				"<SUBEVENTSRCID>222</SUBEVENTSRCID>"+

				"<ADJUNCT>" +
				"<ITEM>" +
				"<TYPE>1</TYPE>" +
				"<CONTENT>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 Ow==</CONTENT>" +
				"<FILENAME>eeee.gif</FILENAME>" +
				"</ITEM>" +
				"</ADJUNCT>" +
				"</EVENTINFO>";
		CmEventInfo cmEventInfo = CmReadManageUtil.dealCmEventInfoXml(eventInfoXml);
		CmEventInfoAffVO cmEventInfoAffVO = cmEventInfo.getCmEventInfoAffVO();
		System.out.println(cmEventInfoAffVO);
		
		/*List list=cmEventInfo.getMenageList();
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}*/


	}

}
