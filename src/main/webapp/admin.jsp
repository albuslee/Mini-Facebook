<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@page import="java.lang.String"%>
<%@page import="java.util.List"%>
<%@ page import="edu.unsw.minifacebook.DAO.DetailDAO"%>
<%@page import="edu.unsw.minifacebook.bean.UserBean"%>
<%@page import="edu.unsw.minifacebook.bean.DetailBean"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
.friendimg {
	width: 80px;
	height: 80px;
	line-height: 0; /* remove line-height */
	display: inline-block; /* circle wraps image */
	transition: linear 0.25s;
}

.center-block {
	display: block;
	margin-left: auto;
	margin-right: auto;
}
</style>
<script>
	function sendAjaxRequest(event) {
		var username = event.target.id;
		document.getElementById(username).setAttribute("disabled", "disabled");
		try {// Firefox, Opera 8.0+, Safari, IE7
			xmlHttp = new XMLHttpRequest();
		} catch (e) {// Old IE
			try {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
				alert("Your browser does not support XMLHTTP!");
				return;
			}
		}
		var url = "mini_facebook/acceptFriendRequest?username=" + username;
		xmlHttp.open("GET", url, true);
		xmlHttp.send();
		xmlHttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				alert(xmlHttp.responseText);
				document.getElementById("username").setAttribute("class",
						"btn btn-default disable");
			}
		}
		//alert("send");
	}
</script>
</head>
<body>
	<jsp:include page="headerreg.jsp"></jsp:include>
	<!--搜索框和表单  -->
	<div style="width: 60%" class="center-block">
		<form class="form-inline" action="">
			<div class="form-group">
					<input type="text" class="form-control" id="exampleInputAmount"
						placeholder="search a user">
			</div>
			<button type="submit" class="btn btn-primary">search</button>
		</form>
	</div>
	<!--显示搜索结果  -->
	<div>
		<ul class="list-group center-block" style="width: 60%">
			<%
				List<UserBean> userList = (List<UserBean>) request.getAttribute("allusers");
			    DetailDAO detaildao=new DetailDAO();
			    DetailBean detailBean = new DetailBean();
				if (userList != null && !userList.isEmpty()) {
					for (UserBean userBean : userList) {
						detailBean = detaildao.getUserByUsername(userBean.getUsername());
						String imgs = detailBean.getPhoto();
						if (!detailBean.getPhoto().contains("image")) {
							imgs = "image/UNSW_0.png";
						}
			%>
			
			<li class="list-group-item row">
			
				<div class="col-sm-3">
					<a href="activity.jsp"><img class="friendimg" src="<%=imgs%>"></img></a>
				</div>
				<div class="col-sm-6">
					<b style="color: blue; font-size: 20px;">
					<%=detailBean.getName()%></b>
					<br><%=detailBean.getGender()%>
					<br><%=userBean.getUserId()%></div>
				<button id="<%=detailBean.getUsername()%>" class="btn btn-primary"
					onclick="sendAjaxRequest(event)">BAN</button>
			</li>
			
			<%
				}
				}
			%>
		</ul>
	</div>
</body>
</html>