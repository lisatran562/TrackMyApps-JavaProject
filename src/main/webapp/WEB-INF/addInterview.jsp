<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add interview</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family="Quicksand">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
<style>
body {
	font-family: "Quicksand", sans-serif;
	background-color: #f5fffa;
}
</style>
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container mt-5">
		<div class="d-flex justify-content-end">
			<a href="/dashboard" class="btn"><span class="material-symbols-outlined">
home
</span></a>
		</div>
		<div class="text-center mt-5">
			<h2>Add interview details</h2>
		</div>
		<div class="d-flex justify-content-center mt-5">
			<form:form action="/interviews/add" method="post"
				modelAttribute="interview">
				<div>
					<form:label path="job" class="form-label">Company:</form:label>
					<form:select path="job" class="form-select">
						<c:forEach var="job" items="${jobList}">
							<c:if test="${userId == job.user.id}">
								<form:option class="form-control" value="${job.id}">${job.company}</form:option>
							</c:if>
						</c:forEach>
					</form:select>
				</div>
				<div>
					<form:label path="description" class="form-label">Description:</form:label>
					<form:input type="text" path="description" class="form-control" />
				</div>
				<div>
					<form:label path="date" class="form-label">Date:</form:label>
					<form:input type="date" path="date" class="form-control" />
				</div>
				<div>
					<form:label path="time" class="form-label">Time:</form:label>
					<form:input type="time" path="time" class="form-control" />
				</div>
				<div>
					<form:label path="location" class="form-label">Location:</form:label>
					<form:select path="location" class="form-select">
						<form:option class="form-control" value="">Choose location</form:option>
						<form:option class="form-control" value="virtual">Virtual</form:option>
						<form:option class="form-control" value="location">Location</form:option>
					</form:select>
				</div>
				<div>
					<form:label path="address" class="form-label">Address or Link:</form:label>
					<form:input type="text" path="address" class="form-control" />
				</div>
				<button type="submit" class="btn btn-sm btn-dark text-light mt-3">Add</button>
			</form:form>
		</div>
	</div>
</body>
</html>