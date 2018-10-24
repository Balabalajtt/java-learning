<%@ page contentType="text/html; charset=utf-8" language="java" errorPage=""%>
<!DOCTYPE html>
<html>
<head>
	<title>main</title>
	<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="style/normalize.css">
	<link rel="stylesheet" type="text/css" href="style/main.css">
	<script type="text/javascript" src="js/click.js"></script>
</head>
<body>
<%
  response.setHeader("Cache-Control","no-cache");
  response.setHeader("Cache-Control","no-store");
  response.setHeader("Pragma","no-cache");
  response.setDateHeader ("Expires", 0);

  if(session==null || session.getAttribute("username")==null){
     response.sendRedirect("index");
  }

%> 

    <div id="headBar">
		<div class="uname"><%=session.getAttribute("username")%></div>
		<div class="qt">
			<form method="post" action="logout">
				<input type="submit" name="quit" value="退出" class="sub">
			</form>
		</div>
	</div>

    <div id="navBar">
		<div class="menu">
			<ul>
				<li onclick="forward('welcome')">欢迎</li>
				<li onclick="forward('binding')">管理邮箱账号</li>
				<li onclick="forward('writeMail')">写信</li>
				<li onclick="forward('receiveMail')">收信箱</li>
				<li onclick="forward('sentList')">已发送</li>
			</ul>
		</div>
		<%--<div class="instruction"><sapn>fff</sapn><br/><br/><br/>fff</div>--%>
	</div>

	<div id="content">
		<iframe src="welcome" frameborder="0" width="100%" height="100%" id="frame"></iframe>
	</div>

	<div id="booter">
		<div class="shengming">fff</div>
	</div>

	<div id="rightBar">
		<div class="tips"><br><br>fff<br><br>fff</div>
	</div>

</body>
</html>