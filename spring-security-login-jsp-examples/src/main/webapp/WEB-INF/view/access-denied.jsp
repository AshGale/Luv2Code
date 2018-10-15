<html>

<head>
	<title>Access Denied</title>
	
		<style type="text/css">
		.error-color {
			color: red;
		}
		.granted-color {
			color: green;
		}
	</style>
</head>

<body>
	
	<h2 class="error-color">You are not Authorised to access this page!!</h2>
	
	<hr>
	
	<a href="${pageContext.request.contextPath}/" 
	class="granted-color">Home Page</a>
	
</body>

</html>