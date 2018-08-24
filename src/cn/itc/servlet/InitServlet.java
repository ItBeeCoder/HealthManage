package cn.itc.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import cn.util.ReadTxtData;

public class InitServlet extends HttpServlet {

	/**
	 * author：shaozq
	 * 2017-11-3下午04:55:14
	 * TODO
	 */
	private static final long serialVersionUID = 1L;

	public InitServlet() {
		// TODO Auto-generated constructor stub
	}

	public void init(ServletConfig config){
		try {
			super.init(config);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("servlet自动测试");
		
//		ReadTxtData comread=new ReadTxtData();  
		String filePath = "F:/研究生二年级上学期/02深圳项目/02ProjectCode/0727/hcpoj_1/Brdata.txt";// 文件路径
		// readTxtFile(filePath);
		ReadTxtData.readData(filePath);  
		System.out.println("串口调用结束");
		
	}
	
	
}
