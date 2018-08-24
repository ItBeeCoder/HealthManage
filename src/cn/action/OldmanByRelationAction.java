package cn.action;

import java.util.List;

import cn.entity.Child;
import cn.entity.Oldman;
import cn.entity.Relation;
import cn.service.IChildService;
import cn.service.IRelationService;

import com.opensymphony.xwork2.ActionSupport;

public class OldmanByRelationAction extends ActionSupport {

	/**
	 * author：shaozq
	 * 2017-8-30下午09:40:07
	 * TODO
	 *思路：先根据传送过来的子女账号从子女表中获取子女的ID，
	 *然后根据子女ID和关系名称从关系表中获取老人的ID，再将老人的ID发送给APP
	 */
	private static final long serialVersionUID = 1L;
	private String childuseraccount;
	private String Oldmanuseraccount;
	private String relationship;
	private Integer oldManId;
	
	private String result;
	private String msg;
	
	private IRelationService relationService;
	private IChildService childService;
	
	public String getChilduseraccount() {
		return childuseraccount;
	}
	public void setChilduseraccount(String childuseraccount) {
		this.childuseraccount = childuseraccount;
	}
	public String getOldmanuseraccount() {
		return Oldmanuseraccount;
	}
	public void setOldmanuseraccount(String oldmanuseraccount) {
		Oldmanuseraccount = oldmanuseraccount;
	}
	
	public Integer getOldManId() {
		return oldManId;
	}
	public void setOldManId(Integer oldManId) {
		this.oldManId = oldManId;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
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

	public void setChildService(IChildService childService) {
		this.childService = childService;
	}
	
	public void setRelationService(IRelationService relationService) {
		this.relationService = relationService;
	}
	
	
	public String execute() throws Exception {

		
		System.out.println("relationship =="+relationship);
		
		List<Child> childlist = childService.findByProperty("childuseraccount", childuseraccount);
	
		Child child = new Child();
		if(childlist.size()!=0){
			 child=childlist.get(0);
		}
		//思路：先根据传送过来的子女账号从子女表中获取子女的ID，
		 //*然后根据子女ID和关系名称从关系表中获取老人的ID，再将老人的ID发送给APP
		
		Relation  relation = new  Relation();
//		relation.setRelationship(relationship);
//		relation.setChild(child);
		
		List<Relation> relationlist=relationService.findBychildAndRelation(child,relationship);
		
		if(relationlist.size()!=0){
		
			relation = relationlist.get(0);
			Oldmanuseraccount = relation.getOldman().getOldmanuseraccount();
			
		 System.out.println("execute successful,Oldmanuseraccount ="+Oldmanuseraccount);
		 result="1";//对应老人和子女的关系添加成功
		 msg = Oldmanuseraccount;
		 System.out.println("execute successful,msg ==="+msg);
		 
		}else{
			result="0";//数据库关联表中已经存在对应老人和子女的关系
			 msg="";	
		}
		return SUCCESS;
	}

}
