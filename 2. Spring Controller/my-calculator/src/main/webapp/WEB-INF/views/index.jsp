<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 10/27/2022
  Time: 8:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Calculator</title>
</head>
<body>
<h1>Calculator</h1>
<form action="calculate" method="post">
    <input type="text" name="number1" value="${number1}">
    <input type="text" name="number2" value="${number2}">
    <p>
        <input type="submit" name="calculation" value="(+)">
        <input type="submit" name="calculation" value="(-)">
        <input type="submit" name="calculation" value="(X)">
        <input type="submit" name="calculation" value="(/)">
    </p>
</form>
<h2>Result ${calcul}: ${result}</h2>
</body>
</html>