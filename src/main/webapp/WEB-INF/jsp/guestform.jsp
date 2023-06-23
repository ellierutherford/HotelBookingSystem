<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Guests Store Application</title>
    <c:url value="/css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" >
</head>
<body>

<h1>Guests Management</h1>
<h2>
    <a href="/new">Add New Guest</a>
    &nbsp;&nbsp;&nbsp;
    <a href="/guests">List All Guests</a>

</h2>

<div >
    <form action="guests" method="post">
        <table >
            <caption>
                <h2> Add New Guest</h2>
            </caption>
            <tr>
                <th>Guest 1st Name: </th>
                <td>
                    <input type="text" name="guest_first_name" size="45"
                           value="<c:out value='${guest.guest_first_name}' />"
                    />
                </td>
            </tr>

            <tr>
                <th>Guest 2st Name:</th>
                <td>
                    <input type="text" name="guest_last_name" size="45"
                           value="<c:out value='${guest.guest_last_name}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" >
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
