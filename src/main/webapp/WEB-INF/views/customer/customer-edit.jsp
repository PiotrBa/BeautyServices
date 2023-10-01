<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit customer</title>
    <link rel="stylesheet" type="text/css" href="/style-edit.css" />

</head>
<body>
<h1>Edit customer</h1>
<form:form method="post" modelAttribute="customers">
    Name: <form:input path="name"/><br/>
    Mobile number: <form:input path="mobileNumber"/><br/>
    Email: <form:input path="email"/><br/><br>
    <form:button>Edit</form:button>
</form:form>
<br>
<a href="/customers">Back to list</a>
</body>
</html>
