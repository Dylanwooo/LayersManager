package project;

public class stationInfoClass {
		   
	   String staNum;
	   String staName;
	   String code;
	   String province;
	   String city;
	   String county;
	   String staEvl;
	   String staType;
	   String lat;
	   String longtitude;
	   String alt;
	   
	   public stationInfoClass(String _staNum,String _staName,String _code,String _province,String _city,String _county,String _staEvl,String _staType,String _lat,String _longtitude,String _alt)
	   {		
		   staNum = _staNum;
		   staName = _staName;
		   code = _code;
		   province = _province;
		   city = _city;
		   county = _county;
		   staEvl = _staEvl;
		   staType = _staType;
		   lat = _lat;
		   longtitude = _longtitude;
		   alt = _alt;
	}
	   
}
