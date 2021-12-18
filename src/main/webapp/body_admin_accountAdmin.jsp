<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Account</title>
<link href="${pageContext.request.contextPath}/css/topnav.css"
	rel="stylesheet">
<style>
.order {
	position: absolute;
	margin-top: 20px;
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
		<h4>Thông tin đơn hàng</h4>
		<table>
			<tr>
				<th>Email</th>
				<th>Vai trò (1:Admin , 0: Khách hàng)</th>
				<th>Tên</th>
				<th>Địa chỉ</th>
				<th>Số điện thoại</th>
			</tr>
			<c:forEach items="${acAdminList}" var="item" varStatus="status">
				<tr>
					<td>${item.usr}</td>
					<td>${item.role}</td>
					<td>${item.name}</td>
					<td>${item.address}</td>
					<td>${item.phone}</td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>