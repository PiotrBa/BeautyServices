<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="/css/style-login.css" />
</head>
<body>
<form method="post" action="/login">
    User name: <input type="text" name="username"/> Password: <input type="password" name="password"/>
    <button type="submit">Login</button><br/>
    <br>
</form>
<a href="/register/customer">Register</a><br>
</body>
</html>
