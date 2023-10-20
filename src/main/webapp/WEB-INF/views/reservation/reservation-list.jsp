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
            <th>Appointment</th>
            <sec:authorize access="hasRole('ADMIN')">
            <th>Create</th>
            <th>Update</th>
            </sec:authorize>
            <th>Actions</th>
        </tr>
        <c:forEach items="${reservations}" var="reservation">
            <tr>
                <td>${reservation.customer.name}</td>
                <td>
                    <ul>
                        <c:forEach items="${reservation.serviceList}" var="service">
                            <li>${service.serviceName}</li>
                        </c:forEach>
                    </ul>
                </td>
                <td>${reservation.appointment}</td>
                <sec:authorize access="hasRole('ADMIN')">
                <td>${reservation.createReservationFormatted}</td>
                <td>${reservation.updateReservationFormatted}</td>
                </sec:authorize>
                <td>
                    <a href="/reservations/edit?id=${reservation.reservationId}">Edit</a><br/>
                    <a href="/reservations/delete?id=${reservation.reservationId}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div>
        <a href="/reservations/add">Add new appointment</a>
        <sec:authorize access="hasRole('ADMIN')">
        <a href="/customers">Customers</a>
        <a href="/services">Services</a>
        </sec:authorize>
        <a href="/logout">Logout</a>
    </div>
</div>
</body>
</html>

