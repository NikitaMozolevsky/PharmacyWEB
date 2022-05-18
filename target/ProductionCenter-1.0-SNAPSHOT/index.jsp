<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<br/>
<form action="controller">
    <input type="hidden" name="command" value="login"/>
    Login: <input type="text" name="login" value=""/>
    <br/>
    Password: <input type="password" name="password" value="">
    <br/>
    <input type="submit" name="sub" value="Push"/>
    <br/>
    ${login_msg.toUpperCase()}
    <br/>
    ${pageContext.session.id}
    <br/>
    ${filter_attr}
</form>
<form action="controller">
    <input type="hidden" name="command" value="register_page"/>
    <input type="submit" name="sub" value = "Register">
</form>
</body>
</html>