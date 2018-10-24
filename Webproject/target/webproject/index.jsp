<%--
  Created by IntelliJ IDEA.
  User: 江婷婷
  Date: 2018/9/27
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=utf-8" language="java" errorPage=""%>
<!DOCTYPE html>
<html>
<head>
    <title>哇夕邮箱</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="style/normalize.css">
    <link rel="stylesheet" type="text/css" href="style/index.css">
</head>
<body>
<div class="main">
    <div class="words">发发发发发发邮件</div>
    <div class="loginFrame">
        <div class="topBar">哇夕邮箱</div>
        <div class="theForm">

            <form method="post" action="login">
                <input type="text" placeholder="用户名" name="username" class="input">
                <input type="password" name="password" placeholder="密码"class="input"><br/>
                <input type="submit" name="submit" class="submit" value="登录">
            </form>

            <form method="post" action="toregister">
                <input type="submit" value="注册" class="sub">
            </form>

        </div>
        <div class="booter"><%if(request.getAttribute("err")!=null) out.println(request.getAttribute("err"));%></div>
    </div>
</div>
</body>
</html>