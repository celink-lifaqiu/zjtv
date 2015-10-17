<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="jfeng" uri="http://www.jfeng-app.com"%>
<h1>session过期</h1>
Session已过期请重新 <a href="<jfeng:appContext/>/jsp/login.jsp">登录</a>
<div class="row-span">
    <div class="span2" style="text-align: center">
        <img src="<jfeng:appContext/>/res/img/alert.png">
    </div>
    <div class="span10">
        <h2>
            <p class="text-warning">
                Session已过期，请重新 <a href="<jfeng:appContext/>/jsp/login.jsp">登录</a>
            </p>
        </h2>
    </div>
</div>