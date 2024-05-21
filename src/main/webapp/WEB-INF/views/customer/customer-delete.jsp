<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer-delete</title>
    <link rel="stylesheet" type="text/css" href="/css/style-delete.css" />
</head>
<body>
<h1>Customer-delete</h1>
<form:form method="post" modelAttribute="customers">
    <p>Do you want to delete ${customers.name}?</p>
    <form:button>Yes</form:button> | <form:button><a href="/customers">No</a></form:button>
</form:form>
</body>
</html>
