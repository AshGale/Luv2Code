<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<html>

<head>
<title>luv2code Company Home Page</title>
</head>

<body>
	<h2>luv2code Company Home Page</h2>
	<hr>

	<!-- displaying username and role -->
	<p>
		User:
		<security:authentication property="principal.username" />
		<br> <br> Role(s):
		<security:authentication property="principal.authorities" />
	</p>

	<hr>

	<!-- Add link for /leaders for Role MANAGER -->
	<security:authorize access="hasRole('MANAGER')">
		<p>
			<a href="${pageContext.request.contextPath}/leaders"> LeaderShip
				Metting</a>
		</p>
	</security:authorize>

	<!-- Add link for role Admin -->
	<security:authorize access="hasRole('ADMIN')">
		<p>
			<a href="${pageContext.request.contextPath}/systems">System Admin</a>
		</p>
	</security:authorize>

	<hr>

	<!-- Logout form -->
	<form:form action="${pageContext.request.contextPath}/logout"
		method="POST">
		<input type="submit" value="Logout" />
	</form:form>

</body>

</html>