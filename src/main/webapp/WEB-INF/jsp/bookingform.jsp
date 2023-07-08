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

<h1>Bookings Management</h1>

<div>
    <table>
        <caption>
            <h2> Booking Details</h2>
        </caption>
        <tr>
            <th>Start Date: ${startDate}</th>
        </tr>
        <tr>
            <th>End Date: ${endDate}</th>
        </tr>
        <tr>
            <th>Number of Guests: ${numGuests}</th>
        </tr>
        <tr>
            <th>Room ID: ${roomId}</th>
        </tr>
    </table>
</div>
<div>


    <form action="bookingsel" method="post">
        <table >
            <caption>
                <h2> Your Details </h2>
            </caption>
            <tr>
                <th>Id: </th>
                <td>
                    <input type="text" name="Last name" size="45"
                           value="<c:out value='${guest.id}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>First Name:</th>
                <td>
                    <input type="text" name="First name" size="45"
                           value="<c:out value='${guest.guest_first_name}' />"
                    />
                </td>
            </tr>

            <tr>
                <th>Last Name: </th>
                <td>
                    <input type="text" name="Last name" size="45"
                           value="<c:out value='${guest.guest_last_name}' />"
                    />
                </td>
            </tr>

            <tr>
                <th>Phone: </th>
                <td>
                    <input type="text" name="Last name" size="45"
                           value="<c:out value='${guest.guest_phone_number}' />"
                    />
                </td>
            </tr>

            <tr>
                <th>Address: </th>
                <td>
                    <input type="text" name="Address" size="10"
                           value="<c:out value='${guest.guest_address}' />"
                    />
                </td>
            </tr>

            <tr>
                <th>Guest email: </th>
                <td>
                    <input type="text" name="Guest email" size="20"
                           value="<c:out value='${guest.guest_email}' />"
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