<%@ page import="java.util.Optional" %>
<%@ page import="static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.FULL_COST" %>
<%@ page import="static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.PURCHASE_COMPLETED" %>
<%@ page import="static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.*" %>
<%@ page import="static by.mozolevskij.pharmacy.command.attribute.UserAttribute.INITIAL_MONEY_AMOUNT" %>
<%@ page import="static by.mozolevskij.pharmacy.command.attribute.UserAttribute.USER" %>
<%@ page import="static by.mozolevskij.pharmacy.command.attribute.Message.PURCHASE_COMPLETED_MSG" %>
<%@ page import="static by.mozolevskij.pharmacy.command.attribute.Message.PRODUCT_LIST_EMPTY_MSG" %>
<%@ page import="static by.mozolevskij.pharmacy.command.attribute.ProductAttribute.PRODUCT_LIST_EMPTY" %>
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
    pageContext.setAttribute("product_list_is_empty", request.getAttribute(PRODUCT_LIST_EMPTY));
    pageContext.setAttribute("all_users", USER);
    pageContext.setAttribute("prescription_request_list_empty", request.getAttribute(PRESCRIPTION_REQUEST_LIST_EMPTY));
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
${prescription_request_list_empty}
${purchase_completed}
${cart_is_empty}
${product_list_is_empty}
<form action="controller">
    <input type="hidden" name="command" value="logout"/>
    <input type="submit" value="logout"/>
</form>

<form action="controller">
    <input type="hidden" name="command" value="add_user_page"/>
    <input type="submit" value="Add user (DOCTOR, PHARMACIST)"/>
</form>

<form action="controller">
    <%--<input type="hidden" name="role" value=${all_users}>--%>
    <input type="hidden" name="command" value="show_all_users"/>
    <input type="submit" value="Show all users (DOCTOR, PHARMACIST)"/>
</form>

<form action="controller">
    <input type="hidden" name="command" value="add_product_page"/>
    <input type="submit" value="Add new product (PHARMACIST)"/>
</form>

<form action="controller">
    <input type="hidden" name="command" value="show_all_products"/>
    <input type="submit" value="Show all products"/>
</form>

<form action="controller">
    <input type="hidden" name="command" value="show_cart">
    <input type="submit" value="Show cart (CLIENT)">
</form>

<form action="controller">
    <input type="hidden" name="command" value="add_product_quantity_page">
    <input type="submit" value="Add product quantity (PHARMACIST)">
</form>

<form action="controller">
    <input type="hidden" name="command" value="prescription_request_list_page">
    <input type="submit" value="Get prescription requests (DOCTOR)">
</form>

<form action="controller">
    <input type="hidden" name="command" value="prescription_response_list_page">
    <input type="submit" value="Get prescription response (CLIENT)">
</form>

<form action="controller">
    <input type="hidden" name="command" value="go_to_top_up_account">
    <input type="submit" value="Go to top up account (CLIENT)">
    <br/>
    Money amount: ${money_amount}
    <br/>
    <br/>
    <c:if test="${full_cost_exist}">
    Full cost: ${full_cost}
    </c:if>
    <br/>
    <%--<script>window.alert('hello')</script>--%>
</form>
</body>
</html>
