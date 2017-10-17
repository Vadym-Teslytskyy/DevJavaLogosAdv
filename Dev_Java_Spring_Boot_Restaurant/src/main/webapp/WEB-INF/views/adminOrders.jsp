<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="custom" uri="/WEB-INF/tags/implicit.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">

<!-- Awesome font+icons -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
    
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
    <!-- My style CSS -->
    <link rel="stylesheet" href="/css/main_page.css" type="text/css">
<title>MyRestaurant(Admin-Orders)</title>
</head>
<body>

 <div class="container-fluid">
        <div class="row">
            <div class="col-12">
                <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
                    <a class="navbar-brand" href="/">MyRestaurant</a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav mr-auto hover-nav">
                            <li class="nav-item">
                                <a class="nav-link active" href="/meals">Meals</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/ingredients">Ingredients</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/places">Places</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Order</a>
                            </li>
                        </ul>
                        <%-- <form:form action="/meals" method="GET" modelAttribute="filter" class="form-inline my-2 my-lg-0" >
                            <form:input path="search" class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search"/>
                            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                        </form:form> --%>
                        <ul class="navbar-nav ml-auto hover-nav">
                            <li class="nav-item">
                            <sec:authorize access="isAnonymous()">
                                <a class="nav-link" href="/login">Sing in <i class="fa fa-sign-in" aria-hidden="true"></i>
                                </a>
                             </sec:authorize>
                             <sec:authorize access="hasRole('ROLE_CLIENT')">
                             <a href="#" class="btn btn-dark"><i class="fa fa-user" aria-hidden="true"></i>
                              ${message}</a>
                             </sec:authorize>
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
									<a href="/admin" class="btn btn-dark"><i class="fa fa-user" aria-hidden="true"></i> Admin</a>
								</sec:authorize>
                            </li>
                            <li class="nav-item">
                            	<sec:authorize access="isAnonymous()">
                                	<a class="nav-link" href="/registration">Sing up <i class="fa fa-user-plus" aria-hidden="true"></i>
                                	</a>
                                </sec:authorize>
                                <sec:authorize access="isAuthenticated()">
									<form:form action="/logout">
										<button class="btn btn-dark ml-1">Logout <i class="fa fa-sign-out" aria-hidden="true"></i>
										</button>
									</form:form>
								</sec:authorize>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        </div>
        </div>
               	<div class="container mt-5">
        	<!-- Accordion for show more ingredients or something else.-->
         <div class="row mt-1">
        <div id="accordion" role="tablist" class="col-12" >
            <div class="card">
                <div class="card-header" role="tab" id="headingOne">
                    <h5 class="mb-0">
                        <a data-toggle="collapse" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">Open to filtering or sorting</a>
                    </h5>
                </div>
                <div id="collapseOne" class="collapse " role="tabpanel" aria-labelledby="headingOne" data-parent="#accordion">
             <div class="card-body">
            <form:form action="/admin/order" method="GET" modelAttribute="orderFilter">
			<div class="row">
                    <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseMeals" aria-expanded="false" aria-controls="collapseMeals">Meals<i class="fa fa-arrow-down ml-1"></i></button>
				</div>
				<div id="collapseMeals" class="collapse">
                    <div class="card-body">
                    <div class="form-group row">
						<div class="col-12">
							<form:checkboxes items="${meals}" path="mealName" element="div" />
						</div>
					</div>
                	</div>
				</div>
			
			<div class="row mt-2">
				<button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapsePlaces" aria-expanded="false" aria-controls="collapsePlaces">Place numbers<i class="fa fa-arrow-down ml-1"></i></button>
				</div>
				<div id="collapsePlaces" class="collapse">
                    <div class="card-body">
                    <c:forEach var="place" items="${places}">
                    	<div class="form-group row">
							<div class="col-12">
								<form:checkbox path="placeNumber" value="${place.number}" element="div" />${place.number}
							</div>
						</div>
					</c:forEach>
                	</div>
				</div>
				<div class="row mt-2">
				<button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseStatuses" aria-expanded="false" aria-controls="collapseStatuses">Statuses<i class="fa fa-arrow-down ml-1"></i></button>
				</div>
				<div id="collapseStatuses" class="collapse">
                    <div class="card-body">
                    <div class="form-group row">
						<div class="col-12">
							<form:checkboxes items="${statuses}" path="status" element="div" />
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
								<custom:sort innerHtml="OrderId asc" paramValue="id"/>
								<custom:sort innerHtml="OrderId desc" paramValue="id,desc"/>
							</div>
					</div>
					<div class="col-6 text-center">
						<custom:size posibleSizes="1,2,5,10" size="${orders.size}" />
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
        <!-- Body -->
        <div class="container">
        	<div class="row">
        		<h3 class="mt-2">All orders:</h3>
        	</div>
        </div>
        <div class="container-fluid">
        <div class="row mt-2">
			<div class="col-12">
				<table class="table table-bordered">
					<tr>
						<th class="text-center">Order ID</th>
						<th class="text-center">Place</th>
						<th class="text-center">Time of order`s changes</th>
						<th class="text-center">Meals</th>
						<th class="text-center">Status</th>
						<th class="text-center">Options</th>
					</tr>
					<c:if test="${empty orders.content}">
		    			<tr>
		    			<td colspan=3><h3 class="text-center">No orders now!</h3></td>
		    			</tr>
					</c:if>
					<c:forEach var="order" items="${orders.content}">
						<tr>
							<td>${order.id}</td>
							<td>${order.place.number}</td>
							<td class="text-center">
								<p>${order.time}</p>
							</td>
							<td>
									<c:forEach var="orderedMeal" items="${order.meals}">
										<img src="${orderedMeal.photoUrl}?version=${orderedMeal.version}" style="height: 50px">${orderedMeal.name}
									</c:forEach>
							</td>
							<td>${order.status}</td>
							<td class="text-center">
								<div class="dropdown">
									<button class="btn btn-outline-warning dropdown-toggle" type="button"
										id="dropdownMenuButton" data-toggle="dropdown"
										aria-haspopup="true" aria-expanded="false">Change Status</button>
									<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
										<a class="dropdown-item" href="/admin/order/updateStatus/${order.id}/${2}<custom:allParams/>">Accepted</a>
										<a class="dropdown-item" href="/admin/order/updateStatus/${order.id}/${1}<custom:allParams/>">Is cooking</a>
										<a class="dropdown-item" href="/admin/order/updateStatus/${order.id}/${0}<custom:allParams/>">Is ready</a>
										<a class="dropdown-item" href="/admin/order/updateStatus/${order.id}/${3}<custom:allParams/>">Is paid</a>
									</div>
								</div> 
							</td>
						</tr>
					</c:forEach>
			</table>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<custom:pageable page="${orders}"/>
			</div>
		</div>
		</div>
       	<!-- /Body -->
         <div class="container-fluid">
        <div class="row footer">
            <div class="col-sm-3">
                <h1>Brand LOGO</h1>
            </div>
            <div class="col-sm-6">
                <h3>About us</h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nostrum nihil repudiandae id commodi quam totam sit atque quod, excepturi veritatis optio saepe impedit, earum, nemo cumque architecto incidunt porro deserunt.</p>
                <br>
                <p>© 2017 Vadym Teslytskyy </p>
                <h4>Contact me:</h4>
                <ul class="nav flex-column hover-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="https://plus.google.com/u/0/102680532622847810137"><i class="fa fa-google-plus-square"></i> Google+</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="https://github.com/Vadym-Teslytskyy"><i class="fa fa-github-square"></i> GitHub</a>
                    </li>
                    <li class="nav-item">
                                <a class="nav-link" href="https://www.linkedin.com/in/vadym-t-a97644a4/"><i class="fa fa-linkedin-square"></i> LinkedIn</a>
                            </li>
                </ul>
            </div>
            <div class="col-sm-3">
                <ul class="nav flex-column hover-nav">
                    <li class="nav-item">
                        <a class="nav-link active" href="/meals">Meals</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/ingredients">Ingredients</a>
                    </li>
                    <li class="nav-item">
                                <a class="nav-link" href="/places">Places</a>
                            </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Order</a>
                    </li>
                     <li class="nav-item">
                            <sec:authorize access="isAnonymous()">
                                <a class="nav-link" href="/login">Sing in <i class="fa fa-sign-in" aria-hidden="true"></i>
                                </a>
                             </sec:authorize>
                             <sec:authorize access="hasRole('ROLE_CLIENT')">
                             <a href="#" class="btn btn-dark"><i class="fa fa-user" aria-hidden="true"></i>
                              ${message}</a>
                             </sec:authorize>
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
									<a href="/admin" class="btn btn-dark mt-1"><i class="fa fa-user" aria-hidden="true"></i> Admin</a>
								</sec:authorize>
                            </li>
                            <li class="nav-item">
                            	<sec:authorize access="isAnonymous()">
                                	<a class="nav-link" href="/registration">Sing up <i class="fa fa-user-plus" aria-hidden="true"></i>
                                	</a>
                                </sec:authorize>
                                <sec:authorize access="isAuthenticated()">
									<form:form action="/logout">
										<button class="btn btn-dark mt-1">Logout <i class="fa fa-sign-out" aria-hidden="true"></i>
										</button>
									</form:form>
								</sec:authorize>
                            </li>

                </ul>
            </div>
        </div>
    </div>
    
	
</body>
</html>