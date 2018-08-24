package cn.service;

import java.util.List;

import cn.entity.Alarm;
import cn.entity.Heart;
import cn.entity.Oldman;

public interface IHeartService {

	void save(Heart heart);

	void update(Heart heart);

	Heart findById(int id);

	List<Heart> getAll();

	List<Heart> getAll(String heartDatetime);

	void delete(int id);

	void deleteMany(int[] ids);

	// 获取所有老人的指定时间的心率
	int getMaxHeart(String heartDateTime);

	int getMinHeart(String heartDateTime);

	double getMeanHeart(String heartDateTime);

	// 获取指定老人的指定时间的新心率
	int getMaxHeart(Oldman oldMan, String heartDateTime);

	int getMinHeart(Oldman oldMan, String heartDateTime);

	double getMeanHeart(Oldman oldMan, String heartDateTime);

	// 获取给定开始时间和结束时间的心跳数据
	int getMaxHeartBySE(Oldman oldMan, String start, String end);

	int getMinHeartBySE(Oldman oldMan, String start, String end);

	double getMeanHeartBySE(Oldman oldMan, String start, String end);

	// 监测心率异常
	public List<Alarm> detectHeartAbnormal(String date);

	public void uploadHeartData(Oldman oldMan, String filePath);
}
