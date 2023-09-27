<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add a customer</title>
</head>
<body>
<form:form method="post" modelAttribute="customers">
    First name: <form:input path="firstName"/><br/>
    Last name: <form:input path="lastName"/><br/>
    Mobile number: <form:input path="mobileNumber"/><br/>
    Email: <form:input path="email"/><br/>
    <form:button>Add</form:button>
</form:form><br>
<a href="/customers">Back to list</a>
</body>
</html>
