package filters;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * Servlet Filter implementation class WrappingFilter
 */
@WebFilter(urlPatterns = { "/input.jsp", "/result.jsp", "/error.jsp" })
public class WrappingFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public WrappingFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		CharResponseWrapper wrapper = new CharResponseWrapper((HttpServletResponse) response);
		// Send the decorated object as a replacement for the original response
		chain.doFilter(request, wrapper);
		// Get the dynamically generated content from the decorator
		String content = wrapper.toString();
		// Modify the content
		StringWriter sw = new StringWriter();
		sw.write("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<meta charset=\"ISO-8859-1\">\r\n"
				+ "<title>Application</title>\r\n" + "<style>\r\n" + ".content {\r\n" + "	margin: auto;\r\n"
				+ "	width: 300px;\r\n" + "	border: 3px solid black;\r\n" + "	padding: 10px;\r\n" + "}\r\n" + "\r\n"
				+ ".form-input {\r\n" + "	padding-top: 10px;\r\n" + "}\r\n" + "\r\n" + "span {\r\n"
				+ "	width: 100px;\r\n" + "}\r\n" + "\r\n" + "table, th, td {\r\n" + "	border: 1px solid black;\r\n"
				+ "}\r\n" + "\r\n" + "table {\r\n" + "	margin: auto;\r\n" + "	margin-top: 50px;\r\n"
				+ "	margin-bottom: 50px;\r\n" + "}\r\n" + "\r\n" + "a {\r\n" + "	width: 50px;\r\n"
				+ "	margin-left: 100px;\r\n" + "}\r\n" + "\r\n" + "img {\r\n" + "	border: 1px solid black;\r\n"
				+ "}\r\n" + "\r\n" + ".img-error {\r\n" + "	margin-left: 50px; margin-top : 50px;\r\n"
				+ "	margin-bottom: 50px;\r\n" + "	width: 200px;\r\n" + "	height: 200px;\r\n"
				+ "	margin-top: 50px;\r\n" + "}\r\n" + "\r\n" + ".error-message {\r\n" + "	text-align: center;\r\n"
				+ "}\r\n" + "\r\n" + "</style>\r\n" + "</head>");

		sw.write(content);

		sw.write("</html>");
		PrintWriter out = response.getWriter();
		out.write(sw.toString());
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	public class CharResponseWrapper extends HttpServletResponseWrapper {
		private CharArrayWriter output;

		public CharResponseWrapper(HttpServletResponse response) {
			super(response);
			output = new CharArrayWriter();
		}

		@Override
		public PrintWriter getWriter() {
			// Hide the original writer
			return new PrintWriter(output);
		}

		public String toString() {
			return output.toString();
		}
	}

}
