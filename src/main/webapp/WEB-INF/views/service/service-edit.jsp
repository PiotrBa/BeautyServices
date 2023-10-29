<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit cosmeticService</title>
    <link rel="stylesheet" type="text/css" href="/css/style-edit.css" />
</head>
<body>
<h1>Edit service</h1>
<form:form method="post" modelAttribute="services">
    <div class="input-group">
        Service Name: <form:input path="serviceName"/>
    </div>
    <div class="input-group">
        Price: <form:input path="price"/>
    </div>
    <div class="input-group">
        Service Duration: <form:input path="serviceDuration"/>
    </div>
    <div class="input-group">
        Service Description: <form:input path="serviceDescription"/>
    </div>
    <form:button class="btn">Save</form:button>
</form:form><br>
<div>
    <a href="/services">Back to list</a>
</div>
</body>
</html>
