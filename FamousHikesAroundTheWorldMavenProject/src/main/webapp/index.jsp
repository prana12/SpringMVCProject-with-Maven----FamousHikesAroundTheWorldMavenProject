<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/view/public/site-head.jsp" />
</head>
<body id="index-background">
	<%-- <jsp:include page="/WEB-INF/view/header.jsp" /> --%>

	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div id="index-content">
					<h1>Discover world famous hiking trails</h1>
					<a href="${pageContext.request.contextPath}/hike/home"
						class="btn btn-lg btn-success">Start Here</a>
				</div>

			</div>
		</div>
	</div>
	<%-- <jsp:include page="/WEB-INF/view/footer.jsp" /> --%>
</body>
</html>