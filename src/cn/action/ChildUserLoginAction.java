/**
 * 2017-7-29下午08:02:59
	author:shaozq
 */
package cn.action;

import java.util.List;

import cn.entity.Child;
import cn.service.IChildService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author shaozq
	2017-7-29下午08:02:59
 *
 */
public class ChildUserLoginAction extends ActionSupport {

	/**
	 * author:shaozq
	   2017-7-29下午08:03:03
	 */
	private static final long serialVersionUID = 1L;
	private String result;
	private String msg;
	private IChildService childService;
	private String useraccount;
	private String password;
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getUseraccount() {
		return useraccount;
	}
	public void setUseraccount(String useraccount) {
		this.useraccount = useraccount;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setChildService(IChildService childService) {
		this.childService = childService;
	}
	
	public String execute() throws Exception {

		System.out.println("childuseraccount ==="+useraccount+"childpassword==="+password);

		List<Child>  list = childService.findByProperty("childuseraccount", useraccount);

		if (list.size() == 0) {
			this.result = "0";
			this.msg = "账户名不存在";
		} else {
			Child user = (Child) list.get(0);
			if (password.equals(user.getPassword())) {
				System.out.println("user.getPassword()======="+user.getPassword());
				this.result = "1";//1表示成功
			} else {
				this.result = "2";
				this.msg = "账号或密码不正确";
			}
		}
		System.out.println("result======="+this.result);
		
		return SUCCESS;
	}
	

}
