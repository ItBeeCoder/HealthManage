package cn.action;

import java.util.List;

import cn.entity.Oldman;
import cn.service.IOldManService;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	
	/**
	 * author:shaozq
	   2017-7-29下午08:02:29
	 */
	private static final long serialVersionUID = 1L;
	private String useraccount;
	private String password;	
	private String result;
	private String msg;
	private IOldManService oldManService;


	public void setOldManService(IOldManService oldManService) {
		this.oldManService = oldManService;
	}

	public String getUseraccount() {
		return useraccount;
	}

	public void setUseraccount(String useraccount) {
		this.useraccount = useraccount;
	}
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String execute() throws Exception {

		System.out.println("useraccount ==="+useraccount+"password==="+password);

//		List list = oldmanDAO.findByProperty("username", username);
		List<Oldman>  list = oldManService.findByProperty("Oldmanuseraccount", useraccount);

		if (list.size() == 0) {
			this.result = "0";
			this.msg = "账户不存在";
		} else {
			Oldman user = (Oldman) list.get(0);
			if (password.equals(user.getPassword())) {
				System.out.println("user.getPassword()======="+user.getPassword());
//				addr=user.getRecvaddr();
				this.result = "1";
			} else {
//				result = "error";
				this.result = "2";
				this.msg = "账号或密码不正确";
			}
		}
		System.out.println("result======="+this.result);
		
		return SUCCESS;
	}
	
	
}
