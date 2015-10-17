<%--
  Created by IntelliJ IDEA.
  User: YinJianFeng
  Date: 14-5-7
  Time: 下午4:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jfeng" uri="http://www.jfeng-app.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<spring:form id="permission_form" action="/saveRolePermission" modelAttribute="rolePermission">
    <spring:hidden path="roleId"/>
    <spring:select path="permissions" id="permissions"
                   multiple="true" cssClass="form-control">
        <spring:options itemLabel="permissionName" itemValue="id" items="${avaPermissions}"/>
    </spring:select>
</spring:form>