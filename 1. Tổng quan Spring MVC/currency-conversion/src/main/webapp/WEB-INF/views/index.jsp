<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Currency Conversion</title>
</head>
<body>
<form action="/usd" method = "post">
    <label for="usd">USD: </label>
    <input type="number" id="usd" step="any" min = "1" name = "usd"/>
    <input type = "submit" value="Conversion"/>
</form>
</body>
</html>
