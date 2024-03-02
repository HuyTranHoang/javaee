<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="mt-3">
    <h4 class="mb-4">Update user</h4>

    <form class="bg-body-secondary rounded p-4" id="userForm"
          style="width: 400px" action="${contextPath}/admin/users/update" method="post">

        <input type="hidden" name="userId" value="${user.user_id}">

        <div class="mb-3">
            <label for="email" class="form-label">Email address</label>
            <input type="text" class="form-control" name="email" id="email" value="${user.email}" placeholder="name@example.com">
        </div>

        <div class="mb-3">
            <label for="fullName" class="form-label">Full name</label>
            <input type="text" class="form-control" name="fullName" id="fullName" value="${user.fullName}" placeholder="full name">
        </div>

        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control" name="password" id="password">
        </div>

        <div class="d-flex justify-content-center">
            <button type="submit" class="btn btn-primary btn-sm me-3">Save</button>
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
            errorElement: 'div', // Use a div for error messages
            errorClass: 'invalid-feedback', // Class name for error message container
            highlight: function(element, errorClass, validClass) {
                // Highlight input element with Bootstrap error class
                $(element).addClass('is-invalid');
            },
            unhighlight: function(element, errorClass, validClass) {
                // Remove Bootstrap error class when error is fixed
                $(element).removeClass('is-invalid');
            },
            errorPlacement: function(error, element) {
                // Display error message within Bootstrap form-group
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
