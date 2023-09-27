<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>List of services</title>
</head>
<body>
<div>
    <table>
        <tr>
            <th>Service</th>
            <th>Price</th>
            <th>Duration</th>
            <th>Description</th>
        </tr>
        <c:forEach items="${services}" var="service">
            <tr>
                <td>${service.serviceName}</td>
                <td>${service.price}£</td>
                <td>${service.serviceDuration}</td>
                <td>${service.serviceDescription}</td>
                <td><a href="services/edit?id=${service.serviceId}">Edit</a><br/>
                    <a href="services/delete?id=${service.serviceId}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table><br>
    <a href="services/add">Add New Service</a>
</div>
</body>
</html>
