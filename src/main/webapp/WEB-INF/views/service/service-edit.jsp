<style>
    body {
        background-color: #FFFFFF;
        font-family: Arial, sans-serif;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        height: 100vh;
        margin: 0;
    }

    h1 {
        background-color: #FFDAB9;
        color: #333333;
        padding: 10px 20px;
        border-radius: 15px;
        font-size: 24px;
        font-weight: bold;
    }

    form {
        background-color: #FFF8DC;
        padding: 20px;
        border-radius: 15px;
        width: 50%;
        text-align: center;
    }

    input[type="text"], select {
        width: 100%;
        padding: 10px;
        margin-bottom: 10px;
        border: 1px solid #ccc;
        border-radius: 15px;
        font-size: 16px;
        font-weight: bold;
    }

    input[type="submit"] {
        background-color: #595959;
        color: white;
        padding: 12px 24px;
        border: none;
        border-radius: 15px;
        cursor: pointer;
    }

    input[type="submit"]:hover {
        background-color: #404040;
    }

    a {
        text-decoration: none;
        color: #333333;
        font-weight: bold;
        margin-right: 10px;
        padding: 5px 10px;
        border-radius: 15px;
        background-color: #FFDAB9;
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
    <title>Edit service</title>
</head>
<body>
<h1>Edit service</h1>
<form:form method="post" modelAttribute="services">
    Service Name: <form:input path="serviceName"/><br/>
    Price: <form:input path="price"/><br/>
    Service Duration: <form:input path="serviceDuration"/><br/>
    Service Description: <form:input path="serviceDescription"/><br/>
    <form:button>Edit</form:button>
</form:form><br>
<a href="/services">Back to list</a>
</body>
</html>
