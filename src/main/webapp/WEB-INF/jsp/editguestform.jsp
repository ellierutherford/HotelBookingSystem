<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Books Store Application</title>
    <c:url value="/css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" >
</head>
<body>
<h1>Guest Management</h1>
<h2>
    &nbsp;&nbsp;&nbsp;
    <a href="/listguests">List All Guests</a>

</h2>
<div>
    <form action="save" method="post">
        <table>
            <caption>
                <h2>
                    Edit Guest
                </h2>
            </caption>
            <input type="hidden" name="id" value="<c:out value='${guest.id}' />"  />
            <tr>
                <th>The First Name: </th>
                <td>
                    <input type="text" name="guest_first_name" size="45"
                           value="<c:out value='${guest.guest_first_name}' />"
                    />
                </td>
            </tr>


            <tr>
                <th>Last: </th>
                <td>
                    <input type="text" name="guest_last_name" size="45"
                           value="<c:out value='${guest.guest_last_name}' />"
                    />
                </td>
            </tr>

            <th>Books: </th>
            <c:forEach var="booking" items="${bookings}">
                <td>
                    <input type="text" name="name" size="45"
                           value="<c:out value='${booking.booking_name} ${booking.bookRef}'/>"
                    />
                </td>

            </c:forEach>
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