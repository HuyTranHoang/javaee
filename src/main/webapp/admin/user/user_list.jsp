<%@include file="../../common/taglib.jsp" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<jsp:useBean id="listUser" scope="request" type="java.util.List"/>

<div class="d-flex align-items-center justify-content-between mt-3">
    <h5 class="text-center">User</h5>
    <a href="${contextPath}/admin/users/new" class="btn btn-success btn-sm me-4">New User</a>
</div>

<div class="d-flex justify-content-center py-3">
    <table class="table">
        <thead>
        <tr class="table-success">
            <th scope="col">#</th>
            <th scope="col">Email</th>
            <th scope="col">Full Name</th>
            <th scope="col" style="width: 20%">Actions</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${listUser}" var="user" varStatus="iterationCount">
            <jsp:useBean id="user" type="com.ebook.entity.User"/>
            <tr>
                <th scope="row">${iterationCount.index + 1}</th>
                <td>${user.email}</td>
                <td>${user.fullName}</td>
                <td>

                    <a href="${contextPath}/admin/users/edit/${user.user_id}"
                       class="fa-light fa-pen-to-square text-warning text-decoration-none me-3">
                    </a>

                    <i class="fa-light fa-trash text-danger delete-btn"
                       style="cursor: pointer;"
                       data-id="${user.user_id}"
                       data-bs-toggle="modal"
                       data-bs-target="#confirmModal">
                    </i>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


<form action="${contextPath}/admin/users/delete" method="post" id="deleteForm">
    <input type="hidden" id="deleteUserId" name="deleteUserId">
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
                Are you sure you want to delete this user?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger">Delete it</button>
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">
    function initializeScript() {

        const deleteBtns = $('.delete-btn');
        const deleteForm = $('#deleteForm');
        const deleteUserId = $('#deleteUserId');
        const confirmButton = $('#confirmModal .btn-danger');

        deleteBtns.each((index, btn) => {
            $(btn).on('click', function () {
                const id = $(this).data('id');
                deleteUserId.val(id);
            });
        });

        confirmButton.on('click', function () {
            deleteForm.trigger('submit');
        });
    }

    <c:if test="${not empty sessionScope.message}">

    function sweetAlertInitialize() {
        /* global Swal */
        Swal.fire({
            title: 'Success!',
            text: '${sessionScope.message}',
            icon: 'success'
        });
    }

    <% session.removeAttribute("message"); %>

    </c:if>

</script>