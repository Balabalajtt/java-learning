<%@ page contentType="text/html; charset=utf-8" language="java" errorPage=""%>
<!DOCTYPE html>
<html>
<head>
	<title>发送结果</title>
	<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="style/normalize.css">
	<link rel="stylesheet" type="text/css" href="style/sendResult.css">
</head>
<body>
	<div class="main">
		<div class="msg"><%=session.getAttribute("msg")%></div>
		<form method="post" action="again"> 
			<input type="submit" name="submit" value="返回" class="sub">
		</form>
	</div>
</body>
</html>