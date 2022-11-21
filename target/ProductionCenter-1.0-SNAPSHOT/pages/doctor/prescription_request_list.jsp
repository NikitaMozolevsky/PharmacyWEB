<%@ page import="static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.PRESCRIPTION_REQUEST_LIST" %>
<%@ page import="static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.PRODUCT_WAS_ADDED" %>
<%@ page import="static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.*" %><%--
  Created by IntelliJ IDEA.
  User: Nikita
  Date: 8/9/2022
  Time: 1:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%
    pageContext.setAttribute("product_was_added", request.getAttribute(PRODUCT_WAS_ADDED));
    pageContext.setAttribute("prescription_request_list", request.getAttribute(PRESCRIPTION_REQUEST_LIST));
    pageContext.setAttribute("response", request.getAttribute(RESPONSE));
    pageContext.setAttribute("condition_is_true", "TRUE");
    pageContext.setAttribute("condition_is_false", "FALSE");
%>
<html>
<head>
    <title>Prescription request list</title>
</head>
<body>
${response}
<table border="1">
    <thead>
    <tr>
        <th>Prescription request ID</th>
        <th>User ID</th>
        <th>Product ID</th>
        <th>Product name</th>
        <th>Response</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="prescriptionRequest" items="${prescription_request_list}">
        <form action="controller">
            <tr>
                <td>${prescriptionRequest.prescriptionRequestId}
                    <input type="hidden" name="prescription_request_id" value=${prescriptionRequest.prescriptionRequestId}></td>
                <td>${prescriptionRequest.clientId}
                    <input type="hidden" name="client_id" value=${prescriptionRequest.clientId}></td>
                <td>${prescriptionRequest.productId}
                    <input type="hidden" name="product_id" value=${prescriptionRequest.productId}></td>
                <td>${prescriptionRequest.productName}
                    <input type="hidden" name="product_name" value=${prescriptionRequest.productName}></td>
                <td><label>
                    <input type="radio" name="response" value="REJECTED" checked="on">
                </label>Reject<br/>
                <label>
                    <input type="radio" name="response" value="APPROVED">
                </label>Approve</td>
                <td>
                <input type="hidden" name="command" value="send_response">
                <input type="submit" value="Send response"></td>
                <%--<c:if test="${product.needPrescription==condition_is_true}">
                    <td><input type="hidden" name="role" value=${doctor}>
                        <input type="hidden" name="command" value="choose_product">
                        <input type="submit" value="Get prescription"></td>
                </c:if>

                <c:if test="${product.needPrescription==condition_is_false}">
                    <td><input type="hidden" name="command" value="choose_product">
                        <input type="submit" value="Choose"></td>
                </c:if>--%>

            </tr>
        </form>
    </c:forEach>
    </tbody>
</table>
<br/>
<form action="controller">
    <input type="hidden" name="command" value="default">
    <input type="submit" value="to main page">
</form>
</body>
</html>
