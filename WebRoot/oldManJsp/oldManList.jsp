<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>老人列表展示</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>

<body>
	<!-- 包含头部页面 -->
	<jsp:include page="/head.jsp"></jsp:include>

	<s:a href="oldMan_viewPillow" align="center">查看睡枕设备</s:a>
	&nbsp;||&nbsp;
	<s:a href="oldMan_viewWifi">查看wifi设备</s:a>
	&nbsp;||&nbsp;
	<s:a href="oldMan_viewNurse">查看护工</s:a>
	&nbsp;||&nbsp;
	<s:a href="oldMan_viewAlarmHistory">查看报警记录</s:a>

	<h3 align="center">老人信息表</h3>
	<table border="1" align="center" width="80%" cellpadding="5"
		cellspacing="0">
		<tr>
			<th>序号</th>
			<!-- <th>老人编号</th> -->

			<th>姓名</th>
			<th>年龄</th>
			<th>住址</th>
			<th>电话</th>
			<th>护工</th>
			<th>护工电话</th>

			<th>操作</th>
		</tr>
		<s:if test="#request.listOldMan != null">
			<s:iterator var="old" value="#request.listOldMan" status="st">
				<tr align="center">
					<td><s:property value="#st.count" /></td>
					<%-- <td><s:property value="#old.oldManId" /></td> --%>
					<td><s:property value="#old.username" /></td>
					<td><s:property value="#old.age" /></td>
					<td><s:property value="#old.address" /></td>
					<td><s:property value="#old.telephone" /></td>
					<td><s:property value="#old.nurse.username" /></td>
					<td><s:property value="#old.nurse.telephone" /></td>
					<td><s:a href="oldMan_viewDetail?oldManId=%{#old.oldManId}">详细信息</s:a>
						&nbsp;||&nbsp; <s:a
							href="oldMan_viewUpdate?oldManId=%{#old.oldManId}">修改</s:a>
						&nbsp;||&nbsp; <s:a href="oldMan_delete?oldManId=%{#old.oldManId}">删除</s:a>
						&nbsp;||&nbsp; <s:a
							href="oldMan_viewUploadData?oldManId=%{#old.oldManId}">上传数据
						</s:a> &nbsp;||&nbsp; <s:a
							href="oldMan_viewDownloadData?oldManId=%{#old.oldManId}">下载用户数据
						</s:a></td>
				</tr>
			</s:iterator>
		</s:if>
		<s:else>
			<tr>
				<td colspan="5">对不起，没有你要找的数据！请先录入。</td>
			</tr>
		</s:else>
		<br />

	</table>
	<br>
	<br>


	<%-- <s:a href="oldMan_viewAlarmRing">是否发生警报</s:a> --%>

	<s:a href="alarm_ring">是否发生警报</s:a>

	<s:a href="alarm_pushMessage">服务器推送的消息</s:a>

</body>
</html>
