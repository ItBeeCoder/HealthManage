package cn.dao;

import java.util.List;


/**
 * ����dao��ͨ�ò����ӿڶ���
 * @author Jie.Yuan
 *
 */
public interface IBaseDao<T> {
	/**
	 * ����
	 * @param emp
	 */
	void save(T emp);

	/**
	 * ���¶�����Ϣ
	 * @param emp
	 */
	void update(T emp);

	/**
	 * �������ɾ��
	 * @param id
	 */
	void delete(int id);

	/**
	 * ��������ѯ
	 * @param id
	 * @return
	 */
	T findById(int id);

	/**
	 * ��ѯȫ��
	 * @return
	 */
	List<T> getAll();

}

