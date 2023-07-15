<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Bookings Application</title>
    <c:url value="/css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" >
</head>
<body>

<h1>List out our users (incl. passwords), terribly insecure!</h1>

<div>
    <c:forEach var="user" items="${users}" varStatus="loop">
        <tr>
            <td><c:out value="${user.username}" /></td>
            <td><c:out value="${user.password}" /></td>
            <td><c:out value="${user.firstName}" /></td>
            <td><c:out value="${user.lastName}" /></td>
        </tr>
    </c:forEach>
</div>
</body>
</html>