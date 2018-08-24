package cn.impl;

import java.util.List;


import cn.dao.IEmployeeDao;
import cn.entity.Employee;

public class EmployeeDao extends BaseDao<Employee> implements IEmployeeDao {
	/**
	 * 模糊查询
	 */
	@SuppressWarnings("unchecked")
	public List<Employee> getAll(String employeeName) {
		return getSessionFactory().getCurrentSession()//
				.createQuery("from Employee where empName like ?")//
				.setParameter(0, "%" + employeeName + "%")//
				.list();
	}

	public Employee findById(int id) {
		String hql = "from Employee e left join fetch e.dept where e.id=?";
		return (Employee) getSessionFactory().getCurrentSession()
				.createQuery(hql).setParameter(0, id).uniqueResult();
	}

}
