<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Delete customer</title>
</head>
<body>
<h1>Delete customer</h1>
<form:form method="post" modelAttribute="customers">
    <p>Do you want to delete ${customer.firstName} ${customer.lastName}?</p>
    <form:button>Yes</form:button> | <a href="/view/customer/customers">No</a>
</form:form>
</body>
</html>
