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