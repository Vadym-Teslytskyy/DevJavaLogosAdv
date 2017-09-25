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
<!-- Awesome font+icons -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
<title>Meal</title>
</head>
<body>
<div class="container">
	<!-- Accordion for show more ingredients or something else.-->
         <div class="row">
        <div id="accordion" role="tablist" class="col-12">
            <div class="card">
                <div class="card-header" role="tab" id="headingOne">
                    <h5 class="mb-0">
                        <a data-toggle="collapse" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">Open to filtering or sorting</a>
                    </h5>
                </div>
                <div id="collapseOne" class="collapse " role="tabpanel" aria-labelledby="headingOne" data-parent="#accordion">
                    <div class="card-body">
                        				<form:form action="/admin/meal" method="GET" modelAttribute="mealFilter">
					<div class="form-group row">
						<div class="col-12">
							<form:input path="search" class="form-control" placeholder="Search"/>
						</div>
					</div>
					<div class="form-group row">
						<div class="col-6">
							<form:input path="minRate" class="form-control" placeholder="Min rate"/>
						</div>
						<div class="col-6">
							<form:input path="maxRate" class="form-control" placeholder="Max rate"/>
						</div>
					</div>
					<div class="form-group row">
						<div class="col-6">
							<form:input path="minPrice" class="form-control" placeholder="Min price"/>
						</div>
						<div class="col-6">
							<form:input path="maxPrice" class="form-control" placeholder="Max prcice"/>
						</div>
					</div>
					<div class="form-group row">
						<div class="col-6">
							<form:input path="minWeight" class="form-control" placeholder="Min wieght"/>
						</div>
						<div class="col-6">
							<form:input path="maxWeight" class="form-control" placeholder="Max wieght"/>
						</div>
					</div>
			<div class="row">
                    <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseCuisines" aria-expanded="false" aria-controls="collapseCuisines">Cuisines<i class="fa fa-arrow-down ml-1"></i></button>
				</div>
				<div id="collapseCuisines" class="collapse">
                    <div class="card-body">
                    <div class="form-group row">
						<div class="col-12">
							<form:checkboxes items="${cuisines}" path="cuisinesIds" element="div" />
						</div>
					</div>
                	</div>
				</div>
			
			<div class="row mt-2">
				<button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseIngredients" aria-expanded="false" aria-controls="collapseIngredients">Ingredients<i class="fa fa-arrow-down ml-1"></i></button>
				</div>
				<div id="collapseIngredients" class="collapse">
                    <div class="card-body">
                    <div class="form-group row">
						<div class="col-12">
							<form:checkboxes items="${ingredients}" path="ingredientIds" element="div" />
						</div>
					</div>
                	</div>
				</div>
			<div class="row mt-2">
			<div class="col-4 ml-auto">
				<div class="row">
					<div class="col-6 text-center">
							<button class="dropdown-toggle btn btn-outline-primary btn-sm" type="button" data-toggle="dropdown">Sort
							</button>
							<div class="dropdown-menu">
								<custom:sort innerHtml="Name asc" paramValue="name"/>
								<custom:sort innerHtml="Name desc" paramValue="name,desc"/>
							</div>
					</div>
					<div class="col-6 text-center">
						<custom:size posibleSizes="1,2,5,10" size="${meals.size}" />
					</div>
				</div>
			</div>
	</div>
					<div class="form-group row mt-4">
						<div class="col-12">
        					<button type="submit" class="btn btn-outline-success btn-sm">Search</button>
      					</div>
					</div>
				</form:form>
                    </div>
                </div>
            </div>
        </div>
	</div>
	<!-- /Accordion -->
	</div>
	<div class="container mt-2">
		<div class="row">
			<div class="col-12">
				<form:form action="/admin/meal" method="POST" modelAttribute="meal">
				<custom:hiddenInputs excludeParams="name, _csrf"/>
					<div class="form-group row">
						<label class="col-2 col-form-label" for="component">Components:</label>
						<div class="col-10">
							<form:select class="form-control" id="component" path="components" items="${components}" multiple="multiple" itemValue="id"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-2 col-form-label" for="cuisine">Cuisine:</label>
						<div class="col-10">
							<form:select class="form-control" id="cuisine" path="cuisine" items="${cuisines}"/>
						</div>
					</div>
					<div class="row">
						<div class="col-10 ml-auto" style="color:red;">
							<form:errors path="name"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-2 col-form-label" for="name">Name:</label>
						<div class="col-10">
							<form:input class="form-control" id="name" path="name"/>
						</div>
					</div>
					<div class="row">
						<div class="col-10 ml-auto" style="color:red;">
							<form:errors path="fullDescription"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-2 col-form-label" for="fullDescription">Full description:</label>
						<div class="col-10">
						 <form:textarea class="form-control form-rounded" rows="3" id="fullDescription" path="fullDescription" placeholder="Full Descroption in 3 rows"/>
						</div>
					</div>
					<div class="row">
						<div class="col-10 ml-auto" style="color:red;">
							<form:errors path="price"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-2 col-form-label" for="price">Price:</label>
						<div class="col-10">
							<form:input class="form-control" id="price" path="price"/>
						</div>
					</div>
					<div class="row">
						<div class="col-10 ml-auto" style="color:red;">
							<form:errors path="weight"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-2 col-form-label" for="weight">Weight:</label>
						<div class="col-10">
							<form:input class="form-control" id="weight" path="weight"/>
						</div>
					</div>
					<div class="form-group row">
							<div class="col-10 ml-auto">
								<button class="btn btn-sm btn-outline-success">Save</button>
								<a href="/admin/meal/cancel<custom:allParams/>" class="btn btn-sm btn-outline-warning">Cancel</a>
							</div>
					</div>
				</form:form>
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
						<th class="text-center">Options</th>
					</tr>
					<c:forEach var="meal" items="${meals.content}">
						<tr>
							<td>${meal.name}</td>
							<td>${meal.rate}</td>
							<%-- <td>${meal.fullDescription}</td>
							<td>${meal.cuisine.name}</td>
							<td>${meal.price}</td>
							<td>${meal.weight}</td> --%>
							<td class="text-center">
								<a href="/admin/meal/update/${meal.id}<custom:allParams/>" class="btn btn-outline-warning btn-sm">Update</a>
								<a href="/admin/meal/delete/${meal.id}<custom:allParams/>" class="btn btn-outline-danger btn-sm">Delete</a>
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
				<custom:pageable page="${meals}"/>
			</div>
		</div>
	</div>
</body>
</html>