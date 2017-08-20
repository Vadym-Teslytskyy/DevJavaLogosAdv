<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
<title>Meal</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-12">
				<form action="/admin/meal" method="POST">
				
<!-- 					<div class="form-check row"> -->
<!-- 					<label class="col-2 col-form-label">Components:</label> -->
<!-- 					<div class="col-10"> -->
<%-- 					<c:forEach var="component" items="${components}"> --%>
<!-- 									<label class="form-check-label"> -->
<%--       									<input type="checkbox" class="form-check-input" value="idComponents" name="idComponents"> ${component.ingredient} ${component.amount} ${component.ms} --%>
<!--     								</label> -->
<%-- 					</c:forEach> --%>
<!-- 					</div> -->
<!--   					</div> -->

					<div class="form-group row">
						<label class="col-2 col-form-label" for="component">Components:</label>
						<div class="col-10">
							<select class="form-control" id="component" name="idComponents" multiple>
								<c:forEach var="component" items="${components}">
									<option value="${component.id}">${component.ingredient} ${component.amount} ${component.ms}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-2 col-form-label" for="cuisine">Cuisine:</label>
						<div class="col-10">
							<select class="form-control" id="cuisine" name="cuisine">
								<c:forEach var="cuisine" items="${cuisines}">
									<option value="${cuisine}">${cuisine}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-2 col-form-label" for="name">Name:</label>
						<div class="col-10">
							<input class="form-control" id="name" name="name">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-2 col-form-label" for="fullDescription">Full description:</label>
						<div class="col-10">
						 <textarea class="form-control form-rounded" rows="3" id="fullDescription" name="fullDescription">Full Description in 3 rows</textarea>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-2 col-form-label" for="price">Price:</label>
						<div class="col-10">
							<input class="form-control" id="price" name="price">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-2 col-form-label" for="weight">Weight:</label>
						<div class="col-10">
							<input class="form-control" id="weight" name="weight">
						</div>
					</div>
					<div class="form-group row">
						<div class="col-8 mr-auto">
							<button class="btn btn-sm btn-outline-success">Save</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<table class="table table-bordered">
					<tr>
						<th class="text-center">Name</th>
						<th class="text-center">Rate</th>
						<th class="text-center">Full Description</th>
						<th class="text-center">Cuisine</th>
						<th class="text-center">Price</th>
						<th class="text-center">Weight</th>
						<th class="text-center">Components</th>
						<th class="text-center">Options</th>
					</tr>
					<c:forEach var="meal" items="${meals}">
						<tr>
							<td>${meal.name}</td>
							<td>${meal.rate}</td>
							<td>${meal.fullDescription}</td>
							<td>${meal.cuisine}</td>
							<td>${meal.price}</td>
							<td>${meal.weight}</td>
							<td>
							<c:forEach var="meal" items="${meals}">
									<label class="form-check-label"> ${meal.components}</label>
    						</c:forEach>
							</td>
							<td class="text-center">
								<a href="/admin/meal/update/${meal.id}" class="btn btn-outline-warning btn-sm">Update</a>
								<a href="/admin/meal/delete/${meal.id}" class="btn btn-outline-danger btn-sm">Delete</a>
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
	</div>
</body>
</html>