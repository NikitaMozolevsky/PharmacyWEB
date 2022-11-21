<%--
  Created by IntelliJ IDEA.
  User: Nikita
  Date: 6/12/2022
  Time: 3:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.*" %>
<%@ page import="static by.mozolevskij.pharmacy.command.attribute.UserAttribute.USER" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Show users</title>
</head>
<body>

    <table border="3">
        <thead>
        <tr>
            <th>User ID</th>
            <th>User name</th>
            <th>Login</th>
            <%--<th>Password</th>--%>
            <th>email</th>
            <th>Phone</th>
            <th>Money amount</th>
            <th>Access Level</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="users" items="${user}">
        <form action="controller">
            <tr>
                <td>${users.userId}//
                    <input type="hidden" name="user_id" value=${users.userId}></td>
                <td>${users.userName}
                    <input type="hidden" name="user_name" value=${users.userName}></td>
                <td>${users.login}
                    <input type="hidden" name="login" value=${users.login}></td>
                <%--<td>${users.password}
                    <input type="hidden" name="password" value=${users.password}></td>--%>
                <td>${users.email}
                    <input type="hidden" name="email" value=${users.email}></td>
                <td>${users.phone}
                    <input type="hidden" name="phone" value=${users.phone}></td>
                <td>${users.moneyAmount}
                    <input type="hidden" name="money_amount" value=${users.moneyAmount}></td>
                <td>${users.accessLevel}
                    <input type="hidden" name="access_level" value=${users.accessLevel}></td>
                <td><input type="hidden" name="command" value="delete_user">
                    <input type="submit" value="Delete user"></td>
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
    </table>
    <br>
<form action="controller">
    <input type="hidden" name="command" value="default">
    <input type="submit" value="to main page">
</form>
</body>
</html>
