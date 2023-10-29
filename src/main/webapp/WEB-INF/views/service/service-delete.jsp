<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Delete cosmeticService</title>
    <link rel="stylesheet" type="text/css" href="/css/style-delete.css" />
</head>
<body>
<h1>Delete service</h1>
<form:form method="post" modelAttribute="services">
    <p>Do you want to delete ${services.serviceName}?</p>
    <form:button>Yes</form:button> | <form:button><a href="/services">No</a></form:button>
</form:form>
</body>
</html>
