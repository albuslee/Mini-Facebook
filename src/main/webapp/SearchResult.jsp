<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@page import="java.util.List"%>
<%@page import="edu.unsw.minifacebook.bean.DetailBean"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
	function sendAjaxRequest(event) {
		var username = event.target.id;

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
				document.getElementById("showRight").innerHTML = xmlHttp.responseText;
			}
		}
		//alert("send");
	}
</script>
</head>
<body>
	<jsp:include page="headerreg.jsp"></jsp:include>
	<%
		List<DetailBean> detailList = (List<DetailBean>) request.getAttribute("detailList");

		if (detailList != null && !detailList.isEmpty()) {
			for (DetailBean detailBean : detailList) {
	%>
	<div>
	<img src="<%=detailBean.getPhoto()%>"></img><%=detailBean.getName()%>
	<%=detailBean.getGender()%>
	<button id="<%=detailBean.getUsername()%>" onclick="sendAjaxRequest(event)">Add Friend</button>
	</div>
	<br>
	<%
		}
		}
	%>

</body>
</html>