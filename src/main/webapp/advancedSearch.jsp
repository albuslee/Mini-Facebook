<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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

		   <s:form style="float:right" class="form-inline navbar-form" action="searchuser">
				<div class="input-group">
                   <s:textfield name="detailform.major" type="text" class="form-control" placeholder="cs"></s:textfield>
                    <span class="input-group-btn">
                     <s:submit value="search" class="btn btn-default"></s:submit>
                    </span>
                  </div>
		   </s:form>
		   <s:form style="float:right" class="form-inline navbar-form" action="searchuser">
				<div class="input-group">
                   <s:textfield name="detailform.birthday" type="text" class="form-control" placeholder="2000/12/12"></s:textfield>
                    <span class="input-group-btn">
                     <s:submit value="search" class="btn btn-default"></s:submit>
                    </span>
                  </div>
		   </s:form>
		   <s:form style="float:right" class="form-inline navbar-form" action="searchuser">
				<div class="input-group">
                   <s:textfield name="detailform.gender" type="text" class="form-control" placeholder="M"></s:textfield>
                    <span class="input-group-btn">
                     <s:submit value="search" class="btn btn-default"></s:submit>
                    </span>
                  </div>
		   </s:form>
<%-- <s:form action = "searchuser" style="float:right" class="form-inline navbar-form">
	gender <br/>
	<s:textfield gender = "detailform.gender" type = "text" placeholder = " "></s:textfield>
	<br/>
	Date of Birth <br/>
	<s:textfield dob = "detailform.birthday" type = "text" placeholder = " "></s:textfield>
	<br/>
	 Major <br/>
	<s:textfield major = "detailform.major" type = "text"  placeholder = ""></s:textfield>
	<br/>
	<br/>
	<span class="input-group-btn">
	 <s:submit value="search" class="btn btn-default"></s:submit>
</s:form> --%>
</body>
</html>


<%-- <s:form action = "searchFriends" style="float:right" class="form-inline navbar-form">
				<div class="input-group">
                   <s:textfield name = "detailform.name" label = "name" placeholder="friendSearch"> </s:textfield>
                    <span class="input-group-btn">
                      <button class="btn btn-default" type="button">search</button>
                      <br/>
                      <br/>
                      <br/>
                      
                     
              
                    </span>
                  </div>
		   </s:form> --%>