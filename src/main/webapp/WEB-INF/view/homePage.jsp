<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
</head>
<body>
<h2> this is a home page for our new Site</h2>
	<h4><i>created by Eialid Ahmed Joy</i></h4>
	<hr>
	
	
<security:authorize access="hasRole('Manager')">
			<p>
			<a href="${pageContext.request.contextPath}/managers">For Managing</a>
			(Only for Managers)
			</p>
</security:authorize>
<br>
	
	<security:authorize access="hasRole('Admin')">
			<p><a href="${pageContext.request.contextPath}/admins">For adminship</a>
				(Only for the Admins)
			</p>
	</security:authorize>

<br><br>
UserName: <security:authentication property="principal.username" />
<br>
UserRole: <security:authentication property="principal.authorities" />
<br><br>
<hr><hr>	
<br>
<form:form action="${pageContext.request.contextPath}/logout" method="POST">
	
	<input type="submit" value="Logout" >


</form:form>


</body>
</html>