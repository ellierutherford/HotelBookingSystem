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
    <h1>Transaction Completed</h1>
    <p>Hi ${guest.guest_first_name},</p>
    <p>The transaction started in the previous step has been successfully completed.</p>
    <p>Booking dates: ${booking.startDate} </p>

    <form action="/newguestbookingsstep2" method="post">
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
