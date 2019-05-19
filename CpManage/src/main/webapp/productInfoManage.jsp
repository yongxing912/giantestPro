<%--
  Created by yongxing912
  WebSite: www.giantest.cn
  Company: GianTest Inc.
  Date: 2019-05-14 13:52
--%>

<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>产品信息管理</title>

    <%--引入EasyUi--%>
    <jsp:include page="easyUi.jsp" flush="true"/>

    <script type="text/javascript">

        var url;

        function productSearch() {
            $('#dg').datagrid('load', {
                productName: $('#s_productName').val(),
                pnMask: $('#s_pnMask').val(),
                pnSize: $('#s_pnSize').combobox("getValue"),
                customerId: $('#s_customerCode').combobox("getValue"),
                bjoinDay: $('#s_bjoinDay').datebox("getValue"),
                ejoinDay: $('#s_ejoinDay').datebox("getValue"),
            });
        }

        function productDelete() {
            var selectedRows = $('#dg').datagrid('getSelections');
            if (selectedRows.length == 0) {
                $.messager.alert("系统提示", "请选择要删除的数据！")
                return;
            }
            var strIds = [];
            for (var i = 0; i < selectedRows.length; i++) {
                strIds.push(selectedRows[i].productId);
            }
            var ids = strIds.join(",");
            // alert(ids);
            $.messager.confirm("系统提示", "您确认要删除这<font color=red>" + selectedRows.length + "</font>数据吗？", function (r) {
                if (r) {
                    $.post("productDelete", {delIds: ids}, function (result) {
                        if (result.success) {
                            $.messager.alert("系统提示", "您已成功删除<font color=red>" + result.delNums + "</font>条数据");
                            $('#dg').datagrid("reload");
                        } else {
                            $.messager.alert("系统提示", result.errorMsg);
                        }
                    }, "json");
                }
            });
        }

        function openProductAddDialog() {
            $('#fm').form('clear');
            $('#dlg').dialog('open').dialog('center').dialog('setTitle', '添加产品型号');
            url = 'productSave';
            // url = 'save_user.php';
        }


        function closeProductDialog() {
            $('#dlg').dialog('close');        // close the dialog
            // resetValue();
        }

        function productSave() {
            $('#fm').form('submit',{
                url:url,
                onSubmit:function(){
                    if($('#customerId').combobox("getValue")==""){
                        $.messager.alert("系统提示","请选择客户代码");
                        return false;
                    }
                    if($('#pnSize').combobox("getValue")==""){
                        $.messager.alert("系统提示","请选择Size");
                        alert(('#pnSize').val());
                        return false;
                    }

                    return $(this).form("validate");
                },
                success:function(result){
                    if(result.errorMsg){
                        $.messager.alert("系统提示",result.errorMsg);
                        return;
                    }else{
                        //alert("ok1")
                        $.messager.alert("系统提示","保存成功");
                        resetValue();
                        $("#dlg").dialog("close");
                        $("#dg").datagrid("reload");
                    }
                }
            });
        }

        function resetValue() {
            $('#fm').form('clear');
        }

        function openProductModifyDialog() {
            var selectedRows = $('#dg').datagrid('getSelections');
            if (selectedRows.length != 1) {
                $.messager.alert("系统提示", "请选择一条要编辑的数据！")
                return;
            }

            // $('#fm').form('clear');
            var row = selectedRows[0];

            url = 'productSave?productId=' + row.productId;
            $('#dlg').dialog('open').dialog('center').dialog('setTitle', '编辑客户信息');
            $('#fm').form('load', row);
            // alert(url);
            // url="customerSave?id="+row.id;
        }
    </script>
</head>

