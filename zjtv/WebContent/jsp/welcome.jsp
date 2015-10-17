<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jfeng" uri="http://www.jfeng-app.com" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jfeng:appContext var="appContext"/>

<sec:authorize ifNotGranted="P_ADMIN">
	<div class="jumbotron">
	  <h3>欢迎使用 "小伙伴" 后台管理系统</h3>
	</div>
</sec:authorize>