/**
 * 2017-9-26下午10:32:11
	author:shaozq
 */
package cn.action;

import cn.entity.Pillow;
import cn.service.IPillowService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author shaozq
	2017-9-26下午10:32:11
 *
 */
public class TestSendDataAction extends ActionSupport {

	/**
	 * author:shaozq
	   2017-9-26下午10:32:16
	 */
	private static final long serialVersionUID = 1L;
	private String result;
	private String msg;
	private String curtime;
	
	private	IPillowService pillowservice;
	
	
	public String getCurtime() {
		return curtime;
	}
	public void setCurtime(String curtime) {
		this.curtime = curtime;
	}
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
	
	public String execute() throws Exception {

		System.out.println("TestSendDataAction curtime ==="+curtime);
		Pillow pillow=new Pillow();
		pillow.setPillowNumber("测试");
		pillow.setConfigurationTime(curtime);
		pillow.setStatement("正常");
		pillowservice.save(pillow);
		
		System.out.println("execute successful=======");
		 result="1";
		 msg="";
		return SUCCESS;
	}
	
}
