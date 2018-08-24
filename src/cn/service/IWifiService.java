package cn.service;

import java.util.List;

import cn.entity.Wifi;

public interface IWifiService {
	void save(Wifi wifi);

	void update(Wifi wifi);

	Wifi findById(int id);

	List<Wifi> getAll();

	List<Wifi> getAll(String configurationTime);

	List<Wifi> getAllByStatement(String statement);

	void delete(int id);

	void deleteMany(int[] ids);
}
