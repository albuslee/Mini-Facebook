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
<s:form action = "searchFriends" style="float:right" class="form-inline navbar-form">
	gender <br/>
	<s:textfield gender = "detailform.gender" label = "gender" placeholder = " Male"></s:textfield>
	<br/>
	Date of Birth <br/>
	<s:textfield dob = "detailform.birthday" label = "birthday" placeholder = " 1992/12/08"></s:textfield>
	<br/>
	 Major <br/>
	<s:textfield major = "detailform.major" label = "major" placeholder = "cs"></s:textfield>
	<br/>
	<br/>
	<span class="input-group-btn">
	<button class="btn btn-default" type="button">advancedSearch</button>
</s:form>
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