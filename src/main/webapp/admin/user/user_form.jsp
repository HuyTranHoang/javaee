<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="mt-3">
    <jsp:useBean id="mode" class="java.lang.String" scope="request"/>
    <c:if test="${mode == 'create'}">
        <h4 class="mb-4">Create new user</h4>
        <c:set var="actionPath" value="${contextPath}/admin/users/insert"/>
    </c:if>
    <c:if test="${mode == 'edit'}">
        <h4 class="mb-4">Update user</h4>
        <c:set var="actionPath" value="${contextPath}/admin/users/update"/>
    </c:if>

    <form class="bg-body-secondary rounded p-4" id="userForm"
          style="width: 400px" action="${actionPath}" method="post">

        <c:if test="${not empty sessionScope.error}">
            <div class="alert alert-danger">${sessionScope.error}</div>
            <% session.removeAttribute("error"); %>
        </c:if>

        <jsp:useBean id="user" class="com.ebook.entity.User" scope="request"/>

        <input type="hidden" name="userId" value="${user.userId}"/>

        <div class="mb-3">
            <label for="email" class="form-label">Email address</label>
            <input type="text" class="form-control" name="email" id="email" placeholder="name@example.com" value="${user.email}">
        </div>

        <div class="mb-3">
            <label for="fullName" class="form-label">Full name</label>
            <input type="text" class="form-control" name="fullName" id="fullName" placeholder="full name" value="${user.fullName}">
        </div>

        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control" name="password" id="password">
        </div>

        <div class="d-flex justify-content-center">
            <button type="submit" class="btn btn-primary btn-sm me-3">Submit</button>
            <a href="${contextPath}/admin/users/" class="btn btn-secondary btn-sm">Cancel</a>
        </div>
    </form>
</div>

<script type="text/javascript">
    function initializeScript() {
        const userForm = $('#userForm');

        userForm.validate({
            rules: {
                email: {
                    required: true,
                    email: true
                },
                fullName: {
                    required: true
                },
                password: {
                    required: true,
                    minlength: 6
                }
            },
            messages: {
                email: {
                    required: "Please enter email",
                    email: "Please enter valid email"
                },
                fullName: {
                    required: "Please enter full name"
                },
                password: {
                    required: "Please enter password",
                    minlength: "Password must be at least 6 characters long"
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

        userForm.on("submit", function (e) {
            if (userForm.valid()) {
                return true;
            }
            e.preventDefault();
        });
    }
</script>
