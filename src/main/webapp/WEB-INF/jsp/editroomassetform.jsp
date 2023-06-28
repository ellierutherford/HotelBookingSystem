<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Room ASSETS</title>
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
        <li><a href="/roomassets">The Rooms themselves</a></li>
    </ul>
</h2>
<h3>
    <ul class="menu">
        <li><a href="/newroomasset">Add New Room Asset</a></li>
    </ul>
</h3>
<div>
    <form action="save" method="post">
        <table>
            <caption>
                <h2>
                    Edit RoomAsset
                </h2>
            </caption>
            <input type="hidden" name="id" value="<c:out value='${roomasset.id}' />"  />
            <tr>
                <th>The Room Name e.g. The JFK suite  </th>
                <td>
                    <input type="text" name="roomasset_name" size="45"
                           value="<c:out value='${roomasset.roomasset_name}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>The Room number e.g. 101 or 237 </th>
                <td>
                    <input type="text" name="roomasset_number" size="45"
                           value="<c:out value='${roomasset.roomasset_number}' />"
                    />
                </td>
            </tr>

            <tr>
                <th>Room Type:</th>
                <td>
                    <select name="roomType.id">
                        <c:forEach items="${roomTypes}" var="roomType">
                            <option value="${roomType.id}">${roomType.room_name}</option>
                        </c:forEach>
                    </select>
                </td>
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