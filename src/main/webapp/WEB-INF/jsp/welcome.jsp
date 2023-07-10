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
        <li><a href="/rooms">The Rooms types</a></li>
        <li><a href="/roomassets">The Rooms themselves</a></li> </ul>
    </ul>
</h2>
<h3>
    <ul class="menu">
        <li <c:if test="${guestCount == 0}">class="disabled"</c:if>><a href="/new">Add New Booking existing guest</a></li>
        <li><a href="/experiment">Add New Booking new guest</a></li>
        <li><a href="/newguest">Add New Guest</a></li>
        <li><a href="/newroom">Add New Room</a></li>
    </ul>
</h3>


<div>
    <table >
        <caption><h2>List of Bookings</h2></caption>
        <tr>
            <th>ID</th>
            <th>Guest id</th>
            <%--<th>LastName</th>--%>
            <th>Asset</th>
            <th>RoomType</th>
        </tr>
        <c:forEach var="booking" items="${listBookings}">
            <tr>
                <td><c:out value="${booking.id}" /></td>
                <td><c:out value="${booking.guest_id}" /></td>
                    <%--<td><c:out value="${booking.leadguest_last_name}" /></td>--%>
                <td><c:out value="${booking.roomasset.roomasset_name}" /></td>
                <td><c:out value="${booking.roomasset.roomType.room_name}" /></td>



            <%--<td>
                <a href="/bookings/${booking.id}"/>Edit</a>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="/delete/${booking.id}"  />Delete</a>
            </td>--%>
        </tr>
    </c:forEach>
</table>
</div>
</body>
</html>