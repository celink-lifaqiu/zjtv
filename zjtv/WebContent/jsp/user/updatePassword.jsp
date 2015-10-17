<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jfeng" uri="/WEB-INF/jfeng.tld"%>
<div class="panel panel-default">
    <div class="panel-heading">重置密码</div>
    <div class="panel-body">
        <table class="table table-form">
            <tbody>
            <tr>
                <th style="width: 25%">用户名</th>
                <td>${user.account}</td>
            </tr>
            <tr>
            	<th style="width: 25%">密码</th>
            	<td><input type="text" id="password"></td>
            </tr>
            <tr>
            	<th colspan="2">
            		<button type='button' class='btn btn-danger btn-xs' onclick='updateUserPassword(${user.id})'>保存</button>
            	</th>
            </tr>
            </tbody>
        </table>
    </div>
</div>