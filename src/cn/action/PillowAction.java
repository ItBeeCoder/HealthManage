package cn.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import cn.entity.Pillow;
import cn.service.IPillowService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

public class PillowAction extends ActionSupport implements ModelDriven<Pillow>,
		RequestAware {

	private Pillow pillow = new Pillow();
	private Map<String, Object> request;
	private IPillowService pillowService;

	public Pillow getModel() {
		return pillow;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public IPillowService getPillowService() {
		return pillowService;
	}

	public void setPillowService(IPillowService pillowService) {
		this.pillowService = pillowService;
	}

	/**
	 * 获取所有的pillow
	 * 
	 * @return
	 */
	public String list() {
		List<Pillow> listPillow = pillowService.getAll();
		// 注册request
		request.put("listPillow", listPillow);
		return "list";
	}

	/**
	 * 跳转到睡枕设备添加页面
	 */
	public String viewAdd() {
		// 直接返回字符串
		return "add";
	}

	/**
	 * 2. 添加睡枕设备
	 */
	public String save() {

		try {

			pillowService.save(pillow);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "listAction";
	}

	/**
	 * 3. 跳转到更新设备页面
	 */
	public String viewUpdate() {

		int id = pillow.getPillowId();
		Pillow newPillow = pillowService.findById(id);
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.pop();
		vs.push(newPillow);

		return "edit";

	}

	/**
	 * 更新设备
	 * 
	 * @return
	 */
	public String update() {
		pillowService.update(pillow);

		return "listAction";

	}

	/**
	 * 删除睡枕设备
	 * 
	 * @return
	 */
	public String delete() {

		// 获取删除的设备的id
		int pillowId = pillow.getPillowId();
		pillowService.delete(pillowId);

		return "listAction";
	}

}
