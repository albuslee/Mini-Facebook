<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="edu.unsw.minifacebook.bean.DetailBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
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
</style>
<%DetailBean detail = (DetailBean) request.getSession().getAttribute("detailbean");
if(detail!=null){%>
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
