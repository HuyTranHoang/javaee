<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="mt-3">
    <jsp:useBean id="mode" class="java.lang.String" scope="request"/>
    <c:if test="${mode == 'create'}">
        <h4 class="mb-4">Create new category</h4>
        <c:set var="actionPath" value="${contextPath}/admin/categories/insert"/>
    </c:if>
    <c:if test="${mode == 'edit'}">
        <h4 class="mb-4">Update category</h4>
        <c:set var="actionPath" value="${contextPath}/admin/categories/update"/>
    </c:if>

    <form class="bg-body-secondary rounded p-4" id="categoryForm"
          style="width: 400px" action="${actionPath}" method="post">

        <c:if test="${not empty sessionScope.error}">
            <div class="alert alert-danger">${sessionScope.error}</div>
            <% session.removeAttribute("error"); %>
        </c:if>

        <jsp:useBean id="category" class="com.ebook.entity.Category" scope="request"/>

        <input type="hidden" name="categoryId" value="${category.categoryId}"/>

        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" class="form-control" name="name" id="name" placeholder="category name" value="${category.name}">
        </div>

        <div class="d-flex justify-content-center">
            <button type="submit" class="btn btn-primary btn-sm me-3">Submit</button>
            <a href="${contextPath}/admin/categories/" class="btn btn-secondary btn-sm">Cancel</a>
        </div>
    </form>
</div>

<script type="text/javascript">
    function initializeScript() {
        const categoryForm = $('#categoryForm');

        categoryForm.validate({
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

        categoryForm.on("submit", function (e) {
            if (categoryForm.valid()) {
                return true;
            }
            e.preventDefault();
        });
    }
</script>
