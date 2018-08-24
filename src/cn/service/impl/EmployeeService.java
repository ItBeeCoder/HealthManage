package cn.service.impl;

import java.util.List;

import cn.dao.IEmployeeDao;
import cn.entity.Employee;
import cn.service.IEmployeeService;

public class EmployeeService implements IEmployeeService {

	// ����ע��
	private IEmployeeDao employeeDao;

	public void setEmployeeDao(IEmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	public void delete(int id) {
		employeeDao.delete(id);
	}

	public void deleteMany(int[] ids) {
		if (ids != null && ids.length > 0) {
			for (int id : ids) {
				delete(id);
			}
		}
	}

	public Employee findById(int id) {
		return employeeDao.findById(id);
	}

	public List<Employee> getAll() {
		return employeeDao.getAll();
	}

	public List<Employee> getAll(String employeeName) {
		return employeeDao.getAll(employeeName);
	}

	public void save(Employee emp) {
		employeeDao.save(emp);
	}

	public void update(Employee emp) {
		employeeDao.update(emp);
	}

}
