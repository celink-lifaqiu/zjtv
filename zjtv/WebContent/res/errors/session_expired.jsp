<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="jfeng" uri="http://www.jfeng-app.com"%>
<h1>账号重复登录</h1>
<div class="row-span">
    <div class="span2" style="text-align: center">
        <img src="<jfeng:appContext/>/res/img/alert.png">
    </div>
    <div class="span10">
        <h2>
            <p class="text-warning">
                账号已在另一个地方登陆，请重新 <a href="<jfeng:appContext/>/jsp/login.jsp">登录</a>
            </p>
        </h2>
    </div>
</div>