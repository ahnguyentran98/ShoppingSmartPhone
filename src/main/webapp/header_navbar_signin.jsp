<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng ký</title>
<link href="${pageContext.request.contextPath}/css/loginStyle.css" rel="stylesheet" >
</head>
<body>

<form action="SigninController" >
  <div class="imgcontainer">
<img src="${pageContext.request.contextPath}/img/avatar.png" alt="Avatar" class="avatar">
  </div>

  <div class="container">
  <!-- set remembered user information -->
    <label for="uname"><b>User mail</b></label>
    <input type="text" placeholder="Enter Email" name="mail" required>
    
    <label for="psw"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="password"  required>

    <label for="psw"><b>User name</b></label>
    <input type="text" placeholder="Enter Name" name="name"  required>
    
    <label for="psw"><b>User Address</b></label>
    <input type="text" placeholder="Enter Address" name="address" >
    
    <label for="psw"><b>User Phone</b></label>
    <input type="text" placeholder="Enter Phone number" name="phone"  >
    
    <button type="submit" name="btn" value="btnlog">Đăng ký</button>

  </div>

  <div class="container" style="background-color:#f1f1f1">
    <button type="button" class="cancelbtn" onclick="window.location='index.jsp';" value="click here">Cancel</button>
  </div>
</form>
</body>
</html>