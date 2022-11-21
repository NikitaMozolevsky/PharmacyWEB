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
<%@ page import="static by.mozolevskij.pharmacy.command.attribute.UserAttribute.DOCTOR" %>
<%@ page import="static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.APPROVED" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<%
    pageContext.setAttribute("product_was_added", request.getAttribute(PRODUCT_WAS_ADDED));
    pageContext.setAttribute("products", request.getAttribute(PRODUCTS));
    pageContext.setAttribute("request_status_approved", request.getAttribute(APPROVED));
    pageContext.setAttribute("condition_is_true", "TRUE");
    pageContext.setAttribute("condition_is_false", "FALSE");
    pageContext.setAttribute("doctor", DOCTOR);
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
            <tr>
            <form action="controller">
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
                <td>
                    <c:if test="${empty product.photo}">
                        <img src="${pageContext.request.contextPath}/images/no_photo.jpg" alt="img">
                    </c:if>
                    <c:if test="${!empty product.photo}">
                        <img src="data:image/jpeg;base64,${product.photo}" width="200" height="200" alt="img">
                    </c:if>

                <td>${product.needPrescription}
                    <input type="hidden" name="need_prescription" value=${product.needPrescription}></td>

                <c:if test="${product.needPrescription==condition_is_true}">
                    <td><input type="hidden" name="role" value=${doctor}>
                        <input type="hidden" name="command" value="choose_product">
                        <input type="submit" value="Get prescription"></td>
                </c:if>

                <c:if test="${product.needPrescription==condition_is_false||request_status_approved}">
                    <td><input type="hidden" name="command" value="choose_product">
                        <input type="submit" value="Choose"></td>
                </c:if>
                </form>
                <form action="<c:url value="/controller"/>">
                    <input type="hidden" name="product_id" value=${product.productId}>
                    <td><input type="hidden" name="command" value="delete_product">
                        <input type="submit" value="Delete product"></td>
                </form>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>

<form action="controller">
    <input type="hidden" name="command" value="show_cart">
    <input type="submit" value="Show cart">
</form>
    <br/>
    <form action="controller">
        <input type="hidden" name="command" value="default">
        <input type="submit" value="to main page">
    </form>
</body>
</html>