<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>List of services</title>
    <link rel="stylesheet" type="text/css" href="/css/style-list.css" />
</head>
<body>
<h1>List of services</h1>
<div>
    <table>
        <tr>
            <th>Service</th>
            <th>Price</th>
            <th>Duration</th>
            <th>Description</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${services}" var="cosmeticService">
            <tr>
                <td>${cosmeticService.serviceName}</td>
                <td>${cosmeticService.price}£</td>
                <td>${cosmeticService.serviceDuration}min</td>
                <td>${cosmeticService.serviceDescription}</td>
                <td class="action-btns">
                    <a href="services/edit?id=${cosmeticService.serviceId}">Edit</a> <a href="services/delete?id=${cosmeticService.serviceId}">Delete</a><br/>
                </td>
            </tr>
        </c:forEach>
    </table><br>
    <a href="services/add">Add New Service</a>
    <a href="/reservations">Appointments</a>
    <a href="/customers">Customers</a>
    <a href="/logout">Logout</a>
</div>
</body>
</html>
