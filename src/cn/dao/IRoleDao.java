package cn.dao;

import java.util.List;

import cn.entity.Role;

public interface IRoleDao {
	List<Role> getAll();

	
	Role findById(int id);
}
