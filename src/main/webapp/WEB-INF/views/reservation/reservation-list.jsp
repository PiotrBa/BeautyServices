<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>List of reservations</title>
    <link rel="stylesheet" type="text/css" href="/css/style-list.css" />

</head>
<body>
<h1>List of appointments</h1>
<div>
    <table>
        <tr>
            <th>Customer</th>
            <th>Service</th>
            <th>Total: price/ duration</th>
            <th>Appointment</th>
            <th>Create</th>
            <th>Update</th>
            <th>Actions</th>
        </tr>
        <c:forEach items="${reservations}" var="reservation">
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
                <td>${reservation.createReservationFormatted}</td>
                <td>${reservation.updateReservationFormatted}</td>
                <td>
                    <a href="/reservations/edit?id=${reservation.reservationId}">Edit</a><br/>
                    <a href="/reservations/delete?id=${reservation.reservationId}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div>
        <a href="/reservations/add">Add new appointment</a>
        <a href="/customers">Customers</a>
        <a href="/services">Services</a>
        <a href="/register/admin">Register new admin</a>
        <br>
        <a href="/logout">Logout</a>
    </div>
</div>
</body>
</html>

