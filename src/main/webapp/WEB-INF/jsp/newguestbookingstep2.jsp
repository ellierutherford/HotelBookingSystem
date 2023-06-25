<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>WELCOME NEW GUEST TO THE BEST MOST INSECURE HOTEL IN THE WORLD</title>
    <c:url value="/css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet">
</head>
<body>

<div>
    <h1>Transaction Completed</h1>

    </head>
    <body>
    <h1>Transaction Completed</h1>
    <p>The transaction started in the previous step has been successfully completed.</p>

    <form action="/newguestbookingsstep2" method="post">
        <input type="submit" value="OK" />
    </form>
    </body>
    </html>
