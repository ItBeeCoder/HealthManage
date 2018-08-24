package cn.service;

import java.util.List;

import cn.entity.Alarm;
import cn.entity.Breath;
import cn.entity.Oldman;

public interface IBreathService {

	void save(Breath breath);

	void update(Breath breath);

	Breath findById(int id);

	List<Breath> getAll();

	/**
	 * 根据给定呼吸的日期字符串，获取所有呼吸对象
	 * 
	 * @param breathDatetime
	 *            查询的所有的呼吸日期，这个日期是以天为单位 但是呼吸的值在数据库中的保存为精确到毫秒值的字符串
	 * @return 呼吸对象列表
	 */
	List<Breath> getAll(String breathDatetime);

	void delete(int id);

	void deleteMany(int[] ids);

	// 下面是所有用户的指定日期下的最大最小平均呼吸数据
	int getMaxBreath(String dayStr);

	int getMinBreath(String dayStr);

	double getMeanBreath(String dayStr);

	// 下面是获取指定老人id,指定日期的的呼吸数据
	int getMaxBreath(Oldman oldMan, String dayStr);

	int getMinBreath(Oldman oldMan, String dayStr);

	double getMeanBreath(Oldman oldMan, String dayStr);

	// 获取用户指定时间段内的呼吸数据
	int getMaxBreathBySE(Oldman oldMan, String start, String end);

	int getMinBreathBySE(Oldman oldMan, String start, String end);

	double getMeanBreathBySE(Oldman oldMan, String start, String end);

	// 上传数据
	public void uploadBreathData(Oldman oldMan, String filePath);

	// 监测呼吸异常
	public List<Alarm> detectAbnormal(String date);

	// 9.5
	public List<Integer> findByOldIdAndDate(Oldman oldMan, String breathDateTime);

	// 9.7
	public List<Integer> getDetailedBreathByDay(Oldman oldMan, String dayStr);

}
