<%@ page import="static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.ORDER_ID" %>
<%@ page import="java.util.Optional" %>
<%@ page import="java.util.*" %>
<%@ page import="by.mozolevskij.pharmacy.dao.impl.UserDaoImpl" %>
<%@ page import="static by.mozolevskij.pharmacy.command.attribute.UserAttribute.USER_ID" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="by.mozolevskij.pharmacy.dao.impl.OrderDaoImpl" %>
<%@ page import="by.mozolevskij.pharmacy.entity.order.Order" %>
<%@ page import="static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.ORDER_EXIST" %>
<%@ page import="static by.mozolevskij.pharmacy.command.attribute.ProductAttribute.PRICE" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %><%--
  Created by IntelliJ IDEA.
  User: Nikita
  Date: 6/7/2022
  Time: 6:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%
    /*OrderDaoImpl orderDao = new OrderDaoImpl();
    Order orderIsExistBoolean;
    String userId = String.valueOf(session.getAttribute(USER_ID));
    try {
        orderIsExistBoolean = orderDao.isOrderForUserIsNotExist(userId);
    } catch (SQLException e) {
        throw new ServletException();
    }*/
    pageContext.setAttribute("price", request.getParameter(PRICE));
    pageContext.setAttribute("order_exist", session.getAttribute(ORDER_EXIST));
%>
<html>
<head>
    <title>Add product to cart</title>
</head>
<body>
<form action="controller" method="post">
    Quantity: <input type="text" name="goods_quantity" value="1">
    <br>
    Dosage:
    <br>
    <label>
        <input type="radio" name="volume" value="20ML" checked="on">
    </label>20ml - Price per one:${price*2}<br/>
    <label>
        <input type="radio" name="volume" value="50ML">
    </label>50ml - Price per one:${price*5}<br/>
    <label>
        <input type="radio" name="volume" value="100ML">
    </label>100ml - Price per one:${price*10}<br/>
    <%--<c:if test="${order_exist==false}">
        <input type="hidden" name="command" value="create_order">
    </c:if>
    <c:if test="${order_exist==true}">
        <input type="hidden" name="command" value="add_order_product_to_cart">
    </c:if>--%>
    <input type="hidden" name="command" value="add_product_to_cart">
    <input type="submit" value="Add to cart">
    <br>
</form>
<form action="controller">
    <input type="hidden" name="command" value="show_all_products">
    <input type="submit" value="All products">
</form>
</body>
</html>
