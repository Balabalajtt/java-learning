<%@ page contentType="text/html; charset=utf-8" language="java" errorPage=""%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
	<title>信</title>
	<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="style/normalize.css">
	<link rel="stylesheet" type="text/css" href="style/mail.css">
</head>
<body>
	<%

		String id=(String)session.getAttribute("id");
		String username=(String)session.getAttribute("username");
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/utte?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC&useSSL=false",
							"root", "980908");
			PreparedStatement statement =
					connection.prepareStatement("select * from mail_info where id= ?");
			statement.setString(1, id);
			rs = statement.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}


	%>
	<%while(rs.next()){
	String title=rs.getString("title");
	String content=rs.getString("content");
	%>
	<h3>标题：<%=title%></h3>
	<h4>来自：<%=username%></h4>
	<p><%=content%></p>
<%}%>
</body>
</html>