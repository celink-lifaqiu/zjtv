<%--
  Created by IntelliJ IDEA.
  User: Yin Jian Feng
  Date: 14-5-7
  Time: 下午8:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jfeng" uri="http://www.jfeng-app.com" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<jfeng:appContext var="app"/>
<spring:form id="changePwdForm" action="${app}/admin/updatePassword"
             modelAttribute="userAdmin" cssClass="form-horizontal">
    <div class="form-group">
        <label class="col-lg-3 control-label" for="username">账号</label>
        <div class="col-lg-9" id="username">
            <spring:hidden path="id"/>
            <label class="control-label">${userAdmin.username}</label>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label" for="oldPwd">密码</label>
        <div class="col-lg-9">
            <spring:input id="oldPwd" path="oldPwd" cssClass="form-control"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label" for="newPwd">新密码</label>
        <div class="col-lg-9">
            <spring:hidden path="id"/>
            <spring:input id="newPwd" path="newPwd" cssClass="form-control"/>
        </div>
    </div>
</spring:form>