<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h3>
    <nav>
        <ul>
            <sec:authorize access="isAnonymous()">
                <li><a href="/register">Register as a starwood user</a></li>
            </sec:authorize>
            <sec:authorize access="isAnonymous()">
                <li><a href="/login">Login</a></li>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <li><a href="/myaccount">Member area</a></li>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <li><a href="/logout">Logout</a></li>
            </sec:authorize>
        </ul>
    </nav>
</h3>