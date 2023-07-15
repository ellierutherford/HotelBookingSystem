<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Bookings Application</title>
    <c:url value="/css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" >
</head>
<body>
<%@ include file="header.jsp" %>
<div>
    <table>
        <caption>
            <h2> Booking Details</h2>
        </caption>
        <tr>
            <th>Start Date: ${booking.startDate}</th>
        </tr>
        <tr>
            <th>End Date: ${booking.endDate}</th>
        </tr>
        <tr>
            <th>Number of Guests: ${booking.numGuests}</th>
        </tr>
        <tr>
            <th>Room name: ${booking.roomasset.roomasset_name}</th>
        </tr>
        <tr>
            <th>Booking ref: ${booking.bookingRef}</th>
        </tr>
    </table>
</div>
<div>

    <form action="bookingForm" method="post">
        <table >
            <caption>
                <h2> Your Details </h2>
            </caption>
            <tr>
                <th>First Name:</th>
                <td>
                    <input type="text" name="guest_first_name" size="45"
                           value="<c:out value='${customer.guest_first_name}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Last Name: </th>
                <td>
                    <input type="text" name="guest_last_name" size="45"
                           value="<c:out value='${customer.guest_last_name}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Phone: </th>
                <td>
                    <input type="text" name="guest_phone_number" size="45"
                           value="<c:out value='${customer.guest_phone_number}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Address: </th>
                <td>
                    <input type="text" name="guest_address" size="10"
                           value="<c:out value='${customer.guest_address}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Guest email: </th>
                <td>
                    <input type="text" name="guest_email" size="20"
                           value="<c:out value='${customer.guest_email}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" >
                    <input type="submit" id="saveButton" value="Save" />
                </td>
            </tr>
        </table>
    </form>

</div>

</body>
</html>