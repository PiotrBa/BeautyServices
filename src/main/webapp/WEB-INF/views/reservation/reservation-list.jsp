<style>
    body {
        background-color: #ffffff;
        font-family: Arial, sans-serif;
        color: #333333;
    }

    h1 {
        color: #FFDAB9;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        margin-bottom: 20px;
        background-color: #FFF3E0;
    }

    th, td {
        padding: 8px;
        text-align: left;
        border-bottom: 1px solid #ccc;
    }

    th {
        background-color: #FFDAB9;
        font-weight: bold;
    }

    td {
        background-color: #FAF3E7;
    }

    tr:hover {
        background-color: #FFE4C4;
    }


    a {
        display: inline-block;
        padding: 5px 10px;
        margin-bottom: 5px;
        border-radius: 10px;
        text-decoration: none;
        color: #333333;
        background-color: #FFDAB9;
        border: 1px solid #FFC0A9;
        transition: background-color 0.3s;
    }

    a:hover {
        background-color: #FFC0A9;
    }

    ul {
        list-style-type: none;
        padding: 0;
    }

    li {
        border-radius: 10px;
        background-color: #FFF3E0;
        margin-bottom: 5px;
        padding: 2px 5px;
    }
</style>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>List of reservations</title>

</head>
<body>
<h1>List of appointments</h1>
<div>
    <table>
        <tr>
            <th>Id</th>
            <th>Customer</th>
            <th>Service</th>
            <th>Appointment</th>
            <th>Create</th>
            <th>Update</th>
            <th>Actions</th>
        </tr>
        <c:forEach items="${reservations}" var="reservation">
            <tr>
                <td>${reservation.reservationId}</td>
                <td>${reservation.customer.name}</td>
                <td>
                    <ul>
                        <c:forEach items="${reservation.serviceList}" var="service">
                            <li>${service.serviceName}</li>
                        </c:forEach>
                    </ul>
                </td>
                <td>${reservation.appointment}</td>
                <td>${reservation.createReservation}</td>
                <td>${reservation.updateReservation}</td>
                <td>
                    <a href="/reservations/edit?id=${reservation.reservationId}">Edit</a><br/>
                    <a href="/reservations/delete?id=${reservation.reservationId}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div>
        <a href="/reservations/add">Add new appointment</a>
    </div>
</div>
</body>
</html>
