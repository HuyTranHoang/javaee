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
    <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>

<body data-bs-theme="dark" class="d-flex flex-column vh-100">
<jsp:include page="../common/user/navbar.jsp" />

<div class="container flex-grow-1">
    <div class="row my-5 mx-auto p-4 rounded bg-primary-subtle" style="width: 600px">
        <div class="col">
            <h2 class="text-center">Sign in</h2>
            <div class="mb-3">
                <label for="email" class="form-label">Email address</label>
                <input type="email" class="form-control" id="email" placeholder="name@example.com">
            </div>
            <div class="mb-5">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password">
            </div>
            <button type="submit" class="btn btn-success w-100">Login</button>
        </div>
    </div>

</div>

<%@include file="../common/user/footer.jsp" %>

</body>
</html>
