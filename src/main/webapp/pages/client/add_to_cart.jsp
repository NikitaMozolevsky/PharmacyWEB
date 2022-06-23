<%@ page import="static com.example.demo.command.constant.OrderAttribute.ORDER_ID" %>
<%@ page import="java.util.Optional" %>
<%@ page import="java.util.*" %>
<%@ page import="com.example.demo.dao.impl.UserDaoImpl" %>
<%@ page import="static com.example.demo.command.constant.UserAttribute.USER_ID" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.example.demo.dao.impl.OrderDaoImpl" %>
<%@ page import="com.example.demo.entity.order.Order" %>
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
   /* OrderDaoImpl orderDao = new OrderDaoImpl();
    Order orderIsExistBoolean;
    String userId = String.valueOf(session.getAttribute(USER_ID));
    try {
        orderIsExistBoolean = orderDao.isOrderForUserAlreadyExist(userId);
    } catch (SQLException e) {
        throw new ServletException();
    }
    pageContext.setAttribute("order_exist", "order_exist");*/
%>
<html>
<head>
    <title>Add product to cart</title>
</head>
<body>
<form action="controller">
    Quantity: <input type="text" name="quantity" value="1">
    <br>
    Dosage:
    <br>
    <label>
        <input type="radio" name="volume" value="20ML" checked="on">
    </label>20ml<br/>
    <label>
        <input type="radio" name="volume" value="50ML">
    </label>50ml<br/>
    <label>
        <input type="radio" name="volume" value="100ML">
    </label>100ml<br/>
    <c:if test="${order_exist.isOrderExist==false&&order_exist}">
        <input type="hidden" name="command" value="create_order">
    </c:if>
    <c:if test="${orderIsExist==true}">
        <input type="hidden" name="command" value="add_order_product_to_cart">
    </c:if>
    <input type="submit" value="Add to cart">

</form>
</body>
</html>
