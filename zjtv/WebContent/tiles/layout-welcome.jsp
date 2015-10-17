<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="jfeng" uri="http://www.jfeng-app.com" %>
<tiles:insertAttribute name="doctype"/>
<head>
    <meta charset="utf-8">
    <title><tiles:insertAttribute name="title" ignore="true"/></title>
    <tiles:insertAttribute name="styles"/>
</head>
<body>
<tiles:insertAttribute name="main-menu"/>
<div id="wrap" class="container-fluid">
    <div class="row-fluid">
        <tiles:insertAttribute name="content"/>
    </div>
</div>
<tiles:insertAttribute name="footer"/>
<tiles:insertAttribute name="scripts"/>
</body>
</html>
