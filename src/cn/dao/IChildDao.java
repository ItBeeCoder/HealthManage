package cn.dao;

import java.util.List;

import cn.entity.Child;
import cn.entity.Oldman;

public interface IChildDao {

	void save(Child child);

	void update(Child child);

	void delete(int id);

	Child findById(int id);

	List<Child> getAll();

	List<Child> getAll(String childName);
	
	public List<Child> findByProperty(String propertyName, Object value);
	
	
}
