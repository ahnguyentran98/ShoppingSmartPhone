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
import model.Orders;
import model.ProductOrders;

/**
 * Servlet implementation class AdminOrdersController
 */
@WebServlet("/AdminOrdersController")
public class AdminOrdersController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminOrdersController() {
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
			OrdersDAO or = new OrdersDAO();
			List<Orders> orAdminList = or.showOrders();
			session.setAttribute("orAdminList", orAdminList);
			// get order details
			List<ProductOrders> orDAdminList = or.showOrderDetails();
			session.setAttribute("orDAdminList", orDAdminList);

			RequestDispatcher rd = request.getRequestDispatcher("/body_admin_ordersAdmin.jsp");

			rd.forward(request, response);

		} catch (Exception e) {
			response.getWriter().println(e);
		}
	}
}
