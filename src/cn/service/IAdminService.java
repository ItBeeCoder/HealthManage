package cn.service;

import cn.entity.Admin;

/**
 * ����Աҵ���߼���ӿ�
 * 
 * @author Jie.Yuan
 * 
 */
public interface IAdminService {

	/**
	 * 管理员注册
	 * @param admin
	 * @return
	 */
	String register(Admin admin);

	/**
	 * 管理员的登录
	 * @param admin
	 * @return
	 */
	Admin login(Admin admin);

}
