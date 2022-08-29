<%--
  Created by IntelliJ IDEA.
  User: Nikita
  Date: 5/11/2022
  Time: 2:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add product page</title>
</head>
<body>
<form style="margin-top: 40px" action="controller" enctype="multipart/form-data" method="post">

    Product name: <label>
    <input type="text" name="product_name" value="" required>
</label>
    <hr/>

    Details: <label>
    <input type="text" name="details" value="">
</label>
    <hr/>

    Price per 10ml: <label>
    <input type="number" name="price" value="" required>
    </label>
    <hr/>

    Type:
    <br>
    <label>
    <input type="radio" name="type" value="ENTERAL" checked="on">
    </label>ENTERAL
    <br>

    <label>
    <input type="radio" name="type" value="EXTERNAL">
    </label>EXTERNAL
    <br>

    <label>
    <input type="radio" name="type" value="INHALATION">
    </label>INHALATION
    <br>
    <hr/>

    Photo: <label>
    <input type="file" class="visually-hidden" name="photo"
           id="photo"
           size="4194304"
           accept=".jpg, .jpeg"/>
</label>
    <hr/>

    Goods quantity: <label>
    <input type="number" name="goods_quantity" value="" required>
</label>
    <hr/>

    Need recipe:
    <br>
    <label>
        <input type="radio" name="need_prescription" value="FALSE" checked="on">
    </label>No
    <br>
    <label>
        <input type="radio" name="need_prescription" value="TRUE">
    </label>Prescription is required
    <hr/>

    <input type="hidden" name="command" value="add_product">
    <input type="submit" value="Add product">
</form>
</body>
</html>
