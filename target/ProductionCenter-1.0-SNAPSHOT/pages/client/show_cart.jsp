<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.FULL_COST" %>
<%@ page import="java.util.Optional" %>
<%@ page import="static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.ORDER_PRODUCTS" %><%--
  Created by IntelliJ IDEA.
  User: Nikita
  Date: 6/28/2022
  Time: 4:43 PM
  To change this template use File | Settings | File Templates.
--%>

<%
    Object fullCost = session.getAttribute(FULL_COST);
    boolean fullCostExist = fullCost!=null;
    pageContext.setAttribute("full_cost", String.valueOf(fullCost));
    pageContext.setAttribute("full_cost_exist", fullCostExist);
    pageContext.setAttribute("order_products", request.getAttribute(ORDER_PRODUCTS));
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show cart</title>
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
        <c:forEach var="orderProduct" items="${order_products}">
            <form action="controller">
                <tr>
                        <input type="hidden" name="order_product_id" value=${orderProduct.orderProductId}>
                        <input type="hidden" name="order_id" value=${orderProduct.orderId}>

                    <td>${orderProduct.productId}
                        <input type="hidden" name="product_id" value=${orderProduct.productId}></td>
                    <td>${orderProduct.productName}
                        <input type="hidden" name="product_name" value=${orderProduct.productName}></td>
                    <td>${orderProduct.quantity}
                        <input type="hidden" name="quantity" value=${orderProduct.quantity}></td>
                    <td>${orderProduct.volume}
                        <input type="hidden" name="volume" value=${orderProduct.volume}></td>
                    <td>${orderProduct.orderProductPrice}
                        <input type="hidden" name="order_product_price" value=${orderProduct.orderProductPrice}></td>
                    <td>
                        <c:if test="${empty product.photo}">
                        <img src="${pageContext.request.contextPath}/images/no_photo.jpg" alt="img">
                    </c:if>
                        <c:if test="${!empty product.photo}">
                            <img src="data:image/jpeg;base64,${product.photo}" width="150" height="150" alt="img">
                        </c:if>
                        <input type="hidden" name="photo" value=${orderProduct.photo}></td>
                    <td>
                        <input type="hidden" name="command" value="remove_from_cart">
                        <input type="submit" value="Remove from cart"></td>
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
    <br/>
    <c:if test="${full_cost_exist}">
    Full cost: ${full_cost}
    </c:if>
    <br/>
</form>
<br>
<form action="controller">
    <input type="hidden" name="command" value="set_address">
    <input type="submit" value="Specify the delivery address">
</form>
<br>
<form action="controller">
    <input type="hidden" name="command" value="default">
    <input type="submit" value="to main page">
</form>
</body>
</html>
