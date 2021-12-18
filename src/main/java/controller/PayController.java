package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OrdersDAO;
import model.Cart;
import model.Orders;
import model.Product;

/**
 * Servlet implementation class PayController
 */
@WebServlet("/PayController")
public class PayController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PayController() {
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

	// payment process
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			// create session
			HttpSession session = request.getSession(true);

			OrdersDAO dao = new OrdersDAO();
			// take orders info
			if (session.getAttribute("cart") != null) {
				Cart c = (Cart) session.getAttribute("cart");
				List<Product> cartList = c.getItems();
				session.setAttribute("orderInfo", cartList);
				double amounto = c.getAmount();
				session.setAttribute("amounto", amounto);
				long millis = System.currentTimeMillis();
				Date date = new java.sql.Date(millis);
				// if account login take mail = account mail
				String mail = "";
				if (session.getAttribute("account") != null) {
					mail = (String) session.getAttribute("mail");
				} else {
					mail = request.getParameter("mail");
				}

				String discount = request.getParameter("discount");
				String address = request.getParameter("address");

				// insert order to database
				Orders order = new Orders(date, address, mail, discount);
				dao.insertOrder(order, c);

				RequestDispatcher rd = request.getRequestDispatcher("body_cart_status.jsp");
				rd.forward(request, response);
			}

		} catch (Exception e) {
			response.getWriter().println(e);
			e.printStackTrace();
		}
	}
}
