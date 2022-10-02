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
<title>Job Details</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/style.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family="Quicksand">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
<style>
body {
	font-family: "Quicksand", sans-serif;
	background-color: #f5fffa;
}
</style>
<!-- change to match your file/naming structure -->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container mt-5">
		<div class="d-flex justify-content-end align-items-center">
			<a href="/archives" class="btn btn-dark ms-3">Archives</a>
			<a href="/interviews/add" class="btn btn-dark ms-3">Add interview</a>
			<a href="/dashboard" class="btn"><span class="material-symbols-outlined">home</span></a> 
			<a href="/logout" class="ms-3 text-dark"><span class="material-symbols-outlined">logout</span></a>
		</div>
		<div class="d-flex justify-content-center">
			<div class="mt-5">
				<h1>${job.company} Job Details</h1>
				<h5 class="text-center">Status: <span style="color:orange">${job.status}</span></h5>
			</div>
		</div>
		<div class="d-flex justify-content-between">
			<div class="mt-5">
				<table class="table table-borderless">
					<tbody>
					<tr>
						<td><strong>Company:</strong></td>
						<td>${job.company}</td>
					</tr>			
					<tr>
						<td><strong>Position:</strong></td>
						<td>${job.position}</td>
					</tr>
					<tr>
						<td><strong>Skills:</strong></td>
						<td>${job.skills}</td>
					</tr>
					<tr>
						<td><strong>Location:</strong></td>
						<td>${job.location}</td>
					</tr>
					<tr>
						<td><strong>Salary:</strong></td>
						<td>$${job.salary}</td>
					</tr>

					<tr>
						<td><strong>Remote:</strong></td>
						<c:choose>
							<c:when test="${job.remote == 'true'}">
								<td>Yes</td>
							</c:when>
							<c:otherwise>
								<td>No</td>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<td><strong>Date applied:</strong></td>
						<td><fmt:formatDate pattern="MM-dd-yyyy" value="${job.date}"></fmt:formatDate></td>
					</tr>
					<tr>
						<td><strong>Source:</strong></td>
						<td><a href="${job.source}">${job.source}</a></td>
					</tr>
					</tbody>
				</table>
				<div class="d-flex justify-content-center align-items-center">
					<div>
						<a href="/jobs/${job.id}/edit" class="btn"><span class="material-symbols-outlined">edit_note</span></a>
					</div>
					<div>
						<form action="/jobs/${job.id}/delete" method="post">
							<input type="hidden" name="_method" value="delete" /> <input
								type="hidden" name=job value="${job.id}" />
							<button type="submit" class="btn mt-2"><span class="material-symbols-outlined">delete</span></button>
						</form>
					</div>
				</div>
			</div>
			<div class="mt-5">
				<div class="">
					<h4 class="test text-center">Scheduled interviews:</h4>
					<table class="table table-striped text-center">
						<thead>
							<tr>
								<th>Position</th>
								<th>Description</th>
								<th>Date</th>
								<th>Time</th>
								<th>Location</th>
								<th>Options</th>
							</tr>
						</thead>
						<c:forEach var="interview" items="${interviewList}">
							<c:if test="${job.id == interview.job.id}">
								<tr>
									<td><c:out value="${interview.job.position}" /></td>
									<td><c:out value="${interview.description}" /></td>
									<td><fmt:formatDate pattern="E MM/dd/yy"
											value="${interview.date}" /></td>
									<td><fmt:formatDate pattern="hh:mm a"
											value="${interview.time}" /></td>
									<c:choose>
									<c:when test="${interview.location == 'location'}">
										<td><c:out value="${interview.address}" /></td>
									</c:when>
									<c:otherwise>
										<td><a href="${interview.address}"><c:out value="${interview.address}" /></a></td>										
									</c:otherwise>
									</c:choose>
										<td>
											<form action="/interviews/${interview.id}/delete" method="post">
											<input type="hidden" name="_method" value="delete"/>
											<a href="/interviews/${interview.id}/edit" class="btn" ><span class="material-symbols-outlined">edit_note</span></a>			
											<button type="submit" class="btn"><span class="material-symbols-outlined">free_cancellation</span></button>
											</form>
										</td>
								</tr>
							</c:if>
						</c:forEach>
					</table>
				</div>
				<h4 class="test text-center">Comments:</h4>
				<div class="d-flex justify-content-center">
					<div style="width: 40rem; height: 18rem; overflow-y: auto"
							class="card overflow-y p-3 d-flex">
						<table class="table table-borderless">
							<c:forEach var="eachComment" items="${commentList}">
								<c:if test="${job.id == eachComment.job.id }">
									<tr class="">
										<td>
											<strong><fmt:formatDate pattern="MM/dd/yy hh:mm a" value="${eachComment.createdAt}" /></strong>								
										</td>
										<td>
											<em><c:out value="${eachComment.comment}" /></em> 
										</td>
										<td>
											<form action="/comments/${eachComment.id}/delete" method="post">
												<input type="hidden" name="_method" value="delete" />
												<button type="submit" class="btn ms-2 text-dark" style=""><span class="material-symbols-outlined">delete</span></button>
											</form>
										</td>											
									</tr>
								</c:if>
							</c:forEach>
						</table>	
					</div>
				</div>
				<div class="mt-3 d-flex justify-content-center">
					<form:form action="/comments/add" method="post"
						modelAttribute="comment">
						<form:hidden path="job" value="${job.id}" />
						<form:input type="text" path="comment" class="form-control" style="width: 30rem" />
						<form:errors path="comment" class="text-danger" />
						<div class="d-flex justify-content-center">
							<button type="submit" class="btn btn-dark btn-sm mt-2">Add
								comment</button>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>