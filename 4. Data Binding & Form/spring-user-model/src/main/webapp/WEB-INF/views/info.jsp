<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Info</title>
</head>
<body>

<fieldset>
    <legend>User information</legend>
    <table>
        <tr>
            <td>Account:</td>
            <td>${user.account}</td>
        </tr>
        <tr>
            <td>Name:</td>
            <td>${user.name}</td>
        </tr>
        <tr>
            <td>Email:</td>
            <td>${user.email}</td>
        </tr>
        <tr>
            <td>Age:</td>
            <td>${user.age}</td>
        </tr>
    </table>
</fieldset>
</body>
</html>
