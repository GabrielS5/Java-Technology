package filters;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestWrapper;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import models.Category;

/**
 * Servlet Filter implementation class ValidationFilter
 */
@WebFilter(urlPatterns = { "/InputServlet" }, initParams = {
		@WebInitParam(name = "default-category", value = "Drama") })
public class ValidationFilter implements Filter {

	private Category category = null;

	/**
	 * Default constructor.
	 */
	public ValidationFilter() {
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
		String defaultC = category.toString();
		String key = request.getParameter("key");
		String value = request.getParameter("value");
		String category = request.getParameter("category");

		if (key == null || key.isEmpty() || value == null || value.isEmpty()) {
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.sendRedirect("/Lab2/input.jsp");
			return;
		}

		if (category == null || category.isEmpty()) {
			ServletRequestWrapper wrapper = new HttpServletRequestWrapper((HttpServletRequest) request) {
				@Override
				public String getParameter(String name) {
					if(name.equals("category")) {
						return defaultC;
					}
					return super.getParameter(name);
				}
			};
			chain.doFilter(wrapper, response);
			return;
		}

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.category = Category.valueOf(fConfig.getInitParameter("default-category"));
	}

}
