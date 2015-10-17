<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jfeng" uri="/WEB-INF/jfeng.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<jfeng:appContext var="appContext"/>
<div class="col-lg-6">
    <div class="panel panel-default">
        <div class="panel-heading">服务套餐类型信息</div>
        <div class="panel-body">
            <spring:form id="packageTypeForm" action="${appContext}/package/saveOrUpdatePackageType" modelAttribute="packageType" cssClass="form-horizontal">
                <spring:hidden path="id"/>
                <div class="form-group">
                    <label class="col-lg-2 control-label" for="type">服务套餐类型</label>
                    <div class="col-lg-10">
                        <spring:input id="type" path="type" cssClass="form-control" data-rule-required="true"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                    	<c:if test="${packageType.id eq null}">
                            <label class="checkbox inline">
                                <input type="checkbox" name="continue" checked="checked" value="true"> 是否继续添加？
                            </label>
                        </c:if>
                        <button type="button" class="btn btn-primary" onclick="saveOrUpdatePackageType()">保存</button>
                        <button type="button" class="btn btn-default" onclick="back()">返回</button>
                    </div>
                </div>
            </spring:form>
        </div>
    </div>
</div>
<script type="text/javascript">
    function saveOrUpdatePackageType(){
        var type=$("#type").val();
        if(type.trim()==null||type.trim()==""){
            alert("服务套餐类型不能为空！");
            return;
        }
        var form = $('#packageTypeForm');
        if (form.valid() && confirm("是否确认保存？")) {
            form.submit();
        }
    }

    function back(){
        location.href = "<jfeng:appContext/>/package/packageType";
    }
</script>