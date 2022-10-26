<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<fieldset>
    <legend>Danh sách gia vị được chọn:</legend>
    <c:forEach items="${condiment}" var="eat">
        <h1><c:if test="${eat == 'Muoi'}">Muối</c:if></h1>
        <h1><c:if test="${eat == 'Ot'}">Ớt</c:if></h1>
        <h1><c:if test="${eat == 'Tieu'}">Tiêu</c:if></h1>
        <h1><c:if test="${eat == 'Hanh'}">Hành</c:if></h1>
    </c:forEach>
</fieldset>
</body>
</html>