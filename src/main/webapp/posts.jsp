<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="s" uri="/struts-tags"%>
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
		   <s:form style="float:right" class="form-inline navbar-form" action="searchuser">
				<div class="input-group">
                   <s:textfield name="detailform.name" type="text" class="form-control" placeholder="freindSearch"></s:textfield>
                    <span class="input-group-btn">
                     <s:submit value="button" class="btn btn-default"></s:submit>
                    </span>
                  </div>
		   </s:form>
		   <ul class="nav navbar-nav navbar-right">
        		<li><a href="login.jsp">login</a></li>
        		<!---can use dropdown write login form-->
            </ul>
        </div>
   </nav>
  

<div id = "wd">
<s:form action = "addposts">
<s:textarea name="postform.description" cols="300" rows="8" placeholder="Input your post"></s:textarea>
        <s:submit value="Submit" class="btn btn-primary"></s:submit>
</s:form>
</div>
<script type="text/javascript">
CKEDITOR.replace( 'postform.description');
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
		<tr>
			
		
		<%	int a=0;
			int post = postBean.getId();
		%>
		<td>
		<button type="button" class="btn btn-default btn-sm" onclick="btnClick(this)" id='like<%=post%>'>
	          <span class="glyphicon glyphicon-thumbs-up"></span> Like
	          <span class="badge" id='like_num<%=post%>'><%=a %></span>
	    </button>
	    </td>
	    <td>
	    <button type="button" class="btn btn-default btn-sm" onclick="btnClick(this)" id='dislike<%=post%>'>
	          <span class="glyphicon glyphicon-thumbs-down"></span> Dislike
	    </button>
	    </td>
		</tr>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8/jquery.min.js">
		</script>
    		<script>
        $("#like<%=post%>").toggle(
            function(){$("#like_num<%=post%>").html(<%=a=a+1%>);},
            function(){$("#like_num<%=post%>").html(<%=a=a-1%>);
        });
        $("#dislike<%=post%>").toggle(
            function(){$("#like_num<%=post%>").html(<%=a=a-1%>);},
            function(){$("#like_num<%=post%>").html(<%=a=a+1%>);
        });
		</script>
		<%
			}
		%>
	</table>
	<%
		}
	%>

</body>
</html>