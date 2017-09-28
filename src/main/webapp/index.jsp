<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
	<link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <title>Home</title>
    <style>
    	#navbar-main ul li{
    	 font-size:18px;
    	}
    	#navbar-main ul{
    	 padding-left:50%;
    	}
    </style>
</head>
<body>
	<nav class="navbar navbar-default">
	    <div class="navbar-header">
	        <img  style="margin-top:10%;margin-left:10%" src="image/UNSW_0.png" height="35" width="82">
	    </div>

        <div class="navbar-collapse collapse" id="navbar-main" >
          <ul class="nav navbar-nav">
            <li>
              <a href="post.jsp">Home</a>
            </li>
            <li>
              <a href="profile.jsp">Profile</a>
            </li>
          </ul>
		   <s:form action = "searchFriends" style="float:right" class="form-inline navbar-form">
				<div class="input-group">
                   <s:textfield name = "detailform.name" label = "name" placeholder="friendSearch"> </s:textfield>
                    <span class="input-group-btn">
                      <button class="btn btn-default" type="button">search</button>
                    </span>
                  </div>
		   </s:form>
        </div>
         
<%--            <div id="formSize">
    <s:form action="register" class="form-horizontal">
        <s:textfield name="userform.username" label="Username" class="form-control" placeholder="Username"></s:textfield>
        <s:password name="userform.password" label="Password" class="form-control" placeholder="password"></s:password>
        <s:select class="form-control" list="#{'M':'Male','F':'Female'}" listKey="key" listValue="value" 
            name="userform.gender" label="Gender" value="M"></s:select>
        <s:textfield name="userform.email" label="email" class="form-control" placeholder="Email"></s:textfield>
        <s:submit value="Register" class="btn btn-primary btn-block"></s:submit>
    </s:form>
   </div> --%>
   </nav>
	<div><jsp:include page="register.jsp"></jsp:include></div>
</body>
</html>
