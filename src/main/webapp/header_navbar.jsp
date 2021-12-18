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

<%

try{
if (session.getAttribute("account") == null || session.getAttribute("account").equals("")){
	%>
		<jsp:include page="header_navbar_login_no.jsp"></jsp:include>
	<%
}else{
	%>
	<jsp:include page="header_navbar_login_yes.jsp"></jsp:include>
	<%
}}catch(Exception e){
	e.printStackTrace();
}
%>
</body>
</html>