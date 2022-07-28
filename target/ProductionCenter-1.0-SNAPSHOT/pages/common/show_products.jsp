<%--
  Created by IntelliJ IDEA.
  User: Nikita
  Date: 5/11/2022
  Time: 2:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.*" %>
<%@ page import="static by.mozolevskij.pharmacy.command.attribute.ProductAttribute.PRODUCTS" %>
<%@ page import="by.mozolevskij.pharmacy.entity.order_product.OrderProduct" %>
<%@ page import="static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.PRODUCT_WAS_ADDED" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%
    pageContext.setAttribute("product_was_added", request.getAttribute(PRODUCT_WAS_ADDED));
    pageContext.setAttribute("products", request.getAttribute(PRODUCTS));
%>

<html>
<head>
    <title>Show products</title>
</head>
<body>
    ${product_was_added}
    <table border="1">
        <thead>
        <tr>
            <th>Product ID</th>
            <th>Product Name</th>
            <th>Details</th>
            <th>Price(10ml)</th>
            <th>Type</th>
            <th>Quantity</th>
            <th>Photo</th>
            <th>Need prescription</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${products}">
        <form action="controller">
            <tr>
                <td>${product.productId}
                    <input type="hidden" name="product_id" value=${product.productId}></td>
                <td>${product.productName}
                    <input type="hidden" name="product_name" value=${product.productName}></td>
                <td>${product.details}
                    <input type="hidden" name="details" value=${product.details}></td>
                <td>${product.price}
                    <input type="hidden" name="price" value=${product.price}></td>
                <td>${product.type}
                    <input type="hidden" name="type" value=${product.type}></td>
                <td>${product.quantity}
                    <input type="hidden" name="goods_quantity" value=${product.quantity}></td>
                <td>${product.photo}
                    <input type="hidden" name="photo" value=${product.photo}></td>
                <td>${product.needPrescription}
                    <input type="hidden" name="need_prescription" value=${product.needPrescription}></td>

                <td><input type="hidden" name="command" value="choose_product">
                    <input type="submit" value="Choose"></td>
            </tr>
        </form>
        </c:forEach>
        </tbody>
        <tfoot>
        <tr>
            <td colspan="2">All users
            <td/>
        </tr>
        </tfoot>
    </table>

<form action="controller">
    <input type="hidden" name="command" value="default">
    <input type="submit" value="to main page">
</form>
<form action="controller">
    <input type="hidden" name="command" value="show_cart">
    <input type="submit" value="Show cart">
</form>
</body>
</html>