package cn.dao;

import java.util.List;

import cn.entity.Oldman;
import cn.entity.Pillow;

public interface IPillowDao {
	void save(Pillow pillow);

	void update(Pillow pillow);

	void delete(int id);

	Pillow findById(int id);
	
	List<Pillow> findByNumber(String pillowNumber);
	
	List<Pillow> findByOld(Oldman oldMan);
	
	List<Pillow> getAll();
	
	/**
	 * 根据配置时间搜索所有的睡枕设备
	 * @param congifurationTime
	 * @return
	 */
	List<Pillow> getAll(String configurationTime);
	
	/**
	 * 根据设备状态查找所有睡枕设备
	 * @param statement
	 * @return
	 */
	List<Pillow> getAllByStatement(String statement);
}
