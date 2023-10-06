<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit service</title>
    <link rel="stylesheet" type="text/css" href="/css/style-edit.css" />
</head>
<body>
<h1>Edit service</h1>
<form:form method="post" modelAttribute="services">
    Service Name: <form:input path="serviceName"/><br/>
    Price: <form:input path="price"/><br/>
    Service Duration: <form:input path="serviceDuration"/><br/>
    Service Description: <form:input path="serviceDescription"/><br/>
    <form:button>Edit</form:button>
</form:form><br>
<a href="/services">Back to list</a>
</body>
</html>
