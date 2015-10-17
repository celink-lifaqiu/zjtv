<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jfeng" uri="http://www.jfeng-app.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<jfeng:appContext var="appContext"/>
<div class="col-lg-8">
    <div class="panel panel-default">
        <div class="panel-heading">服务套餐类型列表
        </div>
        <div class="panel-body">
            <jfeng:grid id="packagetype_grid" action="/package/loadPackageType">
                <jfeng:gridHead>
                    <tr>
                        <th>#</th>
                        <th>服务套餐类型</th>
                        <th>操作</th>
                    </tr>
                </jfeng:gridHead>
                <jfeng:gridColumn data="index"/>
                <jfeng:gridColumn data="type"/>
                <jfeng:gridColumn data="id" width="20%" sortable="false">
                    <jfeng:columnRenderScript>
                        var btnStr = '';

                        btnStr =    "<div class='btn-group'>"+
                        			"   <button type='button' class='btn btn-danger btn-xs' onclick='editPackageType("+row.id+")'><i class='icon-edit'></i>修改</button> &nbsp;&nbsp;&nbsp;&nbsp;"+
                                    "</div>"
                        return btnStr;
                    </jfeng:columnRenderScript>
                </jfeng:gridColumn> 
            </jfeng:grid>
        </div>
    </div>
</div>
<script type="text/javascript">
    function deletePackageType(id){
        if (confirm("是否确认删除?")) {
            sendSimpleAjaxRequestAsync("${appContext}/package/deletePackageType/"+id, "", function(){
                dataTable_packagetype_grid.fnReloadAjax();
            });
        }
    }
    
    
    function editPackageType(id){
        location.href = "${appContext}/package/editPackageType/"+id;
    }
    function createNew(){
        location.href = "${appContext}/package/editPackageType/0";
    }
</script>
