package cn.impl;

import java.util.List;

import cn.dao.INurseDao;
import cn.entity.Nurse;

public class NurseDao extends BaseDao<Nurse> implements INurseDao {

	@SuppressWarnings("unchecked")
	public List<Nurse> getAll(String nurseName) {
		return getSessionFactory().getCurrentSession()//
				.createQuery("from Nurse where username like ?")//
				.setParameter(0, "%" + nurseName + "%")//
				.list();
	}

}
