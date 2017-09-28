<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="edu.unsw.minifacebook.bean.DetailBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page import="java.util.List"%>
<%@ page import="edu.unsw.minifacebook.bean.PostBean"%>
<%@ page import="java.util.List"%>
<%@ page import="edu.unsw.minifacebook.bean.NotificationBean"%>
<%@ page import="edu.unsw.minifacebook.DAO.NotificationDAO"%>
<%@ page import="edu.unsw.minifacebook.bean.UserBean"%>

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
	margin: 2% 2% 2% 2%;
	float: left;
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
                   <s:textfield name="detailform.name" type="text" class="form-control" placeholder="friendSearch"></s:textfield>
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
		<div class="col-lg-3"></div>
		<div class="col-lg-8">
		<div class="list-group">
			<%
				for (PostBean postBean : postlist) {
			%>
			<img class="postimg col-sm-1" src="<%=postBean%>">
			<a href="#" class="list-group-item col-sm-11">
				<%=postBean.getDescription()%></a>


				<%
					int a = 0;
							int post = postBean.getId();
				%>
			<table>
			<tr>
				<td>
					<button type="button" class="btn btn-default btn-sm"
						onclick="btnClick(this)" id='like<%=post%>'>
						<span class="glyphicon glyphicon-thumbs-up"></span> Like <span
							class="badge" id='like_num<%=post%>'><%=a%></span>
					</button>
				</td>
				<td>
					<button type="button" class="btn btn-default btn-sm"
						onclick="btnClick(this)" id='dislike<%=post%>'>
						<span class="glyphicon glyphicon-thumbs-down"></span> Dislike
					</button>
				</td>
			</tr>
			<script
				src="https://ajax.googleapis.com/ajax/libs/jquery/1.8/jquery.min.js">
		</script>
			<script>
        $("#like<%=post%>").toggle(
            function(){$("#like_num<%=post%>").html(<%=a = a + 1%>);},
            function(){$("#like_num<%=post%>").html(<%=a = a - 1%>);
        });
        $("#dislike<%=post%>").toggle(
            function(){$("#like_num<%=post%>").html(<%=a = a - 1%>);},
            function(){$("#like_num<%=post%>
				").html(
			<%=a = a + 1%>
				);
				});
			</script>
			<%
				}
			%>
		</table>
		</div>
		<%
			}
		%>
	</div>
<script>
    $(document).ready(function () {

        // ANIMATEDLY DISPLAY THE NOTIFICATION COUNTER.
        $('#noti_Counter')
            .css({ opacity: 0 })
            .text('7')              // ADD DYNAMIC VALUE (YOU CAN EXTRACT DATA FROM DATABASE OR XML).
            .css({ top: '-10px' })
            .animate({ top: '-2px', opacity: 1 }, 500);

        $('#noti_Button').click(function () {

            // TOGGLE (SHOW OR HIDE) NOTIFICATION WINDOW.
            $('#notifications').fadeToggle('fast', 'linear', function () {
                if ($('#notifications').is(':hidden')) {
                    $('#noti_Button').css('background-color', '#2E467C');
                }
                else $('#noti_Button').css('background-color', '#FFF');        // CHANGE BACKGROUND COLOR OF THE BUTTON.
            });

            $('#noti_Counter').fadeOut('slow');                 // HIDE THE COUNTER.

            return false;
        });

        // HIDE NOTIFICATIONS WHEN CLICKED ANYWHERE ON THE PAGE.
        $(document).click(function () {
            $('#notifications').hide();

            // CHECK IF NOTIFICATION COUNTER IS HIDDEN.
            if ($('#noti_Counter').is(':hidden')) {
                // CHANGE BACKGROUND COLOR OF THE BUTTON.
                $('#noti_Button').css('background-color', '#2E467C');
            }
        });

        $('#notifications').click(function () {
            return false;       // DO NOTHING WHEN CONTAINER IS CLICKED.
        });
    });
</script>
</body>
</html>