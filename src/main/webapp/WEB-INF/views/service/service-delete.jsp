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
    p {
        font-size: 18px;
        margin-bottom: 20px;
    }
    form:button, a {
        padding: 10px 20px;
        text-decoration: none;
        color: white;
        background-color: #595959;
        border-radius: 15px;
        transition: 0.3s;
        margin-right: 10px;
    }
    form:button:hover, a:hover {
        background-color: #404040;
    }
    a {
        color: #595959;
        background-color: white;
    }
</style>


<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Delete service</title>
</head>
<body>
<h1>Delete service</h1>
<form:form method="post" modelAttribute="services">
    <p>Do you want to delete ${service.serviceName}?</p>
    <form:button>Yes</form:button> | <form:button><a href="/services">No</a></form:button>
</form:form>
</body>
</html>
