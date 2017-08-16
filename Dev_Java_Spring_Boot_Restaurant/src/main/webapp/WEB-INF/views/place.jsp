<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
<title>Place</title>
</head>
<body>
	<div class="container">
	<div class="row">
			<div class="col-12">
				<form action="/admin/place" method="POST">
					<div class="form-group row">
						<label class="col-2 col-form-label" for="countOfPeople">Count of people:</label>
						<div class="col-10">
							<input class="form-control" id="countOfPeople" name="countofPeople" placeholder="Enter the max number of people that can sit at the place">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-2 col-form-label" for="number">Number of place:</label>
						<div class="col-10">
							<input class="form-control" id="number" name="number" placeholder="Enter the number of place">
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
						<th class="text-center">Number of place</th>
						<th class="text-center">Count of people</th>
						<th class="text-center">Is Free</th>
						<th class="text-center">Options</th>
					</tr>
					<c:forEach var="place" items="${places}">
						<tr>
							<td>${place.number}</td>
							<td>${place.countofPeople}</td>
							<td>${place.isFree}</td>
							<td class="text-center">
								<a href="/admin/place/update/${place.id}" class="btn btn-outline-warning btn-sm">Update</a>
								<a href="/admin/place/delete/${place.id}" class="btn btn-outline-danger btn-sm">Delete</a>
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