<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/view/public/site-head.jsp" />
</head>
<body>
	<jsp:include page="/WEB-INF/view/public/header.jsp" />


	<!-- add the trails list as thumbnail info -->
	<div class="container" id="collection">
		<h2>Popular Trails Around the World</h2>
		<hr>
		<div class="row text-center" style="display: flex; flex-wrap: wrap;">

			<c:forEach var="tempTrail" items="${trails}">

				<!-- Create Update link -->
				<c:url var="clickLink" value="/hike/showFormForInfo/${tempTrail.id}">
				</c:url>
				
				<div class="col-md-4 col-sm-6">
				<a id="pseudo-link" href="${clickLink}">
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


	<jsp:include page="/WEB-INF/view/public/footer.jsp" />
</body>
</html>