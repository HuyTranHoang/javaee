<%@include file="layout/header.jsp" %>

<body>
<jsp:include page="layout/navbar.jsp"/>
<div class="container">
    <div class="d-flex flex-column align-items-center mt-5 py-3 rounded bg-primary-subtle">
        <h2 class="text-center">Login</h2>
        <div class="mb-3">
            <label for="email" class="form-label">Email address</label>
            <input type="email" class="form-control" id="email" placeholder="name@example.com">
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control" id="password">
        </div>
        <button type="submit" class="btn btn-primary">Login</button>

    </div>
</div>

<jsp:include page="layout/footer.jsp"/>
</body>
</html>