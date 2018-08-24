package cn.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import cn.entity.Oldman;
import cn.entity.Role;
import cn.service.IBreathService;
import cn.service.INurseService;
import cn.service.IOldManService;
import cn.service.IRoleService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

public class OldManAction extends ActionSupport implements ModelDriven<Oldman>,
		RequestAware {

	private static final long serialVersionUID = 1L;

	private Oldman oldMan = new Oldman();
	private int roleId;
	private IOldManService oldManService;
	private IRoleService roleService;
	private INurseService nurseService;
	private IBreathService breathService;

	/**
	 * 老人列表
	 * 
	 * @return
	 */
	public String list() {
		List<Oldman> listOldMan = oldManService.getAll();
		request.put("listOldMan", listOldMan);
		return "list";
	}
	
	public String viewDetail(){
		
		return "detail";
		
	}
	
	public String detail(){
		
		return "listAction";
	}

	/**
	 * 进入老人添加页面
	 * 
	 * @return
	 */
	public String viewAdd() {

		List<Role> listRole = roleService.getAll();

		request.put("listRole", listRole);
		return "add";
	}

	/**
	 * 保存老人进入数据库
	 * 
	 * @return
	 */
	public String save() {
		//
		try {
			Role role = roleService.findById(roleId);
			oldMan.setRole(role);
			oldManService.save(oldMan);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return "listAction";
	}

	/**
	 * 更新老人界面
	 * 
	 * @return
	 */
	public String viewUpdate() {
		int id = oldMan.getOldManId();
		Oldman old = oldManService.findById(id);
		List<Role> listRole = roleService.getAll();

		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.pop();
		vs.push(old);
		request.put("listRole", listRole);
		return "edit";
	}

	/**
	 * 更新老人
	 * 
	 * @return
	 */
	public String update() {

		try {
			// 先查出修改前的oldMan
			Oldman obj = oldManService.findById(oldMan.getOldManId());
			// 然后修改属性值
			Role role = roleService.findById(roleId);
			obj.setRole(role);
			obj.setUsername(oldMan.getUsername());
			obj.setAge(oldMan.getAge());
			oldManService.update(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "listAction";
	}

	/**
	 * 删除老人操作
	 * 
	 * @return
	 */
	public String delete() {
		int oldManId = oldMan.getOldManId();
		oldManService.delete(oldManId);
		return "listAction";
	}

	/**
	 * 管理员进入查看睡枕设备页面
	 * 
	 * @return
	 */
	public String viewPillow() {

		return "viewPillow";

	}

	/**
	 * 管理员进入查看wifi设备页面
	 * 
	 * @return
	 */
	public String viewWifi() {

		return "viewWifi";

	}
	
	public String viewNurse() {

		return "viewNurse";

	}
	
	public String viewAlarmHistory() {
		System.out.println("view Alarm");
		return "viewAlarmHistory";
	}
	
	/**
	 * 查看是否有异常发生，异常包括设备异常，用户呼吸，心率出现异常。
	 * @return
	 */
	public String viewAlarmRing(){
		return "alarmRing";
	}

	/**
	 * 进入上传数据界面
	 * 
	 * @return
	 */
	public String viewUploadData() {

		return "viewUploadData";
	}

	/**
	 * 上传数据
	 * 
	 * @return
	 */
	public String uploadBreathData() {

		//String filePath = "F:\\Br_data.txt";
//		String filePath="F:\\0920p\\Br_data.txt";
		String filePath="E:\\pillow\\Br_data.txt";
		oldManService.uploadBreathData(filePath);
		return "listAction";
	}
	
	/**
	 * 上传指定老人呼吸数据
	 * 
	 * @return
	 */
	public String uploadBreathDataByOldman() {

		
		String filePath="E:\\pillow\\Br_data.txt";
		System.out.println(oldMan.getOldManId());
		oldManService.uploadBreathDataByOldman(filePath,oldMan);
		
		return "listAction";
	}
	
	
	/**
	 * 上传指定老人心率数据
	 * 
	 * @return
	 */
	public String uploadHeartDataByOldman() {

		
		String filePath="E:\\pillow\\hr_data.txt";
		System.out.println(oldMan.getOldManId());
		oldManService.uploadHeartDataByOldman(filePath,oldMan);
		
		return "listAction";
	}
	
	/**
	 * 上传指定老人体动数据
	 * 
	 * @return
	 */
	public String uploadMovementDataByOldman() {

		
		String filePath="E:\\pillow\\move_data.txt";
		System.out.println(oldMan.getOldManId());
		oldManService.uploadMovementDataByOldman(filePath,oldMan);
		
		return "listAction";
	}
	
	public String viewDownloadData(){
		return  "viewDownloadData";
	}
	
	public String download(){
		return  "listAction";
	}

	public void setOldMan(Oldman oldMan) {
		this.oldMan = oldMan;
	}

	public Oldman getModel() {
		return oldMan;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public IOldManService getOldManService() {
		return oldManService;
	}

	public void setOldManService(IOldManService oldManService) {
		this.oldManService = oldManService;
	}

	public IRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	public INurseService getNurseService() {
		return nurseService;
	}

	public void setNurseService(INurseService nurseService) {
		this.nurseService = nurseService;
	}



	private Map<String, Object> request;

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public IBreathService getBreathService() {
		return breathService;
	}

	public void setBreathService(IBreathService breathService) {
		this.breathService = breathService;
	}

}
