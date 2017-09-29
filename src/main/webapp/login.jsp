<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <title>Posts</title>
    <style>
    	#navbar-main ul li{
    	 font-size:18px;
    	}
    	#navbar-main ul{
    	 padding-left:40px;
    	}
    	#formSize{
    	  position: absolute;
    	  top:30%;
    	  left:38%;
    	  width:20%;
    	}
    	.form-control{
    	  margin: 2% 2% 2% 2%;
    	}
    	.btn-block{
    	  margin: 2% 2% 2% 2%;
    	}
    	html{height: 100%}
        body{margin: 0;height: 100%;
            background: 8a8680;}
        canvas{display: block;width: 100%;height: 100%;}
        .text{
            width: 100%;
            background: transparent;
            display: flex;
            justify-content: center;
            height: 100%;
            line-height: 100%;
            top: 0;
            position: absolute;
            top: 50%;
            font-size: 50px;
        }
    </style>
        <script>  
            function check2pwd() {  
                    if(input1.value != input2.value) {  
                        alert("not the same password!")  
                        input1.value = "";  
                        input2.value = "";  
                    }  
            }  
            var xmlHttp=null;
            
            function showHint()
            {
            	var username= document.getElementById("username").value;
            if (username==null)
              { 
              document.getElementById("showRight").innerHTML="";
              return;
              }
            try
              {// Firefox, Opera 8.0+, Safari, IE7
              xmlHttp=new XMLHttpRequest();
              }
            catch(e)
              {// Old IE
              try
                {
                xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
                }
              catch(e)
                {
                alert ("Your browser does not support XMLHTTP!");
                return;  
                }
              }
            var url="mini_facebook/checkSQL?username=" + username;
            xmlHttp.open("POST",url,true);
            xmlHttp.send();
            xmlHttp.onreadystatechange=function(){
            	if (xmlhttp.readyState==4 && xmlhttp.status==200){
                	
                document.getElementById("showRight").innerHTML=xmlHttp.responseText;}
            }
            alert(xmlHttp.responseText);
            alert("send");
            }
        </script>  
<jsp:include page="headerreg.jsp"></jsp:include>
   <canvas id="canvas"></canvas>
   <script src="index.js"></script>
   <div id="formSize">
    <s:form action="login" class="form-horizontal">
         
    	<s:textfield placeholder="Username" type="username" class="form-control" name="userform.username"></s:textfield>    
        <s:password placeholder="Password" type="password" class="form-control" name="userform.password"></s:password>
        <s:submit value="login" class="btn btn-primary btn-block"></s:submit>
    </s:form>
   </div>
<!-- cherryblog.site/  -->
</html>