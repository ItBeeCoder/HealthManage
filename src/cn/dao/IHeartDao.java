package cn.dao;

import java.util.List;

import cn.entity.Heart;
import cn.entity.Oldman;

public interface IHeartDao {
	void save(Heart heart);

	void update(Heart heart);

	void delete(int id);

	Heart findById(int id);

	List<Heart> getAll();

	/**
	 * 根据给定日期字符串，获取指定日期下的所有的心率对象
	 * @param heartDatatime
	 * @return
	 */
	List<Heart> getAll(String heartDatetime);
	
	//获取指定老人id下的数据
	List<Heart> getAllByOld(Oldman oldMan);
	List<Heart> getAllByOldAndDay(Oldman oldMan,String heartDateTime);
	
	List<Heart> getAllByOldStartEnd(Oldman oldMan,String start,String end);
	
	List<Heart> getOldmanBefore3Minute(Oldman oldMan, String dateStr);
}
