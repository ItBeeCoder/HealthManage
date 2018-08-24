package cn.service;

import java.util.List;

import cn.entity.Employee;

/**
 * Ա��ģ��ҵ���߼���ӿ�
 * @author Jie.Yuan
 *
 */
public interface IEmployeeService {
	/**
	 * ����Ա��
	 * @param emp
	 */
	void save(Employee emp);

	/**
	 * ����Ա����Ϣ
	 * @param emp
	 */
	void update(Employee emp);


	/**
	 * ��������ѯ
	 * @param id
	 * @return
	 */
	Employee findById(int id);

	/**
	 * ��ѯȫ��
	 * @return
	 */
	List<Employee> getAll();

	/**
	 * ���Ա�����������ѯ
	 * @param employeeName
	 * @return
	 */
	List<Employee> getAll(String employeeName);
	
	/**
	 * �������ɾ��
	 * @param id
	 */
	void delete(int id);
	
	/**
	 *  ɾ����Ա��
	 */
	void deleteMany(int[] ids);

}

