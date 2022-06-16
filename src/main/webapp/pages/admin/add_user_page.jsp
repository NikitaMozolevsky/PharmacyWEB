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
    Access level:
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
</form>
</body>
</html>
