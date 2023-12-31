<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>List of reservations</title>
    <link rel="stylesheet" type="text/css" href="/css/style-list.css" />

</head>
<body>
<h1>Hello ${customer.username}!</h1>
<div>
    <table>
        <tr>
            <th>Customer</th>
            <th>Service</th>
            <th>Total: price/ duration</th>
            <th>Appointment</th>
            <th>Actions</th>
        </tr>
        <c:forEach items="${reservation}" var="reservation">
            <tr>
                <td>${reservation.customer.name}</td>
                <td>
                    <ul>
                        <c:forEach items="${reservation.serviceList}" var="cosmeticService">
                            <li>${cosmeticService.serviceName}</li>
                        </c:forEach>
                    </ul>
                </td>
                <td>${reservation.getTotalPriceAndDuration()}</td>
                <td>${reservation.createAppointmentFormatted}</td>
                <td>
                    <a href="/homepage/edit?id=${reservation.reservationId}">Edit</a><br/>
                    <a href="/homepage/delete?id=${reservation.reservationId}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div>
        <a href="/homepage/add">Add new appointment</a>
        <a href="/logout">Logout</a>

    </div>
</div>
</body>
</html>

