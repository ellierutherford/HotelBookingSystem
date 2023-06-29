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



<div>
    <table>
        <caption><h2>List of Rooms</h2></caption>
        <tr>
            <th>ID</th>
            <th>Num Guests</th>
        </tr>
        <c:forEach var="room" items="${availableRooms}" varStatus="loop">
            <tr>
                <td><c:out value="${room.roomasset_name}" /></td>
                <td><c:out value="${room.max_guests}" /></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
