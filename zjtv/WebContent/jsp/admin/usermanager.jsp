<%--
  Created by IntelliJ IDEA.
  User: YinJianFeng
  Date: 14-5-7
  Time: 下午12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jfeng" uri="http://www.jfeng-app.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<jfeng:appContext var="app"/>
<jfeng:modalDialog id="role_selection_dialog">
    <jfeng:dialogHeader>可选角色</jfeng:dialogHeader>
    <jfeng:dialogBody>正在加载...</jfeng:dialogBody>
    <jfeng:dialogFooter>
        <%--<jfeng:successButton label="确定"  data-loading-text="加载中..." onclick="assignRole()"/>--%>
        <input type="button" class="btn btn-success" data-loading-text="加载中..." id="sendButton" value="确定" onclick="assignRole()">
    </jfeng:dialogFooter>
</jfeng:modalDialog>
<jfeng:modalDialog id="pwd_update_dialog">
    <jfeng:dialogHeader>修改密码</jfeng:dialogHeader>
    <jfeng:dialogBody>正在加载...</jfeng:dialogBody>
    <jfeng:dialogFooter>
        <input type="button" class="btn btn-success" data-loading-text="加载中..." id="sendBtn" value="确定" onclick="updatePassword()">
        <%--<jfeng:successButton label="确定"  data-loading-text="加载中..." onclick="updatePassword()"/>--%>
    </jfeng:dialogFooter>
</jfeng:modalDialog>

<div class="col-lg-8">
    <div class="panel panel-default">
        <div class="panel-heading">账号信息列表
            <jfeng:primaryButton onclick="createNew()" icon="plus" mini="true"
                                 label="新建账号" small="true" />
        </div>
        <div class="panel-body">
            <jfeng:grid id="user_table" action="/admin/loadalluser">
                <jfeng:gridHead>
                    <tr>
                        <th>#</th>
                        <th>账号</th>
                        <th>账号类型</th>
                        <th>最后一次登录</th>
                        <th>...</th>
                    </tr>
                </jfeng:gridHead>
                <jfeng:gridColumn data="index" width="10%"/>
                <jfeng:gridColumn data="username" width="15%">
                    <jfeng:columnRenderScript>
                        return "<a href='javascript:void(0)' onclick='showRoles("+row.id+")'>"+row.username+"</a>";
                    </jfeng:columnRenderScript>
                </jfeng:gridColumn>
                <jfeng:gridColumn data="usertypeStr" width="15%"/>
                <jfeng:gridColumn data="lastLoginDateStr" width="30%"/>
                <jfeng:gridColumn data="id">
                    <jfeng:columnRenderScript>
                        var btnStr = "";
                        if(row.username == 'admin'){
                            btnStr =    "<div class='btn-group'>"+
                                        " <button type='button' class='btn btn-default btn-xs' onclick='resetPassword("+row.id+")'><i class='icon-edit'></i> 重置密码</button>" +
                                        " <button type='button' data-toggle='modal' class='btn btn-default btn-xs' href='${app}/admin/showUserDialog/"+row.id+"' data-target='#pwd_update_dialog'>修改密码</button>"+
                                        "</div>"
                        }else{
                            btnStr =    "<div class='btn-group'>"+
                                        " <button type='button' class='btn btn-default btn-xs' onclick='resetPassword("+row.id+")'><i class='icon-edit'></i> 重置密码</button>" +
                                        " <button type='button' data-toggle='modal' class='btn btn-default btn-xs' href='${app}/admin/showUserDialog/"+row.id+"' data-target='#pwd_update_dialog'>修改密码</button>"+
                                        " <button type='button' class='btn btn-danger btn-xs' onclick='deleteAccount("+row.id+")'><i class='icon-trash'></i> 删除</button>"+
                                        "</div>"
                        }
                        return btnStr;
                    </jfeng:columnRenderScript>
                </jfeng:gridColumn>
            </jfeng:grid>
        </div>
    </div>
</div>
<div class="col-lg-4">
    <div class="panel panel-default">
        <div class="panel-heading">拥有角色</div>
        <div class="panel-body" id="user_role_panel">

        </div>
    </div>
</div>

<script type="text/javascript">
    function showRoles(userId){
        ajaxRefreshAsync("user_role_panel", "getrolebyuser/"+userId);
    }
    function assignRole(){
        var sendBtn = $('#sendButton');
        sendBtn.button("loading");
        sendAjaxRequestAsync("saveUserRole", "role_form", "", function(resp){
        sendBtn.button("reset");
            $('#role_selection_dialog').modal('toggle');
            showRoles(resp);
        });
    }
    function removeRole(userId, roleId){
        sendSimpleAjaxRequestAsync("removeUserRole", "userId="+userId+"&roleId="+roleId, function(resp){
            showRoles(resp);
        });
    }

    function createNew(){
        location.href = "${app}/admin/editAccount/0";
    }

    function resetPassword(id){
        if(confirm("是否确认重置密码？")){
            sendSimpleAjaxRequestAsync("resetPassword", "id="+id, function(resp){
                var json = $.parseJSON(resp);
                if(json.state == true){
                    alert(json.msg);
                    showRoles(json.userId);
                }else{
                    alert("重置失败！");
                }
            });
        }

    }
    function updatePassword(){
        var oldPwdVal = $('#oldPwd').val();
        var newPwdVal = $('#newPwd').val();

        if(oldPwdVal.trim() == ""){
            alert("请输入旧密码");
            return;
        }
        if(newPwdVal.trim() == ""){
            alert("请输入新密码");
            return;
        }
        var sendBtn = $('#sendBtn');
        sendBtn.button("loading");
        sendAjaxRequestAsync("updatePassword", "changePwdForm", "", function(resp){
            var json = $.parseJSON(resp);
            if(json.state == true){
                showRoles(json.userId);
                alert("密码已修改");
                $('#pwd_update_dialog').modal('toggle');
            }else{
                alert(json.msg);
            }
            sendBtn.button("reset");
        });
    }
    function deleteAccount(id){
        if(confirm("是否确认删除该账号?")){
            sendSimpleAjaxRequestAsync("deleteAccount", "id="+id, function(resp){
                dataTable_user_table.fnReloadAjax();
            });
        }
    }
</script>