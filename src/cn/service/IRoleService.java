package cn.service;

import java.util.List;

import cn.entity.Role;

public interface IRoleService {
	List<Role> getAll();

	Role findById(int id);
}
