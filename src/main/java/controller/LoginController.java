package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDAO;
import model.Account;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	// login process
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			// regex
			String regexMail = "^[A-Z0-9_a-z]+@[A-Z0-9\\.a-z]+\\.[A-Za-z]{2,6}$";
			String regex = "[a-zA-Z0-9_!@#$%^&*]+";

			// get input value
			String userID = request.getParameter("username");
			String password = request.getParameter("password");
			String remember = request.getParameter("remember");

			// create session
			HttpSession session = request.getSession();

			// check userID and password regex
			if (!password.matches(regex) || !userID.matches(regexMail)) {
				// if not match, print error and load login page again
				RequestDispatcher rd = request.getRequestDispatcher("/header_navbar_login.jsp");
				response.getWriter().println("<font color='red'>Invalid mail or password regex</font>");
				rd.include(request, response);
			} else {
				// if matches check existed user
				AccountDAO ac = new AccountDAO();

				// check userID and password to match existed userID and password
				if (userID != null && ac.check(userID, password) == true) {
					if (session.getAttribute("account") == null) {
						session.setAttribute("account", new Account(userID, password));
					}

					// if remember checkbox checked, stored user information to cookies
					if (remember != null) {
						Cookie userIDCookie = new Cookie("userID", userID);
						Cookie passwordCookie = new Cookie("password", password);
						Cookie rememberCookie = new Cookie("remember", remember);
						response.addCookie(userIDCookie);
						response.addCookie(passwordCookie);
						response.addCookie(rememberCookie);
					}

					// check role if customer redirect to home page
					if (!ac.checkRoleAdmin(userID)) {
						RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
						rd.forward(request, response);
					} else {
						// redirec to admin page
						RequestDispatcher rd = request.getRequestDispatcher("/body_admin.jsp");
						rd.forward(request, response);
					}

				} else {
					// if not existed, print error and load login page again
					RequestDispatcher rd = request.getRequestDispatcher("/header_navbar_login.jsp");
					response.getWriter().println("<font color='red'>Username or password is invalid</font>");
					rd.include(request, response);
				}
			}

		} catch (Exception ex) {
			response.getWriter().println(ex);
		}

	}
}
