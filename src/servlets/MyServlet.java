package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/Servlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("request", "bob");
		request.getSession().setAttribute("sessionState", "bob");
		this.getServletContext().setAttribute("applicationState", "bob");
		Cookie c = new Cookie("cookiePermanentState", "bob");
		c.setMaxAge(3600);
		response.addCookie(c);
		c = new Cookie("cookieTemporaryState", "bob");
		response.addCookie(c);
		PrintWriter out = response.getWriter();
		out.print("<html><head><title>Test</title></head><body>");
		out.print("<form method='post'>");
		out.print("<p>Please click the button</p>");
		out.print("<input type='hidden' name='hiddenState' value='bob'>");
		out.print("<input type='submit' value='Click me'/>");
		out.print("</form>");
		out.print("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.print(
				"<html><head><title>Test</title></head><body><p>See the Bundled Tomcat(5.5.17)window</p></body></html>");
		System.out.format("requestState: %s\r\n", request.getAttribute("requestState"));
		System.out.format("sessionState: %s\r\n", request.getSession().getAttribute("sessionState"));
		System.out.format("applicationState: %s\r\n", this.getServletContext().getAttribute("applicationState"));

		Cookie[] cookies = request.getCookies();
		int cnt = 0;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("cookiePermanentState"))
					System.out.format("cookiePermanentState: %s\r\n", cookie.getValue());
				else if (cookie.getName().equals("cookieTemporaryState"))
					System.out.format("cookieTemporaryState: %s\r\n", cookie.getValue());
			}
		}
		System.out.format("hiddenState: %s\r\n", request.getParameter("hiddenState"));


	}

}
