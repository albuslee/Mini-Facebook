<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@page import="java.util.List"%>
<%@page import="edu.unsw.minifacebook.bean.DetailBean"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="headerreg.jsp"></jsp:include>
	<%
	List<DetailBean> detailList = (List<DetailBean>)request.getAttribute("detailList");


	if(detailList != null && !detailList.isEmpty())
	{
		for(DetailBean detailBean: detailList){
			
		%>
	<img src="<%=detailBean.getPhoto() %>"></img><%=detailBean.getName() %>
	<%=detailBean.getGender() %>
	<br>
	<%
		}
	}
%>

</body>
</html>