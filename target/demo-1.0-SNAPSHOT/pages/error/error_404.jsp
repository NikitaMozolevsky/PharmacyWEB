<%--
  Created by IntelliJ IDEA.
  User: Никита
  Date: 01.04.2022
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>404</title>
</head>
<body>
<% if(response.getStatus() == 404){ %>
<font color="red">Error: <%=exception.getMessage() %></font><br>

<%-- include login page --%>
<%@ include file="/index.jsp"%>
<%}else {%>
Hi There, error code is <%=response.getStatus() %><br>
Please go to <a href="/index.jsp">home page</a>
<%} %>
</body>
</html>
