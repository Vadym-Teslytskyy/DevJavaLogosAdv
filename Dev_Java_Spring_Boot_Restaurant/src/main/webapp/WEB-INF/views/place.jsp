<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="custom" uri="/WEB-INF/tags/implicit.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
<title>Place</title>
</head>
<body>
	<div class="container">
		<div class="row mt-2">
		<div class="col-4">
				<form:form action="/admin/place" method="GET" modelAttribute="filter">
					<div class="form-group row">
						<div class="col-12">
							<form:input class="form-control" path="search" placeholder="Search"/>
						</div>
					</div>
				</form:form>
			</div>
			<div class="col-8">
				<div class="row">
					<div class="col-6 text-center">
							<button class="dropdown-toggle btn btn-outline-primary btn-sm" type="button" data-toggle="dropdown">Sort
							</button>
							<div class="dropdown-menu">
								<custom:sort innerHtml="Number asc" paramValue="number"/>
								<custom:sort innerHtml="Number desc" paramValue="number,desc"/>
							</div>
					</div>
					<div class="col-6 text-center">
						<custom:size posibleSizes="1,2,5,10" size="${places.size}" />
					</div>
				</div>
			</div>
	</div>
	<div class="row">
			<div class="col-12">
				<form:form action="/admin/place" method="POST" modelAttribute="place">
				<custom:hiddenInputs excludeParams="countofPeople, number, name, _csrf"/>
					<div class="row">
						<div class="col-10 ml-auto" style="color:red;">
							<form:errors path="countofPeople"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-2 col-form-label" for="countOfPeople">Count of people:</label>
						<div class="col-10">
							<form:input class="form-control" id="countOfPeople" path="countofPeople" placeholder="Enter the max number of people that can sit at the place"/>
						</div>
					</div>
					<div class="row">
						<div class="col-10 ml-auto" style="color:red;">
							<form:errors path="number"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-2 col-form-label" for="number">Number of place:</label>
						<div class="col-10">
							<form:input class="form-control" id="number" path="number" placeholder="Enter the number of place"/>
						</div>
					</div>
					<div class="form-group row">
							<div class="col-10 ml-auto">
								<button class="btn btn-sm btn-outline-success">Save</button>
								<a href="/admin/place/cancel<custom:allParams/>" class="btn btn-sm btn-outline-warning">Cancel</a>
							</div>
						</div>
				</form:form>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<table class="table table-bordered">
					<tr>
						<th class="text-center">Number of place</th>
						<th class="text-center">Count of people</th>
						<th class="text-center">Is Free</th>
						<th class="text-center">Options</th>
					</tr>
					<c:forEach var="place" items="${places.content}">
						<tr>
							<td>${place.number}</td>
							<td>${place.countofPeople}</td>
							<td>${place.isFree}</td>
							<td class="text-center">
								<a href="/admin/place/update/${place.id}<custom:allParams/>" class="btn btn-outline-warning btn-sm">Update</a>
								<a href="/admin/place/delete/${place.id}<custom:allParams/>" class="btn btn-outline-danger btn-sm">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<a href="/admin">Back to admin page</a>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<custom:pageable page="${places}"/>
			</div>
		</div>
	</div>
</body>
</html>