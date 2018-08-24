<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%request.setCharacterEncoding("utf-8"); %>
<html>
  <head>
    <title>下载老人身体健康数据</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  
  <body>	
  	 <!-- 包含头部页面 -->
  	 <jsp:include page="/head.jsp"></jsp:include>
  
  
  	<s:form action="/oldMan_download.action" method="post" theme="simple">
  	 这里是下载老人睡眠数据页面……
  	 </s:form>
  </body>
</html>
