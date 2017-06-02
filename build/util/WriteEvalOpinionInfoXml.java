package com.zhjy.qzfwgz.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import com.zhjy.qzfwgz.vo.ToEvalOpinionInfoVO;



	public class WriteEvalOpinionInfoXml{
		public String getNewsByteArray(ToEvalOpinionInfoVO vo) throws IOException{
			ByteArrayOutputStream byteOPStream = null;
  		// 先建立xml文档
  	
  		Element root = new Element("EVALOPION");
  		Document doc = new Document(root);
      Element evalidELT = new Element("EVALID");
      evalidELT.addContent(StringUtil.dealNull(vo.getEvalId()));
  		root.addContent(evalidELT);
  		Element unitidELT = new Element("UNITID");
  		unitidELT.addContent(StringUtil.dealNull(vo.getUnitId()));
  		root.addContent(unitidELT);
  		Element unitstandardidELT = new Element("UNITSTANDARDID");
  		unitstandardidELT.addContent(StringUtil.dealNull(vo.getUnitStandardId()));
  		root.addContent(unitstandardidELT);
  		Element unittypeidELT = new Element("UNITTYPEID");
  		unittypeidELT.addContent(StringUtil.dealNull(vo.getUnitTypeId()));
  		root.addContent(unittypeidELT);
  		Element evaltypeidELT = new Element("EVALTYPEID");
  		evaltypeidELT.addContent(StringUtil.dealNull(vo.getEvalTypeId()));
  		root.addContent(evaltypeidELT);
  		Element evaltimeareaELT = new Element("EVALTIMEAREA");
  		evaltimeareaELT.addContent(StringUtil.dealNull(vo.getEvalTimeAre()));
  		root.addContent(evaltimeareaELT);
  		Element evalgradeELT = new Element("EVALGRADE");
  		evalgradeELT.addContent(StringUtil.dealNull(vo.getEvalGrade()));
  		root.addContent(evalgradeELT);
  		Element evaladviceELT = new Element("EVALADVICE");
  		evaladviceELT.addContent(StringUtil.dealNull(vo.getEvalAdvice()));
  		root.addContent(evaladviceELT);
  		Element createtimeELT = new Element("CREATETIME");
  		createtimeELT.addContent(StringUtil.dealNull(vo.getCreateTime()));
  		root.addContent(createtimeELT);
  		Element updatetimeELT = new Element("UPDATETIME");
  		updatetimeELT.addContent(StringUtil.dealNull(vo.getUpdateTime()));
  		root.addContent(updatetimeELT);
  		
      XMLOutputter outputter = new XMLOutputter();
  		byteOPStream = new ByteArrayOutputStream();
  		outputter.output(doc, byteOPStream);
  		byteOPStream.close();
  		return new String(byteOPStream.toByteArray(),"UTF-8");
		}
		public static void main(String[] arg) {
			WriteEvalOpinionInfoXml WriteEvalOpinionInfoXml = new WriteEvalOpinionInfoXml();
  		try{
  			ToEvalOpinionInfoVO vo = new ToEvalOpinionInfoVO();
  			vo.setEvalAdvice("rrrrrrrrrrrrrr");
  			String xml = WriteEvalOpinionInfoXml.getNewsByteArray(vo);
  			System.out.print(xml);
  		}catch (Exception e){
  			e.printStackTrace();
  		}
  	}
	}