/**
 * 2017-9-1下午04:14:32
	author:shaozq
 */
package cn.action;

import java.util.List;

import cn.entity.Child;
import cn.entity.Oldman;
import cn.entity.Relation;
import cn.service.IChildService;
import cn.service.IOldManService;
import cn.service.IRelationService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author shaozq
	2017-9-1下午04:14:32
 *
 */
public class FirstPageGetConfirmDataAction extends ActionSupport {

	/**
	 * author:shaozq
	   2017-9-1下午04:14:36
	 */
	private static final long serialVersionUID = 1L;
	
	private String Oldmanuseraccount;
	
	private String flag;
	
	private String result;
	private String msg;
	private IRelationService relationService;
	private IOldManService oldManService;

	
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
	
	
	public void setRelationService(IRelationService relationService) {
		this.relationService = relationService;
	}
	
	
	public String execute() throws Exception {

		System.out.println("Oldmanuseraccount=="+Oldmanuseraccount);
		
		Relation  relation = new  Relation();
		//relation.setRelationship(relationship);
		
		List<Oldman>  list = oldManService.findByProperty("Oldmanuseraccount", Oldmanuseraccount);
		
		Oldman oldMan=new Oldman();
		if(list.size()!=0){
		  oldMan=(Oldman)list.get(0);
		}
		
		List<Relation> relationlist=relationService.findByOldIdAndFlag(oldMan,1);
		
		
		System.out.println("relationlist.size()== "+relationlist.size());
		
		if(relationlist.size()!=0){
		
		 relation = relationlist.get(0);
		 System.out.println("execute successful====");
		 result="1";//对应老人和子女的关系添加成功
		 msg=relation.getChild().getChilduseraccount();//返回要和老人关联的子女的账号
		 
		}else{
			result="0";//数据库关联表中已经存在对应老人和子女的关系
			 msg="";	
		}
		System.out.println("result =="+result);
		return SUCCESS;
	}
	
	
}
