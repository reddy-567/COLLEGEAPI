<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>All Students in section : [${section.getSname()}  ,  Id:${section.getId()}]</h2>
	<p>${obj }
	
	<h4>Enter Details Of Students To add to Specific Section</h4>
	
		<form action="/addStudentsToSection" method="post">
	<h2>Enter Section Id To add Student</h2>
	<input type="number" name="sid"><br>
	<h2>Enter Student Details To add</h2>
	
	
		
		<label>Section Name : </label>
		<input type="text" name="name" required><br>
		
		<input type="submit"><br>
	</form>
</body>
</html>