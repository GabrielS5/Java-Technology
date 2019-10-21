<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error</title>
<style>
.content {
	margin: auto;
	width: 300px;
	border: 3px solid black;
	padding: 10px;
}

img {
	margin-left: 50px; margin-top : 50px;
	margin-bottom: 50px;
	width: 200px;
	height: 200px;
	margin-top: 50px;
}

.error-message {
	text-align: center;
}
</style>
</head>
<body>
	<div class="content">
		<img src="C:\Users\gabri\Desktop\Git\Java-Technology\Lab2\error.png">
		<div class="error-message"><%=request.getParameter("error")%></div>
	</div>
</body>
</html>