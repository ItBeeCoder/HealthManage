<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<html>
<head>
<title>上传数据</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>

<body>
	<!-- 包含头部页面 -->
	<jsp:include page="/head.jsp"></jsp:include>

	<!-- 修改操作：隐藏域保存主键值 -->
	<s:form action="/oldMan_uploadBreathDataByOldman.action" method="post"
		theme="simple">
		<s:hidden name="oldManId" id="oldManId" value="%{oldManId}"></s:hidden>
		<s:submit value="上传老人呼吸数据"></s:submit>

	</s:form>

	<s:form action="/oldMan_uploadHeartDataByOldman.action" method="post"
		theme="simple">
		<s:hidden name="oldManId" id="oldManId" value="%{oldManId}"></s:hidden>
		<s:submit value="上传老人心率数据"></s:submit>
	</s:form>

	<s:form action="/oldMan_uploadMovementDataByOldman.action" method="post"
		theme="simple">
		<s:hidden name="oldManId" id="oldManId" value="%{oldManId}"></s:hidden>
		<s:submit value="上传老人体动数据"></s:submit>
	</s:form>

</body>
</html>
