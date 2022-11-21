<%@ page import="static by.mozolevskij.pharmacy.command.attribute.UserAttribute.EXISTING_USER_INFO" %><%--
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
%>
<html>
<head>
    <title>Add user page</title>
</head>
<body>
<form action="controller">
    Name: <label>
    <input type="text" name="user_name" value="">
</label>
    <hr/>
    Login: <label>
    <input type="text" name="login" value="">
</label>
    <hr/>
    Password: <label>
    <input type="text" name="password" value="">
</label>
    <hr/>
    E-mail: <label>
    <input type="text" name="email" value="">
</label>
    <hr/>
    Phone: <label>
    <input type="text" name="phone" value="">
</label>
    <hr/>
    Access level: <br>
    <label>
        <input type="radio" name="access_level" value="CLIENT" checked="on">
    </label>CLIENT<br/>
    <label>
        <input type="radio" name="access_level" value="PHARMACIST">
    </label>PHARMACIST<br/>
    <label>
        <input type="radio" name="access_level" value="DOCTOR">
    </label>DOCTOR<br/>
    <input type="hidden" name="command" value="add_user">
    <input type="submit" value="Add user">
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
</form><br/>
<form action="controller">
    <input type="hidden" name="command" value="default">
    <input type="submit" value="to main page">
</form>

</body>
</html>
