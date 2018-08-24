package cn.impl;

import java.util.List;

import cn.dao.IWifiDao;
import cn.entity.Oldman;
import cn.entity.Wifi;

public class WifiDao extends BaseDao<Wifi> implements IWifiDao {

	@SuppressWarnings("unchecked")
	public List<Wifi> getAll(String configurationTime) {
		return getSessionFactory().getCurrentSession()//
				.createQuery("from Wifi where configurationTime like ?")//
				.setParameter(0, configurationTime + "%")//
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<Wifi> getAllByStatement(String statement) {
		return getSessionFactory().getCurrentSession()//
				.createQuery("from Wifi where statement like ?")//
				.setParameter(0, statement)//
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<Wifi> findByNumber(String wifiNumber) {
		return getSessionFactory().getCurrentSession()//
				.createQuery("from Wifi where wifiNumber = ?")//
				.setParameter(0, wifiNumber)//
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<Wifi> findByOld(Oldman oldMan) {
		return getSessionFactory().getCurrentSession()//
				.createQuery("from Wifi as w where w.oldman = ?")//
				.setParameter(0, oldMan)//
				.list();
	}

}
