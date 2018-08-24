/**
 * 2017-7-30下午01:27:42
	author:shaozq
 */
package cn.action;

import java.text.SimpleDateFormat;
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
	2017-7-30下午01:27:42
 *
 */
public class AddChildOldmanRelationAction extends ActionSupport {

	/**
	 * author:shaozq
	   2017-7-30下午01:27:45
	 */
	private static final long serialVersionUID = 1L;
	private String childuseraccount;
	private String Oldmanuseraccount;
	private String relationship;
	private String flag;
	
	private String result;
	private String msg;
	private IRelationService relationService;
	private IOldManService oldManService;
	private IChildService childService;

	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public void setChildService(IChildService childService) {
		this.childService = childService;
	}
	public void setOldManService(IOldManService oldManService) {
		this.oldManService = oldManService;
	}
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
	
	
	public void setRelationService(IRelationService relationService) {
		this.relationService = relationService;
	}
	
	
	public String execute() throws Exception {

		
		System.out.println("flag =="+flag+"relationship ==="+relationship+"  Oldmanuseraccount=="+Oldmanuseraccount+"  childuseraccount=="+childuseraccount);
		
		Relation  relation = new  Relation();
		relation.setRelationship(relationship);
		//Oldman oldMan=new Oldman();
		//oldMan.setOldmanuseraccount(Oldmanuseraccount);
		List<Oldman>  list = oldManService.findByProperty("Oldmanuseraccount", Oldmanuseraccount);
		Oldman oldMan = new Oldman();
		if(list.size()!=0){
			oldMan=(Oldman)list.get(0);
		}
		
		List<Child>  childlist = childService.findByProperty("childuseraccount", childuseraccount);
		Child child = new Child();
		if(childlist.size()!=0){
			child=(Child)childlist.get(0);
		}
		
		List<Relation> relationlist=relationService.findByOldIdAndChildId(oldMan,child);
		relation.setOldman(oldMan);
		relation.setChild(child);
		relation.setFlag(Integer.parseInt(flag));
		
		if(relationlist.size()==0){
		
		relationService.save(relation);
		 System.out.println("execute successful====");
		 result="1";//对应老人和子女的关系添加成功
		 msg="";
		}else{
			result="0";//数据库关联表中已经存在对应老人和子女的关系
			 msg="";	
		}
		return SUCCESS;
	}

}
