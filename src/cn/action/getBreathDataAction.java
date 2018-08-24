package cn.action;

import java.util.List;

import cn.entity.Breath;
import cn.entity.Oldman;
import cn.service.IBreathService;
import cn.service.IOldManService;

import com.opensymphony.xwork2.ActionSupport;

public class getBreathDataAction extends ActionSupport {

	/**
	 * author：shaozq
	 * 2017-8-31上午11:24:07
	 * TODO
	 */
	private static final long serialVersionUID = 1L;
	private Long breathId;
	private String startbreathDateTime,endbreathDateTime;
	private String result;
	private String OldmanAccount;
	private int maxbreathdata,minbreathdata;
	private double avgbreathdata;
	private String msg;
	private IBreathService breathService;
	private IOldManService oldmanService;//8.31号加上的
	
	
	public void setOldmanService(IOldManService oldmanService) {
		this.oldmanService = oldmanService;
	}
	public void setBreathService(IBreathService breathService) {
		this.breathService = breathService;
	}
	public int getMaxbreathdata() {
		return maxbreathdata;
	}
	public String getOldmanAccount() {
		return OldmanAccount;
	}
	public void setOldmanAccount(String OldmanAccount) {
		this.OldmanAccount = OldmanAccount;
	}	
	public void setMaxbreathdata(int maxbreathdata) {
		this.maxbreathdata = maxbreathdata;
	}

	public int getMinbreathdata() {
		return minbreathdata;
	}

	public void setMinbreathdata(int minbreathdata) {
		this.minbreathdata = minbreathdata;
	}
	
	public double getAvgbreathdata() {
		return avgbreathdata;
	}
	public void setAvgbreathdata(double avgbreathdata) {
		this.avgbreathdata = avgbreathdata;
	}
	
	public Long getBreathId() {
		return breathId;
	}
	public void setBreathId(Long breathId) {
		this.breathId = breathId;
	}
	
	public String getStartbreathDateTime() {
		return startbreathDateTime;
	}
	public void setStartbreathDateTime(String startbreathDateTime) {
		this.startbreathDateTime = startbreathDateTime;
	}
	public String getEndbreathDateTime() {
		return endbreathDateTime;
	}
	public void setEndbreathDateTime(String endbreathDateTime) {
		this.endbreathDateTime = endbreathDateTime;
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

		System.out.println("startbreathDateTime ==="+startbreathDateTime+"  endbreathDateTime ==="+endbreathDateTime);
		//先通过老人的账号找到老人的ID，再根据老人的ID得到其对应的最高最低和平均呼吸率
		System.out.println("OldmanAccount === "+OldmanAccount);
		
		List<Oldman> oldmanlist = oldmanService.findByProperty("Oldmanuseraccount",OldmanAccount);
		
		Oldman oldman = new Oldman();
		if(oldmanlist.size()!=0){
			 oldman = oldmanlist.get(0);
		}
		System.out.println("oldman.id==="+oldman.getOldManId()+"  OldmanAccount="+OldmanAccount);
		
		if((startbreathDateTime!=null||startbreathDateTime!="")&&(endbreathDateTime!=null||endbreathDateTime!="")){
			
			maxbreathdata = breathService.getMaxBreathBySE(oldman,startbreathDateTime,endbreathDateTime);
		
			 System.out.println("maxbreathdata======="+this.maxbreathdata);
			 minbreathdata = breathService.getMinBreathBySE(oldman, startbreathDateTime, endbreathDateTime);
			
			 System.out.println("minbreathdata======="+this.minbreathdata);
			 avgbreathdata=breathService.getMeanBreathBySE(oldman, startbreathDateTime, endbreathDateTime);
			 
			 System.out.println("avgbreathdata======="+this.avgbreathdata);
		}
		
		
		return SUCCESS;
	}
	
	
}
