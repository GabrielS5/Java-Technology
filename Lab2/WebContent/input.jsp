<body>
<script>
	function reloadCaptcha() {
		let element = document.getElementById("image");

		element.src = "CaptchaServlet?" + (new Date()).getTime().toString();
	}
</script>

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
	<div class="content">
		<form method="GET" action="InputServlet">
			<div class="form-input">
				<span>Key:</span> <input type="text" name="key" size="20" value="" />
			</div>
			<div class="form-input">
				<span>Value:</span> <input type="text" name="value" size="20"
					value="" />
			</div>
			<div class="form-input">
				Category: <select name="category">
					<%
						for (String category : categories) {
					%>
					<option value="<%=category%>"
						<%=category.equals(categoryFromCookie) ? "selected" : ""%>><%=category%></option>
					<%
						}
					%>
				</select>
			</div>
			<div class="form-input">
				<div>
					<img id="image" src="CaptchaServlet">
				</div>
				<br /> <input type="button" size="10" value="Reload"
					onClick="reloadCaptcha()" /> <br /> What do you see in the image:
				<input type="text" name="captcha" size="10" value="" /> <br />
			</div>
			<div class="form-input">
				<input type="submit" name="submit" value="Submit">
			</div>
		</form>
	</div>
</body>