package cn.dao;

import java.util.List;

import cn.entity.Movement;
import cn.entity.Oldman;

public interface IMovementDao {
	
	void save(Movement movement);

	void update(Movement movement);

	void delete(int id);

	Movement findById(int id);

	List<Movement> getAll();
	
	List<Movement> getAllByTime(String movementDateTime);
	
	//指定老人id的所有数据
	List<Movement> getAllByOld(Oldman oldMan);
	List<Movement> getAllByOldAndDay(Oldman oldMan,String dayStr);
	
	
}
