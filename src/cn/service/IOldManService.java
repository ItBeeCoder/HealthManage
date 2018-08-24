package cn.service;

import java.util.List;

import cn.entity.Oldman;
import cn.entity.Pillow;

public interface IOldManService {

	/**
	 * 保存老人
	 * 
	 * @param oldMan
	 */
	void save(Oldman oldMan);

	void updateOldman(Oldman oldMan, String propertyName, Object value);

	void updateOldmanpillowId(Pillow pillow, String propertyName, Object value);

	/**
	 * 更新老人
	 * 
	 * @param oldMan
	 */
	void update(Oldman oldMan);

	/**
	 * 通过id找到老人
	 * 
	 * @param id
	 * @return 老人对象
	 */
	Oldman findById(int id);

	/**
	 * 获取所有老人对象
	 * 
	 * @return
	 */
	List<Oldman> getAll();

	List<Oldman> findByProperty(String propertyName, Object value);

	/**
	 * 通过老人名字模糊匹配
	 * 
	 * @param oldManName
	 * @return
	 */
	List<Oldman> getAll(String oldManName);

	/**
	 * 删除该id的老人
	 * 
	 * @param id
	 */
	void delete(int id);

	/**
	 * 删除该所有id下的老人
	 * 
	 * @param ids
	 */
	void deleteMany(int[] ids);

	// 该方法将老人的呼吸数据导入到数据库中,该方法没有将指定老人id的数据导入到数据库中
	void uploadBreathData(String filePath);

	// 将指定老人的呼吸数据导入到数据库中
	void uploadBreathDataByOldman(String filePath, Oldman oldMan);

	// 将指定老人的心率数据导入到数据库中
	void uploadHeartDataByOldman(String filePath, Oldman oldMan);

	// 将指定老人的体动数据导入到数据库中
	void uploadMovementDataByOldman(String filePath, Oldman oldMan);

	void updateUserPassByAccount(Oldman oldMan, String propertyName,
			Object value);
}
