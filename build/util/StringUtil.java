package  com.zhjy.qzfwgz.util;

import java.util.Date;





//����������ͱ���ת����String��������������ǡ�rull��
public class  StringUtil {
	public static String dealNull(Object o){
		String buffer="";
		if(o!=null){
		 if(o   instanceof   Date){  
			 buffer=DateUtil.formatAllDate((Date)o);
		 }else{
			 buffer=o.toString();
		 }

		}
        return buffer;
		
	}

	public static 	void main(String[] arg){
		System.out.println(StringUtil.dealNull(new Date()));
	}
}
