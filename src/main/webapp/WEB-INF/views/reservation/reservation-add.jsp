<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add a reservation</title>
    <style>
        /* Kolory */
        :root {
            --white-color: #FFFFFF;
            --light-cream-color: #FFF5E1;
            --light-powder-color: #FFD1DC;
        }

        body {
            background-color: var(--white-color);
            font-family: Arial, sans-serif;
            padding: 20px;
            color: var(--light-powder-color);
        }

        h1 {
            color: var(--light-powder-color);
            text-align: center;
            margin-bottom: 20px;
        }

        form {
            background-color: var(--light-cream-color);
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 5px 0px var(--light-powder-color);
        }

        form select, form input, form button {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid var(--light-powder-color);
            border-radius: 5px;
        }

        form button {
            background-color: var(--light-powder-color);
            color: var(--white-color);
            border: none;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        form button:hover {
            background-color: var(--light-cream-color);
            color: var(--light-powder-color);
        }

        a {
            color: var(--light-powder-color);
            text-decoration: none;
            padding: 5px 10px;
            border: 1px solid var(--light-powder-color);
            display: inline-block;
            margin-top: 10px;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        a:hover {
            background-color: var(--light-powder-color);
            color: var(--white-color);
        }

    </style>
</head>
<body>
<h1>Add new appointment</h1>
<div>
    <form:form method="post" modelAttribute="reservations">
        Customer: <form:select path="customer"  items="${customer}" itemValue="customerId" itemLabel="name"/><br/>
        Service: <form:select path="serviceList" multiple="true" items="${serviceList}" itemValue="serviceId" itemLabel="serviceName" /><br/>
        Appointment: <form:input type="datetime-local" path="appointment"/><br/>
        <form:button>Add</form:button>
    </form:form>
    <div>
        <a href="/reservations">Back to list</a>
    </div>
</div>
</body>
</html>
