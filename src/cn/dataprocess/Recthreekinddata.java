package cn.dataprocess;

import java.text.SimpleDateFormat;
import java.util.Date;


import processaldata.ProcThreeData;



import com.mathworks.toolbox.javabuilder.MWException;

public class Recthreekinddata {

	/**
	 * author:shaozq
	 * 2017-9-19上午09:31:54
	 * TODO
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
//		ComRead comread=new ComRead();  
//		comread.start();  
		
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
	    String date = df.format(new Date());// new Date()为获取当前系统时间
	   
	    int n = 9;   
	    ProcThreeData test=null;
	    String str="E:/pillow/"+date+".txt";
	  
	    try {
	    	System.out.println("开始测试");
	    	test=new ProcThreeData();
	    	test.processall(str,n);
	    	System.out.println("测试完成");
		
	    } catch (MWException e) {
		// TODO Auto-generated catch block
	    	e.printStackTrace();
	    }finally{
	    	if (test != null)
        	 test.dispose();
	    }
		
		}

	}
