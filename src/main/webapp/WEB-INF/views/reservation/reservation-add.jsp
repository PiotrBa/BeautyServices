<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <div>
            <form:select path="customer"  items="${customer}" itemValue="customerId" itemLabel="name"/>
        </div>
        <div class="input-container">
            <form:select class="frame" path="cosmeticServiceList" multiple="true">
                <c:forEach items="${serviceList}" var="cosmeticService">
                    <form:option value="${cosmeticService.serviceId}">
                        ${cosmeticService.serviceName} - ${cosmeticService.price}£ - ${cosmeticService.serviceDuration} min
                    </form:option>
                </c:forEach>
            </form:select>
        </div>
        <div>
            <form:input type="datetime-local" path="appointment"/>
        </div>
        <form:button class="btn">Add</form:button>
    </form:form>
    <div>
        <a href="/reservations">Back to list</a>
    </div>
</div>
</body>
</html>
