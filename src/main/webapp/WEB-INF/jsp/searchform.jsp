<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Bookings Application</title>
    <c:url value="/css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" >
</head>
<body>

<%@ include file="header.jsp" %>

<div >
    <form action="availability" method="post">
        <table >
            <caption>
                <h2> Search for availability </h2>
            </caption>

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
                <th>Number of guests: </th>
                <td>
                    <input type="number" name="numGuests" id="numGuests" />
                </td>
            </tr>

            <tr>
                <td colspan="2" >
                    <input type="submit" id="search" value="Search"/>
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
</body>
</html>