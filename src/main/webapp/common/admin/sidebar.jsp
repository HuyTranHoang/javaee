<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="currentPage" value="${pageContext.request.servletPath}" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="mt-3">
    <h4 class="gradient-text"><i class="fa-regular fa-code fa-2xs"></i> Management</h4>
</div>

<div class="list-group mt-3">
    <a href="${contextPath}/admin" class="list-group-item list-group-item-action ${currentPage eq '/admin' ? 'active' : ''}">Dashboard</a>
    <a href="${contextPath}/admin/users/" class="list-group-item list-group-item-action ${currentPage eq '/admin/users' ? 'active' : ''}">Users</a>
    <a href="#" class="list-group-item list-group-item-action ${currentPage eq '/categories/' ? 'active' : ''}">Categories</a>
    <a href="#" class="list-group-item list-group-item-action ${currentPage eq '/books/' ? 'active' : ''}">Books</a>
    <a href="#" class="list-group-item list-group-item-action ${currentPage eq '/customers/' ? 'active' : ''}">Customers</a>
    <a href="#" class="list-group-item list-group-item-action ${currentPage eq '/reviews/' ? 'active' : ''}">Reviews</a>
    <a href="#" class="list-group-item list-group-item-action ${currentPage eq '/orders/' ? 'active' : ''}">Orders</a>
</div>


<div class="mt-3">
    <h4>Quick actions</h4>
</div>

<div class="list-group mt-3">
    <a href="${contextPath}/admin/users/new" class="list-group-item list-group-item-action">New User</a>
    <a href="#" class="list-group-item list-group-item-action">New Category</a>
    <a href="#" class="list-group-item list-group-item-action">New Book</a>
    <a href="#" class="list-group-item list-group-item-action">New Customer</a>
</div>