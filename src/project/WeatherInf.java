package project;

public class WeatherInf {
    private OneDayWeatherInf[] weatherInfs;
    private String dressAdvice;
    private String washCarAdvice;
    private String coldAdvice;
    private String sportsAdvice;
    private String ultravioletRaysAdvice;
    
    public WeatherInf(){  
    	dressAdvice = "";  
    	washCarAdvice = "";  
    	coldAdvice = "";  
    	sportsAdvice = "";  
    	ultravioletRaysAdvice = "";  
    }  
      
    public void printInf(){  
          
        System.out.println(dressAdvice);  
        System.out.println(washCarAdvice);  
        System.out.println(coldAdvice);  
        System.out.println(sportsAdvice);  
        System.out.println(ultravioletRaysAdvice);  
        for(int i=0;i<weatherInfs.length;i++){  
            System.out.println(weatherInfs[i]);  
        }  
          
    }  
      
  
    public OneDayWeatherInf[] getWeatherInfs() {  
        return weatherInfs;  
    }  
  
  
    public void setWeatherInfs(OneDayWeatherInf[] weatherInfs) {  
        this.weatherInfs = weatherInfs;  
    }  
  
  
    public String getDressAdvise() {  
        return dressAdvice;  
    }  
  
  
    public void setDressAdvise(String dressAdvise) {  
        this.dressAdvice = dressAdvice;  
    }  
  
  
    public String getWashCarAdvise() {  
        return washCarAdvice;  
    }  
  
  
    public void setWashCarAdvise(String washCarAdvise) {  
        this.washCarAdvice = washCarAdvice;  
    }  
  
  
    public String getColdAdvise() {  
        return coldAdvice;  
    }  
  
  
    public void setColdAdvise(String coldAdvise) {  
        this.coldAdvice = coldAdvice;  
    }  
  
  
    public String getSportsAdvise() {  
        return sportsAdvice;  
    }  
  
  
    public void setSportsAdvise(String sportsAdvise) {  
        this.sportsAdvice = sportsAdvice;  
    }  
  
  
    public String getUltravioletRaysAdvise() {  
        return ultravioletRaysAdvice;  
    }  
  
  
    public void setUltravioletRaysAdvise(String ultravioletRaysAdvise) {  
        this.ultravioletRaysAdvice = ultravioletRaysAdvice;  
    }  
}
