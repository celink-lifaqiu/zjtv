<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="jfeng" uri="http://www.jfeng-app.com" %>
<jfeng:appContext var="appContext"/>
<jfeng:nonLayout/>
<!DOCTYPE html>
<html class="not-ie" lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="YinJianFeng">
    <title></title>
    <link rel="<jfeng:appContext/>/res/assets/ico/favicon.png">
    <link rel="stylesheet" href="<jfeng:appContext/>/res/css/bootstrap.css">
    <link rel="stylesheet" href="<jfeng:appContext/>/res/css/jquery.pnotify.default.css">

    <link rel="stylesheet" href="<jfeng:appContext/>/res/css/style.css">
    <style>
        html,body {
            padding-top: 0px;
            color: #cdbfe3;
            background-color: #563d7c;
            background-image: url("<jfeng:appContext/>/res/img/bg.jpg");
            margin-top: 50px;
        }
        .bs-docs-nav {
            text-shadow: 0 -1px 0 rgba(0,0,0,.15);
            background-color: #563d7c;
            border-color: #463265;
            box-shadow: 0 1px 0 rgba(255,255,255,.1);
        }
        .center {
            text-align: center;
        }
    </style>
</head>
<body>
<header class="navbar navbar-inverse navbar-fixed-top bs-docs-nav" role="banner">
    <div class="container">
        <div class="navbar-header">
            <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".bs-navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="#" class="navbar-brand">小伙伴&trade;管理系统</a>
        </div>
    </div>
</header>
<div class="container" style="padding-bottom: 100px">

    <div class="col-md-4">
        <%
            String error = request.getParameter("error");
            if ("true".equals(error)) {
        %>
        <div class="alert alert-danger">
            <button type="button" class="close" data-dismiss="alert">×</button>
            <strong>登陆失败!</strong> 用户名或者密码输入有误，请重新输入。
        </div>
        <%}%>
        <form class="form-signin" action="<jfeng:appContext/>/j_spring_security_check" method="post">
            <h2 class="form-signin-heading">请登陆</h2>
            <input type="text" name="j_username" class="form-control" placeholder="用户名" autofocus="">
            <input type="password" name="j_password" class="form-control" placeholder="密码">
            <label class="checkbox">
                <input type="checkbox" value="remember-me"> 记住我
            </label>
            <button class="btn btn-outline-inverse btn-lg" type="submit">登陆</button>
        </form>
    </div>
</div>
<footer class="container" role="contentinfo">
    <div class="row-fluid">
        <div class="col-md-6">
        </div>
    </div>
</footer>
</body>
</html>
<script type="text/javascript" src="<jfeng:appContext/>/res/js/jquery-1.9.0.min.js"></script>
<script type="text/javascript" src="<jfeng:appContext/>/res/js/bootstrap.js"></script>
<script type="text/javascript" src="<jfeng:appContext/>/res/js/jquery.form.js"></script>
<script type="text/javascript" src="<jfeng:appContext/>/res/js/jquery.validate.js"></script>
<script type="text/javascript" src="<jfeng:appContext/>/res/js/jquery.validate.bootstrap.js"></script>
<script type="text/javascript" src="<jfeng:appContext/>/res/js/jquery.pnotify.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#loginForm').validate();
        /*$('#loginForm').ajaxForm(function(data){
         if(!data.status){
         $.pnotify({
         title: '登录失败',
         text: data.error,
         type: 'error',
         history: false
         });
         }
         });*/
    });
</script>