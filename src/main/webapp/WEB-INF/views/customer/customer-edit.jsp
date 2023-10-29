<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit customer</title>
    <link rel="stylesheet" type="text/css" href="/css/style-edit.css" />

</head>
<body>
<h1>Edit customer</h1>
<form:form method="post" modelAttribute="customers">
    <div class="input-group">
        Name: <form:input path="name"/>
    </div>
    <div class="input-group">
        Mobile number: <form:input path="mobileNumber"/>
    </div>
    <div class="input-group">
        Email: <form:input path="email"/>
    </div>
    <form:button class="btn">Save</form:button>
</form:form>
<div>
    <a href="/customers">Back to list</a>
</div>

</body>
</html>
