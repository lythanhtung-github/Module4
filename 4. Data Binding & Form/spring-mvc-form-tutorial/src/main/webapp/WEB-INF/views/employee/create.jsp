<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Create</title>
</head>
<body>
<form:form method="POST" action="employee/add" modelAttribute="employee">
    <fieldset>
        <legend>Employee Information</legend>
        <table>
            <tr>
                <td>
                    <form:label path="id">Employee ID: </form:label>
                </td>
                <td>
                    <form:input path="id"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="name">Employee Name: </form:label>
                </td>
                <td>
                    <form:input path="name"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="contactNumber">Contact Number:</form:label>
                </td>
                <td>
                    <form:input path="contactNumber"/>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="Add"/>
                </td>
            </tr>
        </table>
    </fieldset>
</form:form>
</body>
</html>
