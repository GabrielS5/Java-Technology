<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Input</title>
</head>
<body>
	<form method="GET" action="InputServlet">
		Key: <input type="text" name="key" size="20" value="" /> <br /> Value:
		<input type="text" name="value" size="20" value="" /> <br />
		 <select name="category">
			<option value="Horror">Horror</option>
			<option value="Drama">Drama</option>
			<option value="Comedy">Comedy</option>
			<option value="Action">Action</option>
		</select> <input type="submit" name="submit" value="Submit">
	</form>


</body>
</html>