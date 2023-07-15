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
    <form action="bookingsanon" method="post">
        <table>
            <caption>
                <h3>Add New Booking. The latest Book-a-lator 3000 will give you an available room or crash horribly.</h3>
                <h4>There's no point in complaining, because all our rooms cost nothing and we can't take payments yet anyway</h4>
            </caption>
            <tr>
                <th>Lead Guest:</th>
                <td>
                    <input type="text" name="leadguest_first_name" size="45" />
                </td>
            </tr>

            <tr>
                <th>Lead Last Name:</th>
                <td>
                    <input type="text" name="leadguest_last_name" size="45" />
                </td>
            </tr>

            <!-- Add more fields for additional customer details if needed -->

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
                    <input type="submit" id="saveButton" value="NEXT STEP....."  />
                </td>
            </tr>
        </table>
    </form>

</div>


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
