package cn.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import cn.entity.Dept;
import cn.entity.Employee;
import cn.service.IDeptService;
import cn.service.IEmployeeService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * Ա��ģ�������������
 * 1. Ա���б�չʾ
 * 2. ���Ա��
 * 3. �޸�Ա����Ϣ
 * 5. ɾ��
 * @author Jie.Yuan
 *
 */
public class EmployeeAction extends ActionSupport implements ModelDriven<Employee>, RequestAware{

	private static final long serialVersionUID = 1L;
	private Employee employee = new Employee();   // ��ģ����
	// ��װ����Ĳ���id(�����б��ʵ�ʵ�ֵ)
	private int deptId;
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public int getDeptId() {
		return deptId;
	}
	
	
	public Employee getModel() {
		return employee;   // ����ʵ���Ķ���
	}
	
	
	/*******����ע��Ա��Service********/
	private IEmployeeService employeeService;
	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	// ����Service
	private IDeptService deptService;
	public void setDeptService(IDeptService deptService) {
		this.deptService = deptService;
	}
	

	/**
	 * 1. Ա���б�չʾ
	 */
	public String list() {
		// ��ѯ����Ա��
		List<Employee> listEmp = employeeService.getAll();
		// ���浽request
		request.put("listEmp", listEmp);
		return "list";
	}
	
	/**
	 * 2. ���Ա�� - �������ҳ��
	 */
	public String viewAdd(){
		// ��ѯ���в�����Ϣ, ���浽request
		List<Dept> listDept = deptService.getAll();
		request.put("listDept", listDept);
		return "add";
	}
	
	/**
	 * 2. ���Ա�� - ���Ա�����
	 */
	public String save(){
		
		// �ȸ�ݲ��������ѯ
		Dept dept = deptService.findById(deptId);
		// ���õ�Ա��������
		employee.setDept(dept);
		
		// ����Service������Ա��
		employeeService.save(employee);
		return "listAction";  // �ض���Action
	}
	
	/**
	 *  3. �޸�Ա����Ϣ - �����޸���ͼ
	 */
	public String viewUpdate(){
		// ��ȡҪ�޸ĵļ�¼��id
		int id = employee.getId();
		
		// 1. ���Ա���������ѯ  (lazy="false")
		Employee emp = employeeService.findById(id);  // �Ѿ��в�����Ϣ
		// 2. ��ѯ���еĲ���
		List<Dept> listDept =  deptService.getAll();
		
		// ��ݻ���
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.pop();// �Ƴ�ջ��Ԫ��
		vs.push(emp); // ��ջ
		
		// ����
		request.put("listDept", listDept);
		
		return "edit";
	}
	
	/**
	 *  4. �޸�Ա����Ϣ - ȷ���޸�
	 */
	public String update() {
		
		//1. �ȸ�ݲ���id�� ��ѯ���Ŷ���; �����õ�Ա��������
		Dept dept = deptService.findById(deptId);
		employee.setDept(dept);
		
		//2. ����Ա��
		employeeService.update(employee);
		
		return "listAction";  // �ض����б�
	}
	
	/**
	 *  5. �޸�Ա����Ϣ - ɾ��
	 */
	public String delete(){
		// ��ȡҪɾ��Ա��������
		int empId = employee.getId();
		// ����serviceɾ��
		employeeService.delete(empId);
		return "listAction";
	}
	
	
	
	
	
	
	private Map<String, Object> request;
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
}











