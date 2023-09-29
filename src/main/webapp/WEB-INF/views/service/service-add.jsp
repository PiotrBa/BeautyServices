<style>
    body {
        background-color: #ffffff;
        font-family: Arial, sans-serif;
        color: #333333;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        height: 100vh;
        margin: 0;
    }

    h1 {
        background-color: #FFDAB9;
        padding: 20px;
        border-radius: 20px;
        font-weight: bold;
    }

    form {
        background-color: #FFF3E0;
        padding: 30px;
        border-radius: 20px;
        width: 50%;
        text-align: center;
    }

    input[type="text"], select, form:input {
        width: 100%;
        padding: 10px;
        margin: 10px 0;
        border: 1px solid #ccc;
        border-radius: 20px;
    }

    input[type="submit"], form:button {
        background-color: #FFDAB9;
        color: #333333;
        padding: 10px 20px;
        border: none;
        border-radius: 20px;
        cursor: pointer;
        transition: background-color 0.3s;
    }

    input[type="submit"]:hover, form:button:hover {
        background-color: #FFC0A9;
    }

    a {
        text-decoration: none;
        color: #595959;
        border-radius: 20px;
        padding: 5px 15px;
        background-color: #FFDAB9;
        transition: background-color 0.3s;
    }

    a:hover {
        background-color: #FFC0A9;
    }

</style>


<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add a service</title>
</head>
<body>
<h1>Add service</h1>
<form:form method="post" modelAttribute="services">
    Service Name: <form:input path="serviceName"/><br/>
    Price: <form:input path="price"/><br/>
    Service Duration: <form:input path="serviceDuration"/><br/>
    Service Description: <form:input path="serviceDescription"/><br/>
    <form:errors path="*"/>
    <form:button>Add</form:button>
</form:form><br>
<a href="/services">Back to list</a>
</body>
</html>
