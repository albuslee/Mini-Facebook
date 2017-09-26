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
		   <form style="float:right" class="form-inline navbar-form">
				<div class="input-group">
                   <input type="text" class="form-control" placeholder="freindSearch">
                    <span class="input-group-btn">
                      <button class="btn btn-default" type="button">search</button>
                    </span>
                  </div>
		   </form>
        </div>
   </nav>
	<div><jsp:include page="register.jsp"></jsp:include></div>
</body>
</html>
