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
<div class="container">
    <div class="content">
        <div class="row-fluid">
            <tiles:insertAttribute name="content"/>
        </div>
    </div>
</div> <!-- /container -->
<tiles:insertAttribute name="footer"/>
<tiles:insertAttribute name="scripts"/>
</body>
</html>
