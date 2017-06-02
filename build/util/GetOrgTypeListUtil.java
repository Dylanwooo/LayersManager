package com.zhjy.qzfwgz.util;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.zhjy.common.transaction.TransactionContext;
import com.zhjy.qzfw.app.AppContext;
import com.zhjy.qzfwgz.bo.OrgBO;
import com.zhjy.qzfwgz.coding.YearMonthDay;

public class GetOrgTypeListUtil{
	public static List getOrgTypeList(){
		TransactionContext ctx = AppContext.getInstance()
		.getTransactionContext();
    OrgBO bo = new OrgBO(ctx);
	  List orgTypeList=bo.getShowOrgList();
		return orgTypeList;
		
	}
	public static List getAllUseShowOrgList(){
		TransactionContext ctx = AppContext.getInstance()
		.getTransactionContext();
    OrgBO bo = new OrgBO(ctx);
	  List orgTypeList=bo.getAllUseShowOrgList();
		return orgTypeList;
		
	}
	public static List getAllUseShowOrgByNotInJxkh(Long perId){
		TransactionContext ctx = AppContext.getInstance()
		.getTransactionContext();
    OrgBO bo = new OrgBO(ctx);
	  List orgTypeList=bo.getAllUseShowOrgByNotInJxkh(perId);
		return orgTypeList;
		
	}
	
	
}