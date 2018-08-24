package cn.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.dao.IBreathDao;
import cn.dao.IOldManDao;
import cn.dao.IPillowDao;
import cn.entity.Alarm;
import cn.entity.Breath;
import cn.entity.Oldman;
import cn.entity.Pillow;
import cn.service.IPillowService;

public class PillowService implements IPillowService {

	private IPillowDao pillowDao;
	private IOldManDao oldManDao;
	private IBreathDao breathDao;
	
	public IPillowDao getPillowDao() {
		return pillowDao;
	}

	public void setPillowDao(IPillowDao pillowDao) {
		this.pillowDao = pillowDao;
	}

	public IOldManDao getOldManDao() {
		return oldManDao;
	}

	public void setOldManDao(IOldManDao oldManDao) {
		this.oldManDao = oldManDao;
	}

	public IBreathDao getBreathDao() {
		return breathDao;
	}

	public void setBreathDao(IBreathDao breathDao) {
		this.breathDao = breathDao;
	}

	public void save(Pillow pillow) {
		pillowDao.save(pillow);
	}

	public void update(Pillow pillow) {
		pillowDao.update(pillow);
	}

	public Pillow findById(int id) {
		return pillowDao.findById(id);
	}

	public List<Pillow> getAll() {
		return pillowDao.getAll();
	}

	public List<Pillow> getAll(String configurationTime) {
		return pillowDao.getAll(configurationTime);
	}

	public List<Pillow> getAllByStatement(String statement) {
		return pillowDao.getAllByStatement(statement);
	}

	public void delete(int id) {
		pillowDao.delete(id);
	}

	public void deleteMany(int[] ids) {

		if (ids != null && ids.length > 0) {
			for (int id : ids) {
				delete(id);
			}
		}
	}

	public List<Pillow> findByNumber(String pillowNumber) {

		return pillowDao.findByNumber(pillowNumber);
	}

	public List<Pillow> findByOld(Oldman oldMan) {

		return pillowDao.findByOld(oldMan);

	}

	// 监测设备异常信息
	public List<Alarm> detectPillowAbnormal(String detectHour) {

		// 集合中存放异常报警信息
		List<Alarm> alarmList = null;

		// 获取指定时间下的所有老人的数据
		List<Oldman> oldManList = oldManDao.getAll();
		System.out.println(oldManList);
		System.out.println(detectHour);
		for (Oldman oldMan : oldManList) {

			// 通过监测呼吸数据的有无来判断设备是否异常
			List<Breath> breathList = breathDao.getOldmanDuring4Hours(oldMan,
					detectHour);
			System.out.println(breathList.size());

			// 如果返回值为0，代表正常
			// 如果返回值为1，代表设备采集的数据不完整，间歇性工作或者时断时续的采集数据，或者运行出错
			// 如果返回值为2，代表设备没有工作，没有采集到数据。
			int isAbnormal = detect(breathList, detectHour);

			if (isAbnormal == 0) {
				break;

			} else if (isAbnormal == 1) {
				Alarm alarm = new Alarm();
				alarm.setAlarmTime(detectHour);
				alarm.setOldman(oldMan);
				alarm.setType("采集数据不完整");
				alarmList = new ArrayList<Alarm>();
				alarmList.add(alarm);

			} else if (isAbnormal == 2) {
				Alarm alarm = new Alarm();
				alarm.setAlarmTime(detectHour);
				alarm.setOldman(oldMan);
				alarm.setType("设备停止工作");
				alarmList = new ArrayList<Alarm>();
				alarmList.add(alarm);
			}
		}

		return alarmList;
	}

	public int detect(List<Breath> listBreath, String detectHour) {

		// 默认设备的状态为0，代表正常
		int TYPE = 0;
		
		detectHour =  detectHour.substring(8);
		Set<String> detectHours = new HashSet<String>();
		//如果检测的时间处于这个时间段，那么在这个时间段的前两个小时和后两个小时，系统默认都应该有数据,
		//在夜里 02,03,04
		Set<String> detectMiddle = new HashSet<String>();
		
		detectHours.add("21");
		detectHours.add("22");
		detectHours.add("23");
		detectHours.add("00");
		detectHours.add("01");
		detectHours.add("02");
		detectMiddle.add("02");
		detectHours.add("03");
		detectMiddle.add("03");
		detectHours.add("04");
		detectMiddle.add("04");
		detectHours.add("05");
		detectHours.add("06");
		
		// 如果检测的时间在detectHours中，同时检测到的呼吸值的长度为0,
		if (detectHours.contains(detectHour)&&listBreath.size() < 1) {
			// 设备不工作，没有采集到数据
			TYPE = 2;
		}//如果检测的时间处于要夜里的四个小时内，系统默认应该采集全部的数据 
		else if (detectHours.contains(detectHour)&&listBreath.size() > 1200 && listBreath.size() < 2400) {
			// 呼吸数据有异常，比如没有采集到相应数量的呼吸数据
			TYPE = 1;
		}

		return TYPE;
	}
}
