package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDAO;
import model.Account;

/**
 * Servlet implementation class AdminSigninController
 */
@WebServlet("/AdminSigninController")
public class AdminSigninController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminSigninController() {
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

	// show admin account details
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			// regex
			String regexMail = "^[A-Z0-9_a-z]+@[A-Z0-9\\.a-z]+\\.[A-Za-z]{2,6}$";
			String regex = "[a-zA-Z0-9_!@#$%^&*]+";

			// get input value
			String email = request.getParameter("mail");
			String password = request.getParameter("password");
			// sign in by customer set role = 0; role =1 if signin by admin
			int role = 1;
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String phone = request.getParameter("phone");

			// create new accountDAO and session
			HttpSession session = request.getSession(true);
			AccountDAO ac = new AccountDAO();
			// check userID and password regex
			if (!password.matches(regex) || !email.matches(regexMail)) {
				// if not match, print error and load login page again
				RequestDispatcher rd = request.getRequestDispatcher("/header_navbar_signin_admin.jsp");
				response.getWriter().println("<font color='red'>Invalid mail or password regex</font>");
				rd.include(request, response);
			} else {

				// if match regex check userID and password to match existed userID and password
				if (email != null && ac.check(email, password) == true) {
					// if user existed print error load page again
					RequestDispatcher rd = request.getRequestDispatcher("/header_navbar_signin_admin.jsp");
					response.getWriter().println("<font color='red'>email existed please select another email</font>");
					rd.include(request, response);

					// if not exist insert to database and redirect do login page
				} else if (email != null && ac.check(email, password) == false) {
					Account account = new Account(email, password, role, name, address, phone);
					session.setAttribute("accountSign", account);
					ac.signInCus(account);
					RequestDispatcher rd = request.getRequestDispatcher("/body_admin.jsp");
					rd.forward(request, response);
				}
			}
		} catch (Exception e) {
			response.getWriter().println(e);
		}
	}
}
