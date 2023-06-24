<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>WELCOME NEW GUEST TO THE BEST MOST INSECURE HOTEL IN THE WORLD</title>
    <c:url value="/css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" >
</head>
<body>
<h1>Marketing Slogan here about something at the Heart of Something else</h1>
<h2>We think you're wonderful, but have no clue who you are.</h2>
<h2>So Get one of our Available rooms</h2>

<div>
    <form action="newguestbookings" method="post">
        <table>
            <caption>
                <h3>Add New Booking. Rest assured the Book-a-lator 3000 has no idea what a free room is yet, but that's no impediment, it soon will</h3>
            </caption>
            <tr>
                <th>Lead Guest:</th>
                <td>
                    <input type="text" name="booking_name" size="45" />
                </td>
            </tr>

            <!-- Add fields for new guest details -->
            <tr>
                <th>Guest First Name:</th>
                <td>
                    <input type="text" name="guest_first_name" size="45" />
                </td>
            </tr>
            <tr>
                <th>Guest Last Name:</th>
                <td>
                    <input type="text" name="guest_last_name" size="45" />
                </td>
            </tr>

            <!-- Add more fields for additional guest details if needed -->

            <tr>
                <th>Booking Start Date:</th>
                <td>
                    <input type="date" name="startDate" />
                </td>
            </tr>

            <tr>
                <th>Booking End Date:</th>
                <td>
                    <input type="date" name="endDate" />
                </td>
            </tr>

            <tr>
                <th>Ref:</th>
                <td>
                    <input type="text" name="bookingRef" size="45" />
                </td>
            </tr>

            <tr>
                <th>Number of Checked Guests:</th>
                <td id="guestCount">0</td>
            </tr>

            <tr>
                <td colspan="2">
                    <input type="submit" id="saveButton" value="Save"  />
                </td>
            </tr>
        </table>
    </form>

</div>
<h3>
    <ul class="menu">
        <li><a href="/list">Arrange Your Bookings (and everyone else's too), until #task Login Security</a></li>
    </ul>
</h3>
</body>
</html>