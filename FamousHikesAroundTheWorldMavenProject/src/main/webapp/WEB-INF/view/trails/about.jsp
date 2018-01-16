<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/view/public/site-head.jsp" />
<script src="https://www.youtube.com/iframe_api"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/view/public/header.jsp" />
	
	<div class="container" style="height:100%; min-height: 800px;">
		<h2>About Us...</h2>
		<p>
			Thank you for checking our collection. Being Adventurer is not easy.
			Here is a refined collection of the popular trails around the world.
			To all the hikers, step out and explore often. 
			And plan your journey from here.......
			<br><br>
		</p>
		
		
		One of the random youtube video for you to check out...
		<br><br>
		<iframe width="560" height="350" src="https://www.youtube.com/embed/bwBC7XjsPxs" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen>
			
		</iframe>
		
		<br>
		Video Source: YouTube
		<br><br><br>
		
		
		<div class="form-group">
		<a href="${pageContext.request.contextPath}/hike/home">Go Back</a>
		</div>
	</div>

	<jsp:include page="/WEB-INF/view/public/footer.jsp" />
</body>
</html>

