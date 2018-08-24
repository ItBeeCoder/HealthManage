package cn.impl;

import java.util.List;

import cn.dao.IChildDao;
import cn.entity.Child;

public class ChildDao extends BaseDao<Child> implements IChildDao {

	@SuppressWarnings("unchecked")
	public List<Child> getAll(String childName) {
		return getSessionFactory().getCurrentSession()//
				.createQuery("from Oldman where username like ?")//
				.setParameter(0, "%" + childName + "%")//
				.list();
	}

}
