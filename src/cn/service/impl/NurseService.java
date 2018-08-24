package cn.service.impl;

import java.util.List;

import cn.dao.INurseDao;
import cn.entity.Nurse;
import cn.service.INurseService;

public class NurseService implements INurseService {

	private INurseDao nurseDao;

	public INurseDao getNurseDao() {
		return nurseDao;
	}

	public void setNurseDao(INurseDao nurseDao) {
		this.nurseDao = nurseDao;
	}

	public void save(Nurse nurse) {
		nurseDao.save(nurse);
	}

	public void update(Nurse nurse) {
		nurseDao.update(nurse);
	}

	public Nurse findById(int id) {
		return nurseDao.findById(id);
	}

	public List<Nurse> getAll() {
		return nurseDao.getAll();
	}

	public void delete(int id) {
		nurseDao.delete(id);
	}

	public void deleteMany(int[] ids) {
		
		if (ids != null && ids.length > 0) {
			for (int id : ids) {
				delete(id);
			}
		}
	}

}
