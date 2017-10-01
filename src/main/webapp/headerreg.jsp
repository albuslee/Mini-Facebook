<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="edu.unsw.minifacebook.bean.DetailBean"%>
	<%@ page import="java.util.List"%>
	<%@ page import="java.util.ArrayList"%>

	<%@ page import="edu.unsw.minifacebook.bean.NotificationBean"%>
	<%@ page import="edu.unsw.minifacebook.DAO.NotificationDAO"%>
	<%@ page import="edu.unsw.minifacebook.DAO.FriendDAO"%>
	<%@ page import="edu.unsw.minifacebook.bean.UserBean"%>
	
	<%@ page import="edu.unsw.minifacebook.service.NotificationService"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <style>
    	#navbar-main ul li{
    	 font-size:18px;
    	}
    	#navbar-main ul{
    	 padding-left:5%;
    	}
    	
    #noti_Container {
        position:relative;
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
        left:0;
        background:#FFF;
        border:solid 1px rgba(100, 100, 100, .20);
        -webkit-box-shadow:0 3px 8px rgba(0, 0, 0, .20);
        z-index: 100;
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
        margin-left:10px;
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
<div onload="autoSubmit()">
<form id="notificationform2" action="refreshnotification"></form>
</div>

<%
DetailBean detail = (DetailBean) request.getSession().getAttribute("detailbean");
int nl_size = 0;
if(detail!=null){
	List<NotificationBean> notificationlist = (List<NotificationBean>) request.getSession().getAttribute("notificationList");
	
%>
	<nav class="navbar navbar-default">
	    <div class="navbar-header">
	        <img  style="margin-top:10%;margin-left:10%" src="image/UNSW_0.png" height="35" width="82">
	    </div>

        <div class="navbar-collapse collapse" id="navbar-main" >
          <ul class="nav navbar-nav">
            <li>
              <a href="posts.jsp">Home</a>
            </li>
            <li>
              <a href="profile.jsp">Profile</a>
            </li>
            <li>
              <a href="friendrequest.jsp">Friend Requests</a>
            </li>
            <li id="noti_Container">
                <div id="noti_Counter"></div>   <!--SHOW NOTIFICATIONS COUNT.-->
                
                <!--A CIRCLE LIKE BUTTON TO DISPLAY NOTIFICATION DROPDOWN.-->
                <div id="noti_Button"></div>    

                <!--THE NOTIFICAIONS DROPDOWN BOX.-->
                <div id="notifications" >
                    <h3>Notifications</h3>
                    <div style="height:300px; overflow:scroll">
                    <h3><%
	                    if (notificationlist != null) {
	                    	for(NotificationBean fr: notificationlist){
	                    		if (fr.getnotification_status().equals("unread")) {
	                    			nl_size = nl_size + 1;
	                    		}
	                    		UserBean nfrom = fr.getFrom2();
	                    		DetailBean dBean = nfrom.getDetailBean();
	                    		if (fr.getType().equals("like")) {
	                    			out.println("[" + fr.getnotification_status() + "]");
	                    			out.println("Your post " + fr.getcommented_record() + " was liked by user " + dBean.getName() + ". ");%><br><%
	                    		}
	                    		else if (fr.getType().equals("friend")) {
	                    			out.println("[" + fr.getnotification_status() + "]");
	                    			out.println("User " + dBean.getName() + " sends a friend request. ");%><br><%
	                    		}
	                    	}
	                    }
	                %></h3>
	            	</div>
	            	<a onclick="location='posts.jsp'">See All Friend Requests</a>
                </div>
            </li>
          </ul>
		   <s:form style="float:right" class="form-inline navbar-form" action="searchuser">
				<div class="input-group">
                   <s:textfield name="detailform.name" type="text" class="form-control" placeholder="freindSearch"></s:textfield>
                    <span class="input-group-btn">
                     <s:submit value="search" class="btn btn-default"></s:submit>
                    </span>
                  </div>
		   </s:form>
        </div>
   </nav>
<%}else{ %>
   	<nav class="navbar navbar-default">
	    <div class="navbar-header">
	        <img  style="margin-top:10%;margin-left:10%" src="image/UNSW_0.png" height="35" width="82">
	    </div>

        <div class="navbar-collapse collapse" id="navbar-main">
		   <ul class="nav navbar-nav navbar-right">
        		<li><a href="login.jsp">login</a></li>
        		<li><a href="register.jsp">register</a></li>
        		<!---can use dropdown write login form-->
            </ul>
        </div>
   </nav>
 <%}%>
<script>
function autoSubmit(){
	 document.getElementById("notificationform2").submit();
	}
	var notification_counter = <%=nl_size%>
    $(document).ready(function () {

        // ANIMATEDLY DISPLAY THE NOTIFICATION COUNTER.
        $('#noti_Counter')
            .css({ opacity: 0 })
            .text(notification_counter)              // ADD DYNAMIC VALUE (YOU CAN EXTRACT DATA FROM DATABASE OR XML).
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