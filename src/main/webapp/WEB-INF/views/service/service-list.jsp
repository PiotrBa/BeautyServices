<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>List of services</title>
    <link rel="stylesheet" type="text/css" href="/style-list.css" />
</head>
<body>
<div>
    <table>
        <tr>
            <th>Service</th>
            <th>Price</th>
            <th>Duration</th>
            <th>Description</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${services}" var="service">
            <tr>
                <td>${service.serviceName}</td>
                <td>${service.price}£</td>
                <td>${service.serviceDuration}min</td>
                <td>${service.serviceDescription}</td>
                <td class="action-btns">
                    <a href="services/edit?id=${service.serviceId}">Edit</a> <a href="services/delete?id=${service.serviceId}">Delete</a><br/>
                </td>
            </tr>
        </c:forEach>
    </table><br>
    <a href="services/add" class="add-btn">Add New Service</a>
</div>
</body>
</html>
