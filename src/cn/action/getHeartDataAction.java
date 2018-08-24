package cn.action;


import java.util.List;

import cn.entity.Oldman;
import cn.service.IHeartService;
import cn.service.IOldManService;

import com.opensymphony.xwork2.ActionSupport;

public class getHeartDataAction extends ActionSupport {

	/**
	 * author:shaozq
	   2017-9-5下午05:23:28
	 */
	private static final long serialVersionUID = 1L;
	private Integer heartId;
	private String startheartDateTime,endheartDateTime;
	private String result;
	private String OldmanAccount;
	private int maxheartdata,minheartdata;
	private double avgheartdata;
	private String msg;
	private IHeartService heartService;
	private IOldManService oldmanService;
	
	public Integer getHeartId() {
		return heartId;
	}
	public void setHeartId(Integer heartId) {
		this.heartId = heartId;
	}

	public String getOldmanAccount() {
		return OldmanAccount;
	}
	public void setOldmanAccount(String oldmanAccount) {
		OldmanAccount = oldmanAccount;
	}
	
	public String getStartheartDateTime() {
		return startheartDateTime;
	}
	public void setStartheartDateTime(String startheartDateTime) {
		this.startheartDateTime = startheartDateTime;
	}
	public String getEndheartDateTime() {
		return endheartDateTime;
	}
	public void setEndheartDateTime(String endheartDateTime) {
		this.endheartDateTime = endheartDateTime;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public int getMaxheartdata() {
		return maxheartdata;
	}
	public void setMaxheartdata(int maxheartdata) {
		this.maxheartdata = maxheartdata;
	}
	public int getMinheartdata() {
		return minheartdata;
	}
	public void setMinheartdata(int minheartdata) {
		this.minheartdata = minheartdata;
	}
	public double getAvgheartdata() {
		return avgheartdata;
	}
	public void setAvgheartdata(double avgheartdata) {
		this.avgheartdata = avgheartdata;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public void setHeartService(IHeartService heartService) {
		this.heartService = heartService;
	}
	
	public void setOldmanService(IOldManService oldmanService) {
		this.oldmanService = oldmanService;
	}
	
	
	public String execute() throws Exception {

		System.out.println("startbreathDateTime ==="+startheartDateTime+"  endbreathDateTime ==="+endheartDateTime);
		

		System.out.println("OldmanAccount === "+OldmanAccount);
		
		List<Oldman> oldmanlist = oldmanService.findByProperty("Oldmanuseraccount",OldmanAccount);
		
		Oldman oldman = new Oldman();
		if(oldmanlist.size()!=0){
		 oldman = oldmanlist.get(0);
		}
		System.out.println("OldmanAccount = "+OldmanAccount);
		
		if((startheartDateTime!=null||startheartDateTime!="")&&(endheartDateTime!=null||endheartDateTime!="")){
		
			maxheartdata = heartService.getMaxHeartBySE(oldman, startheartDateTime, endheartDateTime);
		 
		 System.out.println("maxbreathdata======="+this.maxheartdata);
		 minheartdata = heartService.getMinHeartBySE(oldman, startheartDateTime, endheartDateTime);
		 
		 System.out.println("minbreathdata======="+this.minheartdata);
		 avgheartdata=heartService.getMeanHeartBySE(oldman, startheartDateTime, endheartDateTime);
		
		 System.out.println("avgbreathdata======="+this.avgheartdata);
		
		}
		return SUCCESS;
	}
	

}
