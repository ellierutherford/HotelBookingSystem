<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <c:url value="/css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" >
    <title>Room ASSET Addition</title>
</head>
<body>

<h1>Room ASSET Management</h1>
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
        <li><a href="/newroomasset">Add New Room Asset</a></li>
    </ul>
</h3>


<div>
    <table>
        <caption><h2>List of Room Assets</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>AssetLabel</th>
        </tr>
        <c:forEach var="roomasset" items="${roomasset}" varStatus="loop">
            <tr>
                <td><c:out value="${roomasset.id}" /></td>
                <td><c:out value="${roomasset.roomasset_name}" /></td>
                <td><c:out value="${roomasset.roomType.room_name}" /></td>

                <td>
                    <a href="/roomassets/${roomasset.id}"/>Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <c:choose>
                    <c:when test="${bookingCounts[loop.index] > 0}">
                        <td class="disabled">
                            <a href="/deleteroomasset/${roomasset.id}">Delete</a>
                        </td>
                    </c:when>
                <c:otherwise>
                    <td>
                        <a href="/deleteroomasset/${roomasset.id}">Delete</a>
                    </td>
                </c:otherwise>
                </c:choose>
                </td>

            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
