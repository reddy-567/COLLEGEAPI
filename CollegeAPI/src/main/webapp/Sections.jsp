<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- <input type="button" value="Home" onClick="/home.jsp"> -->
	<h2>All Sections in Department [${dep.getName()}  ,  Id:${dep.getId()}]</h2>
	<p>${obj }</p>
	<h4>Enter Section id to view all  students of Section</h4>
	<form action="/getStudentsBySectionId" method="get">
	<input type="number" name="id">
	<input type="submit">
		</form>
	
	<h4>Enter Details to add Section To Specific Department</h4>
	
		<form action="/addSectionToDepartment" method="post">
	<h2>Enter Department Id To add Section</h2>
	<input type="number" name="did"><br>
	<h2>Enter Section Details To add</h2>
	
	
		<label>Section Id : </label>
		<input type="number" name="id"><br>
		<label>Section Name : </label>
		<input type="text" name="sname"><br>
		
		<input type="submit"><br>
	</form>
	
	
</body>
</html>