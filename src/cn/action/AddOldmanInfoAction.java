package cn.action;

import cn.entity.Oldman;
import cn.service.IBreathService;
import cn.service.IOldManService;

import com.opensymphony.xwork2.ActionSupport;

public class AddOldmanInfoAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String result;
	private String msg;

	
	private IOldManService oldmanService;
	private String useraccount;
	private String username;
	private String gender;
	private Double height;
	private Double weight;
	private Integer age;
	private String address;
	private String telephone;

	public void setOldmanService(IOldManService oldmanService) {
		this.oldmanService = oldmanService;
	}
	
	public String getUseraccount() {
		return useraccount;
	}

	public void setUseraccount(String useraccount) {
		this.useraccount = useraccount;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
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
	
	public String execute() throws Exception {

		System.out.println("username ==="+username+"  useraccount=="+useraccount);
		Oldman oldMan=new Oldman();
		oldMan.setUsername(username);
		oldMan.setGender(gender);
		oldMan.setAddress(address);
		oldMan.setAge(age);
		oldMan.setHeight(height);
		oldMan.setWeight(weight);
		oldMan.setTelephone(telephone);
		oldmanService.updateOldman(oldMan, "Oldmanuseraccount", useraccount);//.save(oldMan);
		
//		List list = oldmanDAO.findByProperty("username", username);
//		List<Oldman>  list = oldManService.findByProperty("username", username);
		 
		 System.out.println("execute successful=======");
		 result="ok";
		 msg="";
		return SUCCESS;
	}
	
}


