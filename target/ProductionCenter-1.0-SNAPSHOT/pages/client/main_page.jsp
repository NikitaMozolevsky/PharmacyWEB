<%@ page import="java.util.Optional" %>
<%@ page import="static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.FULL_COST" %>
<%@ page import="static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.PURCHASE_COMPLETED" %>
<%@ page import="static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.*" %>
<%@ page import="static by.mozolevskij.pharmacy.command.attribute.UserAttribute.INITIAL_MONEY_AMOUNT" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Nikita
  Date: 31.03.2022
  Time: 18:35
  To change this template use File | Settings | File Templates.
--%>
<%
    Double fullCost = (Double) session.getAttribute(FULL_COST);
    boolean fullCostExist = fullCost>INITIAL_MONEY_AMOUNT;
    pageContext.setAttribute("full_cost", String.valueOf(fullCost));
    pageContext.setAttribute("full_cost_exist", fullCostExist);
    pageContext.setAttribute("purchase_completed", request.getAttribute(PURCHASE_COMPLETED_MSG));
    pageContext.setAttribute("cart_is_empty", request.getAttribute(CART_IS_EMPTY));
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
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
<%--<form action="controller">
    <input type="hidden" name="command" value="show_all_products">
    <input type="submit" value="to main page">
</form>--%>
<form action="controller">
    <input type="hidden" name="command" value="show_cart">
    <input type="submit" value="Show cart">
</form>
<form action="controller">
    <input type="hidden" name="command" value="add_product_quantity_page">
    <input type="submit" value="Add product quantity">
</form>
<form action="controller">
    <input type="hidden" name="command" value="go_to_top_up_account">
    <input type="submit" value="Go to top up account">
    <br/>
    Money amount: ${money_amount}
    <br/>
    <br/>
    <c:if test="${full_cost_exist}">
    Full cost: ${full_cost}
    </c:if>
    <br/>
    ${purchase_completed}
    ${cart_is_empty}
    <%--<script>window.alert('hello')</script>--%>
</form>
</body>
</html>
