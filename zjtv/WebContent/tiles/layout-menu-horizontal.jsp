<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="jfeng" uri="http://www.jfeng-app.com" %>
<tiles:insertAttribute name="doctype" ignore="true" flush="true"/>
<head>
    <meta charset="utf-8">
    <title><tiles:insertAttribute name="title" ignore="true" flush="true"/></title>
    <tiles:insertAttribute name="styles" ignore="true" flush="true"/>
</head>
<body>
<tiles:insertAttribute name="main-menu" ignore="true" flush="true"/>
<jfeng:breadcrumb/>
<div class="container row-fluid">
        <div id="tiles-sub-menu" class="row-fluid" data-offset="53" style=" margin-bottom:20px">
            <tiles:insertAttribute name="sub-menu"/>
        </div>
        <div class="row-fluid">
            <tiles:insertAttribute name="content"/>
        </div>
</div> <!-- /container -->
<tiles:insertAttribute name="footer"/>
<tiles:insertAttribute name="scripts"/>
</body>
</html>
