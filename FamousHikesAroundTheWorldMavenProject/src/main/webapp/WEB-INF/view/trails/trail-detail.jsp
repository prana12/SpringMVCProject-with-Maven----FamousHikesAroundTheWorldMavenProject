<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/view/public/site-head.jsp" />
</head>
<body>
	<jsp:include page="/WEB-INF/view/public/header.jsp" />

	<!-- Create Update link -->
	<c:url var="editLink" value="/hike/editHikeTrail">
		<c:param name="id" value="${trail.id}" />
	</c:url>
	
	<!-- Create Delete link -->
	<c:url var="deleteLink" value="/hike/deleteHikeTrail">
		<c:param name="id" value="${trail.id}" />
	</c:url>
	
	<!-- Create Review link -->
	<c:url var="reviewLink" value="/hike/showReviewForm/${trail.id}">
		<%-- <c:param name="id" value="${trail.id}" /> --%>
	</c:url>


	<div class="container">
		<div class="row">
			
			<div class="col-md-3">
				<div class="list-group">
					<span class="list-group-item"><label>Location:</label>
						${trail.location}</span> <span class="list-group-item"><label>Distance(miles):</label>
						${trail.distance}</span> <span class="list-group-item"><label>Duration:</label>
						${trail.duration}</span> <span class="list-group-item"><label>Best
							Time to go:</label> ${trail.bestTime}</span> <span class="list-group-item"><label>Added
							on:</label> <fmt:formatDate pattern="yyyy-MM-dd"
							value="${trail.createDate}" /></span>
				</div>
				<!-- Map... <span class="glyphicon glyphicon-map-marker"></span> -->
			</div>

			<div class="col-md-9">
				<div class="thumbnail well">
					<img id="info-image" class="img-responsive" src="${trail.imageUrl}">
					<div class="caption">
						<h4 class="pull-right">
							<sec:authorize ifAnyGranted="ADMIN">
								<a href="${editLink}">Edit</a> 
								| 
								<a href="${deleteLink}" 
								onclick="if(!(confirm('Are you sure you want to delete this Hike Trail?'))) return false;">
									Delete
								</a>
							</sec:authorize>
						</h4>
						<h3>
							<a id="pseudo-link">${trail.name}</a>
						</h3>
						<p>${trail.description}</p>

					</div>
				</div>

				<div class="">
					<i style="color:red;">
						<h4>
							You must be a member to add review.
							<c:if test="${empty reviewsList}">
								Be the first one to review this hike trail.
							</c:if>
						</h4>
					</i>
					<div class="text-right">
						<a class="btn btn-success" href="${reviewLink}">Add Review</a>
					</div>
				</div>

				
				<div class="" style="margin-bottom:40px;">
				<!-- Show all the related comments -->
				<c:forEach var="review" items="${reviewsList}">
				
				
				
				<!-- Create Review link -->
				<c:url var="deleteReviewLink" value="/hike/deleteHikeReview/${trail.id}">
					<c:param name="id" value="${review.id}" />
				</c:url>
				
				<div class="row">
					<div class="col-md-12">
						<a id="pseudo-link">
							<strong>${review.createdBy.firstName} ${review.createdBy.lastName}</strong> 
							<%-- Firstname: <sec:authentication property="principal.firstName" />  --%>
						</a>
						<i><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss"
							value="${review.createDate}" /></i> 
						
						<!-- only display delete review link if the review was added by current user -->
						<span class="">
							<!-- <a class="btn btn-xs btn-warning" href="">Edit </a> -->
							<c:if test="${authenticatedUser != null && authenticatedUser.userId == review.createdBy.userId}">
								<a class="btn btn-xs btn-danger" href="${deleteReviewLink}"
									onclick="if(!(confirm('Are you sure you want to delete this review?'))) return false;"
								>Delete</a>
							</c:if>
						</span>
						
						<p>
							${review.review}
						</p>
						
					</div>
				</div>
				
				</c:forEach>
				</div>
				

			</div>

		</div>


	</div>

	<jsp:include page="/WEB-INF/view/public/footer.jsp" />
</body>