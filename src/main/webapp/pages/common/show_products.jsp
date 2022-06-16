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

<% List<String> list = Arrays.asList("foo", "bar", "waa");
    pageContext.setAttribute("myList", list); %>


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
<form action="controller">
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
                <td><input type="hidden" name="command" value="choose_product">
                    <input type="submit" value="Choose"></td>
            </tr>
        </c:forEach>
        <%--<tr>
            <td>123</td>
            <td>Rname</td>
        </tr>
        <tr>
            <td>234</td>
            <td>R2name</td>
        </tr>--%>
    </tbody>
    <tfoot>
        <tr>
            <td colspan="2">All users<td/>
        </tr>
    </tfoot>
</table>
<form/>
</body>
</html>
