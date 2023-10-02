<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form method="post" action="/login">
    User name: <input type="text" name="username"/> Password: <input type="password" name="password"/>
    <button type="submit">Login</button>
</form>
</body>
</html>