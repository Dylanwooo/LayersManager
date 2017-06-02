package project;

public class OneDayWeatherInf {
  private
    String location;
    String date;
    String week;
    String tempretureOfDay;
    String tempretureNow;
    String wind;
    String weather;
    String picture;
    int pmTwoPoinFive;
  
  public OneDayWeatherInf(){
	  location = "";
	  date = "";
	  week = "";
	  tempretureNow = "";
	  tempretureOfDay = "";
	  wind = "";
	  weather = "";
	  picture = "";
	  pmTwoPoinFive = 0;
  }
  public String getLocation(){
	  return location;
  }
  public void setLocation(String location){
	  this.location = location;
  }
  public String getDate(){
	  return date;
  }
  public void setDate(String date){
	  this.date = date;
  }
  public String getWeek(){
	  return week;
  }
  public void setWeek(String week){
	  this.week = week;
  }
  public String getTempertureOfDay() {    
      return tempretureOfDay;    
  }    
  public void setTempertureOfDay(String tempertureOfDay) {    
      this.tempretureOfDay = tempretureOfDay;    
  }    
  public String getTempertureNow() {    
      return tempretureNow;    
  }    
  public void setTempertureNow(String tempertureNow) {    
      this.tempretureNow = tempretureNow;    
  }    
  public String getWind() {    
      return wind;    
  }    
  public void setWind(String wind) {    
      this.wind = wind;    
  }    
  public String getWeather() {    
      return weather;    
  }    
  public void setWeather(String weather) {    
      this.weather = weather;    
  }    
  public String getPicture() {    
      return picture;    
  }    
  public void setPicture(String picture) {    
      this.picture = picture;    
  }    
  public int getPmTwoPointFive() {    
      return pmTwoPoinFive;    
  }    
  public void setPmTwoPointFive(int pmTwoPointFive) {    
      this.pmTwoPoinFive = pmTwoPoinFive;    
  }    
  
  public String toString(){             
      return location+"   "+date+"   "+week+" tempertureOfDay：  "+tempretureOfDay+" tempertureNow：  "+tempretureNow+"   "+wind+"   "+weather+"   "+picture+"   "+pmTwoPoinFive;    
  }    
}
