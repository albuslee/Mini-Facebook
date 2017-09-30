<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="edu.unsw.minifacebook.bean.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="headerreg.jsp"></jsp:include>
<%
	List<NotificationBean> requests = (List<NotificationBean>)request.getAttribute("friendrequests");
	if(requests != null){
	
	for(NotificationBean fr: requests){
		UserBean nfrom = fr.getFrom();
		DetailBean detailBean = nfrom.getDetailBean();
		%>
		<div>
		<img src="<%=detailBean.getPhoto()%>"></img><%=detailBean.getName()%>
		<%=detailBean.getGender()%>
		<button id="<%=detailBean.getUsername()%>" onclick="sendAjaxRequest(event)">Add Friend</button>
		</div>
		<br>
		<%
	}
	}else{
		response.sendRedirect("loadFriendRequest");
	}
%>
</body>
</html>