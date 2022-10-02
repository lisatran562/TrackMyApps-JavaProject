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
    <title>Archived jobs and interviews</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
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
   		<div class="d-flex justify-content-end align-items-center">
			<a href="/dashboard" class="btn"><span class="material-symbols-outlined">home</span></a> 
			<a href="/logout" class="ms-3 text-dark"><span class="material-symbols-outlined">logout</span></a>
		</div>
		<div class="d-flex justify-content-center">
			<div class="mt-5">
				<h1>Job and Interview Archives</h1>
			</div>
		</div>
		<div class="mt-5 gap-5">
			<h4 class="test text-center">Archived jobs:</h4>
			<div class="d-flex justify-content-center">
				<div style="width: 50rem; height: 25rem; overflow-y: auto" class="card overflow-y p-3 mb-5">
					<table class="table table-striped text-center">
						<thead>
							<tr>
								<th>Company</th>
								<th>Position</th>
								<th>Skills</th>
								<th>Date applied</th>
								<th>Options</th>
							</tr>
						</thead>
						<c:forEach var="archive" items="${archivedJobList}">
							<c:if test="${userId == archive.user.id}">
							<tr>
								<td><a href="/jobs/${archive.job.id}"><c:out value="${archive.job.company}" /></a></td>
								<td><c:out value="${archive.job.position}" /></td>
								<td><c:out value="${archive.job.skills}" /></td>
								<td><fmt:formatDate pattern="E MM/dd/yy" value="${archive.job.date}" /></td>			
								<td>
									<form action="/jobs/${archive.job.id}/delete" method="post">
									<input type="hidden" name="_method" value="delete"/>	
									<input type="hidden" name="job" value="${archive.job.id}"/>		
									<button type="submit" class="btn"><span class="material-symbols-outlined">delete</span></button>
									</form>
									<form action="/archives/${archive.id}/delete" method="post">
									<input type="hidden" name="_method" value="delete"/>
									<input type="hidden" name="archive" value="${archive.id}"/>
									<button type="submit" class="btn btn-sm btn-dark">unarchive</button>
									</form>
								</td>
							</tr>
							</c:if>
						</c:forEach>
					</table>
				</div>
			</div>
			<h4 class="test text-center">Archived interviews:</h4>
			<div class="d-flex justify-content-center mb-5">
				<div style="width: 50rem; height: 25rem; overflow-y: auto" class="card overflow-y p-3">
					<table class="table table-striped text-center">
						<thead>
							<tr>
								<th>Company</th>
								<th>Position</th>
								<th>Description</th>
								<th>Date</th>
								<th>Time</th>
								<th>Location</th>
							</tr>
						</thead>
						<c:forEach var="archive" items="${archivedInterviewList}">
							<c:if test="${userId == archive.user.id}">
							<tr>
								<td><c:out value="${archive.interview.job.company}" /></td>
								<td><c:out value="${archive.interview.job.position}" /></td>
								<td><c:out value="${archive.interview.description}" /></td>
								<td><fmt:formatDate pattern="E MM/dd/yy"
										value="${archive.interview.date}" /></td>
								<td><fmt:formatDate pattern="hh:mm a"
										value="${archive.interview.time}" /></td>
								<c:choose>
								<c:when test="${archive.interview.location == 'location'}">
									<td><c:out value="${archive.interview.address}" /></td>
								</c:when>
								<c:otherwise>
									<td><a href="${archive.interview.address}"><c:out value="${archive.interview.address}" /></a></td>										
								</c:otherwise>
								</c:choose>
									<td>
										<form action="/interviews/${archive.interview.id}/delete" method="post">
										<input type="hidden" name="_method" value="delete"/>			
										<button type="submit" class="btn"><span class="material-symbols-outlined">delete</span></button>
										</form>
									</td>
							</tr>
							</c:if>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
   </div>
</body>
</html>