<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Rooms Store Application</title>
    <c:url value="/css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" >
</head>
<body>

<h1>Rooms Management</h1>
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
        <li><a href="/rooms">BACK</a></li>
    </ul>
</h3>


<div >
    <form action="rooms" method="post">
        <table >
            <caption>
                <h2> Add New Room TYPE</h2>
            </caption>
            <tr>
                <th>Room Type Name: </th>
                <td>
                    <input type="text" name="room_name" size="45"
                           value="<c:out value='${room.room_name}' />"

                    />
                </td>
            </tr>
            <tr>
                <th>Room Type Rate: </th>
                <td>
                    <input type="text" name="night_rate" size="45"
                           value="<c:out value='${room.night_rate}' />"

                    />
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
