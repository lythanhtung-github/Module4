<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Result</title>
</head>
<body>
<span>${usd} USD = </span>
<span>
    <fmt:formatNumber type = "number"
                      maxFractionDigits = "3" value = "${result}" /> VNĐ
</span>
</body>
</html>