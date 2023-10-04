<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add a customer</title>
    <link rel="stylesheet" type="text/css" href="/style-add.css" />
</head>
<body>
<h1>Add customer</h1>
<form:form method="post" modelAttribute="customers">
    <div class="input-container">
        <label for="name">Name:</label>
        <form:input path="name"/>
    </div>
    <div class="input-container">
        <label for="username">User name:</label>
        <form:input path="username"/>
    </div>
    <div class="input-container">
        <label for="mobileNumber">Mobile number:</label>
        <form:input path="mobileNumber"/>
    </div>
    <div class="input-container">
        <label for="email">Email:</label>
        <form:input path="email"/>
    </div>
    <div class="input-container">
        <label for="password">Password:</label>
        <form:input path="password"/>
    </div>


    <form:button>Add</form:button>
</form:form><br>
<a href="/customers">Back to list</a>
</body>
</html>
