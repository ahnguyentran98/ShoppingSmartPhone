<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product info</title>
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet">
	<style>
	.info {
    position: absolute;
    top: 50%;
    left: 50%;
    margin-right: -50%;
    transform: translate(-50%, -50%);
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

/* Clear floats after the columns */
.row:after {
  content: "";
  display: table;
  clear: both;
}
	
	</style>
</head>
<body>

			<div class="info">
				<div class="column">
					<div class="card">
						<img src="${productDetail.src}" width="150" height="180"/>
					</div>
					<div class="card">
						<form action="AddToCartController">
							<input type="hidden" value="${productDetail.id}" name="id" />
							<td><b><c:out value="${productDetail.name}" /></b></td> </br> <b>Thông
								tin: </b>
							<td><c:out value="${productDetail.description}" /></td> </br> <b>Giá: </b>
							<td><c:out value="${productDetail.price}" /></td> </br>
							<button type="submit" name="btnAdd">Thêm vào giỏ hàng</button>
						</form>
					</div>
				</div>
				</div>
</body>
</html>