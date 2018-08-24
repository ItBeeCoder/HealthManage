package com.itc.socket;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import cn.entity.Alarm;
import cn.service.IAlarmService;
import cn.service.IBreathService;
import cn.service.IHeartService;
import cn.service.IPillowService;

import com.itc.util.SpringContextUtil;


/*
 * 广播消息
 */
public class SendOutServlet extends HttpServlet {

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
		System.out.println("===============================");
		System.out.println("===============================");
		System.out.println("===============================");
		System.out.println("===============================");

		// ApplicationContext appCtx =
		// SpringContextUtil.getApplicationContext();

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
		System.out.println(strNow);
		//获取呼吸异常,目前数据库测试用的时间为20170920091107
		final List<Alarm> alarmList = breathService
				//.detectAbnormal(strNow);
				.detectAbnormal("20170920091107");
		
		//获取心率异常
		final List<Alarm> heartAlarmList = heartService
				.detectHeartAbnormal(strNow);
		//获取睡枕异常
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

		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				// task to run goes here
				System.out.println("Hello !!!广播消息窗口");
				CopyOnWriteArraySet<WebSocketTest> webSocketSet = new WebSocketTest().webSocketSet;
				// 打印当前在线人数
				System.out.println("广播：当前在线人数为："
						+ new WebSocketTest().webSocketSet.size());
				for (WebSocketTest item : webSocketSet) {
					try {
						// 添加自己的数据
						// 判断异常列表中是否有异常，有异常将提醒用户
						if (null != alarmList && alarmList.size() > 0) {

							for (Alarm alarm : alarmList) {
								// 将检测到的异常信息保存到数据库alarm表中
								alarmService.save(alarm);
								System.out.println("alarm异常类型打印：" + alarm.getType());
								SimpleDateFormat sdf = new SimpleDateFormat(
										"yyyyMMddhhmmss");
								Date alarmTime = null;
								String strAlarmTime = null;
								try {
									alarmTime = sdf.parse(alarm.getAlarmTime());
								} catch (ParseException e) {
									System.out
											.println("sendOutServlet中的日期转换异常");
									e.printStackTrace();
								}

								item.sendMessage("老人用户名为："
										+ alarm.getOldman().getUsername()
										+ "，报警时间是："
										+ alarmTime.toLocaleString()
										+ ",报警类型是：" + alarm.getType()
										+ ",老人家庭住址："
										+ alarm.getOldman().getAddress());
							}
						}
						item.sendMessage("server send data........................");
					} catch (IOException e) {
						e.printStackTrace();
						continue;
					}
				}
			}
		};
		Timer timer = new Timer();
		long delay = 0;
		//每隔三分钟调用一次
		//long intevalPeriod = 60*3* 1000;
		long intevalPeriod = 60* 1000;
		// schedules the task to be run in an interval
		timer.scheduleAtFixedRate(task, delay, intevalPeriod);
	}

}
