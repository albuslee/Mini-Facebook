<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@page import="java.lang.String"%>
<%@ page import="edu.unsw.minifacebook.bean.DetailBean"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>profile</title>



<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<style>
#detailForm {
	width: 60%;
	margin: 2% 2% 2% 2%;
	float: right;
}

#photo {
	width: 150px;
	height: 150px;
	margin: 2% 2% 2% 15%;
	float: left;
}

.modal .form-control {
	margin: 2% 2% 2% 2%;
}

#accordion1 {
	
}

.commentAvatarImage {
	width: 100%;
	height: 100%;
	line-height: 0; /* remove line-height */
	display: inline-block; /* circle wraps image */
	border-radius: 50%; /* relative value */
	-moz-border-radius: 50%;
	-webkit-border-radius: 50%;
	transition: linear 0.25s;
}

</style>

<jsp:include page="headerreg.jsp"></jsp:include>
</head>
<body>

	<%
		DetailBean detail = (DetailBean) request.getSession().getAttribute("detailbean");
	if(detail != null){
		String User = detail.getUsername();
		String imgsource = detail.getPhoto();
		String name2 = detail.getName();
		String age = detail.getAge();
		String birthday = detail.getBirthday();
		String major = detail.getMajor();
		String gender = detail.getGender();
		if (!imgsource.contains("image")) {
			imgsource = "image/UNSW_0.png";
		}
	%>

	<div id="photo">
		<img class="commentAvatarImage" src="<%=imgsource%>" width="100%">
		<form action="uploadImage" enctype="multipart/form-data" method="post">
			<input type="file" name="uploadFile" /> <input type="submit"
				value="submit">
		</form>
	</div>
	<div id="detailForm" >
		<s:form class="form-horizontal" action="detailchange">
			<div class="form-group">
				<label class="col-sm-2 control-label">UserName</label>
				<div class="col-sm-4">
					<input class="form-control" type="text" value="<%=User%>" disabled>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Name</label>
				<div class="col-sm-4">
					<s:textfield class="form-control" name="detailform.name"
						placeholder="<%=name2%>"></s:textfield>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Age</label>
				<div class="col-sm-4">
					<s:textfield class="form-control" name="detailform.age"
						placeholder="<%=age%>"></s:textfield>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Birthday</label>
				<div class="col-sm-4">
					<s:textfield class="form-control" name="detailform.birthday"
						placeholder="<%=birthday%>"></s:textfield>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Major</label>
				<div class="col-sm-4">
					<s:textfield class="form-control" name="detailform.major"
						placeholder="<%=major%>"></s:textfield>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Gender</label>
				<div class="col-sm-4">
					<s:select class="form-control" list="#{'M':'Male','F':'Female'}"
						listKey="key" listValue="value" name="detailform.gender"
						label="Gender" value='M'></s:select>
				</div>
			</div>
			<s:submit value="submit" class="btn btn-primary"
				style="margin-left:33%"></s:submit>
		</s:form>
		<div class="panel-group col-sm-9" id="accordion1">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion1"
							href="#collapseOne1"> Change Password or Email (need
							verification) </a>
					</h4>
				</div>
				<div id="collapseOne1" class="panel-collapse collapse">
					<div class="panel-body">
						<button type="button" class="btn" data-toggle="modal"
							data-target="#myModal1">change password</button>
						<button type="button" class="btn" data-toggle="modal"
							data-target="#myModal2">change email</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">change password</h4>
				</div>
				<s:form action="detailchange" class="form-inline">
					<div class="modal-body" style="width: 80%">
						<div class="row">
							<s:password name="detailform.password" label="Password"
								id="input1" class="form-control" placeholder="new Password"></s:password>
							<div id="showRight"></div>
							<input label="Password" class="form-control"
								placeholder="password confirm" id="input2" onblur="check2pwd()">
							</td>
							</tr>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<s:submit value="Save" class="btn btn-primary"></s:submit>
					</div>
				</s:form>
			</div>
		</div>
	</div>
	<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">change Email</h4>
				</div>
				<s:form action="detailchange" class="form-inline">
					<div class="modal-body" style="width: 80%">
						<div class="row">
							<s:textfield name="detailform.email" label="email"
								class="form-control inmodal" placeholder="new Email"></s:textfield>
							<div id="showRight"></div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<s:submit value="Save" class="btn btn-primary"></s:submit>
					</div>
				</s:form>
			</div>
		</div>
	</div>
	<%}else
		response.sendRedirect("login.jsp");
		%>
</body>
<script type="text/javascript">
	function check2pwd() {
		if (input1.value != input2.value) {
			alert(input1.value);
			alert(input2.value);
			input1.value = "";
			input2.value = "";
		}
	}
</script>
</html>