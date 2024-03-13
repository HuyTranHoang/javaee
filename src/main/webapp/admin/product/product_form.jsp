<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="mt-3">
    <jsp:useBean id="mode" class="java.lang.String" scope="request"/>
    <c:if test="${mode == 'create'}">
        <h4 class="mb-4">Create new product</h4>
        <c:set var="actionPath" value="${contextPath}/admin/products/insert"/>
    </c:if>
    <c:if test="${mode == 'edit'}">
        <h4 class="mb-4">Update product</h4>
        <c:set var="actionPath" value="${contextPath}/admin/products/update"/>
    </c:if>

    <form class="bg-body-secondary rounded p-4" id="productForm"
          style="width: 400px" action="${actionPath}" method="post">

        <c:if test="${not empty sessionScope.error}">
            <div class="alert alert-danger">${sessionScope.error}</div>
            <% session.removeAttribute("error"); %>
        </c:if>

        <jsp:useBean id="product" class="com.ebook.entity.Product" scope="request"/>

        <input type="hidden" name="productId" value="${product.productId}"/>

        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" class="form-control" name="name" id="name" placeholder="product name" value="${product.name}">
        </div>

        <div class="mb-3">
            <label for="author" class="form-label">Author</label>
            <input type="text" class="form-control" name="author" id="author" placeholder="author" value="${product.author}">
        </div>

        <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <textarea class="form-control" name="description" id="description" rows="3">${product.description}</textarea>
        </div>

        <div class="mb-3">
            <label for="isbn" class="form-label">Isbn</label>
            <input type="text" class="form-control" name="isbn" id="isbn" placeholder="author" value="${product.isbn}">
        </div>

        <div class="mb-3">
            <label for="image" class="form-label">Image</label>
            <input type="file" class="form-control" name="image" id="image">
        </div>

        <div class="mb-3">
            <label for="price" class="form-label">Price</label>
            <input type="text" class="form-control" name="price" id="price" placeholder="price" value="${product.price}">
        </div>

        <div class="mb-3">
            <label for="publishDate" class="form-label">Publish date</label>
            <input type="date" class="form-control" name="publishDate" id="publishDate" value="${product.publishDate}">
        </div>

        <div class="mb-3">
            <label for="categoryId" class="form-label">Category</label>
            <select class="form-select" name="categoryId" id="categoryId">
                <jsp:useBean id="categories" scope="request" type="java.util.List"/>
                <c:forEach items="${categories}" var="category">
                    <option value="${category.categoryId}" <c:if test="${category.categoryId == product.category.categoryId}">selected</c:if>>${category.name}</option>
                </c:forEach>
            </select>
        </div>

        <div class="d-flex justify-content-center">
            <button type="submit" class="btn btn-primary btn-sm me-3">Submit</button>
            <a href="${contextPath}/admin/products/" class="btn btn-secondary btn-sm">Cancel</a>
        </div>
    </form>
</div>

<script type="text/javascript">
    function initializeScript() {
        const productForm = $('#productForm');

        productForm.validate({
            rules: {
                name: {
                    required: true
                },
            },
            messages: {
                name: {
                    required: "Please enter full name"
                }
            },
            errorElement: 'div',
            errorClass: 'invalid-feedback',
            highlight: function(element, errorClass, validClass) {
                $(element).addClass('is-invalid');
            },
            unhighlight: function(element, errorClass, validClass) {
                $(element).removeClass('is-invalid');
            },
            errorPlacement: function(error, element) {
                error.insertAfter(element);
            }
        });

        productForm.on("submit", function (e) {
            if (productForm.valid()) {
                return true;
            }
            e.preventDefault();
        });
    }
</script>
