<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="jfeng" uri="http://www.jfeng-app.com" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tiles:insertAttribute name="doctype" ignore="true" flush="true"/>
<head>
    <meta charset="utf-8">
    <title><tiles:insertAttribute name="title" ignore="true" flush="true"/></title>
    <tiles:insertAttribute name="styles" ignore="true" flush="true"/>
</head>
<body>
<tiles:insertAttribute name="main-menu" ignore="true" flush="true"/>
<%--<div class="page-header">
    <h1>${moduleTitle} <small>${funcTitle}</small></h1>
</div>--%>
<jfeng:breadcrumb/>
<div class="container row-fluid">
    <c:choose>
        <c:when test="${not empty headMenu.menuItemList}">
            <div class="span2">
                <tiles:insertAttribute name="sub-menu"/>
            </div>
            <div class="span10">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <tiles:insertAttribute name="content"/>
                    </div>
                </div>
                <tiles:insertAttribute name="footer"/>
            </div>
        </c:when>
        <c:otherwise>
            <div class="span12">
                <div class="content">
                    <tiles:insertAttribute name="content"/>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</div> <!-- /container -->
<tiles:insertAttribute name="footer"/>
<tiles:insertAttribute name="scripts"/>
</body>
</html>
