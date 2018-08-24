package cn.dao;

import java.util.List;

import cn.entity.Nurse;

public interface INurseDao {

	void save(Nurse nurse);

	void update(Nurse nurse);

	void delete(int id);

	Nurse findById(int id);

	List<Nurse> getAll();

	List<Nurse> getAll(String nurseName);

}
