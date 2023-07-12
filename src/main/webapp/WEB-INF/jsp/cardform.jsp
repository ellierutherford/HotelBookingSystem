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

<h1>Bookings Management</h1>

<div>

    <form action="saveCard" method="post">
        <table >
            <caption>
                <h2> Your Details </h2>
            </caption>
            <tr>
                <th>Card Holder name:</th>
                <td>
                    <input type="text" name="cardHolderName" size="45"
                           value="<c:out value='${card.cardHolderName}' />"
                    />
                </td>
            </tr>

            <tr>
                <th>Card Number: </th>
                <td>
                    <input type="text" name="cardNumber" size="45"
                           value="<c:out value='${card.cardNumber}' />"
                    />
                </td>
            </tr>

            <tr>
                <th>Cvv: </th>
                <td>
                    <input type="text" name="cvv" size="45"
                           value="<c:out value='${card.cvv}' />"
                    />
                </td>
            </tr>

            <tr>
                <td colspan="2" >
                    <input type="submit" id="saveButton" value="Save" />
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>