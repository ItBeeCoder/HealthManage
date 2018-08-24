package cn.impl;

import java.util.List;

import org.hibernate.SessionFactory;

import cn.dao.IRoleDao;
import cn.entity.Role;


public class RoleDao implements IRoleDao{

	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Role findById(int id) {
		return (Role) sessionFactory.getCurrentSession().get(Role.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Role> getAll() {
		return sessionFactory.getCurrentSession().createQuery("from Role").list();
	}

}
