<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.request.contextPath}/css/topnav.css" rel="stylesheet" >
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="topnav">
  <a href="<%=response.encodeUrl(request.getContextPath() + "/index.jsp") %>">Trang chủ</a>
  <a href="<%=response.encodeUrl(request.getContextPath() + "/header_navbar_signin.jsp") %>">Đăng ký tài khoản</a>
  <a href="<%=response.encodeUrl(request.getContextPath() + "/header_navbar_login.jsp") %>">Đăng nhập</a>
  <a href="<%=response.encodeUrl(request.getContextPath() + "/body_cart.jsp") %>">Giỏ hàng</a>
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