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
<%@ include file="header.jsp" %>
<h1>Register</h1>

<div>
    <form action="registerCredentials" method="post">

        <table >
            <caption>
                <h2> Register with us today for great discounts </h2>
            </caption>
            <tr>
                <th>Username</th>
                <td>
                    <input type="hidden" name="user_id" value="${customer.id}">
                    <input type="text" name="username" size="45"
                           value="<c:out value='${user.username}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Password </th>
                <td>
                    <input type="password" name="password" size="45"
                           value="<c:out value='${user.password}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" >
                    <input type="submit" id="saveButton" value="Register" />
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>