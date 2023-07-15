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
<h1>Add new card</h1>

<div>
    
    <c:url var="actionUrl" value="/completeBooking">
        <c:param name="bookingId" value="${booking.id}" />
    </c:url>

    <%@ include file="cardform.jsp" %>

</div>
</body>
</html>