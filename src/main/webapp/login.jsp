<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="css/login.style.css">
    <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon" />
    <script>
        function checkUser(){
            var user = document.getElementById("userid").value;
            var password = document.getElementById("passwordid").value;
            if(result == ""  ){
                alert("用户名不能为空");
                return false;
            }
            if(!(/^1[34578]\d{9}$/.test(user))){
                alert("用户名有误，请重填");
                return false;
            }
            if(password == ""  ){
                alert("密码不能为空");
                return false;
            }
            document.getElementById("loginid").submit();
        }
    </script>
</head>
<body>

<div id="content" style="margin-top: 5%;height: 400px">
    <h2 style="text-align: center">欢迎来到网上银行系统</h2>
    <div class="login-header">
        <img src="img/welcome.jpg">
    </div>
    <form method="post" id="loginid" name="login" action="login.action" >
        <div class="login-input-box">
            <span class="icon icon-user">
                <img src="img/person.png" height="20" width="20"/>
            </span>
            <input type="text" id="userid" name="user" placeholder="Please enter your email/phone">
        </div>
        <div class="login-input-box">
            <span class="icon icon-password">
                <img src="img/lock.png" height="20" width="20"/>
            </span>
            <input type="password" id="passwordid" name="password" placeholder="Please enter your password">
        </div>
    <div class="remember-box">
        <label>
            <input type="checkbox">&nbsp;Remember Me
        </label>
    </div>
    <div class="login-button-box">
        <button onclick="checkUser()">Login</button>
    </div>
    </form>
    <div class="logon-box">
        <a href="">Forgot?</a>
        <a href="register.jsp">Register</a>
    </div>
</div>

</body>
</html>