package com.zhjy.qzfwgz.util;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import com.zhjy.qzfw.vo.AppealBaseInfo;
import com.zhjy.qzfwgz.coding.CmEventCoding;
import com.zhjy.qzfwgz.vo.CmEventInfo;
import com.zhjy.qzfwgz.vo.CmSendManageEventInfo;
import com.zhjy.qzfwgz.vo.LetterSortVO;
import com.zhjy.qzfwgz.vo.MenuUrlShowVO;
import com.zhjy.qzfwgz.vo.MenuUrlVO;
import com.zhjy.qzfwgz.vo.QzAppealBaseSortVO;
import com.zhjy.qzfwgz.vo.UserHasRoleVO;


public class QzfwgzUtil {

	public static Long stringPassLong(String s) {
		Long l = null;
		if (!isNull(s)) {
			try {
				l=new Long(Long.parseLong(s));
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
		return l;
	}
	public static Double stringPassDouble(String s) {
		Double l = null;
		if (!isNull(s)) {
			try {
				l=new Double(Double.parseDouble(s));
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
		return l;
	}
	
	public static Integer stringPassInteger(String s) {
		Integer i = null;
		if (!isNull(s)) {
			try {
				i=new Integer(Integer.parseInt(s));
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
		return i;
	}
	public static int stringPassInt(String s) {
		int i = 0;
		if (!isNull(s)) {
			try {
				i=new Integer(Integer.parseInt(s)).intValue();
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
		return i;
	}

	public static String getNull(String s) {

		if (s == null) {
			return "";
		}else{
			if(s.toLowerCase().equals("null")){
				return "";
			}
		}
		
		return s.trim();
	}

	public static boolean isNull(String s) {
		boolean isNull = false;
		if (s == null || "".equals(s) || "null".equals(s.toLowerCase())) {
			isNull = true;
		}
		return isNull;
	}

	/*
	 * 0 其他 1 图片类型 11 声音文件 12 影像文件 21 txt类型 22 PDF类型附件 23 Word类型附件 24 Excel类型附件 25
	 * rar类型附件
	 */
	public static int dealFileType(String fileName) {
		int type = 0;
		if (!isNull(fileName)) {
			String[] endfiles = fileName.split(".");
			String endfile = "";
			if (endfiles != null && endfiles.length > 1) {
				endfile = endfiles[endfiles.length - 1];
				if (isPictureType(endfile)) {
					type = 1;
				} else if (isVoiceType(endfile)) {
					type = 11;
				} else if (isYingshiType(endfile)) {
					type = 12;
				} else if (isTxtType(endfile)) {
					type = 21;
				} else if (isPdfType(endfile)) {
					type = 22;
				} else if (isWordType(endfile)) {
					type = 23;
				} else if (isExcelType(endfile)) {
					type = 24;
				} else if (isRarType(endfile)) {
					type = 25;
				}
			}

		}
		return type;

	}

	public static boolean isPictureType(String endFile) {
		boolean isTrue = false;
		if (endFile.toLowerCase().equals("jpg")
				|| endFile.toLowerCase().equals("gif")
				|| endFile.toLowerCase().equals("bmp")
				|| endFile.toLowerCase().equals("png")) {
			isTrue = true;
		}
		return isTrue;

	}

	public static boolean isVoiceType(String endFile) {
		boolean isTrue = false;
		if (endFile.toLowerCase().equals("aif")
				|| endFile.toLowerCase().equals("svx")
				|| endFile.toLowerCase().equals("snd")
				|| endFile.toLowerCase().equals("mid")
				|| endFile.toLowerCase().equals("voc")
				|| endFile.toLowerCase().equals("wav")) {
			isTrue = true;
		}
		return isTrue;
	}

	public static boolean isYingshiType(String endFile) {
		boolean isTrue = false;
		if (endFile.toLowerCase().equals("avi")
				|| endFile.toLowerCase().equals("mov")
				|| endFile.toLowerCase().equals("mpeg")
				|| endFile.toLowerCase().equals("mpg")
				|| endFile.toLowerCase().equals("qt")
				|| endFile.toLowerCase().equals("ram")) {
			isTrue = true;
		}
		return isTrue;
	}

	public static boolean isTxtType(String endFile) {
		boolean isTrue = false;
		if (endFile.toLowerCase().equals("txt")) {
			isTrue = true;
		}
		return isTrue;
	}

	public static boolean isPdfType(String endFile) {
		boolean isTrue = false;
		if (endFile.toLowerCase().equals("pdf")) {
			isTrue = true;
		}
		return isTrue;
	}

	public static boolean isWordType(String endFile) {
		boolean isTrue = false;
		if (endFile.toLowerCase().equals("doc")) {
			isTrue = true;
		}
		return isTrue;
	}

	public static boolean isExcelType(String endFile) {
		boolean isTrue = false;
		if (endFile.toLowerCase().equals("xls")) {
			isTrue = true;
		}
		return isTrue;
	}

	public static boolean isRarType(String endFile) {
		boolean isTrue = false;
		if (endFile.toLowerCase().equals("rar")) {
			isTrue = true;
		}
		return isTrue;
	}
	public static String dealCSWGXml(	CmSendManageEventInfo cmSendManageEventInfo) throws Exception{
		String xmlString="";
		if(CmEventCoding.INCEPT_OPERATION_CODE_JA.equals(cmSendManageEventInfo.getSendType())){
			xmlString=dealCSWGJaXml(cmSendManageEventInfo);
		}else if(CmEventCoding.INCEPT_OPERATION_CODE_TH.equals(cmSendManageEventInfo.getSendType())){
			xmlString=dealCSWGThXml(cmSendManageEventInfo);
		}
		return xmlString;
	}
	public static String dealCSWGThXml(	CmSendManageEventInfo cmSendManageEventInfo) throws Exception{
		System.out.println("________xmlString_____________eeeeeeee");
		String xmlString="";
		ByteArrayOutputStream byteOPStream = null;
		// 先建立xml文档
		Element root = new Element("EVENTINFO");
		Document doc = new Document(root);
    Element id = new Element("ID");
		id.addContent(StringUtil.dealNull(cmSendManageEventInfo.getEventId()));
		root.addContent(id);
		Element transbackopinionElt = new Element("TRANSBACKOPINION");
		transbackopinionElt.addContent(cmSendManageEventInfo.getDealIdea());
		root.addContent(transbackopinionElt);
		Element transbackdateElt = new Element("TRANSBACKDATE");
		transbackdateElt.addContent(cmSendManageEventInfo.getDealTime());
		root.addContent(transbackdateElt);
		Element transbackhumanElt = new Element("TRANSBACKHUMAN");
		transbackhumanElt.addContent(cmSendManageEventInfo.getDealTime());
		root.addContent(transbackhumanElt);
    XMLOutputter outputter = new XMLOutputter();
		byteOPStream = new ByteArrayOutputStream();
		outputter.output(doc, byteOPStream);
		byteOPStream.close();
		xmlString= new String(byteOPStream.toByteArray(),"UTF-8");
		System.out.println("________xmlString_____________"+xmlString);
		return xmlString;
		
	}
	public static String dealCSWGJaXml(	CmSendManageEventInfo cmSendManageEventInfo) throws Exception{
		String xmlString="";
		ByteArrayOutputStream byteOPStream = null;
		// 先建立xml文档
		Element root = new Element("EVENTINFO");
		Document doc = new Document(root);
    Element id = new Element("ID");
		id.addContent(StringUtil.dealNull(cmSendManageEventInfo.getEventId()));
		root.addContent(id);
		Element dealdescElt = new Element("DEALDESC");
		dealdescElt.addContent(cmSendManageEventInfo.getDealIdea());
		root.addContent(dealdescElt);
		Element dealunitElt = new Element("DEALUNIT");
		dealunitElt.addContent(cmSendManageEventInfo.getDealOrg());
		root.addContent(dealunitElt);
		Element dealdateElt = new Element("DEALDATE");
		dealdateElt.addContent(cmSendManageEventInfo.getDealTime());
		root.addContent(dealdateElt);
		Element archivedateElt = new Element("ARCHIVEDATE");
		archivedateElt.addContent(cmSendManageEventInfo.getJieanTime());
		root.addContent(archivedateElt);
		Element archiveopinionElt = new Element("ARCHIVEOPINION");
		archiveopinionElt.addContent(cmSendManageEventInfo.getJieanIdea());
		root.addContent(archiveopinionElt);
		Element archivehumanElt = new Element("ARCHIVEHUMAN");
		archivehumanElt.addContent(cmSendManageEventInfo.getOperatorName());
		root.addContent(archivehumanElt);
    XMLOutputter outputter = new XMLOutputter();
		byteOPStream = new ByteArrayOutputStream();
		outputter.output(doc, byteOPStream);
		byteOPStream.close();
		xmlString= new String(byteOPStream.toByteArray(),"UTF-8");
		return xmlString;
		
	}

	public static  CmSendManageEventInfo dealCSWGJaManageEventVO(AppealBaseInfo appealBaseInfo,CmEventInfo cmEventInfo) throws Exception{

		CmSendManageEventInfo cmSendManageEventInfo=new CmSendManageEventInfo();
		cmSendManageEventInfo.setAppealId(new Long(appealBaseInfo.getId()));
		cmSendManageEventInfo.setEventId(cmEventInfo.getEventId());
		cmSendManageEventInfo.setDealIdea(StringUtil.dealNull(appealBaseInfo.getAppealFeedbackInfo()));
		cmSendManageEventInfo.setDealOrg(StringUtil.dealNull(appealBaseInfo.getAppealFjDname()));
		cmSendManageEventInfo.setDealTime(DateUtil.getAllToday());
		cmSendManageEventInfo.setJieanIdea(StringUtil.dealNull(appealBaseInfo.getAppealEndcaseIdea()));
		cmSendManageEventInfo.setJieanTime(DateUtil.getAllToday());
		cmSendManageEventInfo.setOperatorName(StringUtil.dealNull(appealBaseInfo.getAppealEndcaseUname()));
		return cmSendManageEventInfo;
		
	}
	 public static QzAppealBaseSortVO dealSortName(List sortNameList,QzAppealBaseSortVO qzAppealBaseSortVO){
	  	LetterSortVO letterSortVO = new LetterSortVO();
	  	if("".equals(sortNameList) || sortNameList==null ){
				System.out.println("sortNameList为空-----------------");
			}
			else{
				if(sortNameList.size()==3){
					letterSortVO = (LetterSortVO) sortNameList.get(0);
					qzAppealBaseSortVO.setFirstName(letterSortVO.getName());
					letterSortVO = (LetterSortVO) sortNameList.get(1);
					qzAppealBaseSortVO.setSecondName(letterSortVO.getName());
					letterSortVO = (LetterSortVO) sortNameList.get(2);
					qzAppealBaseSortVO.setThirdName(letterSortVO.getName());
				}
				else if(sortNameList.size()==2){
					letterSortVO = (LetterSortVO) sortNameList.get(0);
					qzAppealBaseSortVO.setFirstName(letterSortVO.getName());
					letterSortVO = (LetterSortVO) sortNameList.get(1);
					qzAppealBaseSortVO.setSecondName(letterSortVO.getName());
				}
				else if(sortNameList.size()==1){
					letterSortVO = (LetterSortVO) sortNameList.get(0);
					qzAppealBaseSortVO.setFirstName(letterSortVO.getName());
				}
			}
			return qzAppealBaseSortVO;
			
		}
	 public static List dealMenuToShow(List menuList) {
			List showNenuList = new ArrayList();
			MenuUrlShowVO menuUrlShowVO=null;
			if (menuList != null && menuList.size() > 0) {
				for (int i = 0; i < menuList.size(); i++) {
					MenuUrlVO menuUrlVO=(MenuUrlVO)menuList.get(i);
					if(menuUrlVO.getParentId().intValue()==0){
						if(menuUrlShowVO!=null){
							showNenuList.add(menuUrlShowVO);
						}
						menuUrlShowVO=new MenuUrlShowVO(); 
						menuUrlShowVO.setMenuUrlVO(menuUrlVO);
					}else{
						menuUrlShowVO.getChildMenuList().add(menuUrlVO);
					}
				}
				if(menuUrlShowVO!=null){
					showNenuList.add(menuUrlShowVO);
				}
			}
			System.out.println("====================="+menuList+"==============");
			System.out.println(menuList);
			System.out.println("====================="+showNenuList+"==============");
			System.out.println(showNenuList);
			return showNenuList;

		}
		public static String findMobileType(String mobile) {
			String mobileType = "";
			int mobileNum = Integer.parseInt(mobile.substring(0, 3));
			if (mobileNum >= 130 && mobileNum < 133) {
				mobileType = "un";
			} else if ((mobileNum > 133 && mobileNum < 140)
					|| (mobileNum >= 158 && mobileNum <= 159)) {
				mobileType = "cm";
			} else if (mobileNum == 133) {
				mobileType = "un";
			} else {
				mobileType = "un";
			}

			return mobileType;
		}
		public static String checkedRoles(List roleList,String roleId){
			roleId=getNull(roleId);
			String returnString="";
		  if(roleList!=null && roleList.size()>0){
			for(int i=0;i<roleList.size();i++){
   			  UserHasRoleVO userHasRoleVO=(UserHasRoleVO)roleList.get(i);
   			  if(roleId.equals(userHasRoleVO.getRoleId())){
   			  	returnString="checked";
   			  	break;
   			  }
			}
			}
			return returnString;
		} 

}
