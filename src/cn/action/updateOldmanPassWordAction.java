/**
 * 2017-9-4下午01:40:30
	author:shaozq
 */
package cn.action;

import cn.entity.Oldman;
import cn.service.IOldManService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author shaozq
	2017-9-4下午01:40:30
 *
 */
public class updateOldmanPassWordAction extends ActionSupport {

	/**
	 * author:shaozq
	   2017-9-4下午01:40:34
	 */
	private static final long serialVersionUID = 1L;
	
	private String result;
	private String msg;

	
	private IOldManService oldmanService;
	private String useraccount;
	private String newpassword;

	public void setOldmanService(IOldManService oldmanService) {
		this.oldmanService = oldmanService;
	}
	
	public String getUseraccount() {
		return useraccount;
	}

	public void setUseraccount(String useraccount) {
		this.useraccount = useraccount;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
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

		System.out.println("newpassword  ==="+newpassword +"  useraccount=="+useraccount);
		Oldman oldMan=new Oldman();
		oldMan.setOldmanuseraccount(useraccount);
		oldMan.setPassword(newpassword);
		
		oldmanService.updateUserPassByAccount(oldMan, "Oldmanuseraccount", useraccount);//.save(oldMan);
		
//		List list = oldmanDAO.findByProperty("username", username);
//		List<Oldman>  list = oldManService.findByProperty("username", username);
		 
		 System.out.println("execute successful=======");
		 result="ok";
		 msg="";
		return SUCCESS;
	}
	

}
