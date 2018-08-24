package cn.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import cn.dao.IBaseDao;
import cn.entity.Child;
import cn.entity.Oldman;
import cn.entity.Pillow;
import cn.entity.Relation;

public class BaseDao<T> implements IBaseDao<T> {
	
	// 
	private Class<T> clazz;
	// 
	private String className;
	
	// ���䷺��
	@SuppressWarnings("unchecked")
	public BaseDao(){
		Type type = this.getClass().getGenericSuperclass();
		// ת��Ϊ��������
		ParameterizedType pt = (ParameterizedType) type;  // BaseDao<Employee>
		// �õ�ʵ������
		Type types[] = pt.getActualTypeArguments();
		// ��ȡʵ������
		clazz = (Class<T>) types[0];
		
		className = clazz.getSimpleName();//���磺Employee
	}
	
	// 
	private static SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		BaseDao.sessionFactory = sessionFactory;
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void delete(int id) {
		sessionFactory
			.getCurrentSession()
			.createQuery("delete from " + className + " where id=?")
			.setParameter(0, id).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<T> findByProperty(String propertyName, Object value) {
		System.out.println("childDao propertyName === "+propertyName+"childDao value === "+value);
		
		String queryString = "from " + className + " as model where model." + propertyName
				+ "= ?";
		System.out.println("queryString ==== " + queryString);

		return sessionFactory
		.getCurrentSession()
		.createQuery(queryString)
		.setParameter(0, value).list();
	}
	
	public void updateOldman(Oldman oldMan,String propertyName, Object value){
		String queryString = "update " + className + " as model set model.username=?, model.gender=?, model.age=?, model.address=?, model.telephone=?, model.height=?, model.weight=?  where model." + propertyName
		+ "= ?";
		System.out.println("updateString ==== " + queryString);
		Query q = sessionFactory
		.getCurrentSession()
		.createQuery(queryString);
		//System.out.println("Query q finish");
		q.setString(0, oldMan.getUsername());
		q.setString(1, oldMan.getGender());
		q.setInteger(2, oldMan.getAge());
		q.setString(3, oldMan.getAddress());
		q.setString(4, oldMan.getTelephone());
		q.setDouble(5, oldMan.getHeight());
		q.setDouble(6, oldMan.getWeight());
		q.setParameter(7, value);
		
		 System.out.println("updateOldman BaseDao finish");
		q.executeUpdate();
		
		//.setParameter(0, value).executeUpdate();
//		String hql = "update Web w set w.littlewarm=? w.noticepic=? w.help=? where w.id =?";
//		Query q = session.createQuery(hql);
//		q.setString(0,littlewarmValue);
//		q.setByte(1,noticepicValue);
//		q.setString(2,helpValue);
//		q.setInteger(3,idValue);
//		q.executeUpdate();
	}
	
	public void updateUserPassByAccount(Oldman oldMan,String propertyName, Object value){
		String queryString = "update " + className + " as model set model.password=?  where model." + propertyName
		+ "= ?";
		System.out.println("updateString ==== " + queryString);
		Query q = sessionFactory
		.getCurrentSession()
		.createQuery(queryString);
		//System.out.println("Query q finish");
		q.setString(0, oldMan.getPassword());
		q.setString(1, oldMan.getOldmanuseraccount());
		
		
		 System.out.println("updateOldman BaseDao finish");
		q.executeUpdate();
	
	}
	
	public void updateOldmanpillowId(Pillow pillow, String propertyName, Object value){
		String queryString = "update " + className + " as model set model.pillow=?  where model." + propertyName
		+ "= ?";
		System.out.println("updateString ==== " + queryString);
		Query q = sessionFactory
		.getCurrentSession()
		.createQuery(queryString);
		//q.setInteger(0, pillow);
		q.setParameter(0, pillow);
		q.setParameter(1, value);
		
		 System.out.println("updateOldman BaseDao finish");
		q.executeUpdate();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<T>  findBychildAndRelation(Child child,String relationship){
		String queryString = "from " + className + " as model where model.child=? and  model.relationship=?" ;
		System.out.println("queryString ==== " + queryString);

		return sessionFactory
		.getCurrentSession()
		.createQuery(queryString)
		.setParameter(0, child)
		.setParameter(1,relationship).list();
	}
	
    @SuppressWarnings("unchecked")//9.1号加上的
	public	List<T>  findByOldIdAndFlag(Oldman oldMan,int flag){
    	String queryString = "from " + className + " as model where model.oldman=? and  model.flag=?" ;
		System.out.println("queryString ==== " + queryString);

		return sessionFactory
		.getCurrentSession()
		.createQuery(queryString)
		.setParameter(0, oldMan)
		.setParameter(1,flag).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByOldIdAndChildId(Oldman oldMan,Child child){
		String queryString = "from " + className + " as model where model.oldman=? and  model.child=?" ;
		System.out.println("queryString ==== " + queryString);

		return sessionFactory
		.getCurrentSession()
		.createQuery(queryString)
		.setParameter(0, oldMan)
		.setParameter(1,child).list();
	}
	
	@SuppressWarnings("unchecked")
	public T findById(int id) {
		return (T) sessionFactory.getCurrentSession().get(clazz, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		return sessionFactory.getCurrentSession().createQuery("from " + className).list();
	}
	@Transactional
	public void save(T t) {
		//System.out.println("开始调用sessionFactory.getCurrentSession().save(t) T=="+t);
		sessionFactory.getCurrentSession().save(t);
	}

	public void update(T t) {
		sessionFactory.getCurrentSession().update(t);
	}

	public void updateRelationFlag(Relation relation){
		String queryString = "update " + className + " as model set model.flag=?  where model.oldman=? and model.child=?";
		System.out.println("updateString ==== " + queryString);
		Query q = sessionFactory
		.getCurrentSession()
		.createQuery(queryString);
		//System.out.println("Query q finish");
		q.setInteger(0, relation.getFlag());
		q.setParameter(1, relation.getOldman());
		q.setParameter(2, relation.getChild());
		
		 System.out.println("updateOldman BaseDao finish");
		q.executeUpdate();
	
	}
	
}
