<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Delete service</title>
    <link rel="stylesheet" type="text/css" href="/css/style-delete.css" />
</head>
<body>
<h1>Delete service</h1>
<h2 th:text="${errorMessage}"></h2>
<form:button><a href="/services">OK</a></form:button>
</body>
</html>