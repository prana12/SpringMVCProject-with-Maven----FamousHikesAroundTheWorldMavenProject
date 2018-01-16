<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/view/public/site-head.jsp" />
<style type="text/css">
	.error{color:red;}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/view/public/header.jsp" />

	<div class="container" style="height:100%; min-height: 800px;margin-top:0px;">
		<div class="row">
			<h2 style="text-align: center;">Review Hike Trail - ${trail.name}</h2>
			<div style="width: 40%; margin: 25px auto;">
			
				<form:form action="${pageContext.request.contextPath}/hike/saveHikeTrailReview/${trail.id}" method="POST" modelAttribute="review">
					
					<div class="form-group">
						<label>Location:</label>
						<form:input class="form-control" path=""
						value="${trail.location}" disabled="true" />
					</div>
					
					<div class="form-group">
						<label>Distance(miles):</label>
						<form:input class="form-control" path=""
						value="${trail.distance}" disabled="true" />
					</div>
					
					<div class="form-group">
						<label>Duration:</label>
						<form:input class="form-control" path=""
						value="${trail.duration}" disabled="true" />
					</div>
					
					<div class="form-group">
						<label>Best Time to go:</label>
						<form:input class="form-control" path=""
						value="${trail.bestTime}" disabled="true" />
					</div>
					
					<div class="form-group">
						<label>Please provide User Review below:</label>
						<form:textarea path="review" class="form-control" rows="7" 
						 	placeholder="Add a review......"/>
						 <form:errors path="review" cssClass="error"></form:errors>
					</div>

					<div class="form-group">
						<input class="btn btn-lg btn-default btn-primary btn-block"
							type="submit" value="Add Review" />
					</div>
					
					<div class="form-group">
						<input class="btn btn-sm btn-default btn-warning btn-block"
							type="reset" value="Reset" />
					</div>
					
				</form:form>
				<a href="${pageContext.request.contextPath}/hike/home">Go Back</a>
			</div>



		</div>
	</div>

	<jsp:include page="/WEB-INF/view/public/footer.jsp" />
</body>