<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>List of reservations</title>
</head>
<body>
<div>
    <table>
        <tr>
            <th>Id</th>
            <th>Customer</th>
            <th>Service</th>
            <th>Appointment</th>
            <th>Create</th>
            <th>Update</th>
        </tr>
        <c:forEach items="${reservations}" var="reservation">
            <tr>
                <td>${reservation.reservationId}</td>
                <td>${reservation.customer.name}</td>
                <td>
                    <ul>
                        <c:forEach items="${reservation.service}" var="service">
                            <li>${service.name}</li>
                        </c:forEach>
                    </ul>

                </td>
                <td>${reservation.appointment}</td>
                <td>${reservation.createReservation}</td>
                <td>${reservation.updateReservation}</td>
                <td><a href="/view/reservation/reservations/update?id=${reservation.reservationId}">Edytuj</a><br/>
                    <a href="/view/reservation/reservations/delete?id=${reservation.reservationId}">Usuń</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<div>
    <a href="/reservations/add">Add new reservation</a>
</div>
</body>
</html>
