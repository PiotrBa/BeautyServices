<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add a service</title>
</head>
<body>
<form:form method="post" modelAttribute="services">
    Service Name: <form:input path="serviceName"/><br/>
    Price: <form:input path="price"/><br/>
    Service Duration: <form:input path="serviceDuration"/><br/>
    Service Description: <form:input path="serviceDescription"/><br/>
    <form:errors path="*"/>
    <form:button>Add</form:button>
</form:form><br>
<a href="/services">Back to list</a>
</body>
</html>
