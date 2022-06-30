<%--
  Created by IntelliJ IDEA.
  User: Nikita
  Date: 6/28/2022
  Time: 4:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
</head>
<body>
<form action="controller">
    <table border="1">
        <thead>
        <tr>
            <th>Product ID</th>
            <th>Product Name</th>
            <th>Quantity</th>
            <th>Volume</th>
            <th>Price</th>
            <th>Photo</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="order_product" items="${order_products}">
            <tr>
                <td>${order_product.orderProductId}
                    <input type="hidden" name="product_id" value=${products.productId}></td>
                <td>${order_product.productName}
                    <input type="hidden" name="product_name" value=${products.productName}></td>
                <td>${order_product.details}
                    <input type="hidden" name="details" value=${products.details}></td>
                <td>${order_product.price}
                    <input type="hidden" name="price" value=${products.price}></td>
                <td>${order_product.type}
                    <input type="hidden" name="type" value=${products.type}></td>
                <td>${order_product.photo}
                    <input type="hidden" name="photo" value=${products.photo}></td>
                <td><input type="hidden" name="command" value="choose_product">
                    <input type="submit" value="Choose"></td>
            </tr>
        </c:forEach>
        </tbody>
        <tfoot>
        <tr>
            <td colspan="2">All users
            <td/>
        </tr>
        </tfoot>
    </table>
</form>
<form action="controller">
    <input type="hidden" name="command" value="default">
    <input type="submit" value="to main page">
</form>
</body>
</html>