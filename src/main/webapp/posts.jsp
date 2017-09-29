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
<%@ page import="edu.unsw.minifacebook.DAO.DetailDAO"%>
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
	<div class="row">
	<div class="col-lg-1"></div>
		<div class="col-lg-2">
			<%
				DetailBean detail = (DetailBean) request.getSession().getAttribute("detailbean");
				String imgsource = detail.getPhoto();
				String User = detail.getUsername();
				if (imgsource == null) {
					imgsource = "image/UNSW_0.png";
				}
			%>

			<div id="photo">
				<img class="commentAvatarImage" src="<%=imgsource%>" width="100%">
				<div style="text-align:center" ><%=User%></div>
			</div>
		</div>
		<div id = "wd" class="col-lg-8">
<s:form action = "addposts">
<s:textarea name="postform.description" cols="300" rows="8" placeholder="Input your post"></s:textarea>
        <s:submit value="Submit" class="btn btn-primary"></s:submit>
</s:form>
</div>
<script type="text/javascript">
CKEDITOR.replace( 'postform.description');
</script>

		<%  DetailDAO detaildao=new DetailDAO();
			List<PostBean> postlist = (List<PostBean>) request.getAttribute("postlist");
			if (postlist != null) {
		%>
		<div class="col-lg-3"></div>
		<div class="col-lg-8">
		<div class="list-group">
			<%
				for (PostBean postBean : postlist) {
					DetailBean detailbean = detaildao.getUserByUsername(postBean.getCreator().getUsername());
					String imgsrc=detailbean.getPhoto();
			%>
			<img class="postimg col-sm-1" src="<%=imgsrc%>">
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
            function(){$("#like_num<%=post%>").html(
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