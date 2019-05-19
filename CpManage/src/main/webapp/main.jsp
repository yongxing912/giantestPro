<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>CP信息管理系统主界面</title>
    <%
        //权限验证
        if(session.getAttribute("currentUser")==null){
            response.sendRedirect("index.jsp");
            return;
        }
    %>
    <%--添加css--%>
    <link href="images/style.css" rel="stylesheet" type="text/css"/>
    <%--引入EasyUi--%>
    <%--<link rel="stylesheet" type="text/css" href="jquery-easyui-1.7.0/themes/default/easyui.css">--%>
    <%--<link rel="stylesheet" type="text/css" href="jquery-easyui-1.7.0/themes/icon.css">--%>
    <%--<script type="text/javascript" src="jquery-easyui-1.7.0/jquery.min.js"></script>--%>
    <%--<script type="text/javascript" src="jquery-easyui-1.7.0/jquery.easyui.min.js"></script>--%>
    <%--<script type="text/javascript" src="jquery-easyui-1.7.0/locale/easyui-lang-zh_CN.js"></script>--%>
    <jsp:include page="easyUi.jsp" flush="true"/>
    <script>
        $(function () {
            //数据
            var treeData =
                [
                    {
                        "id": 1, "text": "CP管理", "iconCls": "icon-save", "children":
                            [
                                {
                                    "text": "来料管理", "state": "open",
                                    "children":
                                        [
                                            {"text": "库存总览", "checked": true, attributes: {url: "gradeInfoManage.jsp"}},
                                            {"text": "异常处理", "checked": true, attributes: {url: "gradeInfoManage.jsp"}}
                                        ]
                                },
                                {
                                    "text": "工程管理", "state": "open",
                                    "children":
                                        [
                                            {"text": "客户信息", "checked": true, attributes:{url:"customerInfoManage.jsp"}},
                                            {"text": "产品型号", "checked": true, attributes:{url:"productInfoManage.jsp"}},
                                            {"text": "CP&nbsp;FLOW","checked": true,attributes: {url: "cpFlowInfoManage.jsp"}}
                                        ]
                                }
                            ]
                    },
                    {
                        "text": "参数设置", "state": "closed", "children":
                            [
                                {"text": "设置一"},
                                {"text": "数据库管理"},
                                {"text": "FTP管理管理"},
                                {"text": "管理员工具"},
                                {"text": "密码修改"},
                                {"text": "注销"}
                            ]
                    }
                ];
            //实例化树菜单
            $("#tree").tree(
                {
                    data: treeData,
                    lines: true,
                    onClick: function (node) {
                        if (node.attributes) {
                            openTab(node.text, node.attributes.url);
                        }
                    }
                });

            //新增Tab
            function openTab(text, url) {
                if ($("#tabs").tabs('exists', text)) {
                    $("#tabs").tabs('select', text);
                } else {
                    var content="<iframe frameborder='0' scrolling='auto' style='width: 100%;height: 100%' src="+url+"></iframe>";
                    $("#tabs").tabs('add', {
                        title: text,
                        closable: true,
                        content: content
                    });
                }
            }
        });
    </script>
</head>
<body class="easyui-layout">
<div region="north" style="height: 80px;background-color: #E0EDFE">
    <div align="left" style="width: 80%;float:left"><img src="images/main.jpg"></div>
    <div class="box8" style="padding-top: 50px;padding-right: 20px">当前用户:&nbsp;${currentUser.userName}</div>
</div>



<div region="center">
    <div class="easyui-tabs" fit="true" border="false" id="tabs">
        <div title="首页">
            <div class="box7">欢迎使用</div>
            <div align="left">
                <jsp:include page="mainContent.jsp" flush="true"/>
            </div>
        </div>
    </div>
</div>

<div region="west" style="width: 150px" title="CP导航" split="true">

    <ul id="tree"></ul>
</div>
<div region="south" style="height: 25px" align="center">版权所有@<a href="http://www.baidu.com">江苏嘉兆电子有限公司</a></div>
</body>
</html>