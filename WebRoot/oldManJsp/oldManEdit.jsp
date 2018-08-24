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
  	 <jsp:include page="/head.jsp"></jsp:include>
  
  	
  	<s:form action="/oldMan_update.action" method="post" theme="simple">
  	<!-- 修改操作：隐藏域保存主键值 -->
  	<s:hidden name="oldManId" id="oldManId" value="%{oldManId}"></s:hidden>
  	
  	 <table border="1" align="center" cellpadding="5" cellspacing="0">
  	 	<tr>
  	 		<td>老人姓名</td>
  	 		<td>
  	 			<s:textfield name="username" id="oldManName"></s:textfield>
  	 		</td>
  	 	</tr>
  	 	<tr>
  	 		<td>老人年龄</td>
  	 		<td>
  	 			<s:textfield name="age" id="age"></s:textfield>
  	 		</td>
  	 	</tr>
  	 	
  	 	<tr>
  	 		<td>老人住址</td>
  	 		<td>
  	 			<s:textfield name="address" id="address"></s:textfield>
  	 		</td>
  	 	</tr>
  	 	<tr>
  	 		<td>电话</td>
  	 		<td>
  	 			<s:textfield name="telephone" id="telephone"></s:textfield>
  	 		</td>
  	 	</tr>
  	 
  	 	
  	 	<tr>
  	 		<td>选择角色</td>
  	 		<td>
  	 			 <!-- 
  	 			 		value  默认选择的项的设置 
  	 			 			   即是设置要显示的对象的主键
  	 			 			  (根元素取值)
  	 			  -->
  	 			  
  	 			  <s:select 
  	 			  	name="roleId" 
  	 			  	headerKey="-1"
  	 			  	headerValue="请选择"
  	 			  	list="#request.listRole"
  	 			  	listKey="roleId"
  	 			  	listValue="roleName"
  	 			  	value="role.roleId"
  	 			  ></s:select>
  	 			   
  	 			  
  	 		</td>
  	 	</tr>
  	 	<tr>
  	 		<td colspan="2">
  	 			<s:submit value="修改老人信息"></s:submit>
  	 		</td>
  	 	</tr>
  	 </table>
  	 </s:form>
  </body>
</html>
