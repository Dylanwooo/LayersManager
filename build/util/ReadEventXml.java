/*package  com.zhjy.qzfwgz.util;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import com.zhjy.qzfwgz.util.DateUtil;
import com.zhjy.qzfwgz.vo.ToEventVO;


import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import org.xml.sax.InputSource;



public class ReadEventXml{
	
	private Long parselong(String pl){
		long buffer=0;
		try{
			 buffer=Long.parseLong(pl);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			return null;	
		} 
		return new Long(buffer);
		
	}
	public ToEventVO xmlElements(String xmlDoc) {
		
		ToEventVO toeventvoinfo = new ToEventVO();
		// 创建一个新的字符串
		StringReader read = new StringReader(xmlDoc);
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
			
			
			   if(root.getChild("ID").getText()!=null){
		     toeventvoinfo.setId(parselong(root.getChild("ID").getText()));  
		     toeventvoinfo.setEventid(parselong(root.getChild("EVENTID").getText()));                           
		     toeventvoinfo.setRelid(parselong(root.getChild("RELID").getText()));                                
		     toeventvoinfo.setTasknum(root.getChild("TASKNUM").getText());                         
		     toeventvoinfo.setRecdispnum(root.getChild("RECDISPNUM").getText());                 
		     toeventvoinfo.setSystemid(parselong(root.getChild("SYSTEMID").getText()));                        
		     toeventvoinfo.setSystemname(root.getChild("SYSTEMNAME").getText());                 
		     toeventvoinfo.setEventsrcid(parselong(root.getChild("EVENTSRCID").getText()));                  
		     toeventvoinfo.setEventsrcname(root.getChild("EVENTSRCNAME").getText());           
		     toeventvoinfo.setEventstateid(parselong(root.getChild("EVENTSTATEID").getText()));               
		     toeventvoinfo.setEventstatename(root.getChild("EVENTSTATENAME").getText());        
		     toeventvoinfo.setEventgradeid(parselong(root.getChild("EVENTGRADEID").getText()));             
		     toeventvoinfo.setEventgradename(root.getChild("EVENTGRADENAME").getText());      
		     toeventvoinfo.setRectypeid(parselong(root.getChild("RECTYPEID").getText()));                     
		     toeventvoinfo.setRectypename(root.getChild("RECTYPENAME").getText());              
		     toeventvoinfo.setEventtypeid(parselong(root.getChild("EVENTTYPEID").getText()));                 
		     toeventvoinfo.setEventtypename(root.getChild("EVENTTYPENAME").getText());          
		     toeventvoinfo.setMaintypeid(parselong(root.getChild("MAINTYPEID").getText()));                     
		     toeventvoinfo.setMaintypename(root.getChild("MAINTYPENAME").getText());              
		     toeventvoinfo.setSubtypeid(parselong(root.getChild("SUBTYPEID").getText()));                      
		     toeventvoinfo.setSubtypename(root.getChild("SUBTYPENAME").getText());               
		     toeventvoinfo.setPartcode(root.getChild("PARTCODE").getText());                      
		     toeventvoinfo.setDamagegradeid(parselong(root.getChild("DAMAGEGRADEID").getText()));          
		     toeventvoinfo.setDamagegradename (root.getChild("DAMAGEGRADENAME").getText());   
		     toeventvoinfo.setAppealtheme (root.getChild("APPEALTHEME").getText());               
		     toeventvoinfo.setEventdesc (root.getChild("EVENTDESC").getText());                    
		     toeventvoinfo.setAddress(root.getChild("ADDRESS").getText());                         
		     toeventvoinfo.setStreetid(parselong(root.getChild("STREETID").getText()));                        
		     toeventvoinfo.setStreetname(root.getChild("STREETNAME").getText());                 
		     toeventvoinfo.setCommunityid (parselong(root.getChild("COMMUNITYID").getText()));                
		     toeventvoinfo.setCommunityname (root.getChild("COMMUNITYNAME").getText());         
		     toeventvoinfo.setCellid(parselong(root.getChild("CELLID").getText()));                              
		     toeventvoinfo.setCellname (root.getChild("CELLNAME").getText());                       
		     toeventvoinfo.setDutyrgncode(root.getChild("DUTYRGNCODE").getText());             
		     toeventvoinfo.setCoordinatex(root.getChild("COORDINATEX").getText());               
		     toeventvoinfo.setCoordinatey(root.getChild("COORDINATEY").getText());               
		     toeventvoinfo.setCreatetime(DateUtil.parseAll(root.getChild("CREATETIME").getText()));                   
		     toeventvoinfo.setOperatorid(parselong(root.getChild("OPERATORID").getText()));                  
		     toeventvoinfo.setOperatorname(root.getChild("OPERATORNAME").getText());           
		     toeventvoinfo.setPatrolcardid(root.getChild("PATROLCARDID").getText());             
		     toeventvoinfo.setPatrolname(root.getChild("PATROLNAME").getText());                 
		     toeventvoinfo.setPatroltelmobile(root.getChild("PATROLTELMOBILE").getText());      
		     toeventvoinfo.setReportname(root.getChild("REPORTNAME").getText());                
		     toeventvoinfo.setReporttelmobile(root.getChild("REPORTTELMOBILE").getText());     
		     toeventvoinfo.setReturnvisitflag(parselong(root.getChild("RETURNVISITFLAG").getText()));        
		     toeventvoinfo.setReturnvisittype(parselong(root.getChild("RETURNVISITTYPE").getText()));        
		     toeventvoinfo.setReturnvisitobject(root.getChild("RETURNVISITOBJECT").getText());  
		     toeventvoinfo.setInsttime(DateUtil.parseAll(root.getChild("INSTTIME").getText()));                          
		     toeventvoinfo.setArchivedate(DateUtil.parseAll(root.getChild("ARCHIVEDATE").getText()));                           
		     toeventvoinfo.setPublishflag(parselong(root.getChild("PUBLISHFLAG").getText()));                 
		     toeventvoinfo.setPublishdate(DateUtil.parseAll(root.getChild("PUBLISHDATE").getText()));                           
		     toeventvoinfo.setDispatchcount(parselong(root.getChild("DISPATCHCOUNT").getText()));          
		     toeventvoinfo.setCurunitid(parselong(root.getChild("CURUNITID").getText()));                      
		     toeventvoinfo.setActtimesstateid(parselong(root.getChild("ACTTIMESSTATEID").getText()));        
		     toeventvoinfo.setReworkcount(parselong(root.getChild("REWORKCOUNT").getText()));            
		     toeventvoinfo.setTransbackcount(parselong(root.getChild("TRANSBACKCOUNT").getText()));      
			   }
				
			System.out.println(toeventvoinfo);
			
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} 
		return toeventvoinfo;
	}
	public static void main(String[] args) {
		ReadEventXml readXml = new ReadEventXml();
		String xml ="<?xml version='1.0' encoding='UTF-8'?>" +
				"" +
				"<EVENTINFO><ID></ID><EVENTID>4002137</EVENTID><RELID>0</RELID><TASKNUM></TASKNUM><RECDISPNUM>Y2010012900006</RECDISPNUM><SYSTEMID>2</SYSTEMID><SYSTEMNAME>6</SYSTEMNAME><EVENTSRCID>2</EVENTSRCID><EVENTSRCNAME>电话</EVENTSRCNAME><EVENTSTATEID>1</EVENTSTATEID><EVENTSTATENAME>案件立案</EVENTSTATENAME><EVENTGRADEID></EVENTGRADEID><EVENTGRADENAME>0</EVENTGRADENAME><RECTYPEID /><RECTYPENAME>其他</RECTYPENAME><EVENTTYPEID>0</EVENTTYPEID><EVENTTYPENAME></EVENTTYPENAME><MAINTYPEID></MAINTYPEID><MAINTYPENAME></MAINTYPENAME><SUBTYPEID></SUBTYPEID><SUBTYPENAME></SUBTYPENAME><PARTCODE></PARTCODE><DAMAGEGRADEID></DAMAGEGRADEID><DAMAGEGRADENAME></DAMAGEGRADENAME><APPEALTHEME>主题:hjq7192</APPEALTHEME><EVENTDESC>test23892</EVENTDESC><ADDRESS></ADDRESS><STREETID></STREETID><STREETNAME></STREETNAME><COMMUNITYID></COMMUNITYID><COMMUNITYNAME></COMMUNITYNAME><CELLID></CELLID><CELLNAME></CELLNAME><DUTYRGNCODE></DUTYRGNCODE><COORDINATEX></COORDINATEX><COORDINATEY></COORDINATEY><CREATETIME>2010-01-29 01:03:26</CREATETIME><OPERATORID></OPERATORID><OPERATORNAME>555555555555</OPERATORNAME><PATROLCARDID></PATROLCARDID><PATROLNAME></PATROLNAME><PATROLTELMOBILE></PATROLTELMOBILE><REPORTNAME>回访联系人</REPORTNAME><REPORTTELMOBILE></REPORTTELMOBILE><RETURNVISITFLAG>0</RETURNVISITFLAG><RETURNVISITTYPE>1</RETURNVISITTYPE><RETURNVISITOBJECT>回访对象</RETURNVISITOBJECT><INSTTIME>2010-01-29 01:03:26</INSTTIME><ARCHIVEDATE>2010-01-29 01:03:26</ARCHIVEDATE><PUBLISHDATE></PUBLISHDATE><PUBLISHFLAG></PUBLISHFLAG><DISPATCHCOUNT>0</DISPATCHCOUNT><CURUNITID>0</CURUNITID><ACTTIMESSTATEID>0</ACTTIMESSTATEID><REWORKCOUNT>0</REWORKCOUNT><TRANSBACKCOUNT>0</TRANSBACKCOUNT></EVENTINFO>";
		readXml.xmlElements(xml);
		
		
		
	}
}*/