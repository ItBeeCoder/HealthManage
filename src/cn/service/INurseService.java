package cn.service;

import java.util.List;

import cn.entity.Nurse;

public interface INurseService {
	
	void save(Nurse nurse);

	void update(Nurse nurse);

	Nurse findById(int id);

	List<Nurse> getAll();

	void delete(int id);

	void deleteMany(int[] ids);
}
