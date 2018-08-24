package cn.service.impl;

import java.util.List;

import cn.dao.IAlarmDao;
import cn.entity.Alarm;
import cn.entity.Oldman;
import cn.service.IAlarmService;

public class AlarmService implements IAlarmService {
	private IAlarmDao alarmDao;

	public IAlarmDao getAlarmDao() {
		return alarmDao;
	}

	public void setAlarmDao(IAlarmDao alarmDao) {
		this.alarmDao = alarmDao;
	}

	public void save(Alarm alarm) {
		alarmDao.save(alarm);
	}

	public void update(Alarm alarm) {
		alarmDao.update(alarm);
	}

	public Alarm findById(int id) {
		return alarmDao.findById(id);
	}

	public List<Alarm> getAll() {
		System.out.println("alarmServicestart");
		return alarmDao.getAll();
		
	}

	public List<Alarm> getAll(String alarmTime) {
		return alarmDao.getAll(alarmTime);
	}

	public void delete(int id) {
		alarmDao.delete(id);
	}

	public void deleteMany(int[] ids) {
		if (ids != null && ids.length > 0) {
			for (int id : ids) {
				delete(id);
			}
		}
	}

	public List<Alarm> getAllByTime(String dayStr) {
		return alarmDao.getAll(dayStr);
	}

	public int getAlarmTimes(String dayStr) {
		return alarmDao.getAll(dayStr).size();
	}

	public List<Alarm> getAllByOld(Oldman oldMan) {
		return alarmDao.getAllByOld(oldMan);
	}

	public List<Alarm> getAllByTimeAndOld(Oldman oldMan, String dayStr) {
		return alarmDao.getAllByOldAndDay(oldMan, dayStr);
	}

}
