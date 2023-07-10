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
    <form action="registration" method="post">

        <table >
            <caption>
                <h2> Register with us today for great discounts </h2>
            </caption>
            <tr>
                <th>Username</th>
                <td>
                    <input type="text" name="username" size="45"
                           value="<c:out value='${user.username}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Password </th>
                <td>
                    <input type="text" name="password" size="45"
                           value="<c:out value='${user.password}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Roles </th>
                <td>
                    <input type="text" name="roles" size="45"
                           value="<c:out value='${user.roles}' />"
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