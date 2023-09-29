<style>
    body {
        background-color: white;
        font-family: Arial, sans-serif;
        color: #2c2c2c;
    }

    div {
        padding: 20px;
        background-color: white;
        border-radius: 15px;
        margin: 20px;
        box-shadow: 0px 4px 15px rgba(0,0,0,0.1);
    }

    table {
        width: 100%;
        border-collapse: collapse;
        margin-bottom: 20px;
        background-color: #F5F5DC;
        border-radius: 15px;
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
        background-color: #FAFAF5;
    }

    tr:hover {
        background-color: #E5E5CC;
    }

    .action-btns a {
        margin-right: 10px;
        padding: 5px 10px;
        text-decoration: none;
        color: #595959;
        border: 1px solid #FFDAB9;
        border-radius: 5px;
        transition: 0.3s;
    }

    .action-btns a:hover {
        background-color: #FFDAB9;
    }

    a.add-btn {
        padding: 10px;
        background-color: #595959;
        color: white;
        text-decoration: none;
        border-radius: 5px;
        transition: 0.3s;
    }

    a.add-btn:hover {
        background-color: #404040;
    }

    h1 {
        color: #FFDAB9;
        font-weight: bold;
    }

</style>

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
</div>
</body>
</html>
