<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add a service</title>
    <link rel="stylesheet" type="text/css" href="/css/style-add.css" />
</head>
<body>
<h1>Add service</h1>
<form:form method="post" modelAttribute="services">
    <div class="input-container">
        <label for="serviceName">Service Name:</label>
        <form:input path="serviceName"/>
    </div>

    <div class="input-container">
        <label for="price">Price:</label>
        <form:input path="price"/>
    </div>

    <div class="input-container">
        <label for="serviceDuration">Service Duration:</label>
        <form:input path="serviceDuration"/>
    </div>

    <div class="input-container">
        <label for="serviceDescription">Service Description:</label>
        <form:input path="serviceDescription"/>
    </div>
    <form:errors path="*"/>
    <form:button>Add</form:button>
</form:form><br>
<a href="/services">Back to list</a>
</body>
</html>
