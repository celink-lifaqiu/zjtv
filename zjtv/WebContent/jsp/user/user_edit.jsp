<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jfeng" uri="/WEB-INF/jfeng.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<jfeng:appContext var="appContext"/>
<div class="col-lg-6">
    <div class="panel panel-default">
        <div class="panel-heading">用户信息</div>
        <div class="panel-body">
            <spring:form id="userForm" action="${appContext}/user/saveOrUpdateUser" modelAttribute="user" cssClass="form-horizontal">
                <spring:hidden path="id"/>
                <div class="form-group">
                    <label class="col-lg-2 control-label" for="account">用户账号</label>
                    <div class="col-lg-10">
                        <spring:input id="account" path="account" cssClass="form-control" data-rule-required="true"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label" for="state">状态</label>
                    <div class="col-lg-10" id="state"  data-rule-required="true">
                        <spring:radiobutton path="state" value="0" label="失效"/>
                        <spring:radiobutton path="state" value="1" label="正常"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                        <button type="submit" class="btn btn-primary" onclick="saveOrUpdateUser()">保存</button>
                        <button type="button" class="btn btn-default" onclick="back()">返回</button>
                    </div>
                </div>
            </spring:form>
        </div>
    </div>
</div>
<script type="text/javascript">
    function saveOrUpdateUser(){
        var account=$("#account").val();
        var password=$("#password").val();
        if(account.trim()==null||account.trim()==""){
            alert("账号不能为空！");
            return;
        }
        
        if(password.trim()==null||password.trim()==""){
            alert("密码不能为空！");
            return;
        }
        var form = $('#userForm');
        if (form.valid() && confirm("是否确认保存？")) {
            form.submit();
        }
    }

    function back(){
        location.href = "<jfeng:appContext/>/user/managerusers";
    }
</script>