<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jfeng" uri="http://www.jfeng-app.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<jfeng:appContext var="appContext"/>
<div class="panel panel-default">
    <div class="panel-heading">广告详情</div>
    <div class="panel-body">
        <table class="table table-form">
            <tr>
                <th style="width:25%">广告标题</th>
                <td>
                    ${advertise.title}
                </td>
            </tr>
            <tr>
                <th style="width:25%">图片</th>
                <td><img alt="" src="${advertise.image}" width="100px" height="100px"></td>
            </tr>
        </table>
    </div>
</div>