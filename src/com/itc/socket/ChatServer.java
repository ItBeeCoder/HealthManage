/**
 * 2017-12-11上午10:19:18
	author:shaozq
 */
package com.itc.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArraySet;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import cn.entity.Alarm;
import cn.service.IAlarmService;
import cn.service.IBreathService;
import cn.service.IHeartService;
import cn.service.IPillowService;

import com.itc.util.SpringContextUtil;


public class ChatServer extends WebSocketServer {

	public ChatServer() throws UnknownHostException {
    }

	public ChatServer(InetSocketAddress address) {
        super(address);
    }

    public ChatServer(InetSocketAddress address, int decoders) {
        super(address, decoders);
    }

    public ChatServer(int port) {
        this(new InetSocketAddress(port));
    }

    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        final String info = webSocket.getRemoteSocketAddress().getAddress().getHostAddress() + "接入!";
        showInfo(info);
//        send2All(info);//发送给APP客户端显示
        showInfo("发送：" + info);
    }

    
    public void onClose(WebSocket webSocket, int i, String s, boolean b){ 
    	final String info = webSocket.getRemoteSocketAddress().getAddress().getHostAddress() + " 断开!，reason=" + s;
        showInfo(info);
        send2All(info);
        showInfo("发送：" + info);
    }

    public void onMessage(WebSocket webSocket, String s) {
		
        TimerTask task = new TimerTask() {
			@Override
			public void run() {
				// 获取呼吸的service，里面有检测呼吸的异常
				IBreathService breathService = (IBreathService) SpringContextUtil
						.getBean("breathService");
				// 获取心率的service，里面有心率异常
				IHeartService heartService = (IHeartService) SpringContextUtil
						.getBean("heartService");
				// 获取枕头的service，里面有睡枕设备检测的异常
				IPillowService pillowService = (IPillowService) SpringContextUtil
						.getBean("pillowService");
				// 获取alarm的service，调用里面的保存异常的方法
				final IAlarmService alarmService = (IAlarmService) SpringContextUtil
						.getBean("alarmService");

				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
				Date now = new Date();
				String strNow = sdf.format(now);
				
				// 获取呼吸异常,目前数据库测试用的时间为20170920091107
				final List<Alarm> alarmList = breathService
						.detectAbnormal("20170920091107");
				// 获取心率异常
				final List<Alarm> heartAlarmList = heartService
						.detectHeartAbnormal(strNow);
				// 获取睡枕异常
				final List<Alarm> pillowAlarmList = pillowService
						.detectPillowAbnormal(strNow);

				// 将心率异常和睡枕设备异常加入到呼吸异常的列表中
				if (null != alarmList) {
					if (null != heartAlarmList) {
						alarmList.addAll(heartAlarmList);
					}
				}
				if (null != alarmList) {
					if (null != pillowAlarmList) {
						alarmList.addAll(pillowAlarmList);
					}
				}
				String info = "";
				// 添加自己的数据
				// 判断异常列表中是否有异常，有异常将提醒用户
				if (null != alarmList && alarmList.size() > 0) {

						for (Alarm alarm : alarmList) {
										// 将检测到的异常信息保存到数据库alarm表中
										alarmService.save(alarm);
										//System.out.println("alarm异常类型打印："+ alarm.getType());
										Date alarmTime = null;
										String strAlarmTime = null;
										try {
											alarmTime = sdf.parse(alarm.getAlarmTime());
										} catch (ParseException e) {
											System.out.println("sendOutServlet中的日期转换异常");
											e.printStackTrace();
										}
										info  = alarm.getOldman().getUsername()//用户名
											+ "="+ alarmTime.toLocaleString()//报警时间
											+ "=" + alarm.getType()+ "="//类型
											+ alarm.getOldman().getAddress();//家庭地址
									}
								}
		        send2All(info);//往APP端返回消息，info为具体的消息内容
		        showInfo(info);//在控制台下打印消息
			}
		};
		Timer timer = new Timer();
		long delay = 0;
		//每隔三分钟调用一次
		long intevalPeriod = 60*3* 1000;
		// schedules the task to be run in an interval
		timer.scheduleAtFixedRate(task, delay, intevalPeriod);
    }

    public void onError(WebSocket webSocket, Exception e) {
        final String info = webSocket.getRemoteSocketAddress().getAddress().getHostAddress() + ", error=>" + e;
        showInfo(info);
    }

    /**
     * @param info
     */
    protected void send2All(String info) {
        Iterator<WebSocket> interator = connections().iterator();
        while(interator.hasNext()) {
            interator.next().send(info);
        }
    }

    protected void showInfo(String info) {
        System.out.println(info);
    }


}
