<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form:form method="post" modelAttribute="user">
    <div>
        First name and Last name: <form:input path="name"/><form:errors path="name"/>
    </div>
    <div>
        Mobile number: <form:input path="mobileNumber"/><form:errors path="mobileNumber"/>
    </div>
    <div>
        Email: <form:input path="email"/><form:errors path="email"/>
    </div>
    <div>
        User name: <form:input path="username"/><form:errors path="username"/>
    </div>
    <div>
        Password: <form:password path="password"/><form:errors path="password"/>
    </div>
    <div>
        <form:button>Register</form:button>
    </div>
</form:form>
</body>
</html>