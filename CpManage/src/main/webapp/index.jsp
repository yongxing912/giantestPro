<%--
  Created by yongxing912
  WebSite: www.giantest.cn
  Company: GianTest Inc.
  Date: 2019-05-09 20:49
--%>

<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@page isELIgnored="false"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>嘉兆电子信息系统</title>
    <link href="images/style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        function resetValue() {
            document.getElementById("userName").value = "";
            document.getElementById("password").value = "";
        }
    </script>
</head>
<body>

<div align="center" style="padding-top: 5px;">
    <form action="login" method="post">
        <table class="box2" align="center" width="1024" height="767">
            <%--<caption>江苏嘉兆电子</caption>--%>
            <tr height="280"></tr>
            <tr height="30">
                <td></td>
                <td colspan="3" align="left"><text class="box6">CP信息管理系统</text></td>
            </tr>
            <tr height="100"></tr>
            <tr height="15">
                <td width="10%" height="20"></td>
                <td width="10%" align="right"><text class="box5">用户名：</text></td>
                <td><input type="text" value="${userName}" name="userName" id="userName"/></td>
                <td width="55%"></td>
            </tr>
            <tr height="15">
                <td width="10%" height="20"></td>
                <td width="10%" align="right"><text class="box5">密&nbsp;&nbsp;&nbsp;&nbsp;码：</text></td>
                <td><input type="password" value="${password}" name="password" id="password"/></td>
                <td width="50%"></td>
            </tr>
            <tr height="10"></tr>
            <tr height="20">
                <td width="50%" colspan="2"></td>
                <td width="10%" align="center"><input type="submit" value="登录" class="box5"/>&nbsp;&nbsp;<input type="Button"
                                                                                                                value="重置"  onclick="resetValue()" class="box5"/>
                </td>
                <td></td>
            </tr>
            <tr height="20">
                <td width="50%" colspan="2"></td>
                <td colspan="3"><div class="box8">${error}</div></td>
            </tr>
            <tr>
                <td width="10%"></td>
                <td colspan="3" align="center">Contact with:yx.wang@giantest.cn</td>
            </tr>
            <tr height="50">
                <td colspan="5" align="right"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
