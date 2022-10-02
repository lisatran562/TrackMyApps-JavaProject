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
<title>Dashboard</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/dashboard.css">
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
	<div class="container mt-5 mb-5">
			<div class="d-flex justify-content-between">
				<div style="d-flex align-items-center">
					<c:choose>
					<c:when test="${user.github != null || user.github != ''}">
					<a href="${user.github}"><img src="/images/Octocat.png" alt="github img" style="width:40px" class=""></a>
					</c:when>
					<c:otherwise>
					<a href="http://github.com"><img src="/images/Octocat.png" alt="github img" style="width:40px" class=""></a>
					</c:otherwise>
					</c:choose>
					
					<c:choose>
					<c:when test="${user.linkedin != null || user.linkedin != ''}">
					<a href="${user.linkedin}"><img src="/images/linkedin.png" alt="linkedin img" style="button; width:30px"></a>
					</c:when>
					<c:otherwise>
					<a href="http://linkedin.com"><img src="/images/linkedin.png" alt="linkedin img" style="button; width:30px"></a>
					</c:otherwise>
					</c:choose>
					
					<c:choose>
					<c:when test="${user.resume != null || user.resume != ''}">
					<a href="${user.resume}" style="button; color:lightcoral" class="ms-2">My resume</a>
					</c:when>
					<c:otherwise>
					<a href="http://docs.google.com" style="button; color:lightcoral" class="ms-2">My resume</a>
					</c:otherwise>
					</c:choose>
					<a href="/users/questions" style="color:lightcoral" class="ms-2">Interview questions</a>
				</div>
				<div>
				</div>
				
				<div class="d-flex align-items-center">
					<a href="/archives" class="btn btn-dark">Archives</a>
					<a href="/interviews/add" class="btn btn-dark ms-3">Add interview</a>
					<a href="/users/${user.id}/edit" class="btn btn-dark ms-3">Edit profile</a>
					<a href="/logout" style="color:black" class="ms-3"><span class="material-symbols-outlined">logout</span></a>
				</div>
			</div>
			<div class="text-center mt-5">
				<h1>Hi <c:out value="${user.firstName}"/>! Welcome back!</h1>
				<h5>The beginning of your job search journey...</h5>
			</div>

			<div class="d-flex justify-content-between">
				<form:form action="/jobs/add" method="post" modelAttribute="job">
				<form:hidden path="user" value="${userId}" />
				<div>
					<h4 class="mt-5">Add a Job</h4>
					<div class="">
						<form:label path="company" class="form-label">Company:</form:label>
						<form:input type="text" path="company" class="form-control" />
						<form:errors path="company" class="text-danger" />
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
						<div>
							<form:label path="date" class="form-label">Date applied:</form:label>
							<form:input type="date" path="date" class="form-control" />
							<form:errors path="date" class="text-danger" />
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
					<div class="mt-2">
						Remote:
						<form:checkbox path="remote" value="true" />
					</div>
						<button type="submit" class="btn btn-dark mt-3">Add</button>
					</div>
				</div>
				<div class="mt-5 d-flex align-items-center">

				</div>
				</form:form>
				<div>
					<div class="mt-5">
						<div>
							<h4 class="text-center">Jobs applied to so far:</h4>
						</div>
						<div style="width: 45rem; height: 25rem; overflow-y: auto"
							class="card overflow-y p-3">
							<table class="table table-light text-center">
								<thead>
									<tr>
										<th>Company</th>
										<th>Position</th>
										<th>Skills</th>
										<th>Date applied</th>
										<th>Status</th>
										<th>Favorite</th>
										<th>Archive</th>
									</tr>
								</thead>
								<c:forEach var="job" items="${jobList}">
									<c:if test="${userId == job.user.id}">
										<tr>
											<td><a href="/jobs/${job.id}"><c:out
														value="${job.company}" /></a></td>
											<td><c:out value="${job.position}" /></td>
											<td><c:out value="${job.skills}" /></td>
											<td><fmt:formatDate pattern="MM/dd/yy"
													value="${job.date}" /></td>
											<td><c:out value="${job.status}"/></td>
											<c:choose>
											<c:when test="${job.userFavorites.contains(user)}">
											<td>
												<form action="/jobs/unfavorite/${job.id}" method="post">
													<input type="hidden" name="_method" value="put"/>
													<button type="submit" style="border:none; background-color:none">❤️</button>
												</form>
											</td>
											</c:when>
											<c:otherwise>
												<td>
													<form action="/jobs/favorite/${job.id}" method="post">
														<input type="hidden" name="_method" value="put"/>
														<button type="submit" style="border:none; background-color:none">🤍</button>
													</form>
												</td>
											</c:otherwise>
											</c:choose>
											<td>
												<form action="archives/add" method="post">
													<input type="hidden" name="job" value="${job.id}"/>
													<input type="hidden" name="user" value="${job.user.id}"/>
													<button type="submit" style="border:none; background-color:none"><span class="material-symbols-outlined">send_and_archive</span></button>
												</form>
											</td>
										</tr>
									</c:if>
								</c:forEach>
							</table>
						</div>
					</div>
					<div class="mt-5">
						<div>
							<h4 class="text-center">Interviews scheduled:</h4>
						</div>
						<div style="width: 45rem; height: 25rem; overflow-y: auto"
							class="card overflow-y p-3">
							<table class="table table text-center">
								<thead>
									<tr>
										<th>Company</th>
										<th>Position</th>
										<th>Description</th>
										<th>Date</th>
										<th>Time</th>
										<th>Archive</th>
									</tr>
								</thead>
								<c:forEach var="interview" items="${interviewList}">
									<c:if test="${userId == interview.job.user.id}">
										<tr>
											<td><a href="/jobs/${interview.job.id}"><c:out
														value="${interview.job.company}" /></a></td>
											<td><c:out value="${interview.job.position}" /></td>
											<td><c:out value="${interview.description}" /></td>
											<td><fmt:formatDate pattern="E MM/dd/yy"
													value="${interview.date}" /></td>
											<td><fmt:formatDate pattern="hh:mm a"
													value="${interview.time}" /></td>
											<td>
												<form action="/archives/add" method="post">
													<input type="hidden" name="interview" value="${interview.id}"/>
													<input type="hidden" name="user" value="${user.id}"/>
													<button type="submit" style="border:none; background-color:none"><span class="material-symbols-outlined">send_and_archive</span></button>
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
		
	</div>
</body>
</html>