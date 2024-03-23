<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>



<h3 class="mt-2">Product Details</h3>


<div class="card mb-3">
    <jsp:useBean id="product"  scope="request" type="com.ebook.entity.Product"/>
    <div class="row g-0">
        <div class="col-md-4 p-3">
            <img src="data:image/png;base64, ${product.base64Image}" class="card-img-top" alt="${product.name}">
        </div>
        <div class="col-md-8">
            <div class="card-body d-flex flex-column">
                <h4 class="gradient-text">${product.name}</h4>
                <p class="mb-3">${product.description}</p>

                <div class="mt-auto">
                    <fmt:formatNumber value="${product.price}" type="currency" currencySymbol="$" var="formattedPrice"/>
                    <h3 class="text-warning mb-3">${formattedPrice}</h3>
                    <a href="${contextPath}/product" class="btn btn-success w-100">Back to list</a>
                </div>
            </div>
        </div>
    </div>
</div>
