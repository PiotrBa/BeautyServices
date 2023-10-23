
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit reservation</title>
    <link rel="stylesheet" type="text/css" href="/css/style-edit.css" />
</head>
<body>
<h1>Edit appointment</h1>
<div class="center">
    <form:form method="post" modelAttribute="reservations">
        <h2>${customer.name}</h2><br/>
        <div>
            Service: <form:select path="serviceList" multiple="true" items="${serviceList}" itemValue="serviceId" itemLabel="serviceName"/><br/>
        </div>
        <div>
            Appointment: <form:input type="datetime-local" path="appointment" pattern="yyyy-MM-dd'T'HH:mm"/><br/>
        </div>
        <form:button class="btn">Edit</form:button>
    </form:form>
    <div>
        <a href="/reservations">Back to list</a>
    </div>
</div>
</body>
</html>
