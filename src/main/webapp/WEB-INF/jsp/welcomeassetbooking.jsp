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
        <li><a href="/assetbookings">The Rooms themselves</a></li> </ul>
    </ul>
</h2>
<h3>
    <ul class="menu">
        <li><a href="/newassetbooking">Add New Room Asset</a></li>
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
        <c:forEach var="assetbooking" items="${assetbooking}" varStatus="loop">
            <tr>
                <td><c:out value="${assetbooking.id}" /></td>
                <td><c:out value="${assetbooking.assetbooking_name}" /></td>
                <td><c:out value="${assetbooking.roomType.room_name}" /></td>

                <td>
                    <a href="/assetbookings/${assetbooking.id}"/>Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <c:choose>
                    <c:when test="${bookingCounts[loop.index] > 0}">
                        <td class="disabled">
                            <a href="/deleteassetbooking/${assetbooking.id}">Delete</a>
                        </td>
                    </c:when>
                <c:otherwise>
                    <td>
                        <a href="/deleteassetbooking/${assetbooking.id}">Delete</a>
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
