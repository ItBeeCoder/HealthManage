<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	request.setCharacterEncoding("utf-8");
%>
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


	<s:form action="/alarm_update.action" method="post" theme="simple">
		<!-- 修改操作：隐藏域保存主键值 -->
		<s:hidden name="alarmId" id="alarmId" value="%{alarmId}"></s:hidden>

		<table border="1" align="center" cellpadding="5" cellspacing="0">
			<tr>
				<td>报警类型</td>
				<td><s:textfield name="type" id="type"></s:textfield></td>
			</tr>
			<tr>
				<td>报警真实性</td>
				<td><s:textfield name="realOrNot" id="realOrNot"></s:textfield>
				</td>
			</tr>
			<tr>
				<td>报警时间</td>
				<td><s:textfield name="alarmTime" id="alarmTime"></s:textfield>
				</td>
			</tr>


			<tr>
				<td colspan="2"><s:submit value="修改信息"></s:submit></td>
			</tr>
		</table>
	</s:form>
</body>
</html>
