<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
<title>Admin</title>
</head>
<body>
	<div class="container">
        <div class="row">
            <div class="col-6 text-center ml-auto mr-auto">
                <h2>Admin</h2>
                <div class="btn-block">
                    <a href="/admin/ingredient" class="btn btn-light btn-lg btn-block">Ingredient adding</a>
                    <a href="/admin/cuisine" class="btn btn-light btn-lg btn-block">Cuisine adding</a>
                    <a href="/admin/ms" class="btn btn-light btn-lg btn-block">Measuring system adding</a>
                    <a href="/admin/place" class="btn btn-light btn-lg btn-block">Place adding</a>
                    <a href="/admin/component" class="btn btn-light btn-lg btn-block">Component adding</a>
                    <a href="/admin/meal" class="btn btn-light btn-lg btn-block">Meal adding</a>
                    <a href="/" class="btn btn-default btn-lg btn-block">Back to main page</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>