package cn.impl;

import java.util.List;

import cn.dao.IPillowDao;
import cn.entity.Oldman;
import cn.entity.Pillow;

public class PillowDao extends BaseDao<Pillow> implements IPillowDao {

	@SuppressWarnings("unchecked")
	public List<Pillow> getAll(String configurationTime) {

		return getSessionFactory().getCurrentSession()//
				.createQuery("from Pillow where configurationTime like ?")//
				.setParameter(0, configurationTime + "%")//
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<Pillow> getAllByStatement(String statement) {
		return getSessionFactory().getCurrentSession()//
				.createQuery("from Pillow where statement like ?")//
				.setParameter(0, statement)//
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<Pillow> findByNumber(String pillowNumber) {
		return getSessionFactory().getCurrentSession()//
				.createQuery("from Pillow where pillowNumber=?")//
				.setParameter(0, pillowNumber)//
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<Pillow> findByOld(Oldman oldMan) {
		return getSessionFactory().getCurrentSession()//
				.createQuery("from Pillow as p where p.oldman=?")//
				.setParameter(0, oldMan)//
				.list();
	}

}
