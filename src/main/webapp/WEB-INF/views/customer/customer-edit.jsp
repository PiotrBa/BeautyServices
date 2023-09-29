<style>
    body {
        background-color: white;
        font-family: Arial, sans-serif;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        height: 100vh;
        margin: 0;
        color: #2c2c2c;
    }
    h1 {
        background-color: #FFDAB9;
        padding: 10px;
        border-radius: 15px;
    }
    form {
        background-color: #F5F5DC;
        padding: 20px;
        border-radius: 15px;
        width: 50%;
        text-align: center;
        box-shadow: 0px 4px 15px rgba(0,0,0,0.1);
    }
    input[type="text"], select {
        width: 100%;
        padding: 8px;
        margin-bottom: 10px;
        border: 1px solid #ccc;
        border-radius: 15px;
    }
    input[type="submit"] {
        background-color: #595959;
        color: white;
        padding: 10px 20px;
        border: none;
        border-radius: 15px;
        cursor: pointer;
    }
    input[type="submit"]:hover {
        background-color: #404040;
    }
    a {
        text-decoration: none;
        color: #595959;
        margin-right: 10px;
        border: 1px solid #FFDAB9;
        padding: 5px 10px;
        border-radius: 15px;
        transition: 0.3s;
    }
    a:hover {
        background-color: #FFDAB9;
    }
</style>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit customer</title>
</head>
<body>
<h1>Edit customer</h1>
<form:form method="post" modelAttribute="customers">
    Name: <form:input path="name"/><br/>
    Mobile number: <form:input path="mobileNumber"/><br/>
    Email: <form:input path="email"/><br/><br>
    <form:button>Edit</form:button>
</form:form>
<br>
<a href="/customers">Back to list</a>
</body>
</html>
