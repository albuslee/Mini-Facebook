<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%> --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="edu.unsw.minifacebook.bean.PostBean"%>
<%@ page import="edu.unsw.minifacebook.bean.UserBean"%>
<%@ page import="edu.unsw.minifacebook.bean.LikeBean"%>
<%@ page import="edu.unsw.minifacebook.bean.DetailBean"%>
<%@ page import="edu.unsw.minifacebook.bean.NotificationBean"%>


<%@ page import="edu.unsw.minifacebook.service.LikeService"%>

<%@ page import="edu.unsw.minifacebook.DAO.NotificationDAO"%>
<%@ page import="edu.unsw.minifacebook.DAO.DetailDAO"%>
<%@ page import="edu.unsw.minifacebook.DAO.LikeDAO"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
<title>Posts</title>
<script type="text/javascript">
    var filebrowserUploadUrl = '/upload/';
</script>
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="ckeditor/config.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<style type="text/css">

#navbar-main ul li {
	font-size: 18px;
}

#navbar-main ul {
	padding-left: 40px;
}
    	#navbar-main ul li{
    	 font-size:18px;
    	}
    	#navbar-main ul{
    	 padding-left:40px;
    	}
    	#noti_Container {
	        position: relative;
	    }
        /* A CIRCLE LIKE BUTTON IN THE TOP MENU. */
        #noti_Button {
	        width:22px;
	        height:22px;
	        line-height:22px;
	        border-radius:50%;
	        -moz-border-radius:50%; 
	        -webkit-border-radius:50%;
	        background:#FFF;
	        margin:-3px 10px 0 10px;
	        cursor:pointer;
    	}
    	/* THE POPULAR RED NOTIFICATIONS COUNTER. */
    	#noti_Counter {
	        display:block;
	        position:absolute;
	        background:#E1141E;
	        color:#FFF;
	        font-size:12px;
	        font-weight:normal;
	        padding:1px 3px;
	        margin:-8px 0 0 25px;
	        border-radius:2px;
	        -moz-border-radius:2px; 
	        -webkit-border-radius:2px;
	        z-index:1;
    	}
    	/* THE NOTIFICAIONS WINDOW. THIS REMAINS HIDDEN WHEN THE PAGE LOADS. */
	    #notifications {
	        display:none;
	        width:430px;
	        position:absolute;
	        top:30px;
	        right:0;
	        background:#FFF;
	        border:solid 1px rgba(100, 100, 100, .20);
	        -webkit-box-shadow:0 3px 8px rgba(0, 0, 0, .20);
	        z-index: 0;
	    }
	    /* AN ARROW LIKE STRUCTURE JUST OVER THE NOTIFICATIONS WINDOW */
	    #notifications:before {         
	        content: '';
	        display:block;
	        width:0;
	        height:0;
	        color:transparent;
	        border:10px solid #CCC;
	        border-color:transparent transparent #FFF;
	        margin-top:-20px;
	        margin-left:400px;
	    }
	    h3 {
	        display:block;
	        color:#333; 
	        background:#FFF;
	        font-weight:bold;
	        font-size:13px;    
	        padding:8px;
	        margin:0;
	        border-bottom:solid 1px rgba(100, 100, 100, .30);
	    }

#photo {
	width: 150px;
	height: 150px;
	margin: 2% 2% 2% 12%;
	float: left;
}

