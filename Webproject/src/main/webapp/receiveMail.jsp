<%@ page contentType="text/html; charset=utf-8" language="java" errorPage=""%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.MessageFormat" %>
<!DOCTYPE html>
<html>
<head>
	<title>收件箱</title>
	<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="style/normalize.css">
	<link rel="stylesheet" type="text/css" href="style/sentList.css">
</head>
<body>
    <p>邮件列表</p>
    <%
    	String username=(String)session.getAttribute("username");
    	ResultSet rs = null;
    	List accList = new ArrayList();
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		Connection connection = DriverManager
    				.getConnection("jdbc:mysql://localhost:3306/utte?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC&useSSL=false",
    						"root", "980908");
    		PreparedStatement statement =
    				connection.prepareStatement("select * from account_info where username = ? ");
    		statement.setString(1, username);
    		rs = statement.executeQuery();
    		while (rs.next()) {
    			accList.add(rs.getString("mail_account"));
    		}

    		statement = connection.prepareStatement("select * from mail_info where mail_to= ? order by mail_date desc");

    		for (Object acc : accList) {

    %>
    <p> 收信账号<%=acc%>：<p>
     <%
                statement.setString(1, String.valueOf(acc));
                rs = statement.executeQuery();
                while (rs.next()) {
                    String from = rs.getString("mail_from");
				    String title = rs.getString("title");
				    String date = rs.getString("mail_date");

				    String info = MessageFormat.format("来自：{0}\t\t\t\t标题：{1}\t\t\t\t日期：{2}", from, title, date);
                    String id = rs.getString("id");

	%>

    <form method="post" action="mailDetail">
    	<input type="submit" name="submit" value="<%=info%>" class="sub">
    	<input type="hidden" name="id" value="<%=id%>">
    </form>

    <%
			    }
		    }
	    } catch (Exception e) {
		    e.printStackTrace();
	    }

    %>
<hr>
</body>
</html>