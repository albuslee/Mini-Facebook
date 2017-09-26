<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page import="java.util.List"%>
<%@ page import="edu.unsw.minifacebook.bean.PostBean"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Posts</title>
<script type="text/javascript">
    var filebrowserUploadUrl = '/upload/';
</script>
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="ckeditor/config.js"></script>
<style type = "text/css">
	#wd
	{
		width:600px;
	}
</style>
</head>
<body>

<div id = "wd">
<form action = "test.php" name = "sub" method = "post">
<textarea name="editor" cols="300" rows="8">Input your post</textarea>
<input type = "submit" name = "sub"  value = "Post" />
</form>
</div>
<script type="text/javascript">
CKEDITOR.replace( 'editor');
</script>

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