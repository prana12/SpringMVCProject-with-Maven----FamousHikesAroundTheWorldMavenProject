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

	<div class="container">
		<div class="row">
			<h2 style="text-align: center;">Hike Trail Detail</h2>
			<div style="width: 40%; margin: 25px auto;">
			
				<form:form action="saveHikeTrail" method="POST" modelAttribute="trail">
					
					<!-- need to associate this data with customer id -->
					<form:hidden path="id" />
					
					<div class="form-group">
						<label>Name:</label>
						<form:input path="name" class="form-control" 
						placeholder="name" />
						<form:errors path="name" cssClass="error"></form:errors>
					</div>

					<div class="form-group">
						<label>Location:</label>
						<form:input path="location" class="form-control"
							placeholder="location" />
						<form:errors path="location" cssClass="error"></form:errors>
					</div>

					<div class="form-group">
						<label>Distance(miles):</label>
						<form:input path="distance" class="form-control" 
							placeholder="distance"/>
						<form:errors path="distance" cssClass="error"></form:errors>
					</div>

					<div class="form-group">
						<label>Duration:</label>
						<form:input path="duration" class="form-control" 
							placeholder="duration"/>
						<form:errors path="duration" cssClass="error"></form:errors>
					</div>

					<div class="form-group">
						<label>Best Time to go:</label>
						<form:input path="bestTime" class="form-control" 
							placeholder="best time to go"/>
						<form:errors path="bestTime" cssClass="error"></form:errors>
					</div>

					<div class="form-group">
						<label>Image URL:</label>
						<form:input path="imageUrl" class="form-control" 
							placeholder="image"/>
						<form:errors path="imageUrl" cssClass="error"></form:errors>
					</div>

					<div class="form-group">
						<label>Hike Description:</label>
						<form:textarea path="description" class="form-control" rows="5" 
						 	placeholder="......."/>
						 <form:errors path="description" cssClass="error"></form:errors>
					</div>

					<div class="form-group">
						<input class="btn btn-lg btn-default btn-primary btn-block"
							type="submit" value="Save" />
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