package cn.impl;

import org.hibernate.SessionFactory;

import cn.dao.IAdminDao;
import cn.entity.Admin;

public class AdminDao implements IAdminDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Admin findByAdmin(Admin admin) {
		System.out.println("admin Dao find ");
		return (Admin) sessionFactory.getCurrentSession()//
				.createQuery("from Admin where adminName=? and pwd=?")//
				.setString(0, admin.getAdminName())//
				.setString(1, admin.getPwd())//
				.uniqueResult();
	}

	public void save(Admin admin) {
		System.out.println(" dao save");
		sessionFactory.getCurrentSession().save(admin);
	}

}
