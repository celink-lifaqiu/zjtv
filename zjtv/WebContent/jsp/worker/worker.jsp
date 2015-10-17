<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jfeng" uri="http://www.jfeng-app.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<jfeng:appContext var="appContext"/>
<div class="col-lg-10">
    <div class="panel panel-default">
        <div class="panel-heading">员工列表
        	<jfeng:primaryButton onclick="createNew()" icon="plus" mini="true"
                                 label="增加员工" small="true" />
        </div>
        <div class="panel-body">
            <jfeng:grid id="worker_grid" action="/worker/loadWorker">
                <jfeng:gridHead>
                    <tr>
                        <th>#</th>
                        <th>真实姓名</th>
                        <th>性别</th>
                        <th>身份证号码</th>
                        <th>联系电话</th>
                        <th>登记日期</th>
                        <th>操作</th>
                    </tr>
                </jfeng:gridHead>
                <jfeng:gridColumn data="index"/>
                <jfeng:gridColumn data="name"  width="15%"/>
                <jfeng:gridColumn data="genderStr"  width="8%"/>
                <jfeng:gridColumn data="idCard" width="15%"/>
                <jfeng:gridColumn data="phone"  width="10%"/>
                <jfeng:gridColumn data="registDateStr"/>
                <jfeng:gridColumn data="id" width="15%" sortable="false">
                    <jfeng:columnRenderScript>
                        var btnStr = '';

                        btnStr =    "<div class='btn-group'>"+
                        			"   <button type='button' class='btn btn-danger btn-xs' onclick='editWorker("+row.id+")'><i class='icon-edit'></i>修改</button>&nbsp;&nbsp;"+
                                    "   <button type='button' class='btn btn-danger btn-xs' onclick='deleteWorker("+row.id+")'><i class='icon-trash'></i> 删除</button>"+
                                    "</div>"
                        return btnStr;
                    </jfeng:columnRenderScript>
                </jfeng:gridColumn> 
            </jfeng:grid>
        </div>
    </div>
</div>
<script type="text/javascript">
    function deleteWorker(id){
        if (confirm("是否确认删除?")) {
            sendSimpleAjaxRequestAsync("${appContext}/worker/deleteWorker/"+id, "", function(){
                dataTable_worker_grid.fnReloadAjax();
            });
        }
    }
    
    
    function editWorker(id){
        location.href = "${appContext}/worker/editWorker/"+id;
    }
    function createNew(){
        location.href = "${appContext}/worker/editWorker/0";
    }
</script>
