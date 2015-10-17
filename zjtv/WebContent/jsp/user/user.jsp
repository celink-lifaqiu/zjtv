<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jfeng" uri="http://www.jfeng-app.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<jfeng:appContext var="appContext"/>
<div class="col-lg-10">
    <div class="panel panel-default">
        <div class="panel-heading">员工列表
        </div>
        <div class="panel-body">
            <jfeng:grid id="user_grid" action="/user/loadUsers">
                <jfeng:gridHead>
                    <tr>
                        <th>#</th>
                        <th>账号</th>
                        <th>昵称</th>
                        <th>邮箱</th>
                        <th>生日</th>
                        <th>住址</th>
                        <th>注册日期</th>
                    </tr>
                </jfeng:gridHead>
                <jfeng:gridColumn data="index"/>
                <jfeng:gridColumn data="account" />
                <jfeng:gridColumn data="nickName"/>
                <jfeng:gridColumn data="email"/>
                <jfeng:gridColumn data="birthdayStr"/>
                <jfeng:gridColumn data="address"/>
                <jfeng:gridColumn data="registDateStr"/>
            </jfeng:grid>
        </div>
    </div>
</div>
<script type="text/javascript">
    function deletePackages(id){
        if (confirm("是否确认删除?")) {
            sendSimpleAjaxRequestAsync("${appContext}/package/deletePackages/"+id, "", function(){
                dataTable_packages_grid.fnReloadAjax();
            });
        }
    }
    
    
    function editPackages(id){
        location.href = "${appContext}/package/editPackages/"+id;
    }
    function createNew(){
        location.href = "${appContext}/package/editPackages/0";
    }
</script>
