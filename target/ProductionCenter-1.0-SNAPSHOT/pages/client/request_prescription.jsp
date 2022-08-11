<%--
  Created by IntelliJ IDEA.
  User: Nikita
  Date: 7/28/2022
  Time: 1:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Order prescription</title>
</head>
<body>

<%--<c:forEach var="elem" items="${myList}" varStatus="status">
    <p>
            ${elem}
    </p>
</c:forEach>--%>
List of doctors:
<table border="1">
    <thead>
    <tr>
        <th>User name</th>
        <th>Login</th>
        <th>email</th>
        <th>Phone</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="users" items="${user}">
        <form action="controller">
            <tr>
                <input type="hidden" name="user_id" value=${users.userId}>

                <td>${users.userName}
                    <input type="hidden" name="user_name" value=${users.userName}></td>
                <td>${users.login}
                    <input type="hidden" name="login" value=${users.login}></td>
                <td>${users.email}
                    <input type="hidden" name="email" value=${users.email}></td>
                <td>${users.phone}
                    <input type="hidden" name="phone" value=${users.phone}></td>

                <td><input type="hidden" name="command" value="request_prescription">
                    <input type="submit" value="Request prescription"></td>
            </tr>
        </form>
    </c:forEach>
    <%--<tr>
        <td>123</td>
        <td>Rname</td>
    </tr>
    <tr>
        <td>234</td>
        <td>R2name</td>
    </tr>--%>
    </tbody>
    <tfoot>
    <tr>
        <td colspan="2">All users<td/>
    </tr>
    </tfoot>
</table>
<form action="controller">
    <input type="hidden" name="command" value="default">
    <input type="submit" value="to main page">
</form>
</body>
</html>