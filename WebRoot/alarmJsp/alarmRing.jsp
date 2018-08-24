<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>发生报警</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv=refresh content="180">
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

	<div id='abc'></div>

	<h3 align="center">！！！！！！！！！！！！！！异常报警信息提醒！！！！！！！！！！！！！！！！！</h3>

	<hr>
	<div id="message"></div>

</body>
<script type="text/javascript">
	var websocket = null;
	var adminName = "${sessionScope.adminInfo.adminName}";
	var adminId = "${sessionScope.adminInfo.id}";
	var adminPass = "${sessionScope.adminInfo.pwd}";

	//判断当前浏览器是否支持WebSocket
	if ('WebSocket' in window) {

		websocket = new WebSocket(
				"ws://localhost:8080/healthCare_s/newwebsocket/" + adminId);
	} else {
		alert('当前浏览器 Not support websocket');
	}
	//连接发生错误的回调方法
	websocket.onerror = function() {
		setMessageInnerHTML("WebSocket连接发生错误");
	};
	//连接成功建立的回调方法
	websocket.onopen = function() {
		setMessageInnerHTML("WebSocket连接成功");
	}
	//接收到消息的回调方法
	websocket.onmessage = function(event) {
		alert(event.data);
		setMessageInnerHTML(event.data);
	}
	//连接关闭的回调方法
	websocket.onclose = function() {
		setMessageInnerHTML("WebSocket连接关闭");
	}
	//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
	window.onbeforeunload = function() {
		closeWebSocket();
	}
	//将消息显示在网页上
	function setMessageInnerHTML(innerHTML) {
		document.getElementById('message').innerHTML += innerHTML + '<br/>';
	}
	//关闭WebSocket连接
	function closeWebSocket() {
		websocket.close();
	}
	//发送消息
	function send() {
		var message = document.getElementById('text').value;
		websocket.send(message);
	}
</script>


</html>
