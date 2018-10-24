<%@ page contentType="text/html; charset=utf-8" language="java" errorPage=""%>
<!DOCTYPE html>
<html>
<head>
	<title>prompt</title>
	<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="style/normalize.css">
	<link rel="stylesheet" type="text/css" href="style/prompt.css">
	<link rel="stylesheet" type="text/css" href="style/font-awesome-4.7.0/css/font-awesome.min.css">
</head>
<body>
	<div class="masking"></div>
	<div class="promptFrame">
		<%
			Boolean flag = (Boolean) session.getAttribute("flag");
            String msg = (String) session.getAttribute("msg");
		%>
		<div class="words"><i class="fa fa-smile-o" style="font-size: 50px"></i>&nbsp;<%=msg%></div>
			<form method="post" action="binding">
				<input type="submit" value="返回" class="sub">
			</form>
	</div>

</body>
</html>