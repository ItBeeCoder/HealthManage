package cn.impl;

import java.util.List;

import cn.dao.IRelationDao;
import cn.entity.Child;
import cn.entity.Oldman;
import cn.entity.Relation;

public class RelationDao extends BaseDao<Relation> implements IRelationDao {

	// 通过老人获取所有关系
	@SuppressWarnings("unchecked")
	public List<Relation> getAllByOld(Oldman oldMan) {
		return getSessionFactory().getCurrentSession()//
				.createQuery("from Relation as r where r.oldman = ?")//
				.setParameter(0, oldMan)//
				.list();
	}

	// 通过child获取所有关系集合
	@SuppressWarnings("unchecked")
	public List<Relation> getAllByChild(Child child) {
		return getSessionFactory().getCurrentSession()//
				.createQuery("from Relation as r where r.child = ?")//
				.setParameter(0, child)//
				.list();
	}

}
