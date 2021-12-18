<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Orders</title>
<link href="${pageContext.request.contextPath}/css/topnav.css"
	rel="stylesheet">
<style>
.order {
	position: absolute;
	margin-top: 200px;
	top: 50%;
	left: 50%;
	margin-right: -50%;
	transform: translate(-50%, -50%);

	
}
.order1{
float:left;
width:50%;
padding:20px;
}
table {
	border-collapse: collapse;

}

table, th, td {
	border: 1px solid black;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

th, td {
	padding: 15px;
	text-align: center;
}
</style>
</head>
<body>
	<!-- get user name by session -->

	<div class="topnav">
		<a>Chào <c:out value="${account.usr}" /></a> 
		<a href="AdminOrdersController">Quản lý đơn hàng</a>
		<a href="AdminAccountController">Quản lý người dùng</a>
		<a href="<%=response.encodeUrl(request.getContextPath() + "/header_navbar_signin_admin.jsp") %>">Đăng ký admin</a>
		<a href="LogoutController">Đăng xuất</a>
	</div>
	
	<%@ page session="true"%>


	<div class="order">
	<div class="order1">
		<h4>Thông tin đơn hàng</h4>
		<table>
			<tr>
				<th>Mã đơn hàng</th>
				<th>Ngày đặt</th>
				<th>Địa chỉ</th>
				<th>Người đặt</th>
				<th>Mã giảm giá</th>
			</tr>
			<c:forEach items="${orAdminList}" var="item" varStatus="status">
				<tr>
					<td>${item.orderId}</td>
					<td>${item.orderDate}</td>
					<td>${item.address}</td>
					<td>${item.userMail}</td>
					<td>${item.discount}</td>
				</tr>
			</c:forEach>
		</table>
		</div>
		<div class="order1">
		<h4>Thông tin chi tiết đơn hàng</h4>
		<table>
			<tr>
				<th>Mã đơn hàng</th>
				<th>Sản phẩm</th>
				<th>Số lượng</th>
				<th>Total</th>
			</tr>
			<c:forEach items="${orDAdminList}" var="item" varStatus="status">
				<tr>
					<td>${item.orderId}</td>
					<td>${item.nameProduct}</td>
					<td>${item.amountProduct}</td>
					<td>${item.totalMoney}</td>
				</tr>
			</c:forEach>
		</table>
		</div>
	</div>

</body>
</html>