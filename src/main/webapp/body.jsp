<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Body</title>
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
}

/* Clear floats after the columns */
.row:after {
	content: "";
	display: table;
	clear: both;
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

button:hover {
	opacity: 0.8;
}
</style>
</head>
<body>	
	<div class="body">
		<div class="row">
			<!-- Check product found -->
			<c:if test="${empty productsList}">
				<h4>No Product Found</h4>
			</c:if>
			<c:forEach items="${productsList}" var="item">
				<div class="column">
					<div class="card">
						<img src="${item.src}" width="150" height="180">
					</div>
					<div class="card">
						<form action="AddToCartController">
							<input type="hidden" value="${item.id}" name="id" />
							<td><b><c:out value="${item.name}" /></b></td> </br> <b>Thông
								tin: </b>
							 </br> <b>Giá: </b>
							<td><c:out value="${item.price}" /></td> </br>
							<button type="submit" name="showInfo">Thông tin</button>
							<button type="submit" name="btnAdd">Thêm vào giỏ hàng</button>
						</form>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>


</body>
</html>