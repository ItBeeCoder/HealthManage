package cn.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import cn.entity.Child;
import cn.entity.Oldman;
import cn.entity.Relation;
import cn.service.IChildService;
import cn.service.IRelationService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ChildAction extends ActionSupport implements ModelDriven<Child>,
		RequestAware {

	private Child child = new Child();
	private Map<String, Object> request;
	private IChildService childService;
	
	private String relationship;

	public Child getModel() {
		return child;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public IChildService getChildService() {
		return childService;
	}

	public void setChildService(IChildService childService) {
		this.childService = childService;
	}

	/**
	 * 老人列表
	 * 
	 * @return
	 */
	public String list() {
		List<Child> listChild = childService.getAll();
		request.put("listChild", listChild);
		return "list";
	}

	public String viewAdd() {
		return null;
	}

	public String save() {

		return "listAction";
	}

	public String viewUpdate() {

		return "edit";
	}

	public String update() {

		return "listAction";
	}

	public String delete() {

		return "listAction";
	}


}
