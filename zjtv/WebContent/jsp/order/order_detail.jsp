<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jfeng" uri="http://www.jfeng-app.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<jfeng:appContext var="appContext"/>
<div class="panel panel-default">
    <div class="panel-heading">订单详情</div>
    <div class="panel-body">
    	订单信息：<br>
        <table class="table table-form">
            <tr>
                <th style="width:25%">订单编号</th>
                <td>
                    ${order.id}
                </td>
            </tr>
            <tr>
                <th style="width:25%">订单名称</th>
                <td>
                    ${order.orderName}
                </td>
            </tr>
            <tr>
                <th style="width:25%">附加要求</th>
                <td>${order.additionalRequirements}</td>
            </tr>
            <tr>
                <th style="width:25%">是否使用抵用卷</th>
                <td>${order.isUseVoucherStr}</td>
            </tr>
            <tr>
                <th style="width:25%">总费用</th>
                <td>${order.sumPrice}</td>
            </tr>
            <tr>
                <th style="width:25%">状态</th>
                <td>${order.stateStr}</td>
            </tr>
            <tr>
                <th style="width:25%">支付日期</th>
                <td>${order.createDateStr}</td>
            </tr>
        </table>
      	 <br> 下单用户信息：<br>
      	  <table class="table table-form">
            <tr>
                <th style="width:25%">用户账号</th>
                <td>
                    ${user.account}
                </td>
            </tr>
            <tr>
                <th style="width:25%">用户昵称</th>
                <td>
                    ${user.nickName}
                </td>
            </tr>
            <tr>
                <th style="width:25%">用户邮箱</th>
                <td>
                    ${user.email}
                </td>
            </tr>
            <tr>
                <th style="width:25%">用户住址</th>
                <td>
                    ${user.address}
                </td>
            </tr>
            <tr>
                <th style="width:25%">用户生日</th>
                <td>
                    ${user.birthdayStr}
                </td>
            </tr>
        </table>
      	 <!--   <br>套餐信息：<br>
      	  <table class="table table-form">
            <tr>
                <th style="width:25%">套餐服务名称</th>
                <td>
                    ${packages.packageServiceName}
                </td>
            </tr>
            <tr>
                <th style="width:25%">套餐服务费用</th>
                <td>
                    ${packages.packageServicePrice}
                </td>
            </tr>
            <tr>
                <th style="width:25%">套餐服务详情</th>
                <td>
                    ${packages.packageServiceDesc}
                </td>
            </tr>
        </table>-->
      	  <br>服务地址信息：<br>
      	  <table class="table table-form">
            <tr>
                <th style="width:25%">预约人</th>
                <td>
                    ${a.reservation}
                </td>
            </tr>
            <tr>
                <th style="width:25%">电话</th>
                <td>
                    ${a.phone}
                </td>
            </tr>
            <tr>
                <th style="width:25%">小区信息</th>
                <td>
                    ${a.districtInformation}
                </td>
            </tr>
            <tr>
                <th style="width:25%">详细地址</th>
                <td>
                    ${a.address}
                </td>
            </tr>
        </table>
      	  <br>上门服务员工信息：<br>
      	  <% 
      	  		Object workertemp = request.getAttribute("worker");
      	  		if(workertemp==null){
      	  		%>
      	  		暂时还没派员工上门服务。
      	  		<%
      	  		}else{
      	  	%>
      	  	<table class="table table-form">
            <tr>
                <th style="width:25%">真实姓名</th>
                <td>
                    ${worker.name}
                </td>
            </tr>
            <tr>
                <th style="width:25%">性别</th>
                <td>
                    ${worker.genderStr}
                </td>
            </tr>
            <tr>
                <th style="width:25%">电话</th>
                <td>
                    ${worker.phone}
                </td>
            </tr>
            <tr>
                <th style="width:25%">身份证号码</th>
                <td>
                    ${worker.idCard}
                </td>
            </tr>
            <tr>
                <th style="width:25%">家庭住址</th>
                <td>
                    ${worker.address}
                </td>
            </tr>
        </table>
      	  	<%	
      	  		}
      	  %>
      	  <br>评论信息：<br>
      	  <% 
      	  		Object commenttemp = request.getAttribute("comment");
      	  		if(commenttemp==null){
      	  		%>
      	  		用户还没有评论。
      	  		<%
      	  		}else{
      	  	%>
      	  	<table class="table table-form">
            <tr>
                <th style="width:25%">评论星级</th>
                <td>
                    ${comment.star}颗星。
                </td>
            </tr>
            <tr>
                <th style="width:25%">评论内容</th>
                <td>
                    ${comment.content}
                </td>
            </tr>
            <tr>
                <th style="width:25%">评论时间</th>
                <td>
                    ${comment.commentTimeStr}
                </td>
            </tr>
        </table>
      	  	<%	
      	  		}
      	  %>
    </div>
</div>