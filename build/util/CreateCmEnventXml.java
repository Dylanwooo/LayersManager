package com.zhjy.qzfwgz.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import com.zhjy.qzfwgz.coding.CmEventCoding;
import com.zhjy.qzfwgz.vo.CmAccessoriesFile;
import com.zhjy.qzfwgz.vo.CmEventInfo;
import com.zhjy.qzfwgz.vo.CmEventInfoAffVO;
import com.zhjy.qzfwgz.vo.QzAffiliateBaseInfoVO;
import com.zhjy.qzfwgz.vo.QzAppealBaseSortVO;
import com.zhjy.qzfwgz.vo.ToDispatchVO;
import com.zhjy.qzfwgz.vo.ToEventVO;


public class CreateCmEnventXml {
	public static String createCmEnventXml(CmEventInfo cmEventInfo,
			QzAffiliateBaseInfoVO qzAffiliateBaseInfoVO ,QzAppealBaseSortVO qzAppealBaseSortVO,ToEventVO toEventVO) throws IOException {
		ByteArrayOutputStream byteOPStream = null;
		// 先建立xml文档
		Element root = new Element("EVENTINFO");
		Document doc = new Document(root);
		Element idElt = new Element("ID");
		idElt.addContent(StringUtil.dealNull(cmEventInfo.getAppealid()));
		root.addContent(idElt);
		Element eventdescElt = new Element("EVENTDESC");
		eventdescElt.addContent(StringUtil.dealNull(cmEventInfo.getEventdesc()));
		root.addContent(eventdescElt);
		Element systemidElt = new Element("SYSTEMID");
		systemidElt.addContent(StringUtil.dealNull(cmEventInfo.getSystemid()));
		root.addContent(systemidElt);
		Element addressElt = new Element("ADDRESS");
		addressElt.addContent(StringUtil.dealNull(cmEventInfo.getAddress()));
		root.addContent(addressElt);
		Element createtimeElt = new Element("CREATETIME");
		createtimeElt.addContent(StringUtil.dealNull(cmEventInfo.getCreatetime()));
		root.addContent(createtimeElt);
		Element operatornameElt = new Element("OPERATORNAME");
		operatornameElt.addContent(StringUtil.dealNull(cmEventInfo.getOperatorname()));
		root.addContent(operatornameElt);
	  if(toEventVO!=null){
	  	Element reportname = new Element ("REPORTNAME");
			reportname.addContent(StringUtil.dealNull(toEventVO.getReportname()));
			root.addContent(reportname);
			Element reporttelmobile = new Element ("REPORTTELMOBILE");
			reporttelmobile.addContent(StringUtil.dealNull(toEventVO.getReporttelmobile()));
			root.addContent(reporttelmobile);
			Element retutnvistitflag = new Element ("RETURNVISITFLAG");
			retutnvistitflag.addContent(StringUtil.dealNull(toEventVO.getReturnvisitflag()));
			root.addContent(retutnvistitflag);
			Element returnvisittype = new Element ("RETURNVISITTYPE");
			returnvisittype.addContent(StringUtil.dealNull(toEventVO.getReturnvisittype()));
			root.addContent(returnvisittype);
			Element returnvisitobject = new Element ("RETURNVISITOBJECT");
			returnvisitobject.addContent(StringUtil.dealNull(toEventVO.getReturnvisitobject()));
			root.addContent(returnvisitobject);
		}
		Element eventtypeElt = new Element("EVENTTYPEID");
		if(qzAppealBaseSortVO!=null){
		  eventtypeElt.addContent(StringUtil.dealNull(qzAppealBaseSortVO.getFirstId()));
		  root.addContent(eventtypeElt);
		  Element maintypeElt = new Element("MAINTYPEID");
		  maintypeElt.addContent(StringUtil.dealNull(qzAppealBaseSortVO.getSecondId()));
		  root.addContent(maintypeElt);
		  Element subtypeidElt = new Element("SUBTYPEID");
		  subtypeidElt.addContent(StringUtil.dealNull(qzAppealBaseSortVO.getThirdId()));
		  root.addContent(subtypeidElt);
		}
		if(	qzAffiliateBaseInfoVO!=null){
		  Element eventgradeidElt = new Element("EVENTGRADEID");
		  eventgradeidElt.addContent(StringUtil.dealNull(qzAffiliateBaseInfoVO.getEventGradeid()));
		  root.addContent(eventgradeidElt);
		  Element streetcodeElt = new Element("STREETCODE");
		  streetcodeElt.addContent(StringUtil.dealNull(qzAffiliateBaseInfoVO.getStreetCode()));
		  root.addContent(streetcodeElt);
		  Element communitycodeElt = new Element("COMMUNITYCODE");
		  communitycodeElt.addContent(StringUtil.dealNull(qzAffiliateBaseInfoVO.getCommunityCode()));
		root.addContent(communitycodeElt);
		Element cellcodeElt = new Element("CELLCODE");
		cellcodeElt.addContent(StringUtil.dealNull(qzAffiliateBaseInfoVO.getCellCode()));
		root.addContent(cellcodeElt);
		Element coordinatexElt = new Element("COORDINATEX");
		coordinatexElt.addContent(StringUtil.dealNull(qzAffiliateBaseInfoVO.getCoordinateX()));
		root.addContent(coordinatexElt);
		Element coordinateyElt = new Element("COORDINATEY");
		coordinateyElt.addContent(StringUtil.dealNull(qzAffiliateBaseInfoVO.getCoordinateY()));
		root.addContent(coordinateyElt);
		Element relrecdispnumElt = new Element("RELRECDISPNUM");
		relrecdispnumElt.addContent(StringUtil.dealNull(qzAffiliateBaseInfoVO.getRelRecdispnum()));
		root.addContent(relrecdispnumElt);
		Element subeventsrcidElt = new Element("SUBEVENTSRCID");
		subeventsrcidElt.addContent(StringUtil.dealNull(qzAffiliateBaseInfoVO.getSubEventsrcid()));
		root.addContent(subeventsrcidElt);
		Element similarElt = new Element("SIMILARID");
		similarElt.addContent(StringUtil.dealNull(qzAffiliateBaseInfoVO.getSimilarId()));
		root.addContent(similarElt);
		}
		List menageList=cmEventInfo.getMenageList();
		FileUtil fileUtil=new FileUtil();
		if(menageList!=null && menageList.size()>0){
				for (int i = 0; i<menageList.size(); i++) {
					CmAccessoriesFile cmAccessoriesFile = (CmAccessoriesFile) menageList.get(i);
					if(!QzfwgzUtil.isNull(cmAccessoriesFile.getFileName())){
					  byte[] accessoriesBytes=fileUtil.getFileToBytes(CmEventCoding.SRCPATH, cmAccessoriesFile.getFileName());
					  Element adjunctElt = new Element("ADJUNCT");
					  root.addContent(adjunctElt);
					  Element itemElt = new Element("ITEM");
					  adjunctElt.addContent(itemElt);
					  Element typeElt = new Element("TYPE");
					  int type= QzfwgzUtil.dealFileType(cmAccessoriesFile.getFileName());
					  typeElt.addContent(type+"");
					  itemElt.addContent(typeElt);
					  if(accessoriesBytes!=null){
					  	 Element contentElt = new Element("CONTENT");
					  	 contentElt.addContent(Base64.encodeBytes(accessoriesBytes));
					  	 itemElt.addContent(contentElt);
					  }
					  Element filenameElt = new Element("FILENAME");
					  filenameElt.addContent(cmAccessoriesFile.getFileName());
				  	itemElt.addContent(filenameElt);
					}
			 }
		}
		XMLOutputter outputter = new XMLOutputter();
		byteOPStream = new ByteArrayOutputStream();
		outputter.output(doc, byteOPStream);
		byteOPStream.close();
		return new String(byteOPStream.toByteArray(),"UTF-8");

	}
	public static void main(String[] arg) {
		//CreateCmEnventXml createCmEnventXml=new CreateCmEnventXml();
		try {
			CmEventInfo cmEventInfo=new CmEventInfo();
			QzAffiliateBaseInfoVO qzAffiliateBaseInfoVO = new QzAffiliateBaseInfoVO();
			QzAppealBaseSortVO qzAppealBaseSortVO =new QzAppealBaseSortVO();
			//vo.setAddress("房管局");
			//vo.setRelid((long)333);
			//vo.setTasknum("YH3333");
			//vo.setRecdispnum("333");
			//vo.setSystemid((long)444);
			//vo.setSystemname("系统服务");
			String xml =CreateCmEnventXml.createCmEnventXml(cmEventInfo,qzAffiliateBaseInfoVO,qzAppealBaseSortVO,null);
			System.out.println(xml);	
			
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}
}
