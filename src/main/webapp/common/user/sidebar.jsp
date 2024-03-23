<%@include file="../taglib.jsp" %>


<div class="d-flex flex-column flex-shrink-0 p-3 float-start h-100" style="width: 300px">
    <ul class="nav nav-pills flex-column mb-auto">
        <jsp:useBean id="categories" scope="request" type="java.util.List"/>
        <c:forEach items="${categories}" var="category" varStatus="status">
            <jsp:useBean id="category" type="com.ebook.entity.Category"/>
            <li class="nav-item mb-3">
                <a href="product?id=${category.categoryId}" class="nav-link align-items-center">
                    <c:choose>
                        <c:when test="${fn:toLowerCase(category.name) == 'comic'}">
                            <img src="<c:url value="/assets/images/comic.svg" />" alt="comic" class="me-2" style="width: 50px; height: 30px">
                        </c:when>
                        <c:when test="${fn:toLowerCase(category.name) == 'programming'}">
                            <img src="<c:url value="/assets/images/programming.svg" />" alt="programming" class="me-2" style="width: 50px; height: 30px">
                        </c:when>
                        <c:when test="${fn:toLowerCase(category.name) == 'romantic'}">
                            <img src="<c:url value="/assets/images/romantic.svg" />" alt="romantic" class="me-2" style="width: 50px; height: 30px">
                        </c:when>
                        <c:when test="${fn:toLowerCase(category.name) == 'fiction'}">
                            <img src="<c:url value="/assets/images/fiction.svg" />" alt="fiction" class="me-2" style="width: 50px; height: 30px">
                        </c:when>
                        <c:otherwise>
                            <img src="<c:url value="/assets/images/book.svg" />" alt="other" >
                        </c:otherwise>
                    </c:choose>
                        ${category.name}
                </a>
            </li>

            <c:if test="${status.last}">
                <hr>
            </c:if>
        </c:forEach>
    </ul>

</div>