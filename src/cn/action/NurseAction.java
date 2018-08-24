package cn.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import cn.entity.Nurse;
import cn.entity.Pillow;
import cn.service.INurseService;
import cn.service.IPillowService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

public class NurseAction extends ActionSupport implements ModelDriven<Nurse>,
		RequestAware {

	private Nurse nurse = new Nurse();
	private Map<String, Object> request;
	private INurseService nurseService;

	public Nurse getModel() {
		return nurse;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public INurseService getNurseService() {
		return nurseService;
	}

	public void setNurseService(INurseService nurseService) {
		this.nurseService = nurseService;
	}

	public String list() {
		List<Nurse> listNurse = nurseService.getAll();
		request.put("listNurse", listNurse);

		return "list";
	}

	public String viewAdd() {
		return "add";
	}

	/**
	 * 2. 添加护工设备
	 */
	public String save() {

		try {

			nurseService.save(nurse);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "listAction";
	}

	/**
	 * 3. 跳转到更新页面
	 */
	public String viewUpdate() {

		int id = nurse.getNurseId();
		Nurse newNurse = nurseService.findById(id);
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.pop();
		vs.push(newNurse);

		return "edit";

	}

	/**
	 * 更新护工
	 * 
	 * @return
	 */
	public String update() {
		nurseService.update(nurse);

		return "listAction";

	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	public String delete() {
		int nurseId = nurse.getNurseId();
		nurseService.delete(nurseId);
		return "listAction";
	}

}
