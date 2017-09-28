<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="css/like.css" rel="stylesheet">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
	<table>
		<tr>
			<th>Hello likes</th>
		</tr>
			<tr></tr>
		<tr>
			
		</tr>
	</table>
	<i onclick="myFunction(this)" class="fa fa-thumbs-up"></i>
	<script>
	function myFunction(x) {
   		x.classList.toggle("fa-thumbs-down");
	}
	</script>
	<button class="button" onclick="btn" id='like'>Like</button>
  	%i.fa.fa-thumbs-up
  	%span 0
</div>

</body>
</html>