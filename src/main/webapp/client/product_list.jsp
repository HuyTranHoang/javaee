<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<div class="row row-cols-1 row-cols-md-3 g-4 mt-1">
    <jsp:useBean id="products" scope="request" type="java.util.List"/>
    <c:forEach var="product" items="${products}">
        <jsp:useBean id="product" type="com.ebook.entity.Product"/>
        <div class="col">
            <div class="card h-100">
                <img src="data:image/png;base64, ${product.base64Image}" class="card-img-top" alt="${product.name}">
                <div class="card-body d-flex flex-column">
                    <h4 class="gradient-text">${product.name}</h4>

                    <p class="text-ellipsis mb-3">${product.description}</p>

                    <div class="mt-auto">
                        <fmt:formatNumber value="${product.price}" type="currency" currencySymbol="$" var="formattedPrice"/>
                        <p class="text-warning">${formattedPrice}</p>
                        <a href="${contextPath}/product/detail?productId=${product.productId}" class="btn btn-success w-100">View</a>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<c:if test="${empty products}">
    <div class="alert alert-warning mt-3" role="alert">
        No products found
    </div>
</c:if>