<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Input</title>
</head>
<body>

	<%@ page import="java.util.ArrayList"%>
	<%@ page import="bussiness.CategoryEndpoint"%>
	<%@ page import="javax.servlet.http.Cookie"%>
	<%@ page import="models.Record"%>

	<%
		CategoryEndpoint categoryEndpoint = new CategoryEndpoint();
		ArrayList<String> categories = categoryEndpoint.getCategories();
		Cookie[] cookies = request.getCookies();
		String categoryFromCookie = categories.get(0);

		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("Category"))
				categoryFromCookie = cookie.getValue();
		}
	%>
	<form method="GET" action="InputServlet">
		Key: <input type="text" name="key" size="20" value="" /> <br />
		Value: <input type="text" name="value" size="20" value="" /> <br />
		Category: <select name="category">
			<%
				for (String category : categories) {
			%>
			<option value="<%=category%>"
				<%=category.equals(categoryFromCookie) ? "selected" : ""%>><%=category%></option>
			<%
				}
			%>
		</select> <br />

		<div>
			<img src="CaptchaServlet">
		</div>

		<br /> What do you see in the image: <input type="text"
			name="captcha" size="10" value="" /> <br /> <input type="submit"
			name="submit" value="Submit">
	</form>
</body>
</html>