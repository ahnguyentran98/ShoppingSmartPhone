<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
<link href="${pageContext.request.contextPath}/css/topnav.css"
	rel="stylesheet">
<style>
.order {
	position: absolute;
	top: 50%;
	left: 50%;
	margin-right: -50%;
	transform: translate(-50%, -50%);
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


</body>
</html>