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
            <form class="d-flex">
                Welcome, Admin
                <span class="mx-3"> | </span>
                Logout
            </form>
        </div>
    </div>
</nav>
