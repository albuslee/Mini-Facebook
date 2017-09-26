<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/main.css">
<title>User Register Page</title>
</head>
<body>
<s:form action="login">
<s:textfield class="inputtext" id="email" tabindex="1" data-testid="royal_email" type="emai" name="userform.username"></s:textfield>
<s:password class="inputtext" id="pass" tabindex="2" data-testid="royal_pass" type="password" name="userform.password"
													></s:password>
<input value="Log In" tabindex="4" data-testid="royal_login_button" id="u_0_5" type="submit"></label>
</s:form>


</body>
</html>