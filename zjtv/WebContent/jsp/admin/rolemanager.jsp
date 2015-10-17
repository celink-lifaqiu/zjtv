<%--
  Created by IntelliJ IDEA.
  User: YinJianFeng
  Date: 14-5-7
  Time: 下午12:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jfeng" uri="http://www.jfeng-app.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<jfeng:modalDialog id="permission_selection_dialog">
    <jfeng:dialogHeader>可选权限</jfeng:dialogHeader>
    <jfeng:dialogBody>正在加载...</jfeng:dialogBody>
    <jfeng:dialogFooter>
        <jfeng:successButton label="确定" onclick="assignPermission()"/>
    </jfeng:dialogFooter>
</jfeng:modalDialog>

<div class="col-lg-8">
    <div class="panel panel-default">
        <div class="panel-heading">角色列表</div>
        <div class="panel-body">
            <table class="table table-form">
                <tr>
                    <th>#</th>
                    <th>角色名</th>
                    <th>权限</th>
                </tr>
                <c:forEach var="role" items="${roles}" varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <td>${role.roleName}</td>
                    <td>
                        <jfeng:button mini="true" label="权限..." onclick="showPermissions(${role.id})"/>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
<div class="col-lg-4">
    <div class="panel panel-default">
        <div class="panel-heading">被指派的权限</div>
        <div class="panel-body" id="role_permission_panel">

        </div>
    </div>
</div>

<script type="text/javascript">
    function showPermissions(roleId){
        ajaxRefreshAsync("role_permission_panel", "getpermissionbyrole/"+roleId);
    }

    function assignPermission(){
        sendAjaxRequestAsync("saveRolePermission", "permission_form", "", function(resp){
            $('#permission_selection_dialog').modal('toggle');
            showPermissions(resp);
        });
    }
    function removePermission(roleId, pId){
        sendSimpleAjaxRequestAsync("removeRolePermission", "roleId="+roleId+"&pId="+pId, function(resp){
            showPermissions(resp);
        });
    }
</script>