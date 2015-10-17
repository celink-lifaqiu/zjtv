<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jfeng" uri="http://www.jfeng-app.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<jfeng:appContext var="appContext"/>
<div class="col-lg-8">
    <div class="panel panel-default">
        <div class="panel-heading">服务套餐列表
        	<jfeng:primaryButton onclick="createNew()" icon="plus" mini="true"
                                 label="增加服务套餐" small="true" />
        </div>
        <div class="panel-body">
            <jfeng:grid id="packages_grid" action="/package/loadPackages">
                <jfeng:gridHead>
                    <tr>
                        <th>#</th>
                        <th>服务套餐类型</th>
                        <th>服务套餐名称</th>
                        <th>服务套餐费用</th>
                        <th>操作</th>
                    </tr>
                </jfeng:gridHead>
                <jfeng:gridColumn data="index"/>
                <jfeng:gridColumn data="packageType" />
                <jfeng:gridColumn data="packageServiceName"/>
                <jfeng:gridColumn data="packageServicePrice"/>
                <jfeng:gridColumn data="id" width="20%" sortable="false">
                    <jfeng:columnRenderScript>
                        var btnStr = '';

                        btnStr =    "<div class='btn-group'>"+
                        			"   <button type='button' class='btn btn-danger btn-xs' onclick='editPackages("+row.id+")'><i class='icon-edit'></i>修改</button>&nbsp;&nbsp;"+
                                    "   <button type='button' class='btn btn-danger btn-xs' onclick='deletePackages("+row.id+")'><i class='icon-trash'></i> 删除</button>"+
                                    "</div>"
                        return btnStr;
                    </jfeng:columnRenderScript>
                </jfeng:gridColumn> 
            </jfeng:grid>
        </div>
    </div>
</div>
<script type="text/javascript">
    function deletePackages(id){
        if (confirm("是否确认删除?")) {
            sendSimpleAjaxRequestAsync("${appContext}/package/deletePackages/"+id, "", function(){
                dataTable_packages_grid.fnReloadAjax();
            });
        }
    }
    
    
    function editPackages(id){
        location.href = "${appContext}/package/editPackages/"+id;
    }
    function createNew(){
        location.href = "${appContext}/package/editPackages/0";
    }
</script>