<body style="margin:5px">
<table id="dg" title="产品型号总览" class="easyui-datagrid" fitcolumns="true"
       pagination="true" rownumbers="true" fit="true" url="productList" toolbar="#tb" remoteSort="false">
    <thead>
    <tr>

        <th field="cb" checkbox="true"></th>
        <th field="productId" width="50" sortable="true" align="center">编号</th>
        <th field="productName" width="100" sortable="true" align="center">产品型号</th>
        <th field="customerId" width="100" align="center" hidden="true">客户ID</th>
        <th field="customerCode" width="100" sortable="true" align="center">客户代码</th>
        <th field="pnMask" width="80" sortable="true" align="center">产品代码</th>
        <th field="pnSpec" width="80" align="center">规格</th>
        <th field="pnSize" width="80" sortable="true" align="center">尺寸</th>
        <th field="grossDie" width="80" sortable="true" align="center">管芯数</th>
        <th field="joinDay" width="200" sortable="true" align="center">立项日期</th>
        <th field="pnDescp" width="200" align="center">产品备注</th>
    </tr>
    </thead>
</table>

<div id="tb">
    <div>
        <a href="javascript:openProductAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true"
           onclick="newUser()">添加</a>&nbsp;&nbsp;|
        <%--<span style="height:20px; border:1px solid #000"></span>--%>
        <a href="javascript:openProductModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true"
           onclick="editUser()">修改</a>&nbsp;&nbsp;|
        <a href="javascript:productDelete()" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
           onclick="destroyUser()">删除</a>
    </div>

    <div>
        &nbsp;产品型号：&nbsp;<input type="text" name="s_productName" id="s_productName" size="10px"/>

        &nbsp;|&nbsp;客户代码：&nbsp;<input class="easyui-combobox" id="s_customerCode"
                                       data-options="editable:false,valueField:'customerId',textField:'customerCode',url:'customerComboList'"
                                       panelHeight="auto" size="12"/>
        &nbsp;|&nbsp;产品代码：&nbsp;<input type="text" name="s_pnMask" id="s_pnMask" size="10"/>
        &nbsp;|&nbsp;Size：&nbsp;<select class="easyui-combobox" id="s_pnSize" name="s_pnSize" editable="false"
                                      panelHeight="auto">
        <option value="">请选择...</option>
        <option value="8">8寸</option>
        <option value="12">12寸</option>
    </select>

        &nbsp;|&nbsp;立项日：&nbsp;<input type="text" class="easyui-datebox" name="s_bjoinDay" id="s_bjoinDay" size="15"
                                      editable="false">-->
        <input type="text" class="easyui-datebox" name="s_ejoinDay" id="s_ejoinDay" size="15" editable="false">

        &nbsp;<a href="javascript:productSearch()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
    </div>

</div>

<div id="dlg" class="easyui-dialog" style="width: 560px;height: 400px;padding:10px 10px" closed="true"
     buttons="#dlg-buttons">
    <form id="fm" method="post">
        <table>
            <tr>
                <td>产品型号：</td>
                <td><input type="text" name="productName" id="productName" class="easyui-validatebox" required="true"/></td>
            </tr>
            <tr>
                <td>客户代码：</td>
                <td><input class="easyui-combobox" name="customerId" id="customerId" data-options=" panelHeight:'auto',editable:false,valueField:'customerId',textField:'customerCode',url:'customerComboList'"/></td>
                <td>产品代码：</td>
                <td><input type="text" id="pnMask" name="pnMask" class="easyui-validatebox" required="true"/></td>
            </tr>
            <tr>
                <td>规&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格：</td>
                <td><input type="text" name="pnSpec" id="pnSpec"/></td>
                <td>Size:</td>
                <td>
                    <select class="easyui-combobox" id="pnSize" name="pnSize" editable="false" panelHeight="auto" style="width:145px">
                        <option value="">请选择...</option>
                        <option value="8">8寸</option>
                        <option value="12">12寸</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Gross&nbsp;Die:</td>
                <td><input type="text" name="grossDie" id="grossDie"/></td>
                <td>立项日期：</td>
                <td><input type="text" class="easyui-datebox" name="joinDay" id="joinDay" required="true" editable="false"/></td>
            </tr>
            <tr>
                <td valign="top">产品备注:</td>
                <td colspan="3"><textarea rows="5" cols="45" name="pnDescp" id="pnDescp"
                                          value="descp"></textarea></td>
            </tr>
        </table>
    </form>
</div>

<div id="dlg-buttons">
    <a href="javascript:productSave()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closeProductDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>


</body>
</html>
