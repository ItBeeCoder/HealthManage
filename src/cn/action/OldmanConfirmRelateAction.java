/**
 * 2017-9-1下午04:52:43
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
	2017-9-1下午04:52:43
 *
 */
public class OldmanConfirmRelateAction extends ActionSupport {

	/**
	 * author:shaozq
	   2017-9-1下午04:52:46
	 */
	private static final long serialVersionUID = 1L;
	
	private String childuseraccount;
	private String Oldmanuseraccount;
	
	private String result;
	private String msg;
	private IRelationService relationService;
	private IOldManService oldManService;
	private IChildService childService;

	

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

		System.out.println("Oldmanuseraccount=="+Oldmanuseraccount+"  childuseraccount=="+childuseraccount);
		
		//Oldman oldMan=new Oldman();
		//oldMan.setOldmanuseraccount(Oldmanuseraccount);
		List<Oldman>  list = oldManService.findByProperty("Oldmanuseraccount", Oldmanuseraccount);
		Oldman oldman = new Oldman();
		if(list.size()!=0){
			oldman=(Oldman)list.get(0);
		}
		System.out.println("oldman.getOldmanuseraccount() ===== "+oldman.getOldmanuseraccount());
		
		System.out.println("childuseraccount ==== "+childuseraccount);
		List<Child>  childlist = childService.findByProperty("childuseraccount",childuseraccount);
		
		Child child = new Child();
		if(childlist.size()!=0){
			child = (Child)childlist.get(0);
		}
		System.out.println("child.getChilduseraccount() === "+child.getChilduseraccount());
		
		Relation  relation = new  Relation();
		relation.setChild(child);
		relation.setOldman(oldman);
		relation.setFlag(2);
		
		relationService.updateRelationFlag(relation);//.save(oldMan);
		
		 System.out.println("execute successful====");
		 result="1";//对应老人和子女的关系添加成功
		 msg="";
		
		return SUCCESS;
	}
	

}
