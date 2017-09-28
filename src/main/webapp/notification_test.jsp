<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ page import="java.util.List"%>
	<%@ page import="edu.unsw.minifacebook.bean.NotificationBean"%>
	<%@ page import="edu.unsw.minifacebook.DAO.NotificationDAO"%>
	<%@ page import="edu.unsw.minifacebook.bean.UserBean"%>
	
	<link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <title>Posts</title>
    <style>
    	#navbar-main ul li{
    	 font-size:18px;
    	}
    	#navbar-main ul{
    	 padding-left:40px;
    	}
    	#formSize{
    	  position: absolute;
    	  top:30%;
    	  left:38%;
    	  width:20%;
    	}
    	.form-control{
    	  margin: 2% 2% 2% 2%;
    	}
    	.btn-block{
    	  margin: 2% 2% 2% 2%;
    	}
    	html{height: 100%}
        body{margin: 0;height: 100%;
            background: 8a8680;}
        canvas{display: block;width: 100%;height: 100%;}
        .text{
            width: 100%;
            background: transparent;
            display: flex;
            justify-content: center;
            height: 100%;
            line-height: 100%;
            top: 0;
            position: absolute;
            top: 50%;
            font-size: 50px;
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
    </style>
</head>
<body>
	<%
	UserBean userBean = new UserBean();
	userBean.setUserId(1);
	NotificationDAO notificationDao = new NotificationDAO();
	List<NotificationBean> notificationlist = notificationDao.getNotificationByUserBean(userBean);
	
	%>
	<nav class="navbar navbar-default">
	    <div class="navbar-header">
	        <img  style="margin-top:10%;margin-left:10%" src="image/UNSW_0.png" height="35" width="82">
	    </div>

        <div class="navbar-collapse collapse" id="navbar-main">
          <ul class="nav navbar-nav">
            <li>
              <a href="notification_test.jsp">Home</a>
            </li>
            <li>
              <a href="profile.jsp">Profile</a>
            </li>
          </ul>
		   <ul class="nav navbar-nav navbar-right">
		   		<li id="noti_Container">
	                <div id="noti_Counter"></div>   <!--SHOW NOTIFICATIONS COUNT.-->
	                
	                <!--A CIRCLE LIKE BUTTON TO DISPLAY NOTIFICATION DROPDOWN.-->
	                <div id="noti_Button"></div>    
	
	                <!--THE NOTIFICAIONS DROPDOWN BOX.-->
	                <div id="notifications">
	                    <h3>Notifications</h3>
	                    <%out.println("hahaha"); %><br>
	                    <%="qwer" %><br>
	                    <%
	                    	for( int i = 0 ; i < notificationlist.size() ; i++) {
	                    	    out.println(notificationlist.get(i));
	                    	}
	                    %>
	                    <div style="height:300px;"></div>
	                    
	                </div>
	            </li>
        		<li><a href="login.jsp">login</a></li>
        		<!---can use dropdown write login form-->
            </ul>
        </div>
   </nav>
   <canvas id="canvas"></canvas>
   <script src="index.js"></script>
   <div id="formSize">
    <s:form action="register" class="form-horizontal">
        <s:textfield name="userform.username" label="Username" class="form-control" placeholder="Username"></s:textfield>
        <s:password name="userform.password" label="Password" class="form-control" placeholder="password"></s:password>
        <s:select class="form-control" list="#{'M':'Male','F':'Female'}" listKey="key" listValue="value" 
            name="userform.gender" label="Gender" value="M"></s:select>
        <s:textfield name="userform.email" label="email" class="form-control" placeholder="Email"></s:textfield>
        <s:submit value="Register" class="btn btn-primary btn-block"></s:submit>
    </s:form>
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
<!-- cherryblog.site/  -->
</html>