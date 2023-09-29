<style>
    body {
        background-color: white;
        font-family: Arial, sans-serif;
        color: #2c2c2c;
    }
    table {
        width: 100%;
        border-collapse: collapse;
        margin-bottom: 20px;
        background-color: #F5F5DC; /
        border-radius: 15px;
        overflow: hidden;
    }
    th, td {
        padding: 8px;
        text-align: left;
        border-bottom: 1px solid #ccc;
    }
    th {
        background-color: #FFDAB9;
    }
    td {
        background-color: #f9f9f9;
    }
    tr:hover {
        background-color: #FFDAB9;
    }
    .action-btns a {
        margin-right: 10px;
        padding: 5px 10px;
        text-decoration: none;
        color: #595959;
        border: 1px solid #b3b3b3;
        border-radius: 15px;
    }
    .action-btns a:hover {
        background-color: #a6a6a6;
    }
    .add-btn {
        padding: 10px;
        background-color: #595959;
        color: white;
        text-decoration: none;
        border-radius: 15px;
    }
    .add-btn:hover {
        background-color: #404040;
    }
</style>

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
