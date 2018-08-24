package cn.service.impl;



import cn.dao.IAdminDao;
import cn.entity.Admin;
import cn.service.IAdminService;

public class AdminService implements IAdminService {
	
	// ע��dao  ������һ��Ҫ�ýӿڽ��ա�
	private IAdminDao adminDao; //JDK
	public void setAdminDao(IAdminDao adminDao) {
		this.adminDao = adminDao;
	}	

	public Admin login(Admin admin) {
		return adminDao.findByAdmin(admin);
	}


	
	public String register(Admin admin) {
		
		System.out.println("service register");
		
		Admin newAdmin = adminDao.findByAdmin(admin);
		
		if(newAdmin != null){
			System.out.println("yong hu cunzai");
			return "registerFail";
		}
		adminDao.save(admin);
		return "registerSuccess";

	}
	

}
