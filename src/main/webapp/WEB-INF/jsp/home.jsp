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

<%@ include file="header.jsp" %>
<form action="find" method="post">
    <label for="bookingRef">Booking Reference:</label>
    <input type="text" name="bookingRef" size="45"
           value='${booking.bookingRef}'/>
    <input type="submit" id="saveButton" value="Search" />
</form>

</body>
</html>