<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý đơn hàng</title>
<link href="${pageContext.request.contextPath}/css/topnav.css" rel="stylesheet" >
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
<a>Chào <c:out value="${account.usr}"/></a>
<a href="<%=response.encodeUrl(request.getContextPath() + "/index.jsp") %>">Trang chủ</a>
  <a href="LogoutController">Đăng xuất</a>
</div>
<%@ page session="true" %>

<a></a>
<div class="order">
		<h4>Thông tin đơn hàng</h4>
		<table>
			<tr>
				<th>Mã đơn hàng</th>
				<th>Sản phẩm</th>
				<th>Số lượng</th>
				<th>Total</th>
			</tr>
			<c:forEach items="${orList}" var="item" varStatus="status">
				<tr>
					<td>${item.orderId}</td>
					<td>${item.nameProduct}</td>
					<td>${item.amountProduct}</td>
					<td>${item.totalMoney}</td>
				</tr>
			</c:forEach>

		</table>
		</div>
</body>
</html>