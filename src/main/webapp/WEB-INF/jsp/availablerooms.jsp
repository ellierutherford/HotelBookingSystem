<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <c:url value="/css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" >
    <title>Room Addition</title>
</head>
<body>
<%@ include file="header.jsp" %>
<div>
    <table>
        <caption><h2>List of Rooms</h2></caption>
        <tr>
            <th>Room name</th>
            <th>Num Guests</th>
            <th>Price per night</th>
        </tr>
        <c:forEach var="room" items="${availableRooms}" varStatus="loop">
            <td><c:out value="${room.roomasset_name}" /></td>
            <td><c:out value="${room.max_guests}" /></td>
            <td>
                <c:choose>
                    <c:when test="${username eq 'anonymous'}">
                        <c:out value="${room.roomType.night_rate}"/>
                    </c:when>
                    <c:otherwise>
                        <c:out value="${room.roomType.night_rate/100*90}"/>
                    </c:otherwise>
                </c:choose>
            </td>
            <td><a href="/book?startDate=${startDate}&endDate=${endDate}&numGuests=${numGuests}&roomId=${room.id}">Book this room</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
