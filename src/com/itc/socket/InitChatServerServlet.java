/**
 * 2017-12-11上午10:21:31
	author:shaozq
 */
package com.itc.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import cn.entity.Alarm;
import cn.service.IAlarmService;
import cn.service.IBreathService;
import cn.service.IHeartService;
import cn.service.IPillowService;

import com.itc.util.SpringContextUtil;

/**
 * @author shaozq
	2017-12-11上午10:21:31
 *
 */
public class InitChatServerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public InitChatServerServlet() {
		// TODO Auto-generated constructor stub
	}

	public void init(ServletConfig config){
		try {
			super.init(config);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("InitChatServerServlet自动测试");
		new ListenPort().start();
		System.out.println("InitChatServerServlet线程启动结束");
		
	}
	/*
	 * 作用：服务器端的线程一直监听8881端口，监测到客户端有请求消息之后，获取该消息并进行后续处理
	 */
	class ListenPort extends Thread{
		
	  public void run(){
		  ChatServer server = new ChatServer(8881);
	    	server.start();
	    	BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	        while(true){
	        	 //System.out.println("启动完成111");
	            String msg="";
				try {
					msg = buffer.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            server.send2All(msg);
	            System.out.println("获取到的消息"+msg);
	            server.showInfo("获取："+msg);
	        }
	  }
}
	
}
