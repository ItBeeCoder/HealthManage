package cn.action;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import cn.entity.Oldman;
import cn.service.IOldManService;

import com.opensymphony.xwork2.ActionSupport;

public class UserAddAction extends ActionSupport {

	
	/**
	 * author:shaozq
	2017-7-28下午12:58:15
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String useraccount;
	private String password;
	
	
	//////UserAddAction中除了数据库USer表中对应的属性外，新增加的三个属性
	private String result;
	private String msg;
//	private IOldManDao oldmanDAO;
	private IOldManService oldManService;
	
	public void setOldManService(IOldManService oldManService) {
		this.oldManService = oldManService;
	}
	//	public void setOldmanDAO(IOldManDao oldmanDAO) {
//		this.oldmanDAO = oldmanDAO;
//	}
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String execute() throws Exception {
		
		System.out.println("useraccount = "+useraccount +"  password=== "+password);
		
//		List<Oldman> list = oldmanDAO.findByProperty("username", username);
//		List<Oldman> listOldMan = oldManService.getAll();
		List<Oldman> listOldMan = oldManService.findByProperty("Oldmanuseraccount", useraccount);
		System.out.println("listOldMan.size() ==== "+listOldMan.size());
		
		
		if(listOldMan.size()==0){
			Oldman user = new Oldman();
			user.setOldmanuseraccount(useraccount);//.setUseraccount(useraccount);
			user.setPassword(password);

			Date date = new Date(0);
			DateFormat format1 = new SimpleDateFormat("MM-dd");  
	        System.out.println("date===="+format1.format(date));
	        
			
//			oldmanDAO.save(user);
	        oldManService.save(user);
	        this.msg = "恭喜你，注册成功";
			this.result = "1";//1表示注册成功
			
		}else{
			this.result = "0";//0表示此账号已经存在
			this.msg = "账号名已经存在！";
		}
		
		return SUCCESS;
	}
	
	
}
