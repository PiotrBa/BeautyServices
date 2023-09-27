<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>List of customers</title>
</head>
<body>
<div>
    <table>
        <tr>
            <th>First Name</th>
            <th>Last name</th>
            <th>Mobile number</th>
            <th>Email</th>
        </tr>
        <c:forEach items="${customers}" var="customer">
            <tr>
                <td>${customer.firstName}</td>
                <td>${customer.lastName}</td>
                <td>${customer.mobileNumber}</td>
                <td>${customer.email}</td>
                <td><a href="customers/edit?id=${customer.customerId}">Edit</a><br/>
                    <a href="customers/delete?id=${customer.customerId}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table><br>
    <a href="customers/add">Add New Customer</a>
</div>
</body>
</html>
