<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>欢迎注册</title>
    <link href="css/register.css" rel="stylesheet">
    <style>
        .el-dropdown-link {
            cursor: pointer;
            color: #409EFF;
        }
        .el-icon-arrow-down {
            font-size: 12px;
        }

    </style>
</head>
<body >

<div class="form-div">
    <div class="reg-content">
        <h1>欢迎注册</h1>
        <span>已有帐号？</span> <a href="login.jsp">登录</a>
    </div>
    <form id="reg-form" action="/brand-case/registerServlet" method="post">
        <table>
            <tr>
                <td style="text-align: center">用户名</td>
                <td class="inputs">
                    <input name="username" id="username" type="text" value="${cookie.username.value}">
                    <br>
                    <span id="username_err" class="err_msg" >${register_msg}</span>
                </td>

            </tr>

            <tr>
                <td style="text-align: center">密码</td>
                <td class="inputs">
                    <input name="password" type="password" id="password" value="${cookie.password.value}">
                    <br>
                    <span id="password_err" class="err_msg" style="display: none">密码格式有误</span>
                </td>
            </tr>

            <tr >
                <td style="text-align: center">角色</td>
                <td class="selected" style="text-align: left">
                <select name="identity" id="identity" value="${cookie.identity.value}" >
                    <option value="A">A厂家</option>>
                    <option value="B">B厂家</option>>
                    <option value="C">C厂家</option>>
                </select>
                </td>
            </tr>


            <tr>
                <td style="text-align: center">验证码</td>
                <td class="inputs">
                    <input name="checkCode" type="text" id="checkCoded" value="${cookie.checkCode.value}">
                    <img id="checkCodeImg" src="/brand-case/checkCodeServlet">
                    <a href="#" id="changeImg" >看不清？</a>
                </td>
            </tr>

        </table>

        <div class="buttons">
            <input value="注 册" type="submit" id="reg_btn">
        </div>
        <br class="clear">
    </form>

</div>

<script src="js/vue.js"></script>
<script src="element-ui/lib/index.js"></script>


<script src="js/axios-0.18.0.js"></script>
<script>
    document.getElementById("changeImg").onclick = function () {
        document.getElementById("checkCodeImg").src = "/brand-case/checkCodeServlet?"+new Date().getMilliseconds();
    }

</script>
</body>
</html>