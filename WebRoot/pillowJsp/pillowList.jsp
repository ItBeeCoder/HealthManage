<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>睡枕设备列表展示</title>
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
	<h3 align="center">睡枕信息</h3>
	<table border="1" align="center" width="80%" cellpadding="5"
		cellspacing="0">
		<tr>
			<th>序号</th>
			<th>睡枕编号</th>
			<th>状态</th>
			<th>配置时间</th>
			<th>操作</th>
		</tr>
		<s:if test="#request.listPillow != null">
			<s:iterator var="pillow" value="#request.listPillow" status="st">
				<tr align="center">
					<td><s:property value="#st.count" /></td>
					<td><s:property value="#pillow.pillowId" /></td>
					<td><s:property value="#pillow.statement" /></td>
					<td><s:property value="#pillow.configurationTime" /></td>
					<td><s:a href="pillow_viewUpdate?pillowId=%{#pillow.pillowId}">修改</s:a>
						&nbsp;||&nbsp; <s:a
							href="pillow_delete?pillowId=%{#pillow.pillowId}">删除</s:a></td>
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
