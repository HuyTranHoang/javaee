<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="mt-3">
    <h4 class="mb-4">Create new user</h4>

    <form class="bg-body-secondary rounded p-4" id="userForm"
          style="width: 400px" action="${contextPath}/admin/users/insert" method="post">
        <div class="mb-3">
            <label for="email" class="form-label">Email address</label>
            <input type="email" class="form-control" name="email" id="email" placeholder="name@example.com">
        </div>

        <div class="mb-3">
            <label for="fullName" class="form-label">Full name</label>
            <input type="text" class="form-control" name="fullName" id="fullName" placeholder="full name">
        </div>

        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control" name="password" id="password">
        </div>

        <div class="d-flex justify-content-center">
            <button type="submit" class="btn btn-primary btn-sm me-3">Submit</button>
            <a href="admin/users/" class="btn btn-secondary btn-sm">Cancel</a>
        </div>
    </form>
</div>

<script type="text/javascript">
    function initializeUserForm() {
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
                    required: true
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
                    required: "Please enter password"
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
