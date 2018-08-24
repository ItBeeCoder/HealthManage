package cn.dao;

import java.util.List;

import cn.entity.Oldman;
import cn.entity.Wifi;

public interface IWifiDao {

	void save(Wifi wifi);

	void update(Wifi wifi);

	void delete(int id);

	Wifi findById(int id);
	
	//通过wifi设备编号找到这个设备，虽然返回的是一个集合，但是只有一个元素
	List<Wifi> findByNumber(String wifiNumber);
	
	//老人和设备是一一对应的关系，这里虽然返回的是一个集合，但是里面只有一个元素
	List<Wifi> findByOld(Oldman oldMan);

	List<Wifi> getAll();

	/**
	 * 根据配置时间搜索所有的睡枕设备
	 * 
	 * @param congifurationTime
	 * @return
	 */
	List<Wifi> getAll(String configurationTime);

	/**
	 * 根据设备状态查找所有睡枕设备
	 * 
	 * @param statement
	 * @return
	 */
	List<Wifi> getAllByStatement(String statement);
}
