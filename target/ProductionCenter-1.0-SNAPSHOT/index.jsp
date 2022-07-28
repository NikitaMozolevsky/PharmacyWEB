<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="pages/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link rel = "shortcut icon" href="https://atom.io/favicon.ico"/>
    <title>JSP - Hello World</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col">1</div>
        <div class="col">2</div>
        <div class="col">3</div>
    </div>
</div>
<br/>
<form action="controller">
    <input type="hidden" name="command" value="login"/>
    Login: <input type="text" name="login" value="1"/>
    <br/>
    Password: <input type="number" name="password" min="1" value="1" placeholder="password" maxlength="10" pattern="[0-9]+">
    <br/>
    <input type="submit" name="sub" value="LogIn"/>
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
        <script src="pages/bootstrap/js/bootstrap.js"></script>
</body>
</html>