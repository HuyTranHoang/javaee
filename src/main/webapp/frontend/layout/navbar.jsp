<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="d-flex justify-content-between align-items-center">
    <%--    <img src="<%= request.getContextPath() %>/images/gura.jpg" alt="logo image" style="width: 150px">--%>
<%--    <img src="<c:url value="/images/gura.jpg"/>" width="100px" alt="logo image">--%>
        <img src="${pageContext.request.contextPath}/images/gura.jpg" width="100px" alt="logo image">


        <div class="d-flex justify-content-end vw-50">
        <div class="position-relative">
            <input class="form-control me-3" name="keyword" type="text" size="50" style="padding-left: 50px"
                   placeholder="Search...">
            <i class="fa-solid fa-magnifying-glass position-absolute" style="top: 25%; left: 12px"></i>
        </div>

        <button class="btn btn-primary me-5">Search</button>

        <a href="#" class="text-decoration-none text-dark">
            <i class="fa-solid fa-cart-shopping fa-2x me-3"></i>
        </a>

        <input class="btn btn-outline-secondary me-3" type="button" value="Login">
        <input class="btn btn-outline-secondary me-3" type="button" value="Sign up">
    </div>
</div>