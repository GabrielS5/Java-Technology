<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Result</title>
<style>
.content {
	margin: auto;
	width: 300px;
	border: 3px solid black;
	padding: 10px;
}

.form-input {
	padding-top: 10px;
}

span {
	width: 100px;
}

table, th, td {
	border: 1px solid black;
}

table {
	margin: auto;
	margin-top: 50px;
	margin-bottom: 50px;
}

a {
	width: 50px;
	margin-left: 100px;
}
</style>
</head>
<body>
	<div class="content">
		<%@ page import="java.util.ArrayList"%>
		<%@ page import="bussiness.MockDatabase"%>
		<%@ page import="models.Record"%>
		<jsp:useBean id="RecordsBean" class="models.RecordsBean"
			scope="session" />

		<%
			MockDatabase database = new MockDatabase();
			ArrayList<Record> records = RecordsBean.getRecords();
		%>

		<table>
			<thead>
				<tr>
					<th>Key</th>
					<th>Value</th>
					<th>Category</th>
				</tr>
			</thead>
			<tbody>
				<%
					for (Record record : records) {
				%>
				<tr>
					<td><%=record.getKey()%></td>
					<td><%=record.getValue()%></td>
					<td><%=record.getCategory().name()%></td>
				</tr>
				<%
					}
				%>

			</tbody>
		</table>

		<a href="input.jsp">Add more</a>

	</div>
</body>
</html>