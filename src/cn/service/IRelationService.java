package cn.service;

import java.util.List;

import cn.entity.Child;
import cn.entity.Oldman;
import cn.entity.Relation;

public interface IRelationService {
	
	void save(Relation relation);

	void update(Relation relation);

	Relation findById(int id);

	List<Relation> getAll();

	List<Relation> getAllByOld(Oldman oldMan);

	List<Relation> getAllByChild(Child child);

	void delete(int id);

	void deleteMany(int[] ids);
	
	List<Relation> findByOldIdAndChildId(Oldman oldMan,Child child);
	
	List<Relation> findBychildAndRelation(Child child,String relationship);
	
	List<Relation> findByOldIdAndFlag(Oldman oldMan,int flag);
	
	void updateRelationFlag(Relation relation);
	
}
