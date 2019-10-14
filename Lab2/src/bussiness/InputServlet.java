package bussiness;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Category;
import models.Record;
import models.RecordsBean;

/**
 * Servlet implementation class InputServlet
 */
@WebServlet("/InputServlet")
public class InputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MockDatabase database = new MockDatabase();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InputServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String errorMessage = null;
		Record record = null;

		try {
			String value = request.getParameter("value");
			String key = request.getParameter("key");
			Category category = Category.valueOf(request.getParameter("category"));
			String captcha = request.getParameter("captcha");
			Object originalCaptcha = request.getSession().getAttribute("CaptchaBean").toString();
			record = new Record(key, value, category);
			
			if(!captcha.equals(originalCaptcha)) {
				errorMessage = "Error. Invalid captcha.";
			}

			if (value == null || value.isEmpty() || key == null || key.isEmpty()) {
				errorMessage = "Error. You left empty fields.";
			}

			for (Record r : database.getRecords()) {
				if (r.getKey().equals(record.getKey()) && r.getCategory() == record.getCategory()) {
					errorMessage = "Error. You can't add duplicate records.";
					break;
				}
			}
		} catch (Exception e) {
			errorMessage = "Error. Invalid input data.";
		}

		if (errorMessage != null) {
			response.sendRedirect("error.jsp?error=" + errorMessage);
		} else {
			Cookie categoryCookie = new Cookie("Category", record.getCategory().name());
			response.addCookie(categoryCookie);
			database.add(record);
			response.sendRedirect("result.jsp");
		}
		
		RecordsBean bean = new RecordsBean();
		bean.setRecords(database.getRecords());
		request.getSession().setAttribute("RecordsBean", bean);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
