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

<h1>
    Select a card to use for this booking
</h1>
<body>


<div>
    <form action="selectCard" method="post">
        <c:forEach items="${cards}" var="creditCard">
            <label>
                <input type="radio" name="selectedCardNumber" value="${creditCard.cardNumber}">
                    ${creditCard.cardNumber} - ${creditCard.cardHolderName}
            </label><br>
        </c:forEach>

    </div>
        <div>
            <input type="submit" value="Submit">
        </div>
</form>
</body>
</html>