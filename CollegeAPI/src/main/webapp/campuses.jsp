<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>All Campuses of RGUKT</h2>
	
	<p>${obj}</p>
	
	<a href="/getAllDepartments">click here to view the departments in all campuses</a>
	<h4>Enter campus id to know the departments of campus</h4>
	<form action="/getDepartmentsById" method="get">
	<input type="number" name="id">
	<input type="submit">
		</form>
		
	<h2>Enter Campus Details To add</h2>
	
	<form action="/addCampus" method="post">
		<label>Campus Id : </label>
		<input type="number" name="id"><br>
		<label>Campus Name : </label>
		<input type="text" name="name"><br>
		<label>Campus Location : </label>
		<input type="text" name="location"><br>
		
		<input type="submit"><br>
	</form>
	
	
</body>
</html>