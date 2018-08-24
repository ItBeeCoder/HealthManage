package cn.service.impl;

import java.util.List;

import cn.dao.IRelationDao;
import cn.entity.Child;
import cn.entity.Oldman;
import cn.entity.Relation;
import cn.service.IRelationService;

public class RelationService implements IRelationService {

	private IRelationDao relationDao;

	public IRelationDao getRelationDao() {
		return relationDao;
	}

	public void setRelationDao(IRelationDao relationDao) {
		this.relationDao = relationDao;
	}

	public void save(Relation relation) {
		relationDao.save(relation);
	}

	public void update(Relation relation) {
		relationDao.update(relation);
	}

	public Relation findById(int id) {
		return relationDao.findById(id);
	}

	public List<Relation> getAll() {
		return relationDao.getAll();
	}

	public List<Relation> getAllByOld(Oldman oldMan) {
		return relationDao.getAllByOld(oldMan);
	}

	public List<Relation> getAllByChild(Child child) {
		return relationDao.getAllByChild(child);
	}

	public void delete(int id) {
		relationDao.delete(id);
	}

	public void deleteMany(int[] ids) {
		if (ids != null && ids.length > 0) {
			for (int i : ids) {
				delete(i);
			}
		}
	}
	//By shaozq
	public List<Relation> findByOldIdAndChildId(Oldman oldMan,Child child){
		return relationDao.findByOldIdAndChildId(oldMan,child);
	}
	
	public List<Relation>  findBychildAndRelation(Child child,String relationship){
		return relationDao.findBychildAndRelation(child,relationship);
	}
	
	public List<Relation> findByOldIdAndFlag(Oldman oldMan,int flag){
		return relationDao.findByOldIdAndFlag(oldMan,flag);
	}
	
	public void updateRelationFlag(Relation relation){
		relationDao.updateRelationFlag(relation);
	}
	
}