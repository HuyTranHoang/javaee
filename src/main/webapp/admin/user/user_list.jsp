<%@include file="../../common/taglib.jsp" %>

<div class="d-flex align-items-center justify-content-between mt-3">
    <h5 class="text-center">User</h5>
    <a href="${pageContext.request.contextPath}/admin/users/new" class="btn btn-success btn-sm me-4">New User</a>
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

        <jsp:useBean id="listUser" scope="request" type="java.util.List"/>
        <c:forEach items="${listUser}" var="user" varStatus="iterationCount">
            <tr>
                <th scope="row">${iterationCount.index + 1}</th>
                <td>${user.email}</td>
                <td>${user.fullName}</td>
                <td>
                    <i class="fa-light fa-pen-to-square text-warning me-3"></i>
                    <i class="fa-light fa-trash text-danger"></i>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>