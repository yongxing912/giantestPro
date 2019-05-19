<%--
  Created by yongxing912
  WebSite: www.giantest.cn
  Company: GianTest Inc.
  Date: 2019-05-14 13:52
--%>

<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>客户信息管理</title>

    <%--引入EasyUi--%>
    <jsp:include page="easyUi.jsp" flush="true"/>

    <script type="text/javascript">
        var url;

        function customerSearch() {
            $('#dg').datagrid('load', {
                customerName: $('#s_customerName').val()
            });
        }

        function customerDelete() {
            var selectedRows = $('#dg').datagrid('getSelections');
            if (selectedRows.length == 0) {
                $.messager.alert("系统提示", "请选择要删除的数据！")
                return;
            }
            var strIds = [];
            for (var i = 0; i < selectedRows.length; i++) {
                strIds.push(selectedRows[i].customerId);
            }
            var ids = strIds.join(",");
            // alert(ids);
            $.messager.confirm("系统提示", "您确认要删除这<font color=red>" + selectedRows.length + "</font>数据吗？", function (r) {
                if (r) {
                    $.post("customerDelete", {delIds: ids}, function (result) {
                        if (result.success) {
                            $.messager.alert("系统提示", "您已成功删除<font color=red>" + result.delNums + "</font>条数据");
                            $('#dg').datagrid("reload");
                        } else {
                            $.messager.alert("系统提示", '<font color=red>'+selectedRows[result.errorIndex].customerName+'</font>'+result.errorMsg);
                        }
                    }, "json");
                }
            });
        }

        function openCustomerAddDialog() {
            $('#fm').form('clear');
            $('#dlg').dialog('open').dialog('center').dialog('setTitle', '添加客户信息');
            url = 'customerSave';
            // url = 'save_user.php';
        }

        function openCustomerModifyDialog() {
            var selectedRows = $('#dg').datagrid('getSelections');
            if (selectedRows.length != 1) {
                $.messager.alert("系统提示", "请选择一条要编辑的数据！")
                return;
            }

            // $('#fm').form('clear');
            var row = selectedRows[0];

            url = 'customerSave?customerId=' + row.customerId;
            $('#dlg').dialog('open').dialog('center').dialog('setTitle', '编辑客户信息');
            $('#fm').form('load', row);
            // alert(url);
            // url="customerSave?id="+row.id;
        }

        function closeCustomerDialog() {
            $('#dlg').dialog('close');        // close the dialog
            // resetValue();
        }

        function resetValue() {
            $('#fm').form('clear');
        }

        function saveCustomer() {
            // alert(url);
            $('#fm').form('submit', {
                url: url,//modify时，此处url时如何提交到servlet的？？？

                onSubmit: function () {
                    return $(this).form('validate');
                },
                success: function (result) {
                    if (result.errorMsg) {
                        $.messager.alert("系统提示", result.errorMsg);
                        return;
                    } else {
                        $.messager.alert("系统提示", "保存成功");
                        resetValue();
                        $('#dlg').dialog('close');
                        $('#dg').datagrid('reload');
                    }
                }
            });
        }


    </script>

</head>
<body style="margin:5px">
<table id="dg" title="客户信息总览" class="easyui-datagrid" fitcolumns="true"
       pagination="true" rownumbers="true" fit="true" url="customerList" toolbar="#tb" remoteSort="false">
    <thead>
    <tr>

        <th field="cb" checkbox="true"></th>
        <th field="customerId" width="50" sortable="true" align="center">编号</th>
        <th field="customerName" width="100" sortable="true" align="center">客户名称</th>
        <th field="customerCode" width="80" sortable="true" align="center">客户代码</th>
        <th field="contacts" width="80" align="center">联系人</th>
        <th field="phone" width="80" align="center">联系电话</th>
        <th field="fax" width="80" align="center">传真号码</th>
        <th field="email" width="100" align="center">Email</th>
        <th field="address" width="200" sortable="true" align="center">地址</th>
        <th field="customerDescp" width="200" align="center">客户备注</th>
    </tr>
    </thead>
</table>

<div id="tb">
    <div>
        <a href="javascript:openCustomerAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true"
           onclick="newUser()">添加</a>&nbsp;&nbsp;|
        <%--<span style="height:20px; border:1px solid #000"></span>--%>
        <a href="javascript:openCustomerModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true"
           onclick="editUser()">修改</a>&nbsp;&nbsp;|
        <a href="javascript:customerDelete()" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
           onclick="destroyUser()">删除</a>
    </div>
    <div>
        &nbsp;&nbsp;&nbsp;客户名称：&nbsp;&nbsp;<input type="text" name="s_customerName" id="s_customerName"/>
        <a href="javascript:customerSearch()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
    </div>

</div>

<div id="dlg" class="easyui-dialog" style="width: 530px;height: 400px;padding:10px 10px" closed="true"
     buttons="#dlg-buttons">
    <form id="fm" method="post">
        <table>
            <tr>
                <td>客户名称:</td>
                <td><input type="text" name="customerName" id="customerName" class="easyui-validatebox" required="true">
                </td>
                <td>&nbsp;&nbsp;&nbsp;</td>
                <td>客户代码:</td>
                <td><input type="text" name="customerCode" id="customerCode" class="easyui-validatebox" required="true">
                </td>
            </tr>
            <tr>
                <td>联&nbsp;系&nbsp;人:</td>
                <td><input type="text" name="contacts" id="contacts"></td>
                <td>&nbsp;&nbsp;&nbsp;</td>
                <td>联系电话:</td>
                <td><input type="text" name="phone" id="phone"></td>
            </tr>
            <tr>
                <td>传真号码:</td>
                <td><input type="text" name="fax" id="fax"></td>
                <td>&nbsp;&nbsp;&nbsp;</td>
                <td>Email:</td>
                <td><input type="text" name="email" id="email" class="easyui-validatebox" required="true"
                           validType="email"></td>
            </tr>
            <tr>
                <td valign="top">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址:</td>
                <td colspan="4"><textarea rows="4" cols="45" name="address" id="address" value="address"></textarea>
                </td>
            </tr>
            <tr>
                <td valign="top">客户备注:</td>
                <td colspan="4"><textarea rows="4" cols="45" name="customerDescp" id="customerDescp"
                                          value="descp"></textarea></td>
            </tr>
        </table>
    </form>
</div>

<div id="dlg-buttons">
    <a href="javascript:saveCustomer()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closeCustomerDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>
