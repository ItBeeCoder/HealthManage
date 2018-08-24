package cn.action;

import java.util.List;

import cn.entity.Movement;
import cn.entity.Oldman;
import cn.service.IMovementService;
import cn.service.IOldManService;

import com.opensymphony.xwork2.ActionSupport;

public class getBodyMoveDataAction extends ActionSupport {

	/**
	 * author:shaozq
	2017-7-27下午09:44:41
	 */
	private static final long serialVersionUID = 1L;
	private Long movementId;
	private String movementDateTime;
	private String start;
	private String stop;
	private String result;
	private String OldmanAccount;//8.31号加上的
	private IOldManService oldmanService;//8.31号加上的
	private String msg,totalnum,starttiime,endtime;
	private IMovementService  movemwnetService;
	
	
	public String getOldmanAccount() {
		return OldmanAccount;
	}
	public void setOldmanAccount(String oldmanAccount) {
		OldmanAccount = oldmanAccount;
	}
	public void setOldmanService(IOldManService oldmanService) {
		this.oldmanService = oldmanService;
	}

	
	public String getTotalnum() {
		return totalnum;
	}
	public void setTotalnum(String totalnum) {
		this.totalnum = totalnum;
	}
	public String getStarttiime() {
		return starttiime;
	}
	public void setStarttiime(String starttiime) {
		this.starttiime = starttiime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public Long getMovementId() {
		return movementId;
	}
	public void setMovementId(Long movementId) {
		this.movementId = movementId;
	}
	public String getMovementDateTime() {
		return movementDateTime;
	}
	public void setMovementDateTime(String movementDateTime) {
		this.movementDateTime = movementDateTime;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getStop() {
		return stop;
	}
	public void setStop(String stop) {
		this.stop = stop;
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

	public void setMovemwnetService(IMovementService movemwnetService) {
		this.movemwnetService = movemwnetService;
	}

	public String execute() throws Exception {

//		System.out.println("breathDateTime ==="+breathDateTime);

		System.out.println("movementDateTime ==="+movementDateTime);
		//先通过老人的账号找到老人的ID，再根据老人的ID得到其对应的最高最低和平均呼吸率
		System.out.println("getBodyMoveDataAction OldmanAccount === "+OldmanAccount);
		
		List<Oldman> oldmanlist = oldmanService.findByProperty("Oldmanuseraccount",OldmanAccount);
		Oldman oldman = new Oldman();
		if(oldmanlist.size()!=0){
			oldman = oldmanlist.get(0);
		}
		System.out.println("oldman.id==="+oldman.getOldManId()+"  OldmanAccount="+OldmanAccount);
		
		int totalmovement = movemwnetService.getMovementAccount(oldman, movementDateTime);
		totalnum = String.valueOf(totalmovement);
		
		System.out.println("totalmovement=== "+totalmovement);
		
		//totalnum="2";
		starttiime="2017-07-27 17:10:59";
		endtime="2017-07-27 17:12:00";
		 
		 
		return SUCCESS;
	}
	
	
}
