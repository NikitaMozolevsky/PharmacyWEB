<%--
  Created by IntelliJ IDEA.
  User: Nikita
  Date: 5/11/2022
  Time: 2:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Show products</title>s
</head>
<body>

<%--<c:forEach var="elem" items="${myList}" varStatus="status">
    <p>
            ${elem}
    </p>
</c:forEach>--%>

    <table border="1">
        <thead>
        <tr>
            <th>Product ID</th>
            <th>Product Name</th>
            <th>Details</th>
            <th>Price(10ml)</th>
            <th>Type</th>
            <th>Photo</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="products" items="${product}">
        <form action="controller">
            <tr>
                <td>${products.productId}
                    <input type="hidden" name="product_id" value=${products.productId}></td>
                <td>${products.productName}
                    <input type="hidden" name="product_name" value=${products.productName}></td>
                <td>${products.details}
                    <input type="hidden" name="details" value=${products.details}></td>
                <td>${products.price}
                    <input type="hidden" name="price" value=${products.price}></td>
                <td>${products.type}
                    <input type="hidden" name="type" value=${products.type}></td>
                <td>${products.photo}
                    <input type="hidden" name="photo" value=${products.photo}></td>
                <td>
                    <input type="hidden" name="command" value="choose_product">
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
    <input type="hidden" name="command" value="pay_for_order">
    <input type="submit" value="Pay for order">
</form>
</body>
</html>