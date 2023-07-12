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

<h1>You have successfully booked! Please find your details below</h1>
<div>
    <table>
        <caption>
            <h2> Booking Details</h2>
        </caption>
        <tr>
            <th>Start Date: ${booking.startDate}</th>
        </tr>
        <tr>
            <th>End Date: ${booking.endDate}</th>
        </tr>
        <tr>
            <th>Number of Guests: ${booking.numGuests}</th>
        </tr>
        <tr>
            <th>Room name: ${booking.roomasset.roomasset_name}</th>
        </tr>
        <tr>
            <th>Booking ref: ${booking.bookingRef}</th>
        </tr>
    </table>
</div>
</body>
</html>