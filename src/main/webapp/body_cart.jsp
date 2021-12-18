<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart</title>
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet">
<style>
* {
	box-sizing: border-box;
}

/* Create three equal columns that floats next to each other */
.column {
	float: left;
	width: 33.33%;
	padding: 10px;
}

.card {
	float: left;
	padding: 10px;
	width :  50%;
	height : 300px;
}
.addminus{
	float: left;
	width :  50%;
	padding: 10px;
}

/* Clear floats after the columns */
.row:after {
	content: "";
	display: table;
	clear: both;
}

.foot{
	float: left;
	width: 15%;
	padding: 10px;
}
button {
	background-color: #04AA6D;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 100%;
}
.cartfoot{
    position: fixed;
    bottom: 0;
    width: 100%;
    margin-top:20px;}

button:hover {
	opacity: 0.8;
}
</style>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<jsp:include page="header_navbar.jsp"></jsp:include>

	<c:forEach items="${cartList}" var="item">
		
			<div class="column">
				<div class="card">
					<img src="${item.src}" width="150" height="180">
				</div>
				<div class="card">
					<form action="AddToCartController">
						<input type="hidden" value="${item.id}" name="id" />
						<td><b><c:out value="${item.name}" /></b></td>
						</br> <b>Số lượng: </b>
						<td><b><c:out value="${item.number}" /></b></td>
						</br> <b>Giá: </b>
						<td><c:out
								value="${Math.round(item.number * item.price * 100.0) / 100.0}" /></td>
						</br>
						<div class="addminus">
						<button type="submit" name="btnRemove"><b>-</b></button>
						
						</div>
						<div class="addminus">
						<button type="submit" name="btnAdd"><b>+</b></button>
						</div>
						<button type="submit" name="btnDel">Xóa</button>
					</form>
				</div>
			</div>
		
	</c:forEach>


<div class="cartfoot">
	<form action="PayController" >
	<div class="foot">
		<b>Tổng cộng</b> 
		</div>
		<div class="foot">
		<input type="text" value="${amount}"
			name="id" /> 
			</div>
			<div class="foot">
			<input type="text"value="${mail}" name="mail" 
			required> 
			</div>
			<div class="foot">
			<input type="text"
			placeholder="Enter Discount code" name="discount"> 
			</div>
			<div class="foot">
			<input
			type="text" placeholder="Enter Address" name="address" required>
			</div>
		
		<button type="submit">Đặt hàng</button>
	
		
	</form>
</div>
</body>
</html>