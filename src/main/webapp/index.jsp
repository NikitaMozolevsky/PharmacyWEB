<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <style type="text/css">
        <%@include file="pages/bootstrap/css/bootstrap.css"%>
    </style>
    <%--<script src="pages/bootstrap/js/bootstrap.js"></script>--%>
    <script>
        <%@include file="pages/bootstrap/js/bootstrap.js"%>
    </script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="pages/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link rel = "shortcut icon" href="https://atom.io/favicon.ico"/>
    <title>JSP - Hello World</title>
</head>

    <body>
    <div class="form_auth_block">
        <div class="form_auth_block_content">
            <p class="form_auth_block_head_text">AUTHORIZATION:</p>
            <form class="form_auth_style" action="controller">
                <input type="hidden" name="command" value="login"/>
                <label>Set your login:</label>
                <input type="text" name="login" maxlength="25"/>
                <br/>
                <label>Set your password:</label>
                <input type="password" id="password-input" name="password" maxlength="25">
                <br/>
                <input class="form_auth_button" type="submit" name="sub" value="LogIn"/>
                <p align="center"><span class="constructor"><big>${login_msg.toUpperCase()}</big></span></p>
            </form>
            <form action="controller">
                <input type="hidden" name="command" value="register_page"/>
                <input type="submit" name="sub" value = "Register">
            </form>
        </div>
    </div>
    </body>

</html>