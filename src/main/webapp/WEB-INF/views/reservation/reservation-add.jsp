<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add a reservation</title>
    <link rel="stylesheet" type="text/css" href="/css/style-add.css" />

</head>
<body>
<h1>Add new appointment</h1>
<div class="center">
    <form:form method="post" modelAttribute="reservations">
        <div class="input-container">
            Customer: <form:select path="customer"  items="${customer}" itemValue="customerId" itemLabel="name"/>
        </div>
        <div class="input-container">
            Service:<form:select path="serviceList" multiple="true" items="${serviceList}" itemValue="serviceId" itemLabel="serviceName"/>
        </div>
        <div class="input-container">
            Appointment:<form:input type="datetime-local" path="appointment"/>
        </div>
        <form:button>Add</form:button>
    </form:form>
    <div>
        <a href="/reservations">Back to list</a>
    </div>
</div>
</body>
</html>
