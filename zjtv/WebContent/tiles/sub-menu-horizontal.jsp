<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jfeng" uri="http://www.jfeng-app.com" %>
<div class="btn-group">
    <c:forEach var="menu" items="${headMenu.menuItemList}">
        <c:if test="${menu.id ne '' && menu.visible}">
        <c:choose>
            <c:when test="${menu.actived}">
                <a href="<jfeng:appContext/>${menu.uri}" class="btn btn-default active"><i class="${menu.icon}"></i> ${menu.label}</a>
            </c:when>
            <c:otherwise>
                <a href="<jfeng:appContext/>${menu.uri}" class="btn btn-default"><i class="${menu.icon}"></i> ${menu.label}</a>
            </c:otherwise>
        </c:choose>
        </c:if>
    </c:forEach>
</div>