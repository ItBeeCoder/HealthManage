package cn.service.impl;

import java.util.List;

import cn.dao.IRoleDao;
import cn.entity.Role;
import cn.service.IRoleService;

public class RoleService implements IRoleService {
	private IRoleDao roleDao;

	public IRoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public List<Role> getAll() {
		return roleDao.getAll();
	}

	public Role findById(int id) {
		return roleDao.findById(id);
	}

}
