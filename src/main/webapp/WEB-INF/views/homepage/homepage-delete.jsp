<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Homepage delete</title>
    <link rel="stylesheet" type="text/css" href="/css/style-delete.css" />

</head>
<body>
<h1>Homepage-delete</h1>
    <form:form method="post" modelAttribute="reservation">
        <p>Do you really want to cancel your appointment?</p>
        <form:button>Yes</form:button>
        <form:button><a href="/homepage">No</a></form:button>
    </form:form>
</body>
</html>
