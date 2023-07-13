<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>



<sec:authorize access="isAuthenticated()">
    Welcome, <sec:authentication property="principal.username" />
</sec:authorize>

<h1>Welcome to our hotel!</h1>
<h2>
    <ul class="menu">
        <sec:authorize access="isAnonymous()">
            <li><a href="/login">Login</a></li>
        </sec:authorize>
        <li><a href="/search">Search for availability</a></li>
        <sec:authorize access="isAnonymous()">
            <li><a href="/register">Register as a starwood user</a></li>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <li><a href="/logout">Logout</a></li>
        </sec:authorize>
    </ul>
</h2>