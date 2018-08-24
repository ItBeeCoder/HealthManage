package cn.service;

import java.util.List;

import cn.entity.Alarm;
import cn.entity.Oldman;
import cn.entity.Pillow;

public interface IPillowService {
	void save(Pillow pillow);

	void update(Pillow pillow);

	Pillow findById(int id);
	
	List<Pillow> findByNumber(String pillowNumber);
	
	List<Pillow> findByOld(Oldman oldMan);

	List<Pillow> getAll();
	
	

	List<Pillow> getAll(String configurationTime);

	List<Pillow> getAllByStatement(String configurationTime);

	void delete(int id);

	void deleteMany(int[] ids);

	List<Alarm> detectPillowAbnormal(String detectHour);
}
