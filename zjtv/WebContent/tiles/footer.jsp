<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<div class="footer">
    <div class="row-fluid">
        <div class="span3"></div>
        <div class="span6">
            <p class="text-center">CECLINK 黎法秋 &copy; Company 2015<br><%out.println("SESSION:" + session.getId()+"<br>");%></p>
        </div>
        <%--<div class="span3"><div id="status"><p class="text-warning text-right">正在连接消息服务器...</p></div></div>--%>
    </div>
</div>