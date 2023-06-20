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

<h1>Booking</h1>

<h2>
    <ul class="menu">
        <li><a href="/list">The Bookings</a></li>
        <li><a href="/guests">The Guests</a></li>
        <li><a href="/rooms">The Rooms</a></li>
    </ul>
</h2>
<h3>
    <ul class="menu">
        <li <c:if test="${guestCount == 0}">class="disabled"</c:if>><a href="/new">Add New Booking</a></li>
        <li><a href="/newguest">Add New Guest</a></li>
        <li><a href="/newroom">Add New Room</a></li>
    </ul>
</h3>


<div>
    <table >
        <caption><h2>List of Bookings</h2></caption>
        <tr>
            <th>ID</th>
            <th>Title</th>

            <th>Ref</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="booking" items="${listBookings}">
            <tr>
                <td><c:out value="${booking.id}" /></td>
                <td><c:out value="${booking.booking_name}" /></td>

                <td><c:out value="${booking.bookRef}" /></td>
                <td>
                    <a href="/bookings/${booking.id}"/>Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="/delete/${booking.id}"  />Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>