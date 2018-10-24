<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %><%--
  Created by IntelliJ IDEA.
  User: 江婷婷
  Date: 2018/10/24
  Time: 17:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>绑定邮箱账号</title>
    <meta charset="utf-8">
    <%--<link rel="stylesheet" type="text/css" href="style/normalize.css">--%>
    <link rel="stylesheet" type="text/css" href="style/binding.css">
</head>
    <body>
        <form method="post" action="tobinding">
            <input type="text" name="account" placeholder="邮箱账号" class="input">
            <input type="password" name="password" placeholder="邮箱授权码（POP3设置）" class="input">
            <input type="submit" name="submit" class="submit" value="绑定">
        </form>


        <p>已绑定邮箱账号列表</p>
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
                String account = rs.getString("mail_account");
                String id = rs.getString("id");

        %>


        <form method="post" action="unbinding">
            <input type="text" name="account" disabled="disabled" value="<%=account%>" class="tt">
            <input type="hidden" name="id" value="<%=id%>">
            <input type="submit" name="submit" value="解绑" class="submit">
        </form>

        <%
            }
        %>

    </body>
</html>
