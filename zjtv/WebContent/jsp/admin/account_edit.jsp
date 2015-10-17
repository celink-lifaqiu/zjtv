<%--
  Created by IntelliJ IDEA.
  User: YinJianFeng
  Date: 14-5-7
  Time: 下午5:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jfeng" uri="http://www.jfeng-app.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<jfeng:appContext var="app"/>
<div class="col-lg-9">
    <div class="panel panel-default">
        <div class="panel-heading">账号信息</div>
        <div class="panel-body">
            <spring:form id="userAdminForm" action="${app}/admin/saveOrUpdateAccount" modelAttribute="userAdmin">
                <spring:hidden path="id"/>
                <div class="form-group">
                    <label class="col-lg-3 control-label" for="username">账号</label>
                    <div class="col-lg-9">
                        <jfeng:input id="username" path="username" class="form-control" rule_required="true"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">账号类型</label>
                    <div class="col-lg-9">
                        <spring:radiobutton path="usertype" class="utype" value="normal" label="普通账号"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label" for="password">密码</label>
                    <div class="col-lg-9">
                        <jfeng:password id="password" path="password" class="form-control" rule_required="true"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label" for="repeat_pwd">确认密码</label>
                    <div class="col-lg-9">
                        <input type="password" id="repeat_pwd" name="repeat_pwd" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                        <c:if test="${userAdmin.id eq null}">
                            <label class="checkbox inline">
                                <input type="checkbox" name="continue" checked="checked" value="true"> 是否继续添加？
                            </label>
                        </c:if>
                        <button type="button" class="btn btn-primary" onclick="saveOrUpdateAccount()">保存</button>
                        <button type="button" class="btn btn-default" onclick="back()">返回</button>
                    </div>

                </div>
            </spring:form>
        </div>
    </div>
</div>
<script type="text/javascript">
    var utype=$(".utype");
    utype.click(function(){
        if($(this).val()=="normal"){
            $("#usertype").hide();
        }else{
            $("#usertype").show();
        }
    });
    $(function(){
        $("#usertype").hide();
    })
    function saveOrUpdateAccount(){
        var form=$("#userAdminForm");
        var username = $('#username').val();
        var password = $('#password').val();
        var repeatpwd = $('#repeat_pwd').val();
        if(username.trim()== ""){
            alert("请输入用户名");
            return;
        }
        if(password.trim() == ""){
            alert("请输入密码");
            return;
        }
        if(password.trim() != repeatpwd.trim()){
            alert("两次输入密码不一致");
            return;
        }
        sendSimpleAjaxRequestAsync("${app}/admin/checkUserName", 'username='+username,function(msg){
            if(msg){
                alert("已有该用户！");
                return;
            }
            if (form.valid() && confirm("是否确认保存？")) {
                form.submit();
            }
        });

        <%--sendAjaxRequestAsync("${app}/admin/saveOrUpdateAccount", "userAdminForm", "", function(resp){--%>

        <%--});--%>
    }
    function back(){
        location.href = "${app}/admin/usermanager";
    }
</script>