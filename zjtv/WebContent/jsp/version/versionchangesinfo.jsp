<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jfeng" uri="http://www.jfeng-app.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<jfeng:appContext var="appContext"/>
<div class="col-lg-8">
    <div class="panel panel-default">
        <div class="panel-heading">版本更新内容
        <jfeng:primaryButton onclick="createNew()" icon="plus" mini="true"
                                 label="添加版本更新内容" small="true" />
        </div>
        <div class="panel-body">
            <jfeng:grid id="versionchanges_grid" action="/version/loadVersionChanges">
                <jfeng:gridHead>
                    <tr>
                        <th>#</th>
                        <th>平台</th>
                        <th>版本号</th>
                        <th>更新内容</th>
                        <th>...</th>
                    </tr>
                </jfeng:gridHead>
                <jfeng:gridColumn data="index"/>
                <jfeng:gridColumn data="platform"/>
                <jfeng:gridColumn data="versionCode"/>
                <jfeng:gridColumn data="content"/>
                <jfeng:gridColumn data="id" width="10%" sortable="false">
                    <jfeng:columnRenderScript>
                        var btnStr = '';

                        btnStr =    "<div class='btn-group'>"+
                        			"   <button type='button' class='btn btn-danger btn-xs' onclick='editVersionChanges("+row.id+")'><i class='icon-edit'></i>编辑</button><br>"+
                                    "   <button type='button' class='btn btn-danger btn-xs' onclick='deleteVersionChanges("+row.id+")'><i class='icon-trash'></i> 删除</button><br>"+
                                    "</div>"
                        return btnStr;
                    </jfeng:columnRenderScript>
                </jfeng:gridColumn>
            </jfeng:grid>
        </div>
    </div>
</div>
<script type="text/javascript">
function deleteVersionChanges(id){
    if (confirm("是否确认删除?")) {
        sendSimpleAjaxRequestAsync("${appContext}/version/deleteVersionChanges/"+id, "", function(){
            dataTable_versionchanges_grid.fnReloadAjax();
        });
    }
}
function createNew(){
    location.href = "editVersionChanges/0";
}
    function editVersionChanges(id){
        location.href = "editVersionChanges/"+id;
    }
</script>
