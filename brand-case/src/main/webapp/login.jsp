<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>login</title>
    <link href="css/login.css" rel="stylesheet">
</head>

<body>
<div id="loginDiv" style="height: 350px">
    <form action="/brand-case/loginServlet" method="post" id="form">
        <h1 id="loginMsg">三方CRM管理系统</h1>
        <div id="errorMsg">${login_msg} ${register_msg}</div>
        <p>Username:<input id="username" name="username" value="${cookie.username.value}" type="text"></p>

        <p>Password:&nbsp;<input id="password" name="password" value="${cookie.password.value}" type="password"></p>

            <p>Remember:<input id="remember" name="remember" value="1" type="checkbox"></p>

        <p>
            角色:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <select id="identity" name="identity" value="${cookie.identity.value}"  style="margin-left: 6px">
                <option value="A">A厂家</option>>
                <option value="B">B厂家</option>>
                <option value="C">C厂家</option>>
            </select>
        </p>

        <div id="subDiv">
            <input type="submit" class="button" value="login up">
            <input type="reset" class="button" value="reset">&nbsp;&nbsp;&nbsp;
            <a href="register.jsp">没有账号？</a>
        </div>
    </form>
</div>
</body>
</html>