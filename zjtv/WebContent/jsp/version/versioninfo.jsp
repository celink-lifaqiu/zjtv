<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jfeng" uri="http://www.jfeng-app.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<jfeng:appContext var="appContext"/>
<div class="col-lg-8">
    <div class="panel panel-default">
        <div class="panel-heading">android/ios 当前版本<br>注意，如果更改版本号，请先增加版本更新内容。
        </div>
        <div class="panel-body">
            <jfeng:grid id="version_grid" action="/version/loadVersions">
                <jfeng:gridHead>
                    <tr>
                        <th>#</th>
                        <th>文件名称</th>
                        <th>平台</th>
                        <th>版本号</th>
                        <th>类型</th>
                        <th>...</th>
                    </tr>
                </jfeng:gridHead>
                <jfeng:gridColumn data="index"/>
                <jfeng:gridColumn data="filename"/>
                <jfeng:gridColumn data="platform"/>
                <jfeng:gridColumn data="versionCode"/>
                <jfeng:gridColumn data="typeStr"/>
                <jfeng:gridColumn data="id" width="10%" sortable="false">
                    <jfeng:columnRenderScript>
                        var btnStr = '';

                        btnStr =    "<div class='btn-group'>"+
                        			"   <button type='button' class='btn btn-danger btn-xs' onclick='editVersion("+row.id+")'><i class='icon-edit'></i>编辑</button><br>"+
                                    "</div>"
                        return btnStr;
                    </jfeng:columnRenderScript>
                </jfeng:gridColumn>
            </jfeng:grid>
        </div>
    </div>
</div>
<script type="text/javascript">
    
    function editVersion(id){
        location.href = "editVersion/"+id;
    }
</script>
