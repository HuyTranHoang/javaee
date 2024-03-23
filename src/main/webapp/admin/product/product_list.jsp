<%@include file="../../common/taglib.jsp" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="actionPath" value="${contextPath}/admin/products" />
<jsp:useBean id="products" scope="request" type="java.util.List"/>

<div class="d-flex align-items-center justify-content-between mt-3">
    <div class="d-flex align-items-center">
        <h4 class="me-3 gradient-text">Product List</h4>
        <form class="position-relative me-3" action="${actionPath}">
            <input name="searchString" id="search" value="${param.searchString}"
                   class="form-control" style="padding-right: 40px" type="text" placeholder="search..">
            <button type="submit" class="btn position-absolute" style="top: 8%; right: 0">
                <i class="fa-solid fa-magnifying-glass"></i>
            </button>
        </form>

        <c:if test="${not empty param.searchString}">
            <a href="${actionPath}" class="btn btn-danger btn-sm">Clear</a>
        </c:if>
    </div>

    <a href="${actionPath}/new" class="btn btn-success btn-sm">New Product</a>
</div>

<c:if test="${not empty sessionScope.error}">
    <div class="alert alert-danger mt-3">${sessionScope.error}</div>
    <% session.removeAttribute("error"); %>
</c:if>
<div class="d-flex justify-content-center py-3">
    <table class="table table-striped">
        <thead>
        <tr class="table-success">
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Category</th>
            <th scope="col">Author</th>
            <th scope="col">Image</th>
            <th scope="col">Price</th>
            <th scope="col" style="width: 20%">Actions</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${products}" var="product" varStatus="iterationCount">
            <jsp:useBean id="product" type="com.ebook.entity.Product"/>
            <tr>
                <th scope="row">${iterationCount.index + 1}</th>
                <td>${product.name}</td>
                <td>${product.category.name}</td>
                <td>${product.author}</td>
                <td>
                    <img src="data:image/png;base64, ${product.base64Image}"
                         style="width: 100px"
                         alt="Product image">
                </td>
                <td>${product.price}</td>
                <td>
                    <a href="${actionPath}/edit/${product.productId}"
                       class="fa-light fa-pen-to-square text-warning text-decoration-none me-3">
                    </a>

                    <i class="fa-light fa-trash text-danger delete-btn"
                       style="cursor: pointer;"
                       data-id="${product.productId}"
                       data-bs-toggle="modal"
                       data-bs-target="#confirmModal">
                    </i>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


<form action="${actionPath}/delete" method="post" id="deleteForm">
    <input type="hidden" id="deleteProductId" name="deleteProductId">
</form>

<!-- Modal -->
<div class="modal fade" id="confirmModal" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Confirm delete</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Are you sure you want to delete this product?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger">Delete it</button>
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
            </div>
        </div>
    </div>
</div>


<script defer type="text/javascript">
    function initializeScript() {

        const deleteBtns = $('.delete-btn');
        const deleteForm = $('#deleteForm');
        const deleteProductId = $('#deleteProductId');
        const confirmButton = $('#confirmModal .btn-danger');

        deleteBtns.each((index, btn) => {
            $(btn).on('click', function () {
                const id = $(this).data('id');
                deleteProductId.val(id);
            });
        });

        confirmButton.on('click', function () {
            deleteForm.trigger('submit');
        });
    }
</script>
