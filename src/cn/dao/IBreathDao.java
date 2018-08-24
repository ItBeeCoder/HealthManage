package cn.dao;

import java.util.List;

import cn.entity.Breath;
import cn.entity.Oldman;

public interface IBreathDao {
	void save(Breath breath);

	void update(Breath breath);

	void delete(int id);

	Breath findById(int id);

	List<Breath> getAll();

	List<Breath> getAll(String datetime);

	List<Breath> getAllByOld(Oldman oldMan);

	List<Breath> getAllByOldAndDay(Oldman oldMan, String dayStr);

	// 开始时间和结束时间格式为 2017072712 分开来看就是 2017-07-27 12
	// 结束时间格式 2017072718 分开来看就是 2017-07-27 18
	List<Breath> getByOldStartEnd(Oldman oldMan, String start, String end);

	// 获取从现在往前推三分钟的数据
	List<Breath> getOldmanBefore3Minute(Oldman oldMan, String date);

	List<Breath> getOldmanDuring4Hours(Oldman oldMan, String detectHour);
}
