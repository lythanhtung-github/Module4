<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h3>Home</h3>
<span style ="color:red">${error}</span>
<form:form action="login" method="post" modelAttribute="login">
    <fieldset>
        <legend>Login</legend>
        <table>
            <tr>
                <td>
                    <form:label path="account">Account: </form:label>
                </td>
                <td>
                    <form:input path="account"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="password">Password: </form:label>
                </td>
                <td>
                    <form:input path="password" type="password"/>
                </td>
            </tr>
            <tr>
               <td colspan="2">
                   <form:button type="submid">Login</form:button>
                   <form:button type="reset">Reset</form:button>
               </td>
            </tr>
        </table>
    </fieldset>
</form:form>
</body>
</html>
