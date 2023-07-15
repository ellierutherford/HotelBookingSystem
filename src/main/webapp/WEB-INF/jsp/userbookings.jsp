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

    <h2>Your bookings</h2>

    <c:forEach var="booking" items="${bookings}" varStatus="loop">
        <div>
        <table>
            <caption>
                <h3>Booking with reference ${booking.bookingRef}</h3>
            </caption>
            <tr>
                <th>Booking Start Date: </th>
                <td>
                    <c:out value='${booking.startDate}' />
                </td>
            </tr>
            <tr>
                <th>Booking End Date: </th>
                <td>
                    <c:out value='${booking.endDate}' />
                </td>
            </tr>
            <tr>
                <th>Room booked: </th>
                <td>
                    <c:out value='${booking.roomasset.roomasset_name}' />
                </td>
            </tr>
            <tr>
                <th>Booking status: </th>
                <td>
                    <c:out value='${booking.status}' />
                </td>
            </tr>
            <c:if test="${booking.status eq 'ACTIVE'}">
                <tr>
                    <td><a href="/cancel/${booking.id}">Cancel booking</a></td>
                </tr>
            </c:if>
        </table>
        </div>
    </c:forEach>
</body>
</html>
