<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="jfeng" uri="/WEB-INF/jfeng.tld"%>
<div class="col-lg-8">
    <div class="panel panel-default">
        <div class="panel-heading">广告列表
            <jfeng:primaryButton onclick="createNew()" icon="plus" mini="true"
                                 label="添加广告" small="true" />
        </div>
        <div class="panel-body">
            <jfeng:grid id="advertise_grid" action="/advertise/loadadvertises">
                <jfeng:gridHead>
                    <tr>
                        <th>#</th>
                        <th>广告标题</th>
                        <th>...</th>
                    </tr>
                </jfeng:gridHead>
                <jfeng:gridColumn data="index"/>
                <jfeng:gridColumn data="title">
                    <jfeng:columnRenderScript>
                        return "<a href='javascript:void(0)' onclick='showAdvertiseDetail("+row.id+")'>"+row.title+"</a>";
                    </jfeng:columnRenderScript>
                </jfeng:gridColumn>
                <jfeng:gridColumn data="id" width="20%" sortable="false">
                    <jfeng:columnRenderScript>
                        var btnStr =    "<div class='btn-group'>"+
                        " <button type='button' class='btn btn-default btn-xs' onclick='editAdvertise("+row.id+")'><i class='icon-edit'></i> 编辑</button>"+
                        " <button type='button' class='btn btn-danger btn-xs' onclick='deleteAdvertise("+row.id+")'><i class='icon-trash'></i> 删除</button>"+
                        "</div>"
                        return btnStr;
                    </jfeng:columnRenderScript>
                </jfeng:gridColumn>
            </jfeng:grid>
        </div>
    </div>
</div>
<div class="col-lg-4">
    <div class="row-fluid" id="advertise_detail_panel"></div>
</div>
<script type="text/javascript">
    function editAdvertise(id){
        location.href = "editAdvertise/"+id;
    }
    function deleteAdvertise(id){
        if(confirm("是否确认删除?")){
            sendSimpleAjaxRequestAsync("deleteAdvertise", 'id='+id, function(){
                dataTable_advertise_grid.fnReloadAjax();
            });
        }
    }
    function createNew(){
        location.href = "editAdvertise/0";
    }

    function showAdvertiseDetail(shopId){
        ajaxRefreshAsync("advertise_detail_panel", "showAdvertiseDetail/"+shopId);
    }
</script>