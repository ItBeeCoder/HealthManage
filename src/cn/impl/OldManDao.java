package cn.impl;

import java.util.List;

import org.hibernate.Query;

import cn.dao.IOldManDao;
import cn.entity.Oldman;

public class OldManDao extends BaseDao<Oldman> implements IOldManDao {

	/**
	 * 模糊查询，根据用户名进行模糊匹配
	 */
	@SuppressWarnings("unchecked")
	public List<Oldman> getAll(String oldManName) {
		return getSessionFactory().getCurrentSession()//
				.createQuery("from Oldman where username like ?")//
				.setParameter(0, "%" + oldManName + "%")//
				.list();
	}

	// by shao
//	public List<Oldman> findByProperty(String propertyName, Object value) {
//		String queryString = "from Oldman as model where model." + propertyName
//				+ "= ?";
//		System.out.println("queryString ==== " + queryString);
//
//		Query queryObject = getSessionFactory().getCurrentSession()
//				.createQuery(queryString);
//		queryObject.setParameter(0, value);
//		return queryObject.list();
//
//	}

}
