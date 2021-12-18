package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OrdersDAO;
import model.Account;
import model.ProductOrders;

/**
 * Servlet implementation class UserOrderController
 */
@WebServlet("/UserOrderController")
public class UserOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserOrderController() {
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

	// show users orders details
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		// take info from session
		try {
			HttpSession session = request.getSession(true);
			Account account = (Account) session.getAttribute("account");
			String usermail = account.getUsr();

			// get orders by email
			OrdersDAO or = new OrdersDAO();
			List<ProductOrders> orList = or.getUserProduct(usermail);
			session.setAttribute("orList", orList);

			RequestDispatcher rd = request.getRequestDispatcher("/header_navbar_user_orders.jsp");

			rd.forward(request, response);

		} catch (Exception e) {
			response.getWriter().println(e);
		}
	}

}
