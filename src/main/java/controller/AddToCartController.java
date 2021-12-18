package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ListProductDAO;
import model.Account;
import model.Cart;
import model.Product;

/**
 * Servlet implementation class AddToCartController
 */
@WebServlet("/AddToCartController")
public class AddToCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddToCartController() {
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

	// add to cart, show info, add product, minus product
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		try {
			HttpSession session = request.getSession(true);
			// get product id from jsp
			String idget = request.getParameter("id");
			int id = Integer.parseInt(idget);

			List<Product> cartList = new ArrayList<>();
			//// create_get cart session
			if (session.getAttribute("cart") == null) {
				session.setAttribute("cart", new Cart());
			}
			Cart c = (Cart) session.getAttribute("cart");
			// create_get account session for user
			if (session.getAttribute("account") != null) {
				Account ac = (Account) session.getAttribute("account");
				String mail = ac.getUsr();
				session.setAttribute("mail", mail);
			}

			// add to cart if button add onclick
			if (request.getParameter("btnAdd") != null) {
				if (session.getAttribute("cart") == null) {
					session.setAttribute("cart", new Cart());
				}
				// get product add to cart and cartList
				Product product = new ListProductDAO().getProduct(id);
				c.add(product);
				// update cart
				cartList = c.getItems();
				double amount = c.getAmount();
				// set session
				session.setAttribute("amount", amount);
				session.setAttribute("cartList", cartList);
				// redirect to cart page
				RequestDispatcher rd = request.getRequestDispatcher("body_cart.jsp");
				rd.forward(request, response);

				// remove 1 items if button remove onclick
			} else if (request.getParameter("btnRemove") != null) {
				// get product to remove from cart and cartList
				Product product = new ListProductDAO().getProduct(id);
				c.remove(product);
				// update cart
				cartList = c.getItems();
				double amount = c.getAmount();
				// set session
				session.setAttribute("amount", amount);
				session.setAttribute("cartList", cartList);
				// redirect to cart page
				RequestDispatcher rd = request.getRequestDispatcher("body_cart.jsp");
				rd.forward(request, response);

				// deleted product if button delete onclick
			} else if (request.getParameter("btnDel") != null) {
				// get product to delete from cart and cartList
				Product product = new ListProductDAO().getProduct(id);
				c.delete(product);
				// update cart
				cartList = c.getItems();
				double amount = c.getAmount();
				// set session
				session.setAttribute("amount", amount);
				session.setAttribute("cartList", cartList);
				// redirect to cart page
				RequestDispatcher rd = request.getRequestDispatcher("body_cart.jsp");
				rd.forward(request, response);

				// show product if button showinfo onclick
			} else if (request.getParameter("showInfo") != null) {
				// get product info
				request.setAttribute("productDetail", new ListProductDAO().getProduct(id));
				// redirect to product info page
				RequestDispatcher rd = request.getRequestDispatcher("body_productInfo.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			response.getWriter().println(e);
		}
	}
}
