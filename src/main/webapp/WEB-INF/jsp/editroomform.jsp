<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title> Application</title>
    <c:url value="/css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" >
</head>
<body>
<h1>Room Management</h1>
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
        <li <c:if test="${guestCount == 0}">class="disabled"</c:if>><a href="/new">Add New Booking</a></li>
        <li><a href="/newguest">Add New Guest</a></li>
        <li><a href="/newroom">Add New Room</a></li>
    </ul>
</h3>
<div>
    <form action="save" method="post">
        <table>
            <caption>
                <h2>
                    Edit Room
                </h2>
            </caption>
            <input type="hidden" name="id" value="<c:out value='${room.id}' />"  />
            <tr>
                <th>The First Name: </th>
                <td>
                    <input type="text" name="room_name" size="45"
                           value="<c:out value='${room.room_name}' />"
                    />
                </td>
            </tr>




            <th>Bookings: TBC</th>
            <c:forEach var="booking" items="${bookings}">
                <td>
                    <input type="text" name="name" size="45"
                           value="<c:out value='${booking.leadguest_first_name} ${booking.bookingRef}'/>"
                    />
                </td>

            </c:forEach>
            </tr>


            <tr>
                <td colspan="2" >
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>