package cn.service;

import java.util.List;

import cn.entity.Movement;
import cn.entity.Oldman;

public interface IMovementService {

	/**
	 * 保存数据
	 * @param movement
	 */
	void save(Movement movement);

	/**
	 * 删除指定id的数据
	 * @param id
	 */
	void delete(int id);

	/**
	 * 删除多条数据
	 * @param ids
	 */
	void deleteMany(int[] ids);

	
	/**
	 * 更新数据
	 * @param heart
	 */
	void update(Movement movement);

	/**
	 * 通过movement的id找到movement
	 * @param id
	 * @return
	 */
	Movement findById(int id);

	/**
	 * 获取所有的体动数据
	 * 
	 * @return
	 */
	List<Movement> getAll();

	/**
	 * 获取指定老人id的所有的体动数据
	 * @param oldManId
	 * @return
	 */
	List<Movement> getAll(Oldman oldMan);

	/**
	 * 获取指定老人id，指定日期下的该天的体动数据
	 * @param oldManId
	 * @param dayStr
	 * @return 所有体动数据
	 */
	List<Movement> getAll(String dayStr);
	
	/**
	 * 获取指定老人，指定日期下的老人体动次数
	 * @param oldManId
	 * @param dayStr
	 * @return
	 */
	int getMovementAccount(Oldman oldMan,String dayStr);

	
	/**
	 * 获取指定老人，指定日期下的老人的总移动时间
	 * @param oldManId
	 * @param dayStr
	 * @return
	 */
	int getAllMovementDuration(Oldman oldMan,String dayStr);
	
	public void uploadMovementData(Oldman oldMan,String filePath);
}
