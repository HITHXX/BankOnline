<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="css/login.style.css">
    <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon" />
    <script src="js/jQuery-3.3.1.js"/>
    <script>
        function checkUser(){
            var result = document.getElementById("userid").value;
            var password = document.getElementById("passwordid").value;
            if(result == ""  ){
                alert("用户名不能为空");
                return false;
            }
            if(password == ""  ){
                alert("密码不能为空");
                return false;
            }
            document.getElementById("loginid").submit();
        }
    </script>
    <script>
        function genVerify(){

            phone = document.getElementById("userid").value;
            if(!(/^1[34578]\d{9}$/.test(phone))){
                alert("手机号码有误，请重填");
                return false;
            }
            else {
                var url = "genvcode.action";
                var params = {
                    user: eval(phone)
                };
                jQuery.post(url, params, callbackFun, 'json');
            }
            function callbackFun(data){
                alert(data.result);
            }

        }
    </script>
</head>
<body>

<div id="content" style="margin-top: 5%;height: 400px">
    <div class="login-header">
        <img src="img/welcome.jpg">
    </div>
    <form method="post" id="loginid" name="login" action="register.action" >
        <div class="login-input-box">
            <span class="icon icon-user">
                <img src="img/person.png" height="20" width="20"/>
            </span>
            <input type="text" id="userid" name="user" placeholder="Please enter your phone">
        </div>
        <div class="login-button-box">
            <button type="button" id="genVerifybtn" onclick ="genVerify()" clientidmode="Static" style="background-color: #6a6765;border-color: #6a6765">生成验证码</button>
        </div>
        <div class="login-input-box">
            <span class="icon icon-password">
                <img src="img/lock.png" height="20" width="20"/>
            </span>
            <input type="password" id="passwordid" name="password" placeholder="Please enter your password">
        </div>
        <div class="login-input-box">
            <span class="icon icon-password">
                <img src="img/verify.png" height="20" width="20"/>
            </span>
            <input type="text" id="vcodeid" name="verifyCode" placeholder="Please enter the verify code you received">
        </div>
        <div class="remember-box">
            <label>
                <input type="checkbox">&nbsp;Remember Me
            </label>
        </div>
        <div class="login-button-box">
            <button id="register" onclick="checkUser()">Register</button>
        </div>
    </form>
    <div class="logon-box">
        <a href="">Forgot?</a>
        <a href="login.jsp">Login</a>
    </div>
</div>

</body>
</html>