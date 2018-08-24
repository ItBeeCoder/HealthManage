package cn.service;

import java.util.List;

import cn.entity.Child;
import cn.entity.Oldman;

public interface IChildService {

	void save(Child child);

	void update(Child child);

	Child findById(int id);

	List<Child> getAll();

	List<Child> getAll(String childName);

	void delete(int id);

	void deleteMany(int[] ids);
	
	List<Child> findByProperty(String propertyName, Object value);
	
}
