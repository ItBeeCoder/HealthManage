<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%request.setCharacterEncoding("utf-8"); %>
<html>
  <head>
    <title>修改</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  
  <body>
  	 <!-- 包含头部页面 -->
  	 <jsp:include page="head.jsp"></jsp:include>
  
  	
  	<s:form action="/nurse_update.action" method="post" theme="simple">
  	<!-- 修改操作：隐藏域保存主键值 -->
  	<s:hidden name="nurseId" id="nurseId" value="%{nurseId}"></s:hidden>
  	
  	 <table border="1" align="center" cellpadding="5" cellspacing="0">
  	 	<tr>
  	 		<td>用户名</td>
  	 		<td>
  	 			<s:textfield name="username" id="username"></s:textfield>
  	 		</td>
  	 	</tr>
  	 	<tr>
  	 		<td>密码</td>
  	 		<td>
  	 			<s:textfield name="password" id="password"></s:textfield>
  	 		</td>
  	 	</tr>
  	 	<tr>
  	 		<td>手机号</td>
  	 		<td>
  	 			<s:textfield name="telephone" id="telephone"></s:textfield>
  	 		</td>
  	 	</tr>
  	 	
  	 	<tr>
  	 		<td colspan="2">
  	 			<s:submit value="修改护工信息"></s:submit>
  	 		</td>
  	 	</tr>
  	 </table>
  	 </s:form>
  </body>
</html>
