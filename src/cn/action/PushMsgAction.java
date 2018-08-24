/**
 * 2017-7-31下午07:49:57
	author:shaozq
 */
package cn.action;

import java.util.List;

import cn.entity.Oldman;
import cn.service.IOldManService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author shaozq
	2017-7-31下午07:49:57
 *
 */
public class PushMsgAction extends ActionSupport {

	/**
	 * author:shaozq
	   2017-7-31下午07:50:00
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String result;
	private String msg;
	
	//////private String account;
	private String username;
	private String Oldmanuseraccount;
	private String gender;
	private Integer age;
	private String address;
	private String telephone;
	
	private IOldManService oldManService;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getOldmanuseraccount() {
		return Oldmanuseraccount;
	}
	public void setOldmanuseraccount(String oldmanuseraccount) {
		Oldmanuseraccount = oldmanuseraccount;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
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
	public void setOldManService(IOldManService oldManService) {
		this.oldManService = oldManService;
	}
	
	public String execute() throws Exception {

		System.out.println("useraccount ===");

		List<Oldman>  list = oldManService.findByProperty("Oldmanuseraccount","test123");

		if (list.size() != 0)  {
			Oldman user = (Oldman) list.get(0);
			this.username=user.getUsername();
			this.Oldmanuseraccount=user.getOldmanuseraccount();
			this.gender=user.getGender();
			this.age=user.getAge();
			this.address=user.getAddress();
			this.telephone=user.getTelephone();
			
		}
		System.out.println("this.Oldmanuseraccount======="+this.Oldmanuseraccount);
	
		return SUCCESS;
	}
	
	

}
