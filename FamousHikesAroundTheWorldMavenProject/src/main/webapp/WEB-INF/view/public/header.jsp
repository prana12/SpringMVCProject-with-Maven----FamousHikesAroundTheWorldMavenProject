<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>



<div>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container" style="padding-bottom: 4px;">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<span class="navbar-brand" style="">
					<strong>FamousHikes</strong>
					<span class="glyphicon glyphicon-tent"></span>
				</span>
			</div>
			
			<div style="" class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar">
				<li>
					<a href="${pageContext.request.contextPath}/hike/home">
						Home <span class="glyphicon glyphicon-home"></span>
					</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/hike/about">
						
						About <span class="glyphicon glyphicon-info-sign"></span> 
					</a>
				</li>
			</ul>
			
			<!-- <div class="collapse navbar-collapse"> -->
			<ul class="nav navbar-nav navbar-right">
					
				<!-- Check if user is logged in -->
				<c:if test="${pageContext.request.userPrincipal.name == null}">
					<li>
						<a href="${pageContext.request.contextPath}/account/showRegisterPage"">
							Sign Up <span class="glyphicon glyphicon-plus-sign"></span>
						</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/account/showLoginPage">
							Login <span class="glyphicon glyphicon-log-in"></span>
						</a>
					</li>
				</c:if>
				<c:if test="${pageContext.request.userPrincipal.name != null}">
					
					<li>
						<a>
							<span class="glyphicon glyphicon-user"><!-- Signed In as --> </span> 
							<strong>
								${pageContext.request.userPrincipal.name}
								<sec:authorize ifAnyGranted="ADMIN">
								    <%-- <sec:authentication property="principal.authorities" var="authorities" />
									<c:forEach items="${authorities}" var="authority" varStatus="vs">
									<p>${authority.authority}</p>
									</c:forEach> --%>
									[admin]
							  	</sec:authorize>
							</strong>
						</a>
					</li>
					
					<%-- <c:url var="logoutUrl"
		                  value="${pageContext.request.contextPath}/logout"/> --%>
		            <li>
		            		<a href="${pageContext.request.contextPath}/account/logout">
		            			Log out <span class="glyphicon glyphicon-log-out">
		            		</a>
		           	</li>
				
				</c:if>
					
			</ul>
			<!-- </div> -->
			</div>
			
		</div>
	</nav>
	
	<c:if test="${message != null}">
		<div id="flash-messages" style="border:2px solid darkslategray; color:dimgray; background:aliceblue;
			border-radius: 5px; width: 80%; margin: 16px auto; text-align: center; font-weight: bold;" 
			class="container flash-messages">
			<h4>${message}</h4>
		</div>
	</c:if>
	
</div>


