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
<div>
    <h2>
        <a href="/search">Search for availability</a>
    </h2>
</div>
<div>
    <h2>
        <a href="/findbooking">Find a booking</a>
    </h2>
</div>

</body>
</html>