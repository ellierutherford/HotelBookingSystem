<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <c:url value="/css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" >
    <title>Room Addition</title>
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
        <li><a href="/newroom">Add New Room Type</a></li>
    </ul>
</h3>


<div>
    <table>
        <caption><h2>List of Room Type</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Rate</th>
        </tr>
        <c:forEach var="room" items="${listRooms}" varStatus="loop">
            <tr>
                <td><c:out value="${room.id}" /></td>
                <td><c:out value="${room.room_name}" /></td>
                <td><c:out value="${room.night_rate}" /></td>

                <td>
                    <a href="/rooms/${room.id}"/>Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <c:choose>
                    <c:when test="${bookingCounts[loop.index] > 0}">
                <td class="disabled">
                    <a href="/deleteroom/${room.id}">Delete</a>
                </td>
                </c:when>
                <c:otherwise>
                    <td>
                        <a href="/deleteroom/${room.id}">Delete</a>
                    </td>
                </c:otherwise>
                </c:choose>
                </td>
                <% /* This is for debugging the rooms' works counter

                         <td>
                            <c:out value="${bookingCounts[loop.index]}" />
                        </td>*/ %>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
