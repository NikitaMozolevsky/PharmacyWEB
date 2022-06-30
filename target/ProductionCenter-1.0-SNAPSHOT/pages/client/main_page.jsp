<%--
  Created by IntelliJ IDEA.
  User: Nikita
  Date: 31.03.2022
  Time: 18:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
<hr/>
e-mail ${mail}
<hr/>
Hello (forward) = ${user}
<hr/>
Hi (redirect/forward) = ${user_name}
<hr/>
${filter_attr}
<hr/>
<form action="controller">
    <input type="hidden" name="command" value="logout"/>
    <input type="submit" value="logout"/>
</form>
<form action="controller">
    <input type="hidden" name="command" value="add_user_page"/>
    <input type="submit" value="Add user"/>
</form>
<form action="controller">
    <input type="hidden" name="command" value="show_all_users"/>
    <input type="submit" value="Show all users"/>
</form>
<form action="controller">
    <input type="hidden" name="command" value="add_product_page"/>
    <input type="submit" value="Add new product"/>
</form>
<form action="controller">
    <input type="hidden" name="command" value="show_all_products"/>
    <input type="submit" value="Show all products"/>
</form>
<form action="controller">
    <input type="hidden" name="command" value="show_all_products">
    <input type="submit" value="to main page">
</form>
<form action="controller">
    <input type="hidden" name="command" value="pay_for_order">
    <input type="submit" value="Pay for order">
</form>
</body>
</html>
