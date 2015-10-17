<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jfeng" uri="/WEB-INF/jfeng.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<jfeng:appContext var="appContext"/>
<div class="col-lg-7">
    <div class="panel panel-default">
        <div class="panel-heading">订单信息</div>
        <div class="panel-body">
            <spring:form id="orderForm" action="${appContext}/order/saveOrUpdateOrder" modelAttribute="order" cssClass="form-horizontal">
                <spring:hidden path="id"/>
                
				<div class="form-group">
                    <label class="col-lg-3 control-label" for="orderName">订单名称</label>
                    <div class="col-lg-9">
                        <spring:input id="orderName" path="orderName" cssClass="form-control" data-rule-required="true" />
                    </div>
                </div>
                
				<div class="form-group">
                    <label class="col-lg-3 control-label" for="price">小计费用</label>
                    <div class="col-lg-9">
                        <spring:input id="price" path="price" cssClass="form-control" data-rule-required="true" />
                    </div>
                </div>
                
				<div class="form-group">
                    <label class="col-lg-3 control-label" for="sumPrice">总费用</label>
                    <div class="col-lg-9">
                        <spring:input id="sumPrice" path="sumPrice" cssClass="form-control" data-rule-required="true" />
                    </div>
                </div>
                
				<div class="form-group">
                    <label class="col-lg-3 control-label" for="workerId">上门师傅</label>
                    <div class="col-lg-9">
                        <spring:select path="workerId" cssClass="form-control">
							<spring:options itemValue="id" itemLabel="name"
								items="${workerList}" />
						</spring:select>
                    </div>
                </div>
                
                <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                        <button type="button" class="btn btn-primary" onclick="saveOrUpdateOrder()">保存</button>
                        <button type="button" class="btn btn-default" onclick="back()">返回</button>
                    </div>
                </div>
            </spring:form>
        </div>
    </div>
</div>
<script type="text/javascript">
    function saveOrUpdateOrder(){
        var orderName=$("#orderName").val();
        var price=$("#price").val();
        var sumPrice=$("#sumPrice").val();
        if(orderName.trim()==null||orderName.trim()==""){
            alert("订单名称不能为空！");
            return;
        }
        if(isNaN(price)){
		   alert("小计费用请输入数字");
		   return;
		}
        if(isNaN(sumPrice)){
		   alert("总费用请输入数字");
		   return;
		}
        var form = $('#orderForm');
        if (form.valid() && confirm("是否确认保存？")) {
            form.submit();
        }
    }

    function back(){
        location.href = "<jfeng:appContext/>/order/orders";
    }
    
</script>