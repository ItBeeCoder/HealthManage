<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%request.setCharacterEncoding("utf-8"); %>
<html>
  <head>
    <title>老人详细信息</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  
  <body>	
  	 <!-- 包含头部页面 -->
  	 <jsp:include page="/head.jsp"></jsp:include>
  
  
  	<s:form action="/oldMan_detail.action" method="post" theme="simple">
  	 <h3 align="center">用户详细信息页面</h3>
  	 </s:form>
  </body>
</html>
