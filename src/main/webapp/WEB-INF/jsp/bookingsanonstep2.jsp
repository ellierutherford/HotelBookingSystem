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
<div>
    <h1>Transaction STEP 2</h1>
    <p>Hi ${guest.guest_first_name},</p>
    <p>You seek a ${guest.guest_first_name},</p>
    <p>Booking dates: ${booking.startDate} </p>
    <p>First Name: ${booking.leadguest_first_name} + "");</p>



    <form action="/bookingsanonstep2" method="post">


        < ! ---  we must hold these vars --- >
        <input type="hidden" name="startDate" value="${booking.startDate}" />
        <input type="hidden" name="endDate" value="${booking.endDate}" />
        <input type="hidden" name="leadguest_first_name" value="${booking.leadguest_first_name}" />
        <input type="hidden" name="leadguest_last_name" value="${booking.leadguest_last_name}" />


        <th>Room Asset Type:</th>
        <td>
            <select name="listroomType">
                <c:forEach items="${listroomTypes}" var="listroomType">
                    <option value="${listroomType.id}">${listroomType.room_name}</option>
                </c:forEach>
            </select>
        </td>

        <! ------- types in type out    -------------- >



        <input type="submit" value="OK" />
    </form>

</div>
<h3>
    <ul class="menu">
        <li><a href="/list">Arrange Your Bookings (and everyone else's too), until #task Login Security</a></li>
    </ul>
</h3>

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

</body>
</html>
