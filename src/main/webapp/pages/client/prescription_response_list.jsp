<%@ page import="static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.PRESCRIPTION_REQUEST_LIST" %>
<%@ page import="static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.PRODUCT_WAS_ADDED" %><%--
  Created by IntelliJ IDEA.
  User: Nikita
  Date: 8/10/2022
  Time: 3:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%
  pageContext.setAttribute("product_was_added", request.getAttribute(PRODUCT_WAS_ADDED));
  pageContext.setAttribute("prescription_request_list", request.getAttribute(PRESCRIPTION_REQUEST_LIST));
  pageContext.setAttribute("condition_is_true", "TRUE");
  pageContext.setAttribute("condition_is_false", "FALSE");
  pageContext.setAttribute("approved", "APPROVED");
%>
<html>
<head>
  <title>Show responses</title>
</head>
<body>
<table border="1">
  <thead>
  <tr>
    <th>Prescription request ID</th>
    <th>Doctor ID</th>
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
        <td>${prescriptionRequest.doctorId}
          <input type="hidden" name="doctor_id" value=${prescriptionRequest.doctorId}></td>
        <td>${prescriptionRequest.productId}
          <input type="hidden" name="product_id" value=${prescriptionRequest.productId}></td>
        <td>${prescriptionRequest.productName}
          <input type="hidden" name="product_name" value=${prescriptionRequest.productName}></td>
        <td>${prescriptionRequest.prescriptionRequestStatus}
          <input type="hidden" name="response" value=${prescriptionRequest.prescriptionRequestStatus}></td>
          <c:if test="${prescriptionRequest.prescriptionRequestStatus==approved}">
              <td><input type="hidden" name="command" value="choose_product">
                  <input type="submit" value="Buy product"></td>
          </c:if>

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
  <tfoot>
  <tr>
    <td colspan="2">All users
    <td/>
  </tr>
  </tfoot>
</table>
</body>
</html>
