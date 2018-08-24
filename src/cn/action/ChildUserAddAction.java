/**
 * 2017-7-29下午06:58:29
	author:shaozq
 */
package cn.action;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import cn.entity.Child;
import cn.service.IChildService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Administrator
 *
 */
public class ChildUserAddAction extends ActionSupport {

	/**
	 * author:shaozq
	2017-7-29下午06:59:45
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
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setChildService(IChildService childService) {
		this.childService = childService;
	}

	public String getUseraccount() {
		return useraccount;
	}
	public void setUseraccount(String useraccount) {
		this.useraccount = useraccount;
	}
	
public String execute() throws Exception {
		
		System.out.println("username = "+useraccount +"  password=== "+password);
		

		List<Child> listOldChild = childService.findByProperty("childuseraccount", useraccount);
		System.out.println("listOldMan.size() ==== "+listOldChild.size());
		//System.out.println("listOldMan.get(0)==="+listOldMan.get(0));
	
		
		if(listOldChild.size()==0){
			Child user = new Child();
			user.setChilduseraccount(useraccount);//.setUseraccount(useraccount);
			user.setPassword(password);

			Date date = new Date(0);
			DateFormat format1 = new SimpleDateFormat("MM-dd");  
	        System.out.println("date===="+format1.format(date));
	        
			
//			oldmanDAO.save(user);
	        childService.save(user);
			this.msg = "恭喜你，注册成功";
			this.result = "1";//1表示注册成功
			
		}else{
			this.result = "0";//0表示此账号已经存在
			this.msg = "账号名已经存在！";
		}
		
		return SUCCESS;
	}
	
	
}
