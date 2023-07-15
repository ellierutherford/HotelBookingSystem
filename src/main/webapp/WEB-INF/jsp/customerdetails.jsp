<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Bookings Store Application</title>
    <c:url value="/css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" >
</head>
<body>
<%@ include file="header.jsp" %>



<h1>Welcome to the member area ${customer.guest_first_name}</h1>

    <h2>
        <ul class="menu">
            <li><a href="/list">Manage cards</a></li>
            <li><a href="/mybookings">View past reservations</a></li>
            <li><a href="/rooms">Delete my account</a></li>
        </ul>
    </h2>

</body>
</html>
