<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Bookings Store Application</title>
    <c:url value="/css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" >
</head>
<body>
<h1>Bookings Management</h1>
<h2>
    <a href="/new">Add New Booking</a>
    &nbsp;&nbsp;&nbsp;
    <a href="/list">List All Bookings</a>

</h2>
<div>
    <form action="save" method="post">
        <table>
            <caption>
                <h2>
                    Edit Booking
                </h2>
            </caption>
            <input type="hidden" name="id" value="<c:out value='${booking.id}' />"  />
            <tr>
                <th>Title: </th>
                <td>
                    <input type="text" name="booking_name" size="45"
                           value="<c:out value='${booking.booking_name}' />"
                    />
                </td>
            </tr>


            <tr>
                <th>Assigned Guests: </th>
                <c:forEach var="guest" items="${guests}">
                    <td>
                        <input type="text" name="name" size="45"
                               value="<c:out value='${guest.guest_first_name} ${guest.guest_last_name}'/>" readonly
                        />
                    </td>

                </c:forEach>
            </tr>
            <tr>
                <th>All Guests: </th>
                <c:forEach var="listGuests" items="${listGuests}">
                    <td>
                        <label>
                            <input type="checkbox" name="listGuests" value="<c:out value='${listGuests.id}'/>">
                            <c:out value='${listGuests.guest_first_name} ${listGuests.guest_last_name}'/>
                        </label>
                    </td>
                </c:forEach>
            </tr>
            <tr>
                <th>REF: </th>
                <td>
                    <input type="text" name="bookRef" size="45"
                           value="<c:out value='${booking.bookRef}' />"
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