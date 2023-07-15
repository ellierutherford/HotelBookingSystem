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
<%@ include file="header.jsp" %>
<h2>Find your booking by entering your booking reference below.</h2>
<form action="find" method="post">
    <label for="bookingRef">Booking Reference:</label>
    <input type="text" name="bookingRef" size="45"
           value='${reservation.bookingRef}'/>
    <input type="submit" id="saveButton" value="Search" />
</form>

</body>
</html>