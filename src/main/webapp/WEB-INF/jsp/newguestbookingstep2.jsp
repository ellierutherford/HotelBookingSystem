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
    <form action="newguestbookingsstep2" method="post">
        <table>
            <caption>
                <h3>PICK YOUR ROOM</h3>
            </caption>


            <th>Pick your room type Availability will come later by your previously selected date</th>
            <td>
                <select name="listroomTypes">
                    <c:forEach items="${listroomTypes}" var="listroomTypes">
                        <option value="${listroomTypes.id}">${listroomTypes.room_name}</option>
                    </c:forEach>
                </select>
            </td>





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
