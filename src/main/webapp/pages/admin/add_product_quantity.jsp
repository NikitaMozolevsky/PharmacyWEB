<%--
  Created by IntelliJ IDEA.
  User: Nikita
  Date: 7/18/2022
  Time: 2:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add product quantity</title>
</head>
<body>
Add product quantity:
<br>
<form action="controller">
    Product ID: <label>
    <input type="text" name="product_id" value="">
</label>
    <hr/>
    Quantity: <label>
    <input type="number" name="goods_quantity" value="">
</label>
    <hr/>
    <input type="hidden" name="command" value="add_product_quantity">
    <input type="submit" value="Add product quantity">
</form>
<br/>
<form action="controller">
    <input type="hidden" name="command" value="default">
    <input type="submit" value="to main page">
</form>
</body>
</html>
