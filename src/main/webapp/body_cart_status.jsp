<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Status</title>
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet">
<style>
.ordersuccess {
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
	<div class="ordersuccess">
		<h4>Cảm ơn bạn đã đặt hàng</h4>
		<h4>Thông tin đơn hàng</h4>
		<table>
			<tr>
				<th>Sản phẩm</th>
				<th>Số lượng</th>
				<th>Giá</th>
			</tr>
			<c:forEach items="${orderInfo}" var="item" varStatus="status">
				<tr>
					<td>${item.name}</td>
					<td>${item.number}</td>
					<td>${Math.round(item.number * item.price * 100.0) / 100.0}</td>
				</tr>
			</c:forEach>

		</table>
		<h5>
			Tổng cộng:
			<c:out value="${amounto}" />
		</h5>

		</br> 
			<a href="AfterPaymentController">Trang chủ</a>
	</div>
</body>
</html>