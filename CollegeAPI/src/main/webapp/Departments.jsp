<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%! int i; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>All Departments in Campus ${campus.getName() }</h2>
	<p>${obj}</p>
	
	<h4>Enter department id to know the Sections of Department</h4>
	<form action="/getSectionsByDepartmentId" method="get">
	<input type="number" name="id">
	<input type="submit">
		</form>
		
		<form action="/addDepartmentToCampus" method="post">
	<h2>Enter Campus Id to To add Department</h2>
	<input type="number" name="cid"><br>
	<h2>Enter Department Details To add</h2>
	
	
		<label>Department Id : </label>
		<input type="number" name="id"><br>
		<label>Department Name : </label>
		<input type="text" name="name"><br>
		<label>HOD : </label>
		<input type="text" name="HOD"><br>
		
		<input type="submit"><br>
	</form>
	
		
</body>
</html>