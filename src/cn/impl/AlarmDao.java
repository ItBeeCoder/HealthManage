package cn.impl;

import java.util.List;

import cn.dao.IAlarmDao;
import cn.entity.Alarm;
import cn.entity.Oldman;

public class AlarmDao extends BaseDao<Alarm> implements IAlarmDao {

	@SuppressWarnings("unchecked")
	public List<Alarm> getAll(String datetime) {
		return getSessionFactory().getCurrentSession()//
				.createQuery("from Alarm where alarmTime like ?")//
				.setParameter(0, datetime + "%")//
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<Alarm> getAllByOld(Oldman oldMan) {
		return getSessionFactory().getCurrentSession()//
				.createQuery("from Alarm as a where a.oldMan =?")//
				.setParameter(0, oldMan)//
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<Alarm> getAllByOldAndDay(Oldman oldMan, String dayStr) {
		return getSessionFactory().getCurrentSession()//
				.createQuery("from Alarm as a where a.oldMan =? and alarmTime like ?")//
				.setParameter(0, oldMan)//
				.setParameter(1, dayStr+"%")
				.list();
	}

}
