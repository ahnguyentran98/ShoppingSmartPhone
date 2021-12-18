package model;

import java.sql.Date;
import java.util.List;

public class Orders {
	private int OrderId;
	private float price;
	private int status;
	private Date orderDate;
	private String address;
	private String phoneNumber;
	private List<Product> lp;
	private String userMail;
	private Date receivedDate;
	private String discount;

	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Orders(Date orderDate, String address, String userMail, String discount) {
		super();
		this.orderDate = orderDate;
		this.address = address;
		this.userMail = userMail;
		this.discount = discount;
	}

	public Orders(int orderId, int status, Date orderDate, String address, String userMail, String discount) {
		super();
		OrderId = orderId;
		this.status = status;
		this.orderDate = orderDate;
		this.address = address;
		this.userMail = userMail;
		this.discount = discount;
	}

	public Orders(int orderId, Date orderDate, String address, String userMail, String discount) {
		super();
		OrderId = orderId;
		this.orderDate = orderDate;
		this.address = address;
		this.userMail = userMail;
		this.discount = discount;
	}

	public Orders(int status, String address, String phoneNumber, String userMail, Date receivedDate, String discount) {
		super();
		this.status = status;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.userMail = userMail;
		this.receivedDate = receivedDate;
		this.discount = discount;
	}

	public Orders(int orderId, float price, int status, Date orderDate, String address, String phoneNumber,
			List<Product> lp, String userMail, Date receivedDate, String discount) {
		super();
		OrderId = orderId;
		this.price = price;
		this.status = status;
		this.orderDate = orderDate;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.lp = lp;
		this.userMail = userMail;
		this.receivedDate = receivedDate;
		this.discount = discount;
	}

	public int getOrderId() {
		return OrderId;
	}

	public void setOrderId(int orderId) {
		OrderId = orderId;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Product> getLp() {
		return lp;
	}

	public void setLp(List<Product> lp) {
		this.lp = lp;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

}
