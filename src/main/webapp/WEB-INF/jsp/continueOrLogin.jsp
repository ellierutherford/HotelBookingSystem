<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <c:url value="/css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" >
    <title>Room Addition</title>
</head>
<body>

<h1>Room Management</h1>

<div>
    <a href="/book">Continue as guest</a>
</div>

<div>
    <form action="save" method="post">
            <table>
                empty
            </table>
        </form>
</div>
</body>
</html>
