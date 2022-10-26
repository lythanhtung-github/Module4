<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Sandwich</title>
</head>
<body>
<form method="get" action="save">
    <fieldset>
        <legend>Danh sách gia vị:</legend>
        <td><input type="checkbox" name="condiment" value="Muoi">Muối</td>
        <td><input type="checkbox" name="condiment" value="Ot">Ớt</td>
        <td><input type="checkbox" name="condiment" value="Tieu">Tiêu</td>
        <td><input type="checkbox" name="condiment" value="Hanh">Hành</td>
        <td><input type="submit" value="save"></td>
    </fieldset>
</form>
<c:if test="${message!= null}">
    <span style="color: red">${message}</span>
</c:if>
</body>
</html>
