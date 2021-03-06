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
<title>MyRestaurant(Places)</title>
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
                             <c:if test="${user.place != null }">
                            <li class="nav-item">
                                <a class="nav-link" href="/places/${user.place.id}/order">Order</a>
                            </li>
							</c:if>
							<c:if test="${user.place == null }">
                            <li class="nav-item">
                                <a class="nav-link" href="/places">Order</a>
                            </li>
                            </c:if>
                        </ul>
                        <form:form action="/meals" method="GET" modelAttribute="filter" class="form-inline my-2 my-lg-0" >
                            <form:input path="search" class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search"/>
                            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                        </form:form>
                        <ul class="navbar-nav ml-auto hover-nav">
                            <li class="nav-item">
                            <sec:authorize access="isAnonymous()">
                                <a class="nav-link" href="/login">Sing in <i class="fa fa-sign-in" aria-hidden="true"></i>
                                </a>
                             </sec:authorize>
                             <sec:authorize access="hasRole('ROLE_CLIENT')">
                             <a href="/profile/${user.id}" class="btn btn-dark"><i class="fa fa-user" aria-hidden="true"></i>
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
        
        <!-- Body -->
        <div class="container-fluid">
        	<div class="row mt-5">
        		<!-- Table with places -->
        		<div class="col-5 mt-4">
        			<div class="row">
						<div class="col-12">
						<h4 style="font-style:bold;">All of this places are free now:</h4>
							<table class="table table-bordered">
								<tr>
									<th class="text-center">Number of place</th>
									<th class="text-center">Count of people</th>
									<th class="text-center">Options</th>
					</tr>
					<c:forEach var="place" items="${places.content}">
						<tr>
							<td>${place.number}</td>
							<td>${place.countofPeople}</td>
							<td class="text-center">
							<sec:authorize access="isAnonymous()">
                                <a href="/login<custom:allParams/>" class="btn btn-outline-success btn-sm">Reserve(Please sing in)</a>
                             </sec:authorize>
                             <sec:authorize access="isAuthenticated()">
								<a href="/places/${place.id}" class="btn btn-outline-success btn-sm">Reserve</a>
							</sec:authorize>
							<sec:authorize access="isAuthenticated()">
								<a href="/places/${place.id}/cancel" class="btn btn-outline-warning btn-sm">Cancel</a>
							</sec:authorize>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
        		</div>
        		<!-- Image of places -->
        		<div class="col-7 mt-4">
        			<img alt="Error" src="/images/8bbf376af40a1dfddc7147f871860d2c.jpg" class="img-fluid">
        		</div>
        	</div>
        </div>
        <div class="container">
        				<div class="row mt-2">
		<div class="col-4">
				<form:form action="/places" method="GET" modelAttribute="filter">
					<div class="form-group row">
						<div class="col-12">
							<form:input class="form-control" path="search" placeholder="Search by place number"/>
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
				<custom:pageable page="${places}"/>
			</div>
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
                    <c:if test="${user.place != null }">
                            <li class="nav-item">
                                <a class="nav-link" href="/places/${user.place.id}/order">Order</a>
                            </li>
							</c:if>
							<c:if test="${user.place == null }">
                            <li class="nav-item">
                                <a class="nav-link" href="/places">Order</a>
                            </li>
                            </c:if>
                     <li class="nav-item">
                            <sec:authorize access="isAnonymous()">
                                <a class="nav-link" href="/login">Sing in <i class="fa fa-sign-in" aria-hidden="true"></i>
                                </a>
                             </sec:authorize>
                             <sec:authorize access="hasRole('ROLE_CLIENT')">
                             <a href="/profile/${user.id}" class="btn btn-dark"><i class="fa fa-user" aria-hidden="true"></i>
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