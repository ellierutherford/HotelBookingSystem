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
    <a href="/new">Add New Room</a>
    &nbsp;&nbsp;&nbsp;
    <a href="/list">List All Rooms</a>

</h2>

<div >
    <form action="rooms" method="post">
        <table >
            <caption>
                <h2> Add New Room</h2>
            </caption>
            <tr>
                <th>Room Name: </th>
                <td>
                    <input type="text" name="room_name" size="45"
                           value="<c:out value='${room.room_name}' />"
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
