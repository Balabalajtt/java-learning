<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page contentType="text/html; charset=utf-8" language="java" errorPage=""%>
<!DOCTYPE html>
<html>
<head>
	<title>写信</title>
	<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="style/normalize.css">
	<link rel="stylesheet" type="text/css" href="style/writeMail.css">
	<link rel="stylesheet" type="text/css" href="style/font-awesome-4.7.0/css/font-awesome.min.css">
</head>
<body>
	<div class="main">
		<%--enctype="multipart/form-data"--%>
		<form method="post" action="send"  >
			<label>发信人：</label>

			<select name="id">
				<%
					String username=(String)session.getAttribute("username");
					ResultSet rs = null;
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection connection = DriverManager
								.getConnection("jdbc:mysql://localhost:3306/utte?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC&useSSL=false",
										"root", "980908");
						PreparedStatement statement =
								connection.prepareStatement("select * from account_info where username= ?");
						statement.setString(1, username);
						rs = statement.executeQuery();
					} catch (Exception e) {
						e.printStackTrace();
					}
					while (rs.next()) {
						String mailAccount = rs.getString("mail_account");
						String mailPassword = rs.getString("mail_password");
						String id = rs.getString("id");
				%>

				<option value="<%=mailAccount%>"><%=mailAccount%></option>

				<%
					}
				%>


			</select>

			<hr>
			<label>收件人：</label>
			<input type="text" name="receiver" class="text">
			<hr>
			<label>主&emsp;题：</label>
			<input type="text" name="title" class="text">
			<hr>
			<%--<div class="file-box">--%>
				<%--<input type="button" class="btn" value="添加附件">--%>
				<%--<input type="text" name="textfield" id="textfield">--%>
				<%--<input type="file" size="14" name="myfile" class="myfile" id="myfile"  onchange="document.getElementById('textfield').value=this.value"> 				--%>
			<%--</div>--%>
			<textarea rows="12" class="mytextarea" name="content"></textarea>
			<input type="submit" name="submit" class="submit" value="发送">
			<input type="reset" name="reset" class="reset" value="取消">
		</form>
	</div>
</body>
</html>