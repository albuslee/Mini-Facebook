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
	<link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <title>Posts</title>
    <style type = "text/css">
    	#wd
	{
		width:600px;
	}
    	#navbar-main ul li{
    	 font-size:18px;
    	}
    	#navbar-main ul{
    	 padding-left:40px;
    	}
    </style>

</head>
<body>
	<nav class="navbar navbar-default">
	    <div class="navbar-header">
	        <img  style="margin-top:10%;margin-left:10%" src="image/UNSW_0.png" height="35" width="82">
	    </div>

        <div class="navbar-collapse collapse" id="navbar-main">
          <ul class="nav navbar-nav">
            <li>
              <a href="post.jsp">Home</a>
            </li>
            <li>
              <a href="profile.jsp">Profile</a>
            </li>
          </ul>
		   <form class="navbar-form navbar-right">
				<div class="input-group">
                   <input type="text" class="form-control" placeholder="freindSearch">
                    <span class="input-group-btn">
                      <button class="btn btn-default" type="button">search</button>
                    </span>
                  </div>
		   </form>
		   <ul class="nav navbar-nav navbar-right">
        		<li><a href="login.jsp">login</a></li>
        		<!---can use dropdown write login form-->
            </ul>
        </div>
   </nav>
  

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