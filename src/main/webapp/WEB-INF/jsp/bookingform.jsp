<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Bookings Application</title>
    <c:url value="/css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" >
    <script>
        function countCheckedCheckboxes() {
            var checkboxes = document.getElementsByName("guestIds");
            var checkedCount = 0;
            for (var i = 0; i < checkboxes.length; i++) {
                if (checkboxes[i].checked) {
                    checkedCount++;
                }
            }
            document.getElementById("guestCount").innerText = checkedCount;
            var saveButton = document.getElementById("saveButton");
            if (checkedCount === 0) {
                saveButton.disabled = true;
            } else {
                saveButton.disabled = false;
            }
        }
    </script>
</head>
<body>

<h1>Bookings Management</h1>
<h2>
    <a href="/new">Add New Booking</a>
    &nbsp;&nbsp;&nbsp;
    <a href="/list">List All Bookings</a>
</h2>

<div >
    <form action="bookings" method="post">
        <table >
            <caption>
                <h2> Add New Booking</h2>
            </caption>
            <tr>
                <th>First Name:</th>
                <td>
                    <input type="text" name="leadguest_first_name" size="45"
                           value="<c:out value='${booking.leadguest_first_name}' />"
                    />
                </td>
            </tr>

            <tr>
                <th>Last Name: </th>
                <td>
                    <input type="text" name="leadguest_last_name" size="45"
                           value="<c:out value='${booking.leadguest_last_name}' />"
                    />
                </td>
            </tr>

            <tr>
                <th>Booking Start Date:</th>
                <td>
                    <input type="date" name="startDate" id="startDate" />
                </td>
            </tr>

            <tr>
                <th>Booking End Date:</th>
                <td>
                    <input type="date" name="endDate" id="endDate" />
                </td>
            </tr>

            <th>Room Asset Type:</th>
            <td>
                <select name="listroomType">
                    <c:forEach items="${listroomTypes}" var="listroomType">
                        <option value="${listroomType.id}">${listroomType.room_name}</option>
                    </c:forEach>
                </select>
            </td>

            <tr>
                <th>Ref: </th>
                <td>
                    <input type="text" name="bookingRef" size="45"
                           value="<c:out value='${booking.bookingRef}' />"
                    />
                </td>
            </tr>

            <tr>
                <th>Number of Checked Guests: </th>
                <td id="guestCount">0</td>
            </tr>

            <tr>
                <td colspan="2" >
                    <input type="submit" id="saveButton" value="Save" disabled />
                </td>
            </tr>
        </table>
    </form>
    <script>
        // JavaScript code to set default values for the date fields
        //split out the date
        var today = new Date().toISOString().split('T')[0];
        var future = new Date();
        var nextweek = new Date(future.getTime() + 7 * 24 * 3600000);
        var nextweekFormatted = nextweek.toISOString().split('T')[0];
        document.getElementById("startDate").value = today;
        document.getElementById("endDate").value = nextweekFormatted;
    </script>
</div>
</
