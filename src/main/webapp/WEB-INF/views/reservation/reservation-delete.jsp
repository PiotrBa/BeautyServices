<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Delete reservation</title>
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

        p {
            margin-bottom: 20px;
        }

        form button {
            padding: 10px 20px;
            border: 1px solid var(--light-powder-color);
            border-radius: 5px;
            background-color: var(--light-powder-color);
            color: var(--white-color);
            cursor: pointer;
            transition: background-color 0.3s;
            text-decoration: none;
        }

        form button:hover {
            background-color: var(--light-cream-color);
            color: var(--light-powder-color);
        }

        a {
            color: var(--light-powder-color);
            text-decoration: none;
            display: inline-block;
            margin-left: 10px;
            padding: 10px 20px;
            border: 1px solid var(--light-powder-color);
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
<h1>Delete reservation</h1>
<div>
    <form:form method="post" modelAttribute="reservation">
        <p>Do you want to delete ${reservation.customer.name}?</p>
        <form:button>Yes</form:button>
        <form:button><a href="/reservations">No</a></form:button>
    </form:form>
</div>
</body>
</html>
