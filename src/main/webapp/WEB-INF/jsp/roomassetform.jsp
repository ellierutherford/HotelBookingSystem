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

<h1>ADD NEW ROOM ASSET</h1>
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
        <li><a href="/roomassets">BACK</a></li>
    </ul>
</h3>


<div >
    <form action="roomassets" method="post">
        <table >
            <caption>
                <h2> Add New Room Asset</h2>
            </caption>
            <tr>
                <th>Room Asset Number: </th>
                <td>
                    <input type="int" name="roomasset_number" size="45"
                           value="<c:out value='${roomasset.roomasset_number}' />"
                    />
                </td>
                <th>Room Asset Name: </th>
                <td>
                    <input type="text" name="roomasset_name" size="45"
                           value="<c:out value='${roomasset.roomasset_name}' />"
                    />
                </td>

            </tr>



            <tr>
                <th>Room Type:</th>
                <td>
                    <select name="roomTypeId">
                        <c:forEach items="${roomTypes}" var="roomType">
                            <option value="${roomType.id}">${roomType.room_name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>

            <tr>
                <td colspan="2" >
                    <input type="submit" value="Save" />
                    <!-- need roomType Out to  -->
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
