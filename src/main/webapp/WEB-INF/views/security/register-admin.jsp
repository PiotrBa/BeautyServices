<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registration Admin</title>
    <link rel="stylesheet" type="text/css" href="/css/style-edit.css" />

</head>
<body>
<h1>Admin registration</h1>
<form:form method="post" modelAttribute="admin">
    <div class="input-group">
        First/Last name: <form:input path="name"/><form:errors path="name"/>
    </div>
    <div class="input-group">
        Mobile number: <form:input path="mobileNumber"/><form:errors path="mobileNumber"/>
    </div>
    <div class="input-group">
        Email: <form:input path="email"/><form:errors path="email"/>
    </div>
    <div class="input-group">
        User name: <form:input path="username"/><form:errors path="username"/>
    </div>
    <div class="input-group">
        Password: <form:password path="password"/><form:errors path="password"/>
    </div>
    <form:button class="btn">Register</form:button>
</form:form>
<div>
    <a href="/customers">Back</a>
</div>
</body>
</html>
