package cn.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import cn.entity.Alarm;
import cn.service.IAlarmService;
import cn.service.IBreathService;
import cn.service.IHeartService;
import cn.service.IPillowService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

public class AlarmAction extends ActionSupport implements ModelDriven<Alarm>,
		RequestAware {
	private static final long serialVersionUID = 1L;
	private Alarm alarm = new Alarm();
	private Map<String, Object> request;
	private IAlarmService alarmService;

	private IBreathService breathService;
	private IHeartService heartService;
	private IPillowService pillowService;
	
	public IBreathService getBreathService() {
		return breathService;
	}

	public void setBreathService(IBreathService breathService) {
		this.breathService = breathService;
	}

	public IHeartService getHeartService() {
		return heartService;
	}

	public void setHeartService(IHeartService heartService) {
		this.heartService = heartService;
	}

	public IPillowService getPillowService() {
		return pillowService;
	}

	public void setPillowService(IPillowService pillowService) {
		this.pillowService = pillowService;
	}

	public Alarm getModel() {
		return alarm;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public IAlarmService getAlarmService() {
		return alarmService;
	}

	public void setAlarmService(IAlarmService alarmService) {
		this.alarmService = alarmService;
	}

	public String alarmRing() {
		Alarm newAlarm = new Alarm();
		newAlarm.setType(alarm.getType());
		newAlarm.setAlarmTime(alarm.getAlarmTime());
		newAlarm.setOldman(alarm.getOldman());

		alarmService.save(newAlarm);

		return "alarmRing";
	}

	public String viewAlarmRing() {

		return "alarmRing";
	}

	//实时显示异常的报警信息
	public String ring() {

		System.out.println("11ring method invoke");
		String date = "20170920091107";
		Date now = new Date();

		System.out.println(now);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String nowStr = sdf.format(now);
		System.out.println(nowStr);
		List<Alarm> alarmList = breathService.detectAbnormal(nowStr);
		List<Alarm> heartAlarmList = heartService.detectHeartAbnormal(nowStr);
		List<Alarm> pillowAlarmList = pillowService.detectPillowAbnormal(nowStr);
		
		
		if (alarmList != null) {
			alarmList.addAll(heartAlarmList);
		}

		if(alarmList != null){
			alarmList.addAll(pillowAlarmList);
		}
		
		if (alarmList != null) {
			System.out.println("alarmList size is " + alarmList.size());
		}

		request.put("alarmList", alarmList);

		if (alarmList != null) {
			for (Alarm alarm : alarmList) {
				alarmService.save(alarm);
			}
		}

		return "alarmRing";
	}

	//待完善功能
	public String pushMessage(){
		
		return "pushMessage";
	}
	
	
	public String list() {
		System.out.println("开始list");
		List<Alarm> listAlarm = alarmService.getAll();
		System.out.println("结束得到list");
		request.put("listAlarm", listAlarm);
		System.out.println("request after");
		return "list";
	}

	public String viewUpdate() {
		int id = alarm.getAlarmId();
		Alarm newAlarm = alarmService.findById(id);
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.pop();
		vs.push(newAlarm);

		return "edit";

	}

	public String update() {
		alarmService.update(alarm);

		return "listAction";

	}

	public String delete() {

		int alarmId = alarm.getAlarmId();
		alarmService.delete(alarmId);

		return "listAction";
	}

}
