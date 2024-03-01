<%@include file="layout/header.jsp" %>

<body>
<jsp:include page="layout/navbar.jsp" />
<h1><%= "Hello World!" %>
</h1>

<br/>
<h1 class="text-primary">Hello Servlet</h1>


<img src="${contextPath}/images/gura.jpg" width="100px" alt="logo image">


<%@include file="layout/footer.jsp" %>

</body>
</html>