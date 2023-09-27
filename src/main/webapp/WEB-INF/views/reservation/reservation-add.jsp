<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add a reservation</title>
</head>
<body>
<form:form method="post" modelAttribute="reservations">
    Customer: <form:input path="customer"/><br/>
    Service: <form:select path="serviceList" multiple="true" items="${serviceList}" itemValue="serviceId" itemLabel="serviceName" /><br/>
    Appointment: <form:input path="appointment"/><br/>
    <form:button>Add</form:button>
</form:form><br>
<a href="/reservations">Back to list</a>
</body>
</html>