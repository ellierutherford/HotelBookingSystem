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
<h2><a href="/newcard">Add a new card</a></h2>
<h1>Your cards</h1>

        <c:forEach var="card" items="${cards}" varStatus="loop">
            <div>
        <table >
            <tr>
                <th>Card Holder name:</th>
                <td>
                    <c:out value='${card.cardHolderName}' />
                </td>
            </tr>

            <tr>
                <th>Card Number: </th>
                <td>
                    <c:out value='${card.cardNumber}' />
                </td>
            </tr>

            <tr>
                <th>Cvv: </th>
                <td>
                    <c:out value='${card.cvv}' />
                </td>
            </tr>

            <tr>
                <th>Expiry date: </th>
                <td>
                    <c:out value='${card.expiry}' />
                </td>
            </tr>
            <tr>
                <td><a href="/editcardform?cardId=${card.id}">Modify card</a></td>
            </tr>
        </table>
            </div>
        </c:forEach>
</body>
</html>