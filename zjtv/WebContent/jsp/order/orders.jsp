<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jfeng" uri="http://www.jfeng-app.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<jfeng:appContext var="appContext"/>
<div class="col-lg-7">
    <div class="panel panel-default">
        <div class="panel-heading">订单列表
        </div>
        <div class="panel-body">
            <jfeng:grid id="order_grid" action="/order/loadOrder">
                <jfeng:gridHead>
                    <tr>
                        <th>#</th>
                        <th>订单名称</th>
                        <th>小计费用</th>
                        <th>是否使用抵用卷</th>
                        <th>总费用</th>
                        <th>状态</th>
                        <th>支付日期</th>
                        <th>操作</th>
                    </tr>
                </jfeng:gridHead>
                <jfeng:gridColumn data="index" width="8%"/>
                <jfeng:gridColumn data="orderName"  width="15%">
                    <jfeng:columnRenderScript>
                        return "<a href='javascript:void(0)' onclick='showOrderDetail("+row.id+")'>"+row.orderName+"</a>";
                    </jfeng:columnRenderScript>
                </jfeng:gridColumn>
                <jfeng:gridColumn data="price"  width="8%"/>
                <jfeng:gridColumn data="isUseVoucherStr"  width="10%"/>
                <jfeng:gridColumn data="sumPrice"  width="8%"/>
                <jfeng:gridColumn data="stateStr"  width="10%"/>
                <jfeng:gridColumn data="createDateStr"  width="10%"/>
                <jfeng:gridColumn data="id" width="10%" sortable="false">
                    <jfeng:columnRenderScript>
                        var btnStr = '';

                        btnStr =    "<div class='btn-group'>"+
                        			"   <button type='button' class='btn btn-danger btn-xs' onclick='editOrder("+row.id+")'><i class='icon-edit'></i>修改</button>"
                                    "</div>"
                        return btnStr;
                    </jfeng:columnRenderScript>
                </jfeng:gridColumn> 
            </jfeng:grid>
        </div>
    </div>
</div>
<div class="col-lg-4">
    <div class="row-fluid" id="order_detail_panel"></div>
</div>
<script type="text/javascript">
    function deleteOrder(id){
        if (confirm("是否确认删除?")) {
            sendSimpleAjaxRequestAsync("${appContext}/order/deleteOrder/"+id, "", function(){
                dataTable_worker_grid.fnReloadAjax();
            });
        }
    }
    function showOrderDetail(id){
        ajaxRefreshAsync("order_detail_panel", "${appContext}/order/showOrderDetail/"+id);
    }
    
    function editOrder(id){
        location.href = "${appContext}/order/editOrder/"+id;
    }

</script>
