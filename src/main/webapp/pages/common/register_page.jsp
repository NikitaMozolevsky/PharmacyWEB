<%--
  Created by IntelliJ IDEA.
  User: Nikita
  Date: 5/11/2022
  Time: 2:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register page</title>
</head>
<body>
<form action="controller">
    Name: <input type="text" name="user_name" value="">
    <hr/>
    Login: <input type="text" name="login" value="">
    <hr/>
    Password: <input type="text" name="password" value="">
    <hr/>
    E-mail: <input type="text" name="email" value="">
    <hr/>
    Phone: <input type="text" name="phone" value="">
    <hr/>
    <input type="hidden" name="command" value="register">
    <input type="submit" value="Register user">
    <br>
    ${register_msg.toUpperCase()}

</form>
</body>
</html>
