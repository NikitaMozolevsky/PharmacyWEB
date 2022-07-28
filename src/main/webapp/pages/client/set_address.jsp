<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.NOT_ENOUGH_MONEY" %>
<%@ page import="static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.NOT_ENOUGH_GOODS" %>
<%@ page import="static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.*" %>
<%@ page import="java.util.Optional" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%

    Object notEnoughGoodsMsg = request.getAttribute(NOT_ENOUGH_GOODS);
    Object notEnoughGoodsList = request.getAttribute(NOT_ENOUGH_GOODS_NAMES);
    Object notEnoughMoneyMsg = request.getAttribute(NOT_ENOUGH_MONEY);
    boolean notEnoughMoneyBoolean = notEnoughMoneyMsg!=null;
    boolean notEnoughGoodsBoolean = notEnoughGoodsMsg!=null;
    if(notEnoughGoodsBoolean) {
        /*String[] notEnoughGoodsStrings = notEnoughGoodsList.*/
    }
    pageContext.setAttribute("not_enough_goods", notEnoughGoodsMsg);
    pageContext.setAttribute("not_enough_goods_list", notEnoughGoodsList);
    pageContext.setAttribute("not_enough_money", notEnoughMoneyMsg);
    pageContext.setAttribute("not_enough_goods_condition", notEnoughGoodsBoolean);
    pageContext.setAttribute("not_enough_money_condition", notEnoughMoneyBoolean);
%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="pages/bootstrap/css/bootstrap.css" rel="stylesheet">
    <title>Set address</title>
</head>
<body>
<form action="controller">
    Country: <label>
    <input type="text" name="country" value="">
</label>
    <hr/>
    City: <label>
    <input type="text" name="city" value="">
</label>
    <hr/>
    Street: <label>
    <input type="text" name="street" value="">
</label>
    <hr/>
    Home: <label>
    <input type="text" name="home" value="">
</label>
    <hr/>
    <input type="hidden" name="command" value="pay_for_order">
    <input type="submit" value="Pay for order">
</form>
<form action="controller">
    <input type="hidden" name="command" value="default">
    <input type="submit" value="to main page">
</form>
<c:if test="${not_enough_goods_condition}">
    ${not_enough_goods}
    The following products are missing:
    <hr>
    <c:forEach var="product" items="${not_enough_goods_list}">
        ${product.toUpperCase()}
        <hr>
    </c:forEach>
</c:if>
<%--<c:if test="${not_enough_money_condition}">--%>
    ${not_enough_money}
<%--</c:if>--%>
</body>
</html>
