package cn.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import cn.entity.Wifi;
import cn.service.IWifiService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

public class WifiAction extends ActionSupport implements ModelDriven<Wifi>,
		RequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Wifi wifi = new Wifi();
	private Map<String, Object> request;
	private IWifiService wifiService;

	public Wifi getModel() {
		return wifi;
	}

	public Wifi getWifi() {
		return wifi;
	}

	public void setWifi(Wifi wifi) {
		this.wifi = wifi;
	}

	public IWifiService getWifiService() {
		return wifiService;
	}

	public void setWifiService(IWifiService wifiService) {
		this.wifiService = wifiService;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	/**
	 * 获取所有的wifi
	 * 
	 * @return
	 */

	public String list() {
		List<Wifi> listWifi = wifiService.getAll();

		request.put("listWifi", listWifi);
		return "list";
	}

	/**
	 * 跳转到wifi设备添加页面
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

			wifiService.save(wifi);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "listAction";
	}

	/**
	 * 3. 跳转到更新设备页面
	 */
	public String viewUpdate() {

		int id = wifi.getWifiId();

		Wifi newWifi = wifiService.findById(id);

		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.pop();
		vs.push(newWifi);

		return "edit";

	}

	/**
	 * 更新设备
	 * 
	 * @return
	 */
	public String update() {
		wifiService.update(wifi);

		return "listAction";

	}

	/**
	 * 删除wifi设备
	 * 
	 * @return
	 */
	public String delete() {
		// 获取删除的设备的id
		int wifiId = wifi.getWifiId();
		wifiService.delete(wifiId);
		return "listAction";
	}

}
