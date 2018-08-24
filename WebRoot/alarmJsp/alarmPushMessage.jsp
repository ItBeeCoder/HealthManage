<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>服务器推送的消息</title>
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
	<br>
	<br>
	<br>
	
	<!-- 这是服务器主动推送的消息内容 -->
	<div>
		<input type="button" id="btnConnection" onclick="lianjie()" value="连接" /> <input
			type="button" id="btnClose" value="关闭" /> <input type="button"
			id="btnSend" value="发送" />
	</div>
	
	<script type="text/javascript">
	
	function lianjie(){
		var socket;
		if (typeof (WebSocket) == "undefined") {
			alert("您的浏览器不支持WebSocket");
			return;
		}
		//实例化WebSocket对象，指定要连接的服务器地址与端口
		socket = new WebSocket(
				"ws://127.0.0.1:8080/healthCare/ws/dddd");
		//打开事件
		socket.onopen = function() {
			alert("Socket 已打开");
			// socket.send("这是来自客户端的消息" + location.href + new Date()); 
		};
		//获得消息事件
		socket.onmessage = function(msg) {
			//alert(msg.data);
		};
		//关闭事件
		socket.onclose = function() {
			alert("Socket已关闭");
		};
		//发生了错误事件
		socket.onerror = function() {
			alert("发生了错误");
		}
	}
	/*
		$(function() {
			var socket;
			if (typeof (WebSocket) == "undefined") {
				alert("您的浏览器不支持WebSocket");
				return;
			}

			$("#btnConnection").click(
					function() {
						//实例化WebSocket对象，指定要连接的服务器地址与端口
						socket = new WebSocket(
								"ws://127.0.0.1:8080/healthCare1124/ws/张三");
						//打开事件
						socket.onopen = function() {
							alert("Socket 已打开");
							// socket.send("这是来自客户端的消息" + location.href + new Date()); 
						};
						//获得消息事件
						socket.onmessage = function(msg) {
							//alert(msg.data);
						};
						//关闭事件
						socket.onclose = function() {
							alert("Socket已关闭");
						};
						//发生了错误事件
						socket.onerror = function() {
							alert("发生了错误");
						}
					});

			$("#btnSend").click(function() {
				socket.send("客户端发送的消息 " + location.href + new Date());
			});

			$("#btnClose").click(function() {
				socket.close();
			});

		});
	*/
	</script>

</body>
</html>
