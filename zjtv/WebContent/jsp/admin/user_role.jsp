<%--
  Created by IntelliJ IDEA.
  User: yunchunnan
  Date: 14-5-7
  Time: 下午1:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jfeng" uri="http://www.jfeng-app.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<jfeng:appContext var="app"/>
<table class="table table-form">
    <tbody>
    <c:forEach var="role" items="${userRoles}">
        <tr>
            <td>${role.roleName}</td>
            <td>
                <button class="close" onclick="removeRole(${userId}, ${role.id})">x</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
    <tfoot>
    <tr>
        <td colspan="2">
        <button type="button" data-toggle="modal" class="btn btn-default" href="${app}/admin/loadavaliableroles/${userId}" data-target="#role_selection_dialog"><i class="icon-plus"></i></button>
        </td>
    </tr>
    </tfoot>
</table>