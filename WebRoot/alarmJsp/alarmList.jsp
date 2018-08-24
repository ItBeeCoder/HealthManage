<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>所有报警信息</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>

<body>
	<!-- 包含头部页面 -->
	<jsp:include page="head.jsp"></jsp:include>

	<s:a href="oldMan_viewPillow" align="center">查看睡枕设备</s:a>
	&nbsp;||&nbsp;
	<s:a href="oldMan_viewWifi">查看wifi设备</s:a>
	&nbsp;||&nbsp;
	<s:a href="oldMan_viewNurse">查看护工</s:a>
	&nbsp;||&nbsp;
	<s:a href="oldMan_viewAlarmHistory">查看报警记录</s:a>

	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<s:a href="oldMan_list">回到主页</s:a>

	<h3 align="center">报警信息</h3>
	<table border="1" align="center" width="80%" cellpadding="5"
		cellspacing="0">
		<tr>
			<th>序号</th>
			<th>报警类型</th>
			<th>真实性</th>
			<th>报警时间</th>
			<th>老人id</th>
			<th>操作</th>
		</tr>
		<s:if test="#request.listAlarm != null">
			<s:iterator var="alarm" value="#request.listAlarm" status="st">
				<tr align="center">
					<td><s:property value="#st.count" /></td>
					<td><s:property value="#alarm.type" /></td>
					<td><s:property value="#alarm.realOrNot" /></td>
					<td><s:property value="#alarm.alarmTime" /></td>
					<td><s:property value="#alarm.oldman.oldManId" /></td>
					<td><s:a href="alarm_viewUpdate?alarmId=%{#alarm.alarmId}">修改</s:a>
						&nbsp;||&nbsp; <s:a href="alarm_delete?alarmId=%{#alarm.alarmId}">删除</s:a>
					</td>
				</tr>
			</s:iterator>
		</s:if>
		<s:else>
			<tr>
				<td colspan="5">对不起，没有你要找的数据！请先录入。</td>
			</tr>
		</s:else>

	</table>
</body>
</html>
