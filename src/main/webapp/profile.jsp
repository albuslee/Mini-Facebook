<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">  
<title>无刷新上传图片</title>  
  
  
<script type="text/javascript">  
    $(function() {  
        //当点击"选择按钮"时,调用该函数  
        $("#bt_select").click(function() {  
            $("#imageFile").click();  
        });  
        //如果图片改变，该form就提交  
        $("#imageFile").bind("change", function() {  
            if ($(this).val()) {  
                $("#uploadImageForm").submit();  
            }  
        });  
  
    });  
  
    window.uploadCallback = function(fileName) {  
        var imgUrl = $("#showImageUrl").val();  
        $("#bt_image").attr("src", imgUrl + "?fileName=" + fileName);  
    };  
</script>  
<title>Profile</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <title>Home</title>
    <style>
    	#navbar-main ul li{
    	 font-size:18px;
    	}
    	#navbar-main ul{
    	 padding-left:5%;
    	}
    	#detailForm{
    	 width:80%;
    	 margin:2% 2% 2% 2%;
    	 float:right;
    	}
    	#photo{
    	 width:10%;
    	 margin:2% 2% 2% 2%;
    	 float:left;
    	}
    	#accordion{
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
<div id="photo">
<form action="uploadImage" enctype="multipart/form-data" method="post"> 
     <input type="file" name="uploadFile" />  
     <input type="submit" value="submit">  
</form>
</div>  
<div id="detailForm">
<form class="form-horizontal">
  <div class="form-group">
    <label class="col-sm-2 control-label" >UserName</label>
    <div class="col-sm-3">
      <input class="form-control" type="text" id="formGroupInputLarge" placeholder="UserName" disabled>
    </div>
  </div>
   <div class="form-group">
    <label class="col-sm-2 control-label" >Name</label>
    <div class="col-sm-3">
      <s:textfield class="form-control" type="text" name="detailform.Name" placeholder="Name"></s:textfield>
    </div>
  </div>
     <div class="form-group">
    <label class="col-sm-2 control-label" >Age</label>
    <div class="col-sm-2">
      <s:textfield class="form-control" type="text" id="detailform.Age" placeholder="Age"></s:textfield>    </div>
  </div>
     <div class="form-group">
    <label class="col-sm-2 control-label" >Birthday</label>
    <div class="col-sm-2">
      <s:textfield class="form-control" type="text" id="formGroupInputSmall" placeholder="1990-01-01"></s:textfield>
    </div>
  </div>
     <div class="form-group">
    <label class="col-sm-2 control-label" >Major</label>
    <div class="col-sm-6">
      <s:textfield class="form-control" type="text" id="formGroupInputSmall" placeholder="Major"></s:textfield>
    </div>
  </div>
     <div class="form-group">
    <label class="col-sm-2 control-label" >Gender</label>
    <div class="col-sm-2">
       <s:select class="form-control" list="#{'M':'Male','F':'Female'}" listKey="key" listValue="value" 
            name="userform.gender" label="Gender" value="M"></s:select>
    </div>
  </div>
	<div class="panel-group col-sm-9" id="accordion">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
				<a data-toggle="collapse" data-parent="#accordion" 
				   href="#collapseOne" >
					Change Password or Email (need verification)
				</a>
			</h4>
		</div>
		<div id="collapseOne" class="panel-collapse collapse">
			<div class="panel-body">
			  <div class="form-group">
			    <label class="col-sm-2 control-label" >PassWord</label>
			    <div class="col-sm-8">
			      <s:textfield class="form-control" type="text" id="detailform.Age" placeholder="PassWord"></s:textfield>    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label" >Email</label>
			    <div class="col-sm-8">
			      <s:textfield class="form-control" type="text" id="detailform.Email" placeholder="Email"></s:textfield>    </div>
			  </div>
			</div>
		</div>
	</div>
</form>
</div>
</body>
</html>