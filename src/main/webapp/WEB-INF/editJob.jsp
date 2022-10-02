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
<title>Edit Job</title>
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
		<form:form action="/jobs/${id}/edit" method="post"
			modelAttribute="job">
			<input type="hidden" name="_method" value="put" />
			<form:hidden path="user" />
			<form:hidden path="company" value="${job.company}"/>
			<div class="d-flex justify-content-end">
				<div>
					<a href="/dashboard" class="btn"><span class="material-symbols-outlined">
home
</span></a>
					<a href="/logout" class="btn"><span class="material-symbols-outlined">
logout
</span></a>
				</div>
			</div>

			<div class="d-flex justify-content-center">
				<div>
					<h4 class="mt-5">Edit job details</h4>
					<div class="">
						<form:label path="company" class="form-label">Company:</form:label>
						<form:input type="text" path="company" class="form-control" disabled="true"/>
					</div>
					<div class="">
						<form:label path="position" class="form-label">Position:</form:label>
						<form:input type="text" path="position" class="form-control" />
						<form:errors path="position" class="text-danger" />
					</div>
					<div class="">
						<form:label path="skills" class="form-label">Skills:</form:label>
						<form:input type="text" path="skills" class="form-control" />
						<form:errors path="skills" class="text-danger" />
					</div>
					<div class="">
						<form:label path="location" class="form-label">Location:</form:label>
						<form:input type="text" path="location" class="form-control" />
						<form:errors path="location" class="text-danger" />
					</div>
					<div class="">
						<form:label path="salary" class="form-label">Salary:</form:label>
						<form:input type="text" path="salary" class="form-control" />
						<form:errors path="salary" class="text-danger" />
					</div>
					<div>
						<form:label path="source" class="form-label">Source:</form:label>
						<form:input type="text" path="source" class="form-control" />
						<form:errors path="source" class="text-danger" />
					</div>
					<div>
						<form:label path="status" class="form-label">Status:</form:label>
						<form:select path="status" class="form-select">
							<form:option class="form-control" value="Just applied">Just applied</form:option>
							<form:option class="form-control" value="In contact">In contact</form:option>
							<form:option class="form-control" value="Haven't heard back">Haven't heard back</form:option>
							<form:option class="form-control" value="Interviewing">Interviewing</form:option>
						</form:select>
					</div>
					<div>
						<form:label path="date" class="form-label">Date applied:</form:label>
						<form:input type="date" path="date" class="form-control" />
						<form:errors path="date" class="text-danger" />
					</div>
					<div class="mt-2">
						Remote:
						<form:checkbox path="remote" value="true" />
					</div>
					<button type="submit" class="btn btn-dark mt-3">Edit</button>
				</div>

			</div>
		</form:form>
	</div>
</body>
</html>