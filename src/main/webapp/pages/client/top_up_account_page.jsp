<%--
  Created by IntelliJ IDEA.
  User: Nikita
  Date: 7/5/2022
  Time: 10:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Top up account</title>
</head>
<body>
<form action="controller" method="post">
    Deposit the amount: <input type="text" name="set_money_amount" value="" required="required" placeholder="BYN">
    <input type="hidden" name="command" value="top_up_account">
    <input type="submit" value="Make a payment">
    <br>
</form>
<br/>
<form action="controller">
    <input type="hidden" name="command" value="default">
    <input type="submit" value="to main page">
</form>
</body>
</html>
