<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="jfeng" uri="http://www.jfeng-app.com" %>

<jfeng:modalDialog id="user_profile_dialog">
    <jfeng:dialogHeader>用户信息</jfeng:dialogHeader>
    <jfeng:dialogBody>正在加载...</jfeng:dialogBody>
    <jfeng:dialogFooter>
        <jfeng:successButton label="注销" onclick="confirmExist()"/>
    </jfeng:dialogFooter>
</jfeng:modalDialog>
<header class="navbar navbar-default navbar-fixed-top" role="banner" style="min-width: 1224px;">
    <div class="navbar-header">
        <a class="navbar-brand" href="<jfeng:appContext/>/home"><i class="icon-home"></i> 小伙伴</a>
    </div>
    <div class="navbar-collapse collapse">
        <ul class="nav navbar-nav">
            <c:forEach var="menu" items="${headMenuList}">
                <c:if test="${menu.visible}">
                    <c:choose>
                        <c:when test="${menu.actived}">
                            <li class="active"><a href="<jfeng:appContext/>${menu.uri}"><i
                                    class="${menu.icon}"></i> ${menu.label}</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="<jfeng:appContext/>${menu.uri}"><i class="${menu.icon}"></i> ${menu.label}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:if>
            </c:forEach>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
                <security:authentication var="username" property="name"/>
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="icon-user"></i> ${username} <span class="caret"></span></a>
                <ul class="dropdown-menu">
                     <security:authorize ifAllGranted="P_ADMIN"> 
                    <li><a href="<jfeng:appContext/>/admin/"><i class="icon-wrench"></i> 系统设置</a></li>
                    <li class="divider"></li>
                     </security:authorize> 
                    <li><a href="javascript:void(0)" onclick="confirmExist()"><i class="icon-off"></i> 退出系统</a></li>
                </ul>
            </li>
            <%--<li>
                <security:authentication var="username" property="name"/>
                <a href="#">${username}</a>

                </label>
                &lt;%&ndash;<jfeng:clickover id="usernameClickOver" icon="user" label="${username}" width="300"
                                 action="/settings/getUserProfile/${username}">Loading...</jfeng:clickover>&ndash;%&gt;
            </li>
            <li class="divider-vertical"></li>
            <li class="divider-vertical"></li>
            &lt;%&ndash;<li><a href="<jfeng:appContext/>/settings/"><i class="icon-cogs"></i>设置</a></li>&ndash;%&gt;
            <li><a href="javascript:void(0)" onclick="confirmExist()"><i class="icon-off"></i>退出</a></li>--%>
        </ul>
    </div>
</header>

<div id="confirmDiv" style="display: none;" class=""></div>
<script type="text/javascript">
    function confirmExist() {
        if(confirm("你确定要退出系统吗？")){
            location.href = '<jfeng:appContext/>/j_spring_security_logout';
        }
        /*$("#confirmDiv").confirmModal({
            heading: '退出',
            body: '你确定要退出系统吗？',
            callback: function () {
                location.href = '/j_spring_security_logout';
            }
        });*/
    }
</script>