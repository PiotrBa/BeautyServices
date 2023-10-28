
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <form:select class="frame" path="cosmeticServiceList" multiple="true">
            <c:forEach items="${serviceList}" var="cosmeticService">
                <form:option value="${cosmeticService.serviceId}">
                    ${cosmeticService.serviceName} - ${cosmeticService.price}£ - ${cosmeticService.serviceDuration} min
                </form:option>
            </c:forEach>
            </form:select>
        </div>
        <div>
            <form:input type="datetime-local" path="appointment" pattern="yyyy-MM-dd'T'HH:mm"/><br/>
        </div>
        <form:button class="btn">Edit</form:button>
    </form:form>
    <div>
        <a href="/reservations">Back to list</a>
    </div>
</div>
</body>
</html>
