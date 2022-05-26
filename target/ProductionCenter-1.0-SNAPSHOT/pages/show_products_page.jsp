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
    <title>Show products page</title>
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
    <input type="hidden" name="command" value="register">
    <input type="submit" value="Register user">

</form>
</body>
</html>
