package cn.dao;

import java.util.List;

import cn.entity.Oldman;
import cn.entity.Pillow;

public interface IOldManDao {
	void save(Oldman oldMan);
	
	/******************************by shao******************************/
	void updateOldman(Oldman oldMan,String propertyName, Object value);
	void updateOldmanpillowId(Pillow pillow, String propertyName, Object value);
	/******************************by shao******************************/
	
	void update(Oldman oldMan);

	void delete(int id);

	Oldman findById(int id);

	List<Oldman> getAll();

	List<Oldman> getAll(String oldManName);
	
	//by shao
	public  List<Oldman> findByProperty(String propertyName, Object value);
	public void updateUserPassByAccount(Oldman oldMan,String propertyName, Object value);
	
	
		
}
