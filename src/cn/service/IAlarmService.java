package cn.service;

import java.util.List;

import cn.entity.Alarm;
import cn.entity.Oldman;

public interface IAlarmService {
	
	void save(Alarm alarm);

	void update(Alarm alarm);

	Alarm findById(int id);

	List<Alarm> getAll();

	List<Alarm> getAll(String alarmTime);

	void delete(int id);

	void deleteMany(int[] ids);

	// 下面是获取指定老人id,指定日期
	List<Alarm> getAllByTime(String dayStr);
	
	int getAlarmTimes(String dayStr);
	
	List<Alarm> getAllByOld(Oldman oldMan);
	
	List<Alarm> getAllByTimeAndOld(Oldman oldMan,String dayStr);

}
