<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@page import="java.util.List"%>
<%@page import="edu.unsw.minifacebook.bean.DetailBean"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
.friendimg{
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
		document.getElementById(username).setAttribute("disabled","disabled");
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
		var url = "mini_facebook/sendFriendRequest?username=" + username;
		xmlHttp.open("GET", url, true);
		xmlHttp.send();
		xmlHttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				alert(xmlHttp.responseText);
				document.getElementById("username").setAttribute("class", "btn btn-default disable");
			}
		}
		//alert("send");
	}
</script>
</head>
<body>
	<jsp:include page="headerreg.jsp"></jsp:include>
<div>
<ul class="list-group center-block"  style="width:60%">
	<%
		List<DetailBean> detailList = (List<DetailBean>) request.getAttribute("detailList");

		if (detailList != null && !detailList.isEmpty()) {
			for (DetailBean detailBean : detailList) {
	%>
	<li class="list-group-item row">
	<div class="col-sm-3"><img class="friendimg" src="<%=detailBean.getPhoto()%>"></img></div>
	<div class="col-sm-6"><b style="color:blue;font-size:20px;"><%=detailBean.getName()%></b><br><%=detailBean.getGender()%></div>
	<button id="<%=detailBean.getUsername()%>" class="btn btn-primary" onclick="sendAjaxRequest(event)">Add Friend</button>
	</li>
	<%
		}
		}
	%>
</ul>
</div>
</body>
</html>