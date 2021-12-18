package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import model.Cart;
import model.Orders;
import model.Product;
import model.ProductOrders;

public class OrdersDAO {

	public void insertOrder(Orders order, Cart cart) throws Exception {
		try {
			// connect to database
			DBContext dbcontext = new DBContext();
			Connection conn = dbcontext.getConnection();
			Statement stmtO = conn.createStatement();
			// update orderID
			int orderID = 1;
			ResultSet rs = stmtO.executeQuery("select * from dbo.Orders");
			while (rs.next()) {
				orderID++;
			}
			// take info for Orders db
			PreparedStatement stmt1 = conn.prepareStatement(
					"SET IDENTITY_INSERT dbo.Orders ON; INSERT INTO dbo.Orders (user_mail,order_id, order_status,order_date,order_discount_code,order_address) values (?,?, ?, ?, ?, ?)");
			stmt1.setString(1, order.getUserMail());
			stmt1.setInt(2, orderID);
			stmt1.setInt(3, order.getStatus());
			stmt1.setDate(4, order.getOrderDate());
			stmt1.setString(5, order.getDiscount());
			stmt1.setString(6, order.getAddress());
			stmt1.execute();
			stmt1.close();

			// take info for order_details db
			PreparedStatement stmt = conn.prepareStatement(
					"INSERT INTO dbo.Orders_detail (order_id, product_id,amount_product,price_product) values (?, ?, ?, ?)");
			List<Product> cartPList = cart.getItems();
			for (Product x : cartPList) {
				Float money = x.getNumber() * x.getPrice();
				stmt.setInt(1, orderID);
				stmt.setInt(2, x.getId());
				stmt.setInt(3, x.getNumber());
				stmt.setFloat(4, money);
				stmt.execute();
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// show users orders details
	public List<ProductOrders> getUserProduct(String usermail) throws Exception {
		List<ProductOrders> order = new ArrayList<>();
		List<Integer> orderID = new ArrayList<>();
		try {
			// connect to database
			DBContext dbcontext = new DBContext();
			Connection conn = dbcontext.getConnection();

			// find product id in Orders
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from dbo.Orders");
			while (rs.next()) {
				String find = rs.getString(1);
				if (find.equalsIgnoreCase(usermail)) {
					int orID = rs.getInt(2);
					orderID.add(orID);
				}
			}
			stmt.close();

			// get product info from Order.details
			Statement stmt1 = conn.createStatement();
			rs = stmt1.executeQuery("select * from dbo.Orders_detail");
			while (rs.next()) {
				for (int x : orderID)
					if (x == rs.getInt(1)) {
						int productID = rs.getInt(2);
						Product productGet = new ListProductDAO().getProduct(productID);
						String name = productGet.getName();
						Float money = productGet.getPrice() * rs.getInt(3);
						ProductOrders po = new ProductOrders(rs.getInt(1), rs.getInt(2), rs.getInt(3), name, money);
						order.add(po);
					}
			}
			stmt1.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return order;
	}

	public List<Orders> showOrders() throws Exception {
		List<Orders> order = new ArrayList<>();
		try {
			DBContext dbcontext = new DBContext();
			Connection conn = dbcontext.getConnection();
			// query
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from dbo.Orders");
			while (rs.next()) {
				order.add(new Orders(rs.getInt(2), rs.getDate(4), rs.getString(6), rs.getString(1), rs.getString(5)));
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return order;
	}

	public List<ProductOrders> showOrderDetails() throws Exception {
		List<ProductOrders> order = new ArrayList<>();
		List<Integer> orderID = new ArrayList<>();
		try {
			// connect to database
			DBContext dbcontext = new DBContext();
			Connection conn = dbcontext.getConnection();

			// find product id in Orders
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from dbo.Orders");
			while (rs.next()) {
				int orID = rs.getInt(2);
				orderID.add(orID);
			}
			stmt.close();

			// get product info from Order.details
			Statement stmt1 = conn.createStatement();
			rs = stmt1.executeQuery("select * from dbo.Orders_detail");
			while (rs.next()) {
				for (int x : orderID)
					if (x == rs.getInt(1)) {
						int productID = rs.getInt(2);
						Product productGet = new ListProductDAO().getProduct(productID);
						String name = productGet.getName();
						Float money = productGet.getPrice() * rs.getInt(3);
						ProductOrders po = new ProductOrders(rs.getInt(1), rs.getInt(2), rs.getInt(3), name, money);
						order.add(po);
					}
			}
			stmt1.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return order;
	}
}
