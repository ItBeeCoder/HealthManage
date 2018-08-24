package cn.action;

import cn.entity.Admin;
import cn.service.IAdminService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * This class is used for 管理员登录
 * 
 * @author wy @ version 1.0 1.0, 2017年7月21日 上午8:52:48
 */

public class AdminAction extends ActionSupport implements ModelDriven<Admin> {

	private static final long serialVersionUID = 1L;
	private Admin admin = new Admin();

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Admin getAdmin() {
		return admin;
	}

	public Admin getModel() {
		return admin;
	}

	// ����Service
	private IAdminService adminService;

	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
	}

	public String login() {
		// ��½��֤
		Admin adminInfo = adminService.login(admin);
		// ��֤
		if (adminInfo == null) {
			// ��½ʧ��
			return "loginFaild";
		} else {
			// ��½�ɹ�, ������ݵ�session
			ActionContext.getContext().getSession().put("adminInfo", adminInfo);
			return "index";
		}
	}

	public String register() {
		System.out.println("action method");
		return adminService.register(admin);
	}

}
