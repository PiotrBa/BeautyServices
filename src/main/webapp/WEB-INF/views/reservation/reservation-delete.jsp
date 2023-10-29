<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Delete reservation</title>
    <link rel="stylesheet" type="text/css" href="/css/style-delete.css" />

</head>
<body>
<h1>Delete appointment</h1>
    <form:form method="post" modelAttribute="reservation">
        <p>Do you want to delete ${reservation.customer.name} appointment?</p>
        <form:button>Yes</form:button>
        <form:button><a href="/reservations">No</a></form:button>
    </form:form>
</body>
</html>
