<%--
  Created by IntelliJ IDEA.
  User: 江婷婷
  Date: 2018/9/27
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<body>--%>
<%--<form action="${pageContext.request.contextPath}/register" method="post">--%>
    <%--<input type="text" name="username">--%>
    <%--<input type="text" name="password">--%>
    <%--<input type="text" name="phonenum">--%>
    <%--<input type="text" name="sex">--%>
    <%--<input type="submit" value="注册">--%>
<%--</form>--%>
<%--</body>--%>
<%--</html>--%>
<%@ page contentType="text/html; charset=utf-8" language="java" errorPage=""%>
<!DOCTYPE html>
<html>
<head>
    <title>哇夕邮箱</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="style/normalize.css">
    <link rel="stylesheet" type="text/css" href="style/register.css">
</head>
<body>
<div class="main">
    <div class="words">发发发</div>
    <div class="loginFrame">
        <div class="topBar">哇夕邮箱</div>
        <div class="theForm">
            <form method="post" action="register">
                <input type="text" placeholder="用户名" name="username" class="input">
                <input type="password" name="password" placeholder="密码"class="input"><br/>
                <input type="password" name="confirm" placeholder="确认密码" class="input">
                <input type="submit" name="submit" class="submit" value="注册">
            </form>
        </div>
        <div class="booter"><%if(request.getAttribute("err")!=null) out.println(request.getAttribute("err"));%></div>
    </div>
</div>
</body>
</html>