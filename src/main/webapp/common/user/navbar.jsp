<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="<c:url value="/admin" />">
            <img src="<c:url value="/assets/images/gura.png"/>" width="33px" alt="logo image">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page"
                       href="${contextPath}/index">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page"
                       href="${contextPath}/admin">Admin</a>
                </li>
            </ul>
            <form class="d-flex me-5 mb-0" role="search">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>

            <div class="d-flex mb-0">
                <a class="btn btn-primary me-3" href="${pageContext.request.contextPath}/login">Login</a>
                <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/register">Sign up</a>
            </div>

<%--            <c:choose>--%>
<%--                <c:when test="${empty sessionScope.user}">--%>
<%--                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/login">Login</a>--%>
<%--                </c:when>--%>
<%--                <c:otherwise>--%>
<%--                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/logout">Logout</a>--%>
<%--                </c:otherwise>--%>
<%--            </c:choose>--%>
        </div>
    </div>
</nav>
