<%--
  Created by IntelliJ IDEA.
  User: YinJianFeng
  Date: 14-5-7
  Time: 下午3:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jfeng" uri="http://www.jfeng-app.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<spring:form id="role_form" action="/saveUserRole" modelAttribute="userRole">
    <spring:hidden path="userId"/>
    <spring:select path="roles" multiple="true" cssClass="form-control">
        <spring:options itemLabel="roleName" itemValue="id" items="${avaRoles}"/>
    </spring:select>
</spring:form>