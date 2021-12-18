<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.request.contextPath}/css/topnav.css" rel="stylesheet" >
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ page session="true" %>
<div class="topnav">
<a>Chào <c:out value="${account.usr}"/></a>
  <a href="<%=response.encodeUrl(request.getContextPath() + "/index.jsp") %>">Trang chủ</a>
  <a href="<%=response.encodeUrl(request.getContextPath() + "/body_cart.jsp") %>">Giỏ hàng</a>
  <a href="UserOrderController">Thông tin đơn hàng</a>
 <a href="LogoutController">Đăng xuất</a>
 <div class="search-container">
    <form action="SearchController">
    <div style="width: 70%; float:left">
      <input type="text" placeholder="Search.." name="search">
      </div>
      <div style="width: 30%; float:right">
      <button type="submit">Search</i></button>
      </div>
    </form>
  </div>
</div>
</body>
</html>