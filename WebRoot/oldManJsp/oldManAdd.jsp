<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%request.setCharacterEncoding("utf-8"); %>
<html>
  <head>
    <title>添加老人信息</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  
  <body>	
  	 <!-- 包含头部页面 -->
  	 <jsp:include page="/head.jsp"></jsp:include>
  
  
  	<s:form action="/oldMan_save.action" method="post" theme="simple">
  	 <table border="1" align="center" cellpadding="5" cellspacing="0">
  	 	<tr>
  	 		<td>姓名</td>
  	 		<td>
  	 			<s:textfield name="username" id="oldManName" value=""></s:textfield>
  	 		</td>
  	 	</tr>
  	 	<tr>
  	 		<td>年龄</td>
  	 		<td>
  	 			<s:textfield name="age" id="age" value=""></s:textfield>
  	 		</td>
  	 	</tr>
  	 	<tr>
  	 		<td>选择角色</td>
  	 		<td>
  	 			<!-- 传统的html标签
  	 			<select name="deptId">
  	 				<option value="-1">请选择</option>  
  	 				<option value="1">开发部</option> 循环迭代  s:iterator value="#request.listDept"
  	 			</select>
  	 			 -->
  	 			 <!-- 
  	 			 	Struts下拉列表标签：
  	 			 		name="deptId"  下拉列表标签的名称(服务器根据这个名称获取选择的项的实际的值value值)
  	 			 		headerKey   默认选择项的实际的值
  	 			 		headerValue  默认下拉列表显示的内容
  	 			 		list      下拉列表显示数据的集合
  	 			 		listKey    集合对象的哪个属性作为下拉列表的实例的值，即value值
  	 			 		listValue  集合对象的哪个属性作为下拉列表显示的值
  	 			 		value      默认选择的项的设置 
  	 			  -->
  	 			 
  	 			  
  	 			  <s:select
  	 			  	name="deptId" 
  	 			  	headerKey="-1"
  	 			  	headerValue="请选择"
  	 			  	list="#request.listRole"
  	 			  	listKey="roleId"
  	 			  	listValue="roleName"
  	 			  	value="-1"
  	 			  ></s:select>
  	 			   
  	 		</td>
  	 	</tr>
  	 	<tr>
  	 		<td colspan="2">
  	 			<s:submit value="添加老人"></s:submit>
  	 		</td>
  	 	</tr>
  	 </table>
  	 </s:form>
  </body>
</html>
