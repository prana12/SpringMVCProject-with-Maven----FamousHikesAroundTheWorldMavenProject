<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/view/public/site-head.jsp" />
	<style type="text/css">
	.error{color:red;}
	</style>

</head>
<body id="login-background">
	<jsp:include page="/WEB-INF/view/public/header.jsp" />
	
	<div class="container" style="height:100%; min-height: 800px;margin-top:60px;">
		
		<div id="loginbox" style="margin-top: 25px; margin-left:740px; width:40%;"
			class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">

			<div class="panel panel-info">

				<div class="panel-heading">
					<div class="panel-title">User Registration</div>
				</div>

				<div style="padding-top: 30px" class="panel-body">
				
					<form:form
						action="registerTheUser"
						method="POST" modelAttribute="user">

						<!-- First Name -->
						<form:errors path="firstName" cssClass="error"></form:errors>
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> 
								<form:input type="text"
								placeholder="first name" path="firstName" class="form-control"/>
						</div>
						
						
						<!-- Last Name -->
						<form:errors path="lastName" cssClass="error"></form:errors>
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> 
								<form:input type="text"
								placeholder="last name" path="lastName" class="form-control"/>
						</div>
						
						<!-- Email -->
						<form:errors path="email" cssClass="error"></form:errors>
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i 
									class="glyphicon glyphicon-envelope"></i></span> 
								<form:input type="text"
								placeholder="email" path="email" class="form-control"/>
						</div>

						<!-- Password -->
						<form:errors path="password" cssClass="error"></form:errors>
						<div style="margin-bottom: 25px;" class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-lock"></i>
								</span> 
								<form:input type="password"
								placeholder="password" path="password" class="form-control"/>
						</div>
						
						<!-- Re-confirm Password -->
						<form:errors path="confirmPassword" cssClass="error" id="errorConfirmPassword"></form:errors>
						<div style="margin-bottom: 25px;" class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-lock"></i>
								</span> 
								<form:input type="password"
								placeholder="confirm password" path="confirmPassword" class="form-control"/>
						</div>
						
						<!-- Register/Submit Button -->
						<div class="controls" style="margin-bottom: 25px;">
							<div class="center-block">
								<!-- <input type="submit" class="btn btn-success btn-block" value="Register" /> -->
								<input class="btn btn-lg btn-primary btn-block"
							type="submit" value="Register" />
							</div>
						</div>
						
						<!-- Reset button -->
						<!-- <div class="form-group">
							<input class="btn btn-sm btn-default btn-warning btn-block"
							type="reset" value="Reset" />
						</div> -->
						<!-- <a><input type="reset" value="Reset" /></a> -->
						
						
					</form:form>
				</div>
				
				<div class="panel-footer">
					<div class="row">
					
					<span class="pull-right" style="padding-right:15px;">
						Already a member?
						<a id="pseudo-link" href="${pageContext.request.contextPath}/account/showLoginPage">
							Login here
						</a>
					</span>
					
					</div>
					
					
				</div>
				

			</div>
		</div>


	</div>
	
	

	<jsp:include page="/WEB-INF/view/public/footer.jsp" />
</body>
</html>
