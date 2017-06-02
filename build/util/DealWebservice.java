package com.zhjy.qzfwgz.util;

import org.apache.log4j.Logger;

import com.zhjy.qzfwgz.cmwebservice.client.ggsjk.PublishService;
import com.zhjy.qzfwgz.cmwebservice.client.ggsjk.PublishServiceServiceLocator;
import com.zhjy.qzfwgz.coding.CmEventCoding;
import com.zhjy.qzfwgz.coding.OpertionCoding;



public class DealWebservice {
	public static String dealGgsjkWebservice(String optionCode, String eventInfo) throws Exception {
		Logger.getLogger(DealWebservice.class).info("发送整合数据库信息：（OpertionCode，"+optionCode+"），发送的XML-------------------"+eventInfo);

			String returnString="";
		PublishServiceServiceLocator publishServiceServiceLocator=new PublishServiceServiceLocator();
		PublishService publishService=publishServiceServiceLocator.getHDSZXService();
		returnString=publishService.updateInfo(optionCode, eventInfo);
		Logger.getLogger(DealWebservice.class).info("发送整合数据库信息：（OpertionCode，"+optionCode+"），返回结果-------------------"+returnString);

		return returnString;
}
	public static String dealEvalStatInfoWebservice(String statInfoStr) throws Exception{
		Logger.getLogger(DealWebservice.class).info("发送绩效考核基本信息，发送的XML-------------------"+statInfoStr);
		String returnString="";
		com.zhjy.qzfwgz.cmwebservice.client.kpxx.PublishServiceServiceLocator  publishServiceServiceLocator=new com.zhjy.qzfwgz.cmwebservice.client.kpxx.PublishServiceServiceLocator();
		com.zhjy.qzfwgz.cmwebservice.client.kpxx.PublishService publishService=publishServiceServiceLocator.getHDSZXService();
		returnString=publishService.synStatInfo(statInfoStr);
		Logger.getLogger(DealWebservice.class).info("发送绩效考核基本信息，返回的XML-------------------"+returnString);
		return returnString;
	}
	public static String dealEvalOpinionInfoWebservice(String evalOpinionStr) throws Exception{
		Logger.getLogger(DealWebservice.class).info("发送绩效考核综合信息，发送的XML-------------------"+evalOpinionStr);
		String returnString="";
		com.zhjy.qzfwgz.cmwebservice.client.kpxx.PublishServiceServiceLocator  publishServiceServiceLocator=new com.zhjy.qzfwgz.cmwebservice.client.kpxx.PublishServiceServiceLocator();
		com.zhjy.qzfwgz.cmwebservice.client.kpxx.PublishService publishService=publishServiceServiceLocator.getHDSZXService();
		returnString=publishService.synEvalOpinion(evalOpinionStr);
		Logger.getLogger(DealWebservice.class).info("发送绩效考核综合信息，返回的XML-------------------"+returnString);
		return returnString;
	}
	/*public static String dealEvalStatInfoWebservice(String statInfoStr) throws Exception{
		Logger.getLogger(DealWebservice.class).info("发送绩效考核综合信息，发送的XML-------------------"+statInfoStr);
		String returnString = "<RESULT>" +
		"<RESULTTYPE>0</RESULTTYPE>" +
		"<RESULTINFO>成功或失败的说明</RESULTINFO>" +
		"</RESULT>";
		Logger.getLogger(DealWebservice.class).info("发送绩效考核基本信息，返回的XML-------------------"+returnString);
		return returnString;
	}*/
	/*public static String dealGgsjkWebservice1(String optionCode, String eventInfo) throws Exception{
		Logger.getLogger(DealWebservice.class).info("发送整合数据库信息：（OpertionCode，"+optionCode+"），发送的XML-------------------"+eventInfo);
		String returnString = "<RESULT>" +
		"<ID>8001234</ID>" +
		"<DISPATCHID>88888</DISPATCHID>" +
		"<RESULTTYPE>0</RESULTTYPE>" +
		"<RESULTINFO>成功或失败的说明</RESULTINFO>" +
		"</RESULT>";
		Logger.getLogger(DealWebservice.class).info("发送整合数据库信息：（OpertionCode，"+optionCode+"），返回结果-------------------"+returnString);
		return returnString;
	}*/
	
	
	/*public static String dealGgsjkWebservice(String optionCode, String eventInfo) throws Exception{
		Logger.getLogger(DealWebservice.class).info("发送整合数据库信息：（OpertionCode，"+optionCode+"），发送的XML-------------------"+eventInfo);
		String returnString = "<RESULT>" +
		"<ID>8001234</ID>" +
		"<ITEMID>88888</ITEMID>" +
		"<RESULTTYPE>0</RESULTTYPE>" +
		"<RESULTINFO>成功或失败的说明</RESULTINFO>" +
		"</RESULT>";
		Logger.getLogger(DealWebservice.class).info("发送整合数据库信息：（OpertionCode，"+optionCode+"），返回结果-------------------"+returnString);
		return returnString;
	}*/
	/*public static void main(String args[]) {
		String eventInfo="<EVENTINFO><ID></ID><EVENTID>4002145</EVENTID><RELID>0</RELID><TASKNUM></TASKNUM><RECDISPNUM>Y2010012900009</RECDISPNUM><SYSTEMID>2</SYSTEMID><SYSTEMNAME>6</SYSTEMNAME><EVENTSRCID>2</EVENTSRCID><EVENTSRCNAME>电话</EVENTSRCNAME><EVENTSTATEID>1</EVENTSTATEID><EVENTSTATENAME>案件立案</EVENTSTATENAME><EVENTGRADEID></EVENTGRADEID><EVENTGRADENAME>0</EVENTGRADENAME><RECTYPEID /><RECTYPENAME>其他</RECTYPENAME><EVENTTYPEID>0</EVENTTYPEID><EVENTTYPENAME></EVENTTYPENAME><MAINTYPEID></MAINTYPEID><MAINTYPENAME></MAINTYPENAME><SUBTYPEID></SUBTYPEID><SUBTYPENAME></SUBTYPENAME><PARTCODE></PARTCODE><DAMAGEGRADEID></DAMAGEGRADEID><DAMAGEGRADENAME></DAMAGEGRADENAME><APPEALTHEME>主题:hjq7195</APPEALTHEME><EVENTDESC>test23895</EVENTDESC><ADDRESS></ADDRESS><STREETID></STREETID><STREETNAME></STREETNAME><COMMUNITYID></COMMUNITYID><COMMUNITYNAME></COMMUNITYNAME><CELLID></CELLID><CELLNAME></CELLNAME><DUTYRGNCODE></DUTYRGNCODE><COORDINATEX></COORDINATEX><COORDINATEY></COORDINATEY><CREATETIME>2010-01-29 02:01:38</CREATETIME><OPERATORID></OPERATORID><OPERATORNAME>555555555555</OPERATORNAME><PATROLCARDID></PATROLCARDID><PATROLNAME></PATROLNAME><PATROLTELMOBILE></PATROLTELMOBILE><REPORTNAME>回访联系人</REPORTNAME><REPORTTELMOBILE></REPORTTELMOBILE><RETURNVISITFLAG>0</RETURNVISITFLAG><RETURNVISITTYPE>1</RETURNVISITTYPE><RETURNVISITOBJECT>回访对象</RETURNVISITOBJECT><INSTTIME>2010-01-29 02:01:38</INSTTIME><ARCHIVEDATE>2010-01-29 02:01:38</ARCHIVEDATE><PUBLISHDATE></PUBLISHDATE><PUBLISHFLAG></PUBLISHFLAG><DISPATCHCOUNT>0</DISPATCHCOUNT><CURUNITID>0</CURUNITID><ACTTIMESSTATEID>0</ACTTIMESSTATEID><REWORKCOUNT>0</REWORKCOUNT><TRANSBACKCOUNT>0</TRANSBACKCOUNT></EVENTINFO>";
		try {
			DealWebservice.dealGgsjkWebservice1(OpertionCoding.INSERTBASEINFO, eventInfo);
			System.out.println(DealWebservice.dealGgsjkWebservice1(OpertionCoding.INSERTBASEINFO, eventInfo));
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}*/
	public static String dealSendCMWebservice(String optionCode, String eventInfo) throws Exception {
		Logger.getLogger(DealWebservice.class).info("发送城管系统信息：（OpertionCode，"+optionCode+"），发送的XML-------------------"+eventInfo);
		
		String returnString="";
		com.zhjy.qzfwgz.cmwebservice.client.cm.PublishServiceServiceLocator publishServiceServiceLocator=new com.zhjy.qzfwgz.cmwebservice.client.cm.PublishServiceServiceLocator();
		
		com.zhjy.qzfwgz.cmwebservice.client.cm.PublishService publishServiceService=publishServiceServiceLocator.getHDCGService();
			returnString=publishServiceService.sendData(optionCode, eventInfo);		
				Logger.getLogger(DealWebservice.class).info("发送城管系统信息：（OpertionCode，"+optionCode+"），返回结果-------------------"+returnString);

			/*	String buffer = "<RESULT>" +
				"<ID>2345</ID>" +
				"<ITEMID>2346</ITEMID>" +
				"<RESULTTYPE>0</RESULTTYPE>" +
				"<RESULTINFO>成功或失败的说明</RESULTINFO>" +
				"</RESULT>";*/
		return returnString;

	}
}