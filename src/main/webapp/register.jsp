<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
              <a href="posts.jsp">Home</a>
            </li>
            <li>
              <a href="profile.jsp">Profile</a>
            </li>
          </ul>
		   <ul class="nav navbar-nav navbar-right">
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
</body>
<!-- cherryblog.site/  -->
</html>