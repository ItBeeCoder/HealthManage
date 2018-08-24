package cn.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * Ч���û��Ƿ��½��ֻ�е�½��ſ��Խ��в�����
 * û�е�½��ֻ�ܲ鿴�б?���ܲ�����
 * @author Jie.Yuan
 *
 */
public class UserInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// �õ���ǰִ�еķ���
		String methodName = invocation.getProxy().getMethod();
		// �õ�ActionContext����
		ActionContext ac = invocation.getInvocationContext();
		// ��ȡsession, ��session�л�ȡ��½�Ĺ���Ա�˺�
		Object obj = ac.getSession().get("adminInfo");
		
		// �жϣ�
		if (!"login".equals(methodName) && !"list".equals(methodName)&& !"register".equals(methodName)){
			
			// ��֤
			if (obj == null){
				// û�е�½
				return "login";
			} else {
				
				return invocation.invoke();
			}
			
		} else {
			// ������ʵ�½���б�չʾ
			return invocation.invoke();
		}
	}

}
