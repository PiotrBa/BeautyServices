<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>List of customers</title>
    <link rel="stylesheet" type="text/css" href="/css/style-list.css" />
</head>
<body>
<div>
    <h1>List of customers</h1>
    <table>
        <tr>
            <th>Name</th>
            <th>Mobile number</th>
            <th>Email</th>
            <th>Actions</th>
        </tr>
        <c:forEach items="${customers}" var="customer">
            <tr>
                <td>${customer.name}</td>
                <td>${customer.mobileNumber}</td>
                <td>${customer.email}</td>
                <td class="action-btns">
                    <a href="customers/edit?id=${customer.customerId}">Edit</a>
                    <a href="customers/delete?id=${customer.customerId}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a class="add-btn" href="customers/add">Add New Customer</a>
    <a href="/logout">Logout</a>
</div>
</body>
</html>
