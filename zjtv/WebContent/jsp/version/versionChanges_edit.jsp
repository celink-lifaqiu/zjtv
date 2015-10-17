<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jfeng" uri="/WEB-INF/jfeng.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<jfeng:appContext var="appContext"/>
<div class="col-lg-6">
    <div class="panel panel-default">
        <div class="panel-heading">版本更新内容信息</div>
        <div class="panel-body">
            <spring:form id="versionChangesForm" action="${appContext}/version/saveOrUpdateVersionChanges" modelAttribute="versionChanges" cssClass="form-horizontal">
                <spring:hidden path="id"/>
                <div class="form-group">
                    <label class="col-lg-2 control-label" for="platform">平台</label>
                    <div class="col-lg-10">
                        <spring:input id="platform" path="platform" cssClass="form-control" data-rule-required="true"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label" for="versionCode">版本号</label>
                    <div class="col-lg-10">
                        <spring:input id="versionCode" path="versionCode" cssClass="form-control" data-rule-required="true"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label" for="content">更新内容</label>
                    <div class="col-lg-10">
                        <spring:input id="content" path="content" cssClass="form-control" data-rule-required="true"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                    	<c:if test="${versionChanges.id eq null}">
                            <label class="checkbox inline">
                                <input type="checkbox" name="continue" checked="checked" value="true"> 是否继续添加？
                            </label>
                        </c:if>
                        <button type="submit" class="btn btn-primary" onclick="saveOrUpdateVersionChanges()">保存</button>
                        <button type="button" class="btn btn-default" onclick="back()">返回</button>
                    </div>
                </div>
            </spring:form>
        </div>
    </div>
</div>
<script type="text/javascript">
    function saveOrUpdateVersionChanges(){
        var form = $('#versionChangesForm');
        if (form.valid() && confirm("是否确认保存？")) {
            form.submit();
        }
    }

    function back(){
        location.href = "<jfeng:appContext/>/version/versionchangesinfo";
    }
</script>