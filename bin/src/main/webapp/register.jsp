<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Register Page</title>
</head>
<body>

    <s:form action="register">
        <s:textfield name="userform.username" label="Username"></s:textfield>
        <s:password name="userform.password" label="Password"></s:password>
        <s:select list="#{'M':'Male','F':'Female'}" listKey="key" listValue="value"
            name="userform.gender" label="Gender" value="M"></s:select>
        <s:submit value="Register"></s:submit>
    </s:form>

</body>
</html>