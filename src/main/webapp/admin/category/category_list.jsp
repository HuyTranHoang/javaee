<%@include file="../../common/taglib.jsp" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<jsp:useBean id="categories" scope="request" type="java.util.List"/>

<div class="d-flex align-items-center justify-content-between mt-3">
    <div class="d-flex align-items-center">
        <h4 class="me-3 gradient-text">Category List</h4>
        <form class="position-relative me-3" action="${contextPath}/admin/categories">
            <input name="searchString" id="search" value="${param.searchString}"
                   class="form-control" style="padding-right: 40px" type="text" placeholder="search..">
            <button type="submit" class="btn position-absolute" style="top: 8%; right: 0">
                <i class="fa-solid fa-magnifying-glass"></i>
            </button>
        </form>

        <c:if test="${not empty param.searchString}">
            <a href="${contextPath}/admin/categories" class="btn btn-danger btn-sm">Clear</a>
        </c:if>
    </div>

    <a href="${contextPath}/admin/categories/new" class="btn btn-success btn-sm">New Category</a>
</div>

<div class="d-flex justify-content-center py-3">
    <table class="table table-striped">
        <thead>
        <tr class="table-success">
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col" style="width: 20%">Actions</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${categories}" var="category" varStatus="iterationCount">
            <jsp:useBean id="category" type="com.ebook.entity.Category"/>
            <tr>
                <th scope="row">${iterationCount.index + 1}</th>
                <td>${category.name}</td>
                <td>
                    <a href="${contextPath}/admin/categories/edit/${category.categoryId}"
                       class="fa-light fa-pen-to-square text-warning text-decoration-none me-3">
                    </a>

                    <i class="fa-light fa-trash text-danger delete-btn"
                       style="cursor: pointer;"
                       data-id="${category.categoryId}"
                       data-bs-toggle="modal"
                       data-bs-target="#confirmModal">
                    </i>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


<form action="${contextPath}/admin/categories/delete" method="post" id="deleteForm">
    <input type="hidden" id="deleteCategoryId" name="deleteCategoryId">
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
                Are you sure you want to delete this category?
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
        const deleteCategoryId = $('#deleteCategoryId');
        const confirmButton = $('#confirmModal .btn-danger');

        deleteBtns.each((index, btn) => {
            $(btn).on('click', function () {
                const id = $(this).data('id');
                deleteCategoryId.val(id);
            });
        });

        confirmButton.on('click', function () {
            deleteForm.trigger('submit');
        });
    }
</script>
