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

<h1>Register</h1>

<div>
    <form action="registerDetails" method="post">

        <table >
            <caption>
                <h2> Register with us today for great discounts </h2>
            </caption>
            <tr>
                <th>First name</th>
                <td>
                    <input type="text" name="firstName" size="45"
                           value="<c:out value='${user.firstName}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Surname </th>
                <td>
                    <input type="text" name="lastName" size="45"
                           value="<c:out value='${user.lastName}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Address </th>
                <td>
                    <input type="text" name="address" size="45"
                           value="<c:out value='${user.address}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Phone </th>
                <td>
                    <input type="text" name="phone" size="45"
                           value="<c:out value='${user.phone}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Email </th>
                <td>
                    <input type="text" name="email" size="45"
                           value="<c:out value='${user.email}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" >
                    <input type="submit" id="saveButton" value="Next" />
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>