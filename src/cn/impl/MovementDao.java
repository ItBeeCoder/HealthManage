package cn.impl;

import java.util.List;

import cn.dao.IMovementDao;
import cn.entity.Movement;
import cn.entity.Oldman;

public class MovementDao extends BaseDao<Movement> implements IMovementDao {

	@SuppressWarnings("unchecked")
	public List<Movement> getAllByTime(String movementDateTime) {

		return getSessionFactory().getCurrentSession()//
				.createQuery("from Movement where movementDateTime = ?")//
				.setParameter(0, movementDateTime).list();
	}

	@SuppressWarnings("unchecked")
	public List<Movement> getAllByOld(Oldman oldMan) {
		return getSessionFactory().getCurrentSession()//
				.createQuery("from Movement as m where m.oldMan=?")//
				.setParameter(0, oldMan).list();
	}

	@SuppressWarnings("unchecked")
	public List<Movement> getAllByOldAndDay(Oldman oldMan, String dayStr) {
		String queryString = "from Movement as m where m.oldman = ? and movementDateTime = ?" ;
		System.out.println("queryString ==== " + queryString);
		return getSessionFactory()
				.getCurrentSession()
				.createQuery(queryString)
				.setParameter(0, oldMan).
				setParameter(1, dayStr).list();
		
	}

}
