<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.Iterator"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page import="edu.unsw.minifacebook.bean.DetailBean"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>profile</title>



<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<style>
#detailForm {
	width: 60%;
	margin: 2% 2% 2% 2%;
	float: right;
}

#photo {
	width: 150px;
	height: 150px;
	margin: 2% 2% 2% 15%;
	float: left;
}

.modal .form-control {
	margin: 2% 2% 2% 2%;
}

#accordion1 {
	
}

.commentAvatarImage {
	width: 100%;
	height: 100%;
	line-height: 0; /* remove line-height */
	display: inline-block; /* circle wraps image */
	border-radius: 50%; /* relative value */
	-moz-border-radius: 50%;
	-webkit-border-radius: 50%;
	transition: linear 0.25s;
}

</style>

<jsp:include page="headerreg.jsp"></jsp:include>
</head>
<body>
	<%
		DetailBean detail = (DetailBean) request.getAttribute("detailbean");
		Map<Date,String> activity = (Map<Date,String> )request.getAttribute("activity");
		String User = detail.getUsername();
		String imgsource = detail.getPhoto();
		String name2 = detail.getName();
		String age = detail.getAge();
		String birthday = detail.getBirthday();
		String major = detail.getMajor();
		String gender = detail.getGender();
		if (imgsource == null) {
			imgsource = "image/UNSW_0.png";
		}
	%>

	<div id="photo">
		<img class="commentAvatarImage" src="<%=imgsource%>" width="100%">
		<form action="uploadImage" enctype="multipart/form-data" method="post">
			<input type="file" name="uploadFile" /> <input type="submit"
				value="submit">
		</form>
	</div>
	<div id="detailForm" >
		<ul class="list-group center-block" style="width: 60%">
		<li class="list-group-item"><%
    Set<Date> keySet = activity.keySet();
    Iterator<Date> iter = keySet.iterator();
    while (iter.hasNext()) {
        Date key = iter.next();
        
        %>
        <%=key %> : <%= activity.get(key) %>
        <br>
        <%
    }
	%></li>
		</ul>
	</div>
	
</body>
</html>