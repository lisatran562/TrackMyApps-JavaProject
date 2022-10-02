<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit profile</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/profile.css"> <!-- change to match your file/naming structure -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family="Quicksand">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <style>
body {
	font-family: "Quicksand", sans-serif;
	background-color: #f5fffa;
}
</style>
</head>
<body>
	<div class="nav">
		<a href="/dashboard" class="btn" style="color:black"><span class="material-symbols-outlined">home</span></a>
	</div>
   <div>
   		<h2>Edit your profile</h2>
   </div>
   <div>
   		<form:form action="/users/${user.id}/edit" method="post" modelAttribute="user">  		
		<input type="hidden" name="_method" value="put"/>
		<div>
			<form:label path="firstName" class="form-label">First name:</form:label>
			<form:input type="text" path="firstName" class="form-control"/>
			<form:errors path="firstName" class="text-danger"/>
		</div>
		<div>
			<form:label path="lastName" class="form-label">Last name:</form:label>
			<form:input type="text" path="lastName" class="form-control"/>
			<form:errors path="lastName" class="text-danger"/>
		</div>
		<div>
			<form:label path="email" class="form-label">Email:</form:label>
			<form:input type="text" path="email" class="form-control"/>
			<form:errors path="email" class="text-danger"/>
		</div>
		<div>
			<form:label path="github" class="form-label">Github link:</form:label>
			<form:input type="text" path="github" class="form-control"/>
			<form:errors path="github" class="text-danger"/>
		</div>
		<div>
			<form:label path="linkedin" class="form-label">LinkedIn link:</form:label>
			<form:input type="text" path="linkedin" class="form-control"/>
			<form:errors path="linkedin" class="text-danger"/>
		</div>
		<div>
			<form:label path="resume" class="form-label">Resume link(Google docs):</form:label>
			<form:input type="text" path="resume" class="form-control"/>
			<form:errors path="resume" class="text-danger"/>
		</div>
		<div>
			<form:hidden path="password" value="${user.password}"/>
		</div>
		<div>
			<button type="submit" class="btn btn-sm btn-dark mt-3">Edit</button>
		</div>
   		</form:form>
   </div>
</body>
</html>