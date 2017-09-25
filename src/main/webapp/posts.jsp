<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page import="java.util.List"%>
<%@ page import="edu.unsw.minifacebook.bean.PostBean"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
		List<PostBean> postlist = (List<PostBean>) request.getAttribute("postlist");
		if (postlist != null) {
	%>
	<table>
		<%
			for (PostBean postBean : postlist) {
		%>
		<tr>
			<td>
				<%=postBean.getDescription()%>
			</td>
		</tr>
		<%
			}
		%>
	</table>
	<%
		}
	%>

</body>
</html>