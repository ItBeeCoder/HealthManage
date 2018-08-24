package cn.dao;

import java.util.List;

import cn.entity.Child;
import cn.entity.Oldman;
import cn.entity.Relation;

public interface IRelationDao {

	void save(Relation relation);

	void update(Relation relation);

	void delete(int id);

	Relation findById(int id);

	List<Relation> getAll();

	List<Relation> getAllByOld(Oldman oldMan);

	List<Relation> getAllByChild(Child child);
	
	List<Relation> findByOldIdAndChildId(Oldman oldMan,Child child);
	
	List<Relation>  findBychildAndRelation(Child child,String relationship);
	
	List<Relation>  findByOldIdAndFlag(Oldman oldMan,int flag);
	
	void updateRelationFlag(Relation relation);
	
}
