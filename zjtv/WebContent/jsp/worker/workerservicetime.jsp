<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jfeng" uri="http://www.jfeng-app.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<jfeng:appContext var="appContext"/>
<div class="col-lg-10">
    <div class="panel panel-default">
        <div class="panel-heading">员工可服务时间列表
        	<jfeng:primaryButton onclick="createNew()" icon="plus" mini="true"
                                 label="增加员工可服务时间" small="true" />
        </div>
        <div class="panel-body">
            <jfeng:grid id="workerservicetime_grid" action="/worker/loadWorkerServiceTime">
                <jfeng:gridHead>
                    <tr>
                        <th>#</th>
                        <th>员工姓名</th>
                        <th>可服务时间</th>
                        <th>操作</th>
                    </tr>
                </jfeng:gridHead>
                <jfeng:gridColumn data="index"/>
                <jfeng:gridColumn data="workerName"/>
                <jfeng:gridColumn data="workerServiceTime"/>
                <jfeng:gridColumn data="id" width="15%" sortable="false">
                    <jfeng:columnRenderScript>
                        var btnStr = '';

                        btnStr =    "<div class='btn-group'>"+
                        			"   <button type='button' class='btn btn-danger btn-xs' onclick='editWorkerServiceTime("+row.id+")'><i class='icon-edit'></i>修改</button>&nbsp;&nbsp;"+
                                    "   <button type='button' class='btn btn-danger btn-xs' onclick='deleteWorkerServiceTime("+row.id+")'><i class='icon-trash'></i> 删除</button>"+
                                    "</div>"
                        return btnStr;
                    </jfeng:columnRenderScript>
                </jfeng:gridColumn> 
            </jfeng:grid>
        </div>
    </div>
</div>
<script type="text/javascript">
    function deleteWorkerServiceTime(id){
        if (confirm("是否确认删除?")) {
            sendSimpleAjaxRequestAsync("${appContext}/worker/deleteWorkerServiceTime/"+id, "", function(){
                dataTable_workerservicetime_grid.fnReloadAjax();
            });
        }
    }
    
    
    function editWorkerServiceTime(id){
        location.href = "${appContext}/worker/editWorkerServiceTime/"+id;
    }
    function createNew(){
        location.href = "${appContext}/worker/editWorkerServiceTime/0";
    }
</script>
