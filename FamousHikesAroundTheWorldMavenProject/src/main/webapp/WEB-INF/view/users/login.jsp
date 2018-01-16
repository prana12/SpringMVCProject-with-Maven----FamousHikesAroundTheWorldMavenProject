<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/view/public/site-head.jsp" />
</head>
<body id="login-background">
	<jsp:include page="/WEB-INF/view/public/header.jsp" />
	
	<div class="container" style="height:100%; min-height: 800px;margin-top:60px;">
		
		<div id="loginbox" style="margin-top: 25px; margin-left:740px; width:40%;"
			class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">

			<div class="panel panel-info">

				<div class="panel-heading">
					<div class="panel-title">Sign In</div>
				</div>

				<div style="padding-top: 30px" class="panel-body">
				
					<%-- <c:url value="${pageContext.request.contextPath}/authenticateTheUser" var="loginUrl"></c:url> --%>
					<form:form
						action="${pageContext.request.contextPath}/authenticateTheUser"
						method="POST">

						<div class="form-group">
							<div class="col-xs-15">
								<div>
									<!-- Check for login error -->
									<c:if test="${param.error != null}">
										<div class="alert alert-danger" id="messages">
											Invalid username and password.
										</div>
									</c:if>
									
									<!-- Check for logout -->
									<c:if test="${param.logout != null}">            
									<div class="alert alert-success col-xs-offset-1 col-xs-10" id="messages">
										You have been logged out.
									</div>
									</c:if>
									
								</div>
							</div>
						</div>


						<!-- <hr> -->
						<!-- User name -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-envelope"></i></span> <input type="text"
								name="username" placeholder="email" class="form-control">
						</div>

						<!-- Password -->
						<div style="margin-bottom: 25px;" class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-lock"></i>
								</span> 
								<input type="password"
								name="password" placeholder="password" class="form-control">
						</div>
						<!-- <hr> -->
						
						<!-- Login/Submit Button -->
						<div class="controls" style="margin-bottom: 25px;">
							<div class="center-block">
								<button type="submit" class="btn btn-lg btn-success btn-block">Login</button>
								
							</div>
						</div>
						

					</form:form>
				</div>
				
				<div class="panel-footer">
					<div class="row">
					
					<div class="col-md-5 col-sm-6">
						<a href="">Forgot Password?</a>
					</div>
					
					<div class="col-md-7 col-sm-6">
						<span class="pull-right">
							Not a member yet? 
							<a href="${pageContext.request.contextPath}/account/showRegisterPage"
								class="btn btn-sm btn-primary">Join Us</a>
						</span>
					</div>
					
					</div>
				</div>
				

			</div>
		</div>


	</div>

	<jsp:include page="/WEB-INF/view/public/footer.jsp" />
</body>
</html>
