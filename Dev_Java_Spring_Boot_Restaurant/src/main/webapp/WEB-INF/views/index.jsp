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
    <link rel="stylesheet" href="/css/main_page.css" type="text/css">
<title>MyRestaurant</title>
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
                                <a class="nav-link" href="/meals">Meals</a>
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
                        <form class="form-inline my-2 my-lg-0">
                            <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
                            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                        </form>
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
        <div class="container-fluid d-none d-sm-block">
        <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
                <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
            </ol>
            <div class="carousel-inner" role="listbox">
                <div class="carousel-item active">
                    <img class="d-block img-fluid" src="/slider-images/slider-image-1.jpg" alt="First slide" style="width:100%; height: 410px;">
                </div>
                <div class="carousel-item">
                    <img class="d-block img-fluid" src="/slider-images/slider-image-2.jpg" alt="Second slide" style="width:100%; height: 410px;">
                </div>
                <div class="carousel-item">
                    <img class="d-block img-fluid" src="/slider-images/slider-image-3.jpg" alt="Third slide" style="width:100%; height: 410px;">
                </div>
            </div>
            <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
    </div>
    <div class="container">
    	<div class="row meals-container-first-row">
    	<c:forEach var="meal" items="${meals}" end="5">
			<div class="col-sm-4">
                    <div class="img-hover">
                        <a href="/meal/${meal.id}"><img src="${meal.photoUrl}?version=${meal.version}" title="Show more" alt="..." class="rounded-circle img-fluid" style="width:220px; height:220px;"></a>
                    </div>
                    <div class="caption">
                        <h3>${meal.name}</h3>
                        <h4>${meal.rate}</h4>
                        <h5>${meal.price} $</h5>
                        <p>${meal.shortDescription}</p>
                        <div class="row">
                        	<div class="col-4">
                        		<p><a href="/meal/${meal.id}" class="btn btn-default" role="button">Show more <span class="glyphicon glyphicon-chevron-right"></span></a></p>
                     		</div>
                     		<div class="col-4">
                     			<c:if test="${user.place != null }">
                                			<sec:authorize access="isAuthenticated()">
                             					<form:form action="/places/${user.place.id}/order" method="POST" modelAttribute="order">
													<button class="btn btn-success">Buy now!</button>
												</form:form>
                     						</sec:authorize>
										</c:if>
										<c:if test="${user.place == null }">
												<sec:authorize access="isAuthenticated()">
                             					<a class="nav-link" href="/places">Buy now!(But first reserve place)</a>
                     							</sec:authorize>
                     							<sec:authorize access="isAnonymous()">
                             					<a class="nav-link" href="/login">Buy now!(But first sing in)</a>
                     							</sec:authorize>
                            			</c:if>
                     		</div>
                     </div>
                    </div>
            </div>
				</c:forEach>
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