package com.zhjy.qzfwgz.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;




  public class LoadCalendarProperties{
	public static final String PROPERTIES_FILE_NAME = "calendar.properties";
	 private Properties calenderprops;
	 private static LoadCalendarProperties loadCalendarProperties;
	 private LoadCalendarProperties() {
	   loadProperties();
     }
  public void loadProperties() {
		    Properties props = new Properties();
		    ClassLoader cl = getClass().getClassLoader();
		    InputStream is = cl.getResourceAsStream(PROPERTIES_FILE_NAME);

		    try {
		      props.load(is);
		    }
		    catch (IOException ex) {
		      Logger.getLogger(this.getClass()).fatal(
		          "can not load application properties:" + PROPERTIES_FILE_NAME, ex);
		    }
		    calenderprops = props;
		  }
  public static LoadCalendarProperties getInstance() {
		    if (loadCalendarProperties == null) {
		    	loadCalendarProperties = new LoadCalendarProperties();
		    }
		    return loadCalendarProperties;
		  }	
	 
  public Properties getProperties() {
		    return calenderprops;
		  }

		  public String getProperty(String key) {
		    return calenderprops.getProperty(key);
		  }

		  public String getProperty(String key, String defaultValue) {
		    return calenderprops.getProperty(key, defaultValue);
		  }
	
  public static void main(String args[]) {
		    System.out.println(LoadCalendarProperties.getInstance().getProperty("drivers"));
		  }
}