<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%request.setCharacterEncoding("utf-8"); %>
<html>
  <head>
    <title>添加设备信息</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  
  <body>	
  	 <!-- 包含头部页面 -->
  	 <jsp:include page="head.jsp"></jsp:include>
  
  
  	<s:form action="/wifi_save.action" method="post" theme="simple">
  	 <table border="1" align="center" cellpadding="5" cellspacing="0">
  	 	<tr>
  	 		<td>设备状态</td>
  	 		<td>
  	 			<s:textfield name="statement" id="statement" value=""></s:textfield>
  	 		</td>
  	 	</tr>
  	 	<tr>
  	 		<td>配置时间</td>
  	 		<td>
  	 			<s:textfield name="configurationTime" id="configurationTime" value=""></s:textfield>
  	 		</td>
  	 	</tr>
  	 	
  	 	<tr>
  	 		<td colspan="2">
  	 			<s:submit value="添加wifi设备"></s:submit>
  	 		</td>
  	 	</tr>
  	 </table>
  	 </s:form>
  </body>
</html>
