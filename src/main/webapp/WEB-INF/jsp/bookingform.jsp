<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Bookings Application</title>
    <c:url value="/css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" >
    <script>
        function countCheckedCheckboxes() {
            var checkboxes = document.getElementsByName("guests");
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
                <th>Title: </th>
                <td>
                    <input type="text" name="booking_name" size="45"
                           value="<c:out value='${booking.booking_name}' />"
                    />
                </td>
            </tr>

            <tr>
                <th>Guests: </th>
                <c:forEach var="guest" items="${listGuests}">
                    <td>
                        <label>
                            <input type="checkbox" name="guests" value="<c:out value='${guest.id}'/>" onchange="countCheckedCheckboxes()">
                            <c:out value='${guest.guest_first_name} ${guest.guest_last_name}'/>
                        </label>
                    </td>
                </c:forEach>
            </tr>


            <tr>
                <th>Ref: </th>
                <td>
                    <input type="text" name="bookRef" size="45"
                           value="<c:out value='${booking.bookRef}' />"
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
</div>
</body>
</html>
