<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
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
		<tr>
			
		
	<%	int a=0; 
	%>
	<td><button type="button" class="btn btn-default btn-sm" onclick="btnClick(this)" id='like'>
          <span class="glyphicon glyphicon-thumbs-up"></span> Like
          <span class="badge" id='like_num'><%=a %></span>
    </button>
    </td>
    <td><button type="button" class="btn btn-default btn-sm" onclick="btnClick(this)" id='dislike'>
          <span class="glyphicon glyphicon-thumbs-down"></span> Dislike
    </button>
    </td>
	</tr>
	</table>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8/jquery.min.js">
	</script>
    <script>
        $("#like").toggle(
            function(){$("#like_num").html(<%=a=a+1%>);},
            function(){$("#like_num").html(<%=a=a-1%>);
        });
        $("#dislike").toggle(
            function(){$("#like_num").html(<%=a=a-1%>);},
            function(){$("#like_num").html(<%=a=a+1%>);
        });
	</script>
</div>

</body>
</html>