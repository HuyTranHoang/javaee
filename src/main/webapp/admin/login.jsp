<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/taglib.jsp" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ebook Shop</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="https://site-assets.fontawesome.com/releases/v6.5.0/css/all.css">
</head>

<body data-bs-theme="dark" class="d-flex flex-column vh-100">
<jsp:include page="../common/admin/navbar.jsp" />

<div class="container flex-grow-1">
    <div class="row my-5 mx-auto p-4 rounded bg-primary-subtle" style="width: 600px">
        <form id="loginForm" class="col" method="post" action="${pageContext.request.contextPath}/admin/login">
            <h2 class="text-center">Admin - Sign in</h2>
            <div class="mb-3">
                <label for="email" class="form-label">Email address</label>
                <input name="email" type="email" class="form-control" id="email" placeholder="name@example.com">
            </div>
            <div class="mb-5">
                <label for="password" class="form-label">Password</label>
                <input name="password" type="password" class="form-control" id="password">
            </div>
            <button type="submit" class="btn btn-success w-100">Login</button>
        </form>
    </div>

</div>

<%@include file="../common/admin/footer.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.5/jquery.validate.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.5/additional-methods.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<script type="text/javascript">
    function initializeScript() {
        const loginForm = $('#loginForm');

        loginForm.validate({
            rules: {
                email: {
                    required: true,
                    email: true
                },
                password: {
                    required: true
                }
            },
            messages: {
                name: {
                    required: "Please enter email"
                },
                password: {
                    required: "Please enter password"
                }
            },
            errorElement: 'div',
            errorClass: 'invalid-feedback',
            highlight: function (element, errorClass, validClass) {
                $(element).addClass('is-invalid');
            },
            unhighlight: function (element, errorClass, validClass) {
                $(element).removeClass('is-invalid');
            },
            errorPlacement: function (error, element) {
                error.insertAfter(element);
            }
        });

        loginForm.on("submit", function (e) {
            if (loginForm.valid()) {
                return true;
            }
            e.preventDefault();
        });
    }
</script>


<script type="text/javascript">
    $(function() {
        initializeScript();

        <c:if test="${not empty sessionScope.message}">

        /* global Swal */
        Swal.fire({
            title: 'Success!',
            text: '${sessionScope.message}',
            icon: 'success'
        });

        <% session.removeAttribute("message"); %>

        </c:if>
    });
</script>



</body>
</html>

