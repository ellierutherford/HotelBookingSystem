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

<div>
    <form action="selectCard" method="post">
        <c:forEach items="${cards}" var="creditCard">
            <label>
                <input type="radio" name="selectedCardNumber" value="${creditCard.cardNumber}">
                    ${creditCard.cardNumber} - ${creditCard.cardHolderName}
            </label><br>
        </c:forEach>
        <input type="submit" value="Submit">
    </form>
</div>
</body>
</html>