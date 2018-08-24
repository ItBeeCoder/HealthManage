package cn.service.impl;

import java.util.List;

import cn.dao.IChildDao;
import cn.dao.IRelationDao;
import cn.entity.Child;
import cn.entity.Oldman;
import cn.service.IChildService;

public class ChildService implements IChildService {

	private IChildDao childDao;
	private IRelationDao relationDao;

	public IChildDao getChildDao() {
		return childDao;
	}

	public void setChildDao(IChildDao childDao) {
		this.childDao = childDao;
	}

	public IRelationDao getRelationDao() {
		return relationDao;
	}

	public void setRelationDao(IRelationDao relationDao) {
		this.relationDao = relationDao;
	}

	public void save(Child child) {
		childDao.save(child);
	}

	public void update(Child child) {
		childDao.update(child);
	}

	public Child findById(int id) {
		return childDao.findById(id);
	}
	
	public List<Child> findByProperty(String propertyName, Object value){
		System.out.println("childService propertyName === "+propertyName+"childService value === "+value);
		return childDao.findByProperty(propertyName,value);
	}
	
	
	public List<Child> getAll() {
		return childDao.getAll();
	}

	public List<Child> getAll(String childName) {
		return childDao.getAll(childName);
	}

	public void delete(int id) {
		childDao.delete(id);
	}

	public void deleteMany(int[] ids) {
		if (ids != null && ids.length > 0) {
			for (int i : ids) {
				delete(i);
			}
		}
	}

}
