package cn.impl;

import java.util.List;

import org.hibernate.SessionFactory;

import cn.dao.IDeptDao;
import cn.entity.Dept;

public class DeptDao implements IDeptDao {
	
	// ����ע��
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Dept findById(int id) {
		return (Dept) sessionFactory.getCurrentSession().get(Dept.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Dept> getAll() {
		return sessionFactory.getCurrentSession().createQuery("from Dept").list();
	}

}
