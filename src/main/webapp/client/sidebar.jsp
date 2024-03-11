<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<div class="d-flex flex-column flex-shrink-0 p-3 float-start h-100" style="width: 200px">
    <ul class="nav nav-pills flex-column mb-auto">
        <jsp:useBean id="categories" scope="request" type="java.util.List"/>
        <c:forEach var="category" items="${categories}" varStatus="status">
            <a href="product?command=listByCategory&id=${category.categoryId}"
               class="nav-link link-dark align-items-center"></a>
        </c:forEach>
    </ul>
</div>