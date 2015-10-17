<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jfeng" uri="http://www.jfeng-app.com" %>
    <ul class="nav nav-list jfeng-sidenav">
        <li class="nav-header">${headMenu.label}</li>
        <c:forEach var="menu" items="${headMenu.menuItemList}">
            <c:if test="${menu.id ne '' && menu.visible}">
            <c:choose>
                <c:when test="${menu.actived}">
                    <li class="active">
                        <a href="<jfeng:appContext/>${menu.uri}">
                            <i class="icon-chevron-right"></i>
                            ${menu.label}
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="<jfeng:appContext/>${menu.uri}">
                            <i class="icon-chevron-right"></i>
                            ${menu.label}
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>
            </c:if>
        </c:forEach>
    </ul>
<!--/.well -->