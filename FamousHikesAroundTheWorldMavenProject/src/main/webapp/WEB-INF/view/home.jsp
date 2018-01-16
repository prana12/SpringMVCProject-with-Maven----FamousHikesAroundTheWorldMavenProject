<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/view/public/site-head.jsp" />
</head>
<body>
	<jsp:include page="/WEB-INF/view/public/header.jsp" />
	
	
	<!-- logout -->
	<%-- <form:form action="${pageContext.request.contextPath}/logout" method="POST">
		<input type="Submit" value="Log Out">
	</form:form> --%>
	
	<%-- <a href="<c:url value="/account/logout" />">Logout Here</a> --%>

	
	<div>
	<div class="container">
		<div class="jumbotron">
			<h1>For available best hike trails...<!-- <span class="glyphicon glyphicon-tent"> --></span></h1>
			<a class="btn btn-success btn-lg" href="list">Click Here</a>
			<p>
				<br> We have compiled a list of amazing hikes to do around the
				world.<br> You are about to explore the exciting world...
			</p>
			<sec:authorize ifAnyGranted="ADMIN">
				<a href="showFormForAdd">Add more trails</a>
			</sec:authorize>
		</div>
	</div>

	<%-- <sec:authorize access="hasRole('ROLE_USER')">
		User role
	</sec:authorize>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		ADMIN role
	</sec:authorize> --%>

	
	<!-- add the trails list as thumbnail info -->
	<div class="container" id="collection">
		<h2>Recently Added Trails...</h2>
		<hr>
		<div class="row text-center" style="display:flex; flex-wrap:wrap;">

			<c:forEach var="tempTrail" items="${trails}">
				
				<!-- Create Update link -->
				<c:url var="clinkLink" value="/hike/showFormForInfo/${tempTrail.id}">
				</c:url>
				
				<div class="col-md-4 col-sm-6">
					<a id="pseudo-link" href="${clinkLink}">
					<div class="thumbnail">
						<img src="${tempTrail.imageUrl}">
						<div class="caption">
							<h4 style="color:#069;">${tempTrail.name}</h4>
						</div>
					</div>
					</a>
				</div>
				
			</c:forEach>
		</div>
	</div>
	
	</div>
	<jsp:include page="/WEB-INF/view/public/footer.jsp" />
</body>
</html>