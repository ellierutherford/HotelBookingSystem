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

<div>
        <table>
            <caption>
                <h2>Booking Retrieved</h2>
            </caption>
            <tr>
                <th>Booking Ref: </th>
                <td>
                    <c:out value='${booking.bookingRef}' />
                </td>
            </tr>
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
            <c:if test="${allowCancel}">
                <tr>
                    <td><a href="/delete/${booking.id}">Cancel booking</a></td>
                </tr>
            </c:if>


        </table>
</div>
</body>
</html>
