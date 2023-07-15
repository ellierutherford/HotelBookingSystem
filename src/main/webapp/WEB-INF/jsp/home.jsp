<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <c:url value="/css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" >
    <title>Marriott Hotel Booking System</title>

</head>
<body>
<h1>Welcome to our hotel!</h1>
<%@ include file="header.jsp" %>
<h2>
<ul class="menu">
    <li><a href="/search">Search for availability</a></li>
    <li><a href="/findbooking">Find a booking</a></li>
</ul>
</h2>

</body>
</html>