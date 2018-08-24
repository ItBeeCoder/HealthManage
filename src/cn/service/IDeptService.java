package cn.service;

import java.util.List;

import cn.entity.Dept;

/**
 * ����ģ��ҵ���߼���ӿ�
 * @author Jie.Yuan
 *
 */
public interface IDeptService {

	/**
	 * ��ѯȫ��
	 * @return ����ȫ����Ϣ
	 */
	List<Dept> getAll();

	/**
	 * ��������ѯ
	 * @param id  ����
	 * @return ���ز�ѯ��Ľ��
	 */
	Dept findById(int id);
}
