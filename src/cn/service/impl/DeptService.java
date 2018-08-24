package cn.service.impl;

import java.util.List;

import cn.dao.IDeptDao;
import cn.entity.Dept;
import cn.service.IDeptService;

public class DeptService implements IDeptService {
	
	private IDeptDao deptDao;
	public void setDeptDao(IDeptDao deptDao) {
		this.deptDao = deptDao;
	}

	public Dept findById(int id) {
		return deptDao.findById(id);
	}

	public List<Dept> getAll() {
		return deptDao.getAll();
	}

}
