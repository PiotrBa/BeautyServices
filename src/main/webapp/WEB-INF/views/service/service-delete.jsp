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
    <form:button>Yes</form:button> | <a href="/view/service/services">No</a>
</form:form>
</body>
</html>
