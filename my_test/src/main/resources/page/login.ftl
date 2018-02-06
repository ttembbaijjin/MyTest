<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
	<meta charset="UTF-8" />
<title>Title</title>
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
		$(function(){
			$("#btn2").click(function(){
				alert(11);
				$("#formLogin").submit();
			})
		})
	</script>
</head>
<body>
	poi!
	<form id="form_re" action="http://localhost:8081/boot/import"
		method="post" enctype="multipart/form-data">
		<input type="text" name="username" /> <input type="text"
			name="password" /> <input type="file" name="memberXls" /> <input
			type="submit" id="btn" value="上传" />
	</form>
	用户登录!
	<form id="formLogin" action="http://localhost:8081/boot/test/login"
		method="post">
		<input type="text" name="username" /> <input type="text"
			name="password" />
		<button id="bt3">login</button>
	</form>
		<div >
				    	<#if message??>
		    			<label class="col-sm-10" style="color:red;">${message}</label>
						</#if>
		</div>
		
		<div>
			<img   src="${request.contextPath}/static/img/home_bg01.jpg" />
		</div>


</body>
</html>