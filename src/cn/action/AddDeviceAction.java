/**
 * 
 */
package cn.action;

import java.text.SimpleDateFormat;

import cn.entity.Pillow;
import cn.service.IOldManService;
import cn.service.IPillowService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Administrator
 *
 */
public class AddDeviceAction extends ActionSupport {

	/**
	 * author:shaozq
	2017-7-26下午05:21:43
	 */
	private static final long serialVersionUID = 1L;
	private String result;
	private String msg;
	
	private Integer pillowId;
	private String statement;
	private String configurationTime;
	private String pillowNumber;
	private String useraccount;
	private	IPillowService pillowservice;
	private IOldManService oldmanService;
	
	public void setPillowservice(IPillowService pillowservice) {
		this.pillowservice = pillowservice;
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
	public Integer getPillowId() {
		return pillowId;
	}
	public void setPillowId(Integer pillowId) {
		this.pillowId = pillowId;
	}
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	public String getConfigurationTime() {
		return configurationTime;
	}
	public void setConfigurationTime(String configurationTime) {
		this.configurationTime = configurationTime;
	}
	public String getPillowNumber() {
		return pillowNumber;
	}
	public void setPillowNumber(String pillowNumber) {
		this.pillowNumber = pillowNumber;
	}
	
	public String getUseraccount() {
		return useraccount;
	}
	public void setUseraccount(String useraccount) {
		this.useraccount = useraccount;
	}
	
	public void setOldmanService(IOldManService oldmanService) {
		this.oldmanService = oldmanService;
	}
	
	
	public String execute() throws Exception {

		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String curdate=format.format(new java.util.Date());
		System.out.println("pillowNumber ==="+pillowNumber+"  Oldmanuseraccount=="+useraccount);
		Pillow pillow=new Pillow();
		pillow.setPillowNumber(pillowNumber);
		pillow.setConfigurationTime(curdate);
		pillow.setStatement("正常");
		pillowservice.save(pillow);
		
		oldmanService.updateOldmanpillowId(pillow, "Oldmanuseraccount", useraccount);
		
		 System.out.println("execute successful=======");
		 result="ok";
		 msg="";
		return SUCCESS;
	}
	
	
}
