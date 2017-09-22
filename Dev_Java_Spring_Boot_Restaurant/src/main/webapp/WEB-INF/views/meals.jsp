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
<title>MyRestaurant(Meals-Menu)</title>
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
                                <a class="nav-link" href="#">Order</a>
                            </li>
                        </ul>
                        <form:form action="/meals" method="GET" modelAttribute="mealFilter" class="form-inline my-2 my-lg-0" >
                        <custom:hiddenInputs excludeParams="name, _csrf"/>
                            <form:input path="search" class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search"/>
                            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                        </form:form>
                        <ul class="navbar-nav ml-auto hover-nav">
                            <li class="nav-item">
                            <sec:authorize access="isAnonymous()">
                                <a class="nav-link" href="/login">Sing in</a>
                             </sec:authorize>
                             <sec:authorize access="hasRole('ROLE_CLIENT')">
                             <a href="#" class="btn btn-dark">${message}</a>
                             </sec:authorize>
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
									<a href="/admin" class="btn btn-dark">Admin</a>
								</sec:authorize>
                            </li>
                            <li class="nav-item">
                            	<sec:authorize access="isAnonymous()">
                                	<a class="nav-link" href="/registration">Sing up</a>
                                </sec:authorize>
                                <sec:authorize access="isAuthenticated()">
									<form:form action="/logout">
										<button class="btn btn-dark ml-1">Logout</button>
									</form:form>
								</sec:authorize>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        </div>
        </div>
         <!-- Accordion for show more ingredients or something else.-->
        <!-- <div class="container">
        <div id="accordion" role="tablist" class="mt-10">
            <div class="card">
                <div class="card-header" role="tab" id="headingOne">
                    <h5 class="mb-0">
                        <a data-toggle="collapse" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">Open to filtering</a>
                    </h5>
                </div>
                <div id="collapseOne" class="collapse " role="tabpanel" aria-labelledby="headingOne" data-parent="#accordion">
                    <div class="card-body">
                        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                    </div>
                </div>
            </div>
        </div>
	</div> -->
	<div class="container">
		<div class="row mt-2">
			<div class="col-12">
				<form:form action="/meals" method="GET" modelAttribute="mealFilter">
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
					<div class="form-group row">
						<div class="col-12">
							<form:checkboxes items="${cuisines}" path="cuisinesIds" element="div" />
						</div>
					</div>
					<div class="form-group row">
						<div class="col-12">
							<form:checkboxes items="${ingredients}" path="ingredientIds" element="div" />
						</div>
					</div>
					<div class="form-group row">
						<div class="col-12">
        					<button type="submit" class="btn btn-outline-success btn-sm">Search with filtering</button>
        					<a href="/meals/cancel<custom:allParams/>" class="btn btn-sm btn-outline-warning">Cancel</a>
      					</div>
					</div>
				</form:form>
			</div>
		</div>
		<div class="row mt-2">
			<div class="col-8">
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
	</div>
	<div class="container">
	<div class="row">
     <c:forEach var="meal" items="${meals.content}">
			<div class="col-sm-4">
                    <div class="img-hover">
                        <a href="#"><img src="<%-- ${meal.photoUrl} --%>/slider-images/slider-image-1.jpg" title="Show more" alt="Meal-Image" class="rounded-circle img-fluid" style="width:220px; height:220px;"></a>
                    </div>
                    <div class="caption">
                        <h3>${meal.name}</h3>
                        <h4>${meal.rate}</h4>
                        <p>${meal.shortDescription}</p>
                        <p><a href="#" class="btn btn-default" role="button">Show more <span class="glyphicon glyphicon-chevron-right"></span></a></p>
                    </div>
            </div>
		</c:forEach> 
    </div>
         <div class="row">
			<div class="col-12">
				<custom:pageable page="${meals}"/>
			</div>
		</div>
    </div>
        
         <div class="container-fluid">
        <div class="row footer">
            <div class="col-sm-3">
                <h1>Brand LOGO</h1>
            </div>
            <div class="col-sm-6">
                <h3>About us</h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nostrum nihil repudiandae id commodi quam totam sit atque quod, excepturi veritatis optio saepe impedit, earum, nemo cumque architecto incidunt porro deserunt.</p>
            </div>
            <div class="col-sm-3">
                <ul class="nav flex-column hover-nav">
                    <li class="nav-item">
                        <a class="nav-link active" href="#">Meals</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Ingredients</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Order</a>
                    </li>
                     <li class="nav-item">
                            <sec:authorize access="isAnonymous()">
                                <a class="nav-link" href="/login">Sing in</a>
                             </sec:authorize>
                             <sec:authorize access="hasRole('ROLE_CLIENT')">
                             <a href="#">${message}</a>
                             </sec:authorize>
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
									<a href="/admin" class="btn btn-dark mt-1">Admin</a>
								</sec:authorize>
                            </li>
                            <li class="nav-item">
                            	<sec:authorize access="isAnonymous()">
                                	<a class="nav-link" href="/registration">Sing up</a>
                                </sec:authorize>
                                <sec:authorize access="isAuthenticated()">
									<form:form action="/logout">
										<button class="btn btn-dark mt-1">Logout</button>
									</form:form>
								</sec:authorize>
                            </li>

                </ul>
            </div>
        </div>
    </div>
    
	
	
</body>
</html>