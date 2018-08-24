/**
 * 2017-9-5上午11:54:40
	author:shaozq
 */
package cn.action;

import java.util.List;

import cn.entity.Child;
import cn.entity.Oldman;
import cn.entity.Relation;
import cn.service.IBreathService;
import cn.service.IChildService;
import cn.service.IOldManService;
import cn.service.IRelationService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author shaozq
	2017-9-5上午11:54:40
 *
 */
public class getPrenightByOldAndDateAction extends ActionSupport {

	/**
	 * author:shaozq
	   2017-9-5上午11:54:49
	 */
	private static final long serialVersionUID = 1L;
	
	private String Oldmanuseraccount;
	private String breathDateTime;
	private List<Integer> OldmanPrenightBreathData;
	private String flag;
	
	private String result;
	private String msg;
	private IOldManService oldManService;
	private IBreathService breathService;

	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	public void setOldManService(IOldManService oldManService) {
		this.oldManService = oldManService;
	}
	
	public String getOldmanuseraccount() {
		return Oldmanuseraccount;
	}
	public void setOldmanuseraccount(String oldmanuseraccount) {
		Oldmanuseraccount = oldmanuseraccount;
	}
	
	public List<Integer> getOldmanPrenightBreathData() {
		return OldmanPrenightBreathData;
	}
	public void setOldmanPrenightBreathData(List<Integer> oldmanPrenightBreathData) {
		OldmanPrenightBreathData = oldmanPrenightBreathData;
	}
	public void setBreathService(IBreathService breathService) {
		this.breathService = breathService;
	}
	public String getBreathDateTime() {
		return breathDateTime;
	}
	public void setBreathDateTime(String breathDateTime) {
		this.breathDateTime = breathDateTime;
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

		breathDateTime = breathDateTime.replace("-", "");
		
		System.out.println("getPrenightByOldAndDateAction breathDateTime ==="+breathDateTime+"  getPrenightByOldAndDateAction Oldmanuseraccount=="+Oldmanuseraccount);
		
		List<Oldman>  list = oldManService.findByProperty("Oldmanuseraccount", Oldmanuseraccount);
		
		Oldman oldMan = new Oldman();
		if(list.size()!=0){
			oldMan=(Oldman)list.get(0);
		}
		
		if(breathDateTime!=null||breathDateTime!=""){
			
			List<Integer> datelist = breathService.getDetailedBreathByDay(oldMan, breathDateTime);
		
		
		if(datelist.size()!=0){
		
		 System.out.println("execute successful====");
		 result="1";//对应老人和子女的关系添加成功
		 msg="";
		 OldmanPrenightBreathData = datelist;
		 System.out.println("OldmanPrenightBreathData.size()==== "+OldmanPrenightBreathData.size());
		}else{
			result="0";//数据库关联表中已经存在对应老人和子女的关系
			 msg="";	
		}
		
		}
		return SUCCESS;
	}
	
}