.commentAvatarImage {
	width: 150px;
	height: 150px;
	line-height: 0; /* remove line-height */
	display: inline-block; /* circle wraps image */
	border-radius: 50%; /* relative value */
	-moz-border-radius: 50%;
	-webkit-border-radius: 50%;
	transition: linear 0.25s;
}
.postimg{
	width: 32px;
	height: 32px;
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
	DetailBean detailbean2 =(DetailBean) request.getSession().getAttribute("detailbean");
	String User=detailbean2.getUsername();
	String imgsource=detailbean2.getPhoto();
	if (!imgsource.contains("image")){imgsource = "image/UNSW_0.png";}
	%>
<div class="row" style="width:100%">
<div id="photo" class="col-lg-3">
<img class="commentAvatarImage" src="<%=imgsource%>" width="100%">
</div>
<div id = "wd" class="col-lg-8">
<s:form action = "addposts">
<s:textarea name="postform.description" cols="300" rows="8" placeholder="Input your post"></s:textarea>
        <s:submit value="Submit" class="btn btn-primary"></s:submit>
</s:form>
</div>
</div>
<script type="text/javascript">
CKEDITOR.replace( 'postform.description');
</script>
		<%  //DetailDAO detaildao=new DetailDAO();
			//DetailBean detailBean = new DetailBean();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<PostBean> postlist = (List<PostBean>) request.getAttribute("postlist");
			if (postlist != null) {
		%>
<div class="row" style="width:100%">
		<div class="col-lg-3"></div>
		<div class="col-lg-8">
		<div class="list-group" style="width:102%">
			<%
				for (PostBean postBean : postlist) {
					//detailBean = detaildao.getUserByUsername(postBean.getCreator().getUsername());
					//String imgsrc=detailBean.getPhoto();
			%>
			<%-- <img class="postimg col-sm-1" src="<%=imgsrc%>"> --%>
			<a href="#" class="list-group-item" style="margin-left:1%"> 
			<%=postBean.getDescription()%>
			<% 
			   Date o=postBean.getPosttime();%>
			<%=sdf.format(o)%>
			
			<% int post = postBean.getId(); %>

			<%
				UserBean userBean = postBean.getCreator();

				//LikeDAO likeDao = new LikeDAO();
				String num = "0";
				int a = postBean.getLikenum();

			%>
			<button type="button" class="btn btn-default btn-sm"
				onclick="btnClick(this)" id='like<%=post%>'>
				<span class="glyphicon glyphicon-thumbs-up"></span> Like 
				<span class="badge" id='like_num<%=post%>'><%=a%></span>
			</button>
			<button type="button" class="btn btn-default btn-sm"
				onclick="btnClick(this)" id='dislike<%=post%>'>
				<span class="glyphicon glyphicon-thumbs-down"></span> Dislike
			</button></a>
			<script
				src="https://ajax.googleapis.com/ajax/libs/jquery/1.8/jquery.min.js">
			</script>
			<script>
			function addLikes(event, thumb, del) {
				var postid = event;
				var thumb = thumb;
				var del = del;
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
				if(del == 1){
					var url = "mini_facebook/addLikes?postid=" + postid + "&thumb=" + thumb + "&del=" + del;
				}else if (del == -1){
					var url = "mini_facebook/addLikes?postid=" + postid + "&thumb=" + thumb + "&del=" + del;
				}
				<%	
		        		request.getSession().setAttribute("User", User);
		        		//request.getSession().setAttribute("Post", postBean);
		        		//request.getSession().setAttribute("Thumb", thumb);
		        		//LikeBean id = (LikeBean)request.getSession().getAttribute("id");
		        %>
				xmlHttp.open("GET", url, true);
				xmlHttp.send();
				xmlHttp.onreadystatechange = function() {
					if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
						alert(xmlHttp.responseText);
						document.getElementById("likeId").innerHTML = xmlHttp.responseText;
					}
				}
				xmlHttp.close();
				//alert("send");
			}
			$(document).ready(function(){
	        $("#like<%=post%>").toggle(
	            function(){	
	            		$("#like_num<%=post%>").html(<%=a = a + 1%>),
	            		$("#dislike<%=post%>").prop("disabled",true),
	            		addLikes(<%=post%>, 1, 1);
		            	<%  //String n = request.getAttribute("numLikes").toString();
		            		//a = Integer.parseInt(n);
		            	%>
		            	
		        },
	            function(){
		        		$("#like_num<%=post%>").html(<%=a = a - 1%>),
		        		$("#dislike<%=post%>").prop("disabled",false),
			        	addLikes(<%=post%>, 1, -1);
			        	
		        });
			$("#dislike<%=post%>").toggle(
	            function(){
	            		$("#like_num<%=post%>").html(<%=a = a - 1%>),
	            		$("#like<%=post%>").prop("disabled",true),
	            		addLikes(<%=post%>, -1, 1);
	             },
	            function(){
	            	 	$("#like_num<%=post%>").html(<%=a = a + 1%>),
	            	 	$("#like<%=post%>").prop("disabled",false),
	            	 	addLikes(<%=post%>, -1, -1);
	             }
	            );
			});
			</script>
			<%
				}
			%>
		</div>
		<%
		}
		%>
	</div>
</div>
</body>
</html>