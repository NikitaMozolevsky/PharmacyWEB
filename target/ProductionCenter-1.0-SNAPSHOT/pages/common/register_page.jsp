<%@ page import="static by.mozolevskij.pharmacy.command.attribute.UserAttribute.EXISTING_USER_INFO" %>
<%@ page import="static by.mozolevskij.pharmacy.command.attribute.DefaultAttribute.REGISTER_MSG" %><%--
  Created by IntelliJ IDEA.
  User: Nikita
  Date: 5/11/2022
  Time: 2:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    pageContext.setAttribute("existing_user_info", request.getAttribute(EXISTING_USER_INFO));
    pageContext.setAttribute("register_msg", request.getAttribute(REGISTER_MSG));
%>
<html>
<head>
    <title>Register page</title>
</head>
<body>
<form action="controller">
    Name: <input type="text" name="user_name" value="" required="required">
    <hr/>
    Login: <input type="text" name="login" value="" required="required">
    <hr/>
    Password: <input type="text" name="password" value="" required="required">
    <hr/>
    E-mail: <input type="text" name="email" value="" required="required">
    <hr/>
    Phone: <input type="text" name="phone" value="" required="required">
    <hr/>
    <input type="hidden" name="command" value="register">
    <input type="submit" value="Register user">
    <br>
    ${register_msg.toUpperCase()}
</form>
<form action="controller">
    <input type="hidden" name="command" value="default">
    <input type="submit" value="to main page">
</form>
<c:if test="${existing_user_info!=null}">
    User with these parameters already exist:
    <c:if test="${existing_user_info.login!=null}">
        <hr>
        Login: ${existing_user_info.login}
    </c:if>
    <c:if test="${existing_user_info.email!=null}">
        <hr>
        Email: ${existing_user_info.email}
    </c:if>
    <c:if test="${existing_user_info.phone!=null}">
        <hr>
        Phone: ${existing_user_info.phone}
    </c:if>
</c:if>
<br/>
<form action="controller">
    <input type="hidden" name="command" value="default">
    <input type="submit" value="to main page">
</form>
</body>
</html>
