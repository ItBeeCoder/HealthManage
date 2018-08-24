package cn.dao;

import cn.entity.Admin;

/**
 * ����Աģ��dao�ӿ�
 * 
 * @author Jie.Yuan
 * 
 */
public interface IAdminDao {

	/**
	 * ����
	 * @param admin  ����Ա����
	 */
	void save(Admin admin);

	/**
	 * ��ݹ���Ա��Ϣ��ѯ
	 * @param admin  ����Ա����
	 * @return	���ز�ѯ��Ľ��
	 */
	Admin findByAdmin(Admin admin);

}
