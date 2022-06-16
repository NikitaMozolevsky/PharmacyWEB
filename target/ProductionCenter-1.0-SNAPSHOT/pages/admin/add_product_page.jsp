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
    <title>Add product page</title>
</head>
<body>
<form action="controller">
    Product name: <label>
    <input type="text" name="product_name" value="">
</label>
    <hr/>
    Details: <label>
    <input type="text" name="details" value="">
</label>
    <hr/>
    Price: <label>
    <input type="text" name="price" value="">
</label>
    <hr/>
    Type:
    <br>
    <label>
    <input type="radio" name="type" value="ENTERAL" checked="on">
</label>ENTERAL
    <br>
    <label>
    <input type="radio" name="type" value="EXTERNAL">
    </label>EXTERNAL
    <br>
    <label>
    <input type="radio" name="type" value="INHALATION">
    </label>INHALATION
    <br>
    <hr/>
    Photo: <label>
    <input type="text" name="photo" value="">
</label>
    <hr/>
    <input type="hidden" name="command" value="add_product">
    <input type="submit" value="Add product">
</form>
</body>
</html>
