<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
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
	background-color: #06AA6D;
	color: white;
	padding: 16px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 100%;
}

button:hover {
	opacity: 0.8;
}

.p{
  float: left;
  background-color: blue;
  width: 50px;
  height: 50px;
}
</style>
</head>
<body>
	<%@ page import="model.*"%>
	<%@ page import="dao.*"%>
	<%@ page import="java.util.List" %>
	<!-- auto show product -->
	<%
/* 	ListProductDAO listDAO = new ListProductDAO();
	List<Product> list = listDAO.search(""); */
	int first = 0, last = 0, pages = 1;
    
    if (request.getParameter("pages") != null) {
        pages = (int) Integer.parseInt(request.getParameter("pages"));
    }
    //Lấy tổng sản phẩm trong data bằng query select count(id) from name_table với JDBC Connect
    int total = new ListProductDAO().getCount();
    
    if (total <= 6) {
        first = 0; 
        last = total; 
    } else {
        first = (pages - 1) * 6;
        last = 6;
    }
    //Lấy ra danh sách sản phẩm
    ListProductDAO listDAO = new ListProductDAO();
    List<Product> list = listDAO.getShop(first,last);
	
	
	%>
	
	<div class="body">
		<div class="row">
			<c:forEach items="<%=list%>" var="item">
				<div class="column">
					<div class="card">
						<img src="${item.src}" width="150" height="180"/>
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
	
<div>
<ul class="start">    
    <%                //Button Previous
        int back = 0;
        if (pages == 0 || pages == 1) {
            back = 1;//Luon la page 1
        } else {
            back = pages - 1;//Neu pages tu 2 tro len thi back tru 1
        }
    %>
    <li id="p"><a href="index.jsp?pages=<%=back%>"><i></i></a></li>
                <%
                    //Button Number pages
                    int loop = 0, num = 0;
                    if ((total / 6) % 2 == 0) {
                        num = total / 6;
                    } else {
                        num = (total + 1) / 6;
                    }
                    //Nếu total lẻ thêm 1
                    if (total % 2 != 0) {
                        loop = (total / 6) + 1;

                    } else {
                        //Nếu total chẵn nhỏ hơn fullpage và # fullPage thì thêm 1
                        if (total < (num * 6) + 6 && total != num * 6) {
                            loop = (total / 6) + 1;
                        } else {
                            //Nếu bằng fullPage thì không thêm
                            loop = (total / 6);
                        }
                    }
                    //Lap so pages
                    for (int i = 1; i <= loop; i++) {%>
                <% if (pages == i) {%> 

    <li id="p"><span><a href="index.jsp?pages=<%=i%>"><%=i%></a></span></li>
                <%} else {%>
    <li id="p" class="arrow"><a href="index.jsp?pages=<%=i%>"><%=i%></a> </li>

        <%}
             }%>
        <%
            //Button Next
            int next = 0;
            //Nếu total lẻ
            if (total % 2 != 0) {
                if (pages == (total / 6) + 1) {
                    next = pages;//Khong next
                } else {
                    next = pages + 1;//Co next
                }
            } else {
                //Nếu total chẵn nhỏ hơn fullpage
                //Và không fullPage thì thêm 1
                if (total < (num * 6) + 6 && total != num * 6) {
                    if (pages == (total / 6) + 1) {
                        next = pages;//Khong next
                    } else {
                        next = pages + 1;//Co next
                    }
                } else {
                    //Nếu fullPage đến trang cuối dừng
                    //Chưa tới trang cuối thì được next
                    if (pages == (total / 6)) {
                        next = pages;//Khong next
                    } else {
                        next = pages + 1;//Co next
                    }
                }
            }
        %>
    <li id="p"><a href="index.jsp?pages=<%=next%>"><i class="next"></i></a></li>
</ul>
</div>
<form action="yourservlet" method="post">
    <input type="hidden" name="firstrow" value="${firstrow}">
    <input type="hidden" name="rowcount" value="${rowcount}">
    <input type="submit" name="page" value="next">
    <input type="submit" name="page" value="previous">
</form>
</body>
</html>