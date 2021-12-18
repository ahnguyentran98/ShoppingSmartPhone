<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
<link href="${pageContext.request.contextPath}/css/loginStyle.css" rel="stylesheet" >
</head>
<body>

<!-- remember user information by get user value from cookies  -->
<%
String cname="",cpass="",crem="";
	Cookie[] cookies = request.getCookies();
	if(cookies!=null){
		for(Cookie cook: cookies){
			if(cook.getName().equals("userID")){
				cname = cook.getValue();
			}else if(cook.getName().equals("password")){
				cpass = cook.getValue();
			}else if(cook.getName().equals("remember")){
				crem=cook.getValue();
			}
		}
	}

%>
<form action="LoginController" >
  <div class="imgcontainer">
<img src="${pageContext.request.contextPath}/img/avatar.png" alt="Avatar" class="avatar">
  </div>

  <div class="container">
  <!-- set remembered user information -->
    <label for="uname"><b>Username</b></label>
    <input type="text" placeholder="Enter Email" name="username" value="<%=cname %> " required>
    
    
    <label for="psw"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="password" value="<%=cpass %>" required>

    <button type="submit" name="btn" value="btnlog">Đăng nhập</button>
    <label>
      <input type="checkbox" checked="checked" name="remember" value="<%=crem %>"> Remember me
    </label>
  </div>

  <div class="container" style="background-color:#f1f1f1">
    <button type="button" class="cancelbtn" onclick="window.location='index.jsp';" value="click here">Cancel</button>
  </div>
</form>
</body>
</html>