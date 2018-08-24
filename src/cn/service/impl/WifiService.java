package cn.service.impl;

import java.util.List;

import cn.dao.IWifiDao;
import cn.entity.Wifi;
import cn.service.IWifiService;

public class WifiService implements IWifiService {

	private IWifiDao wifiDao;

	public IWifiDao getWifiDao() {
		return wifiDao;
	}

	public void setWifiDao(IWifiDao wifiDao) {
		this.wifiDao = wifiDao;
	}

	public void save(Wifi wifi) {
		wifiDao.save(wifi);
	}

	public void update(Wifi wifi) {
		wifiDao.update(wifi);
	}

	public Wifi findById(int id) {
		return wifiDao.findById(id);
	}

	public List<Wifi> getAll() {

		return wifiDao.getAll();
	}

	public List<Wifi> getAll(String configurationTime) {
		return wifiDao.getAll(configurationTime);
	}

	public List<Wifi> getAllByStatement(String statement) {
		return wifiDao.getAllByStatement(statement);
	}

	public void delete(int id) {
		wifiDao.delete(id);
	}

	public void deleteMany(int[] ids) {
		if (ids != null && ids.length > 0) {
			for (int id : ids) {
				delete(id);
			}
		}
	}

}
