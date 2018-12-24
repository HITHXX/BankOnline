<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>homepage</title>
    <link rel="stylesheet" type="text/css" href="css/login.style.css">
    <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon" />
    <script src="js/jQuery-3.3.1.js"></script>
    <script src="js/jsencrypt.min.js"></script>
    <script>
        function beginTransfer() {

            var publickey = '<%=session.getAttribute("publicKey") %>'
            var encrypt = new JSEncrypt();
            encrypt.setPublicKey(publickey);


            var phone = document.getElementById("destid").value;
            var amount = document.getElementById("amounted").value;
            if (!(/^1[34578]\d{9}$/.test(phone))) {
                alert("手机号码有误，请重填");
                return false;
            } else if (amount == "" || amount < 0) {
                alert("转账金额错误，请重填");
                return false;
            }
            else {
                var url = "genTransVCode.action";
                var params = {};
                jQuery.post(url, params, callbackFun, 'json');
            }

            function callbackFun(data) {
                if (data.flag == 1) {
                    verifycode = prompt("请输入收到的验证码");
                    if (verifycode == data.vcode) {
                        alert("验证码输入正确！");
                        var encryphone = encrypt.encrypt(phone);
                        var encryamount = encrypt.encrypt(amount)
                        var nurl = "transfer.action";
                        var params = {
                            dest: encryphone,
                            amount: encryamount
                        }
                        jQuery.post(nurl, params, callbackFun1, 'json');
                    } else {
                        alert("验证码输入错误！")
                    }
                } else {
                    alert("验证码发送失败！");
                }

                function callbackFun1(data) {
                    if (data.result == 1) {
                        alert("转账成功！");
                        window.location.reload();
                    }
                    else {
                        alert("转账失败！");
                    }

                }
            }
        }
    </script>
    <style>
        body {
            text-align: center
        }

        table.gridtable {
            font-family: verdana, arial, sans-serif;
            font-size: 11px;
            color: #333333;
            border-width: 1px;
            border-color: #666666;
            border-collapse: collapse;
        }

        table.gridtable th {
            border-width: 1px;
            padding: 10px;
            border-style: solid;
            border-color: #666666;
            background-color: #dedede;
        }

        table.gridtable td {
            border-width: 1px;
            padding: 10px;
            border-style: solid;
            border-color: #666666;
            background-color: #ffffff;
        }
        .button1 {
            background-color: white;
            color: black;
            border: 2px solid #000000; /* Green */
        }
    </style>
</head>
<body>
<div id="content" style="margin-top: 5%;width: 800px;height: 400px">
<h4>Welcome!<s:property value="#session.user"/> &nbsp; <a href="login.jsp">注销</a></h4>
<h4>Balance:<s:property value="#session.balance"/></h4>
<br/>

<form id="transferform">
    转账给：<input type="text" id="destid" name="destUser"/>
    转账额：<input type="text" id="amounted" name="amount"/>
    <%--验证码: <input type="text" name="verifycode" id="vcode"/>--%>
    <button type="button" class="button1" onclick="beginTransfer()">转账</button>
</form>

<table class="gridtable" cellpadding="10" cellspacing="10" align="center">
    <thead>
    <tr>
        <th>转账人</th>
        <th>收款人</th>
        <th>转账额</th>
        <th>时间</th>
    </tr>
    </thead>
    <tbody>
    <s:iterator value="logList" status="stat">
        <tr>
            <td><s:property value="src"/></td>
            <td><s:property value="dest"/></td>
            <td><s:property value="amount"/></td>
            <td><s:property value="date"/></td>
        </tr>
    </s:iterator>
    </tbody>
</table>

</div>
</body>
</html>
