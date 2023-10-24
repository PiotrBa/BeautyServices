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
        <form:select class="frame" path="serviceList" multiple="true">
        <c:forEach items="${serviceList}" var="service">
            <form:option value="${service.serviceId}">
                ${service.serviceName} - ${service.price}£ - ${service.serviceDuration} min
            </form:option>
        </c:forEach>
        </form:select><br/>
        <form:input type="datetime-local" path="appointment" pattern="yyyy-MM-dd'T'HH:mm"/><br/>
        <form:button>Edit</form:button>
    </form:form>
    <div>
        <a href="/homepage">Back to list</a>
    </div>
</div>
</body>
</html>
