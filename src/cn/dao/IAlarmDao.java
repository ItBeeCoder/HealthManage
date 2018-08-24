package cn.dao;

import java.util.List;

import cn.entity.Alarm;
import cn.entity.Oldman;

public interface IAlarmDao {
	
	void save(Alarm alarm);

	void update(Alarm alarm);

	void delete(int id);

	Alarm findById(int id);

	List<Alarm> getAll();

	List<Alarm> getAll(String datetime);
	
	List<Alarm> getAllByOld(Oldman oldMan);
	
	List<Alarm> getAllByOldAndDay(Oldman oldMan,String dayStr);
}
