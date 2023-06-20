<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <c:url value="/css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" >
    <title>Guest Addition</title>
</head>
<body>

<h1>Guest Management</h1>
<h2>
    <ul class="menu">
        <li><a href="/list">The bookings</a></li>
        <li><a href="/guests">The Guests</a></li>
        <li><a href="/rooms">The Rooms</a></li>
    </ul>
</h2>
<h3>
    <ul class="menu">

        <li><a href="/newguest">Add New Guest</a></li>

    </ul>
</h3>

<div>
    <table>
        <caption><h2>List of Guests</h2></caption>
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
        </tr>
        <c:forEach var="guest" items="${listGuests}" varStatus="loop">
            <tr>
                <td><c:out value="${guest.id}" /></td>
                <td><c:out value="${guest.guest_first_name}" /></td>
                <td><c:out value="${guest.guest_last_name}" /></td>
                <td>
                    <a href="/guests/${guest.id}"/>Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <c:choose>
                    <c:when test="${bookingCounts[loop.index] > 0}">
                <td class="disabled">
                    <a href="/deleteguest/${guest.id}">Delete</a>
                </td>
                </c:when>
                <c:otherwise>
                    <td>
                        <a href="/deleteguest/${guest.id}">Delete</a>
                    </td>
                </c:otherwise>
                </c:choose>
                </td>
                <% /* This is for debugging the guests' works counter

                         <td>
                            <c:out value="${bookingCounts[loop.index]}" />
                        </td>*/ %>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
