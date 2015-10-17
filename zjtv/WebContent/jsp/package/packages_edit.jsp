<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jfeng" uri="/WEB-INF/jfeng.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<jfeng:appContext var="appContext"/>
<div class="col-lg-7">
    <div class="panel panel-default">
    <%
        	if(request.getAttribute("packageTypeList")!=null){
        	%>
        <div class="panel-heading">服务套餐信息</div>
        <div class="panel-body">
            <spring:form id="packagesForm" action="${appContext}/package/saveOrUpdatePackages" modelAttribute="packages" cssClass="form-horizontal">
                <spring:hidden path="id"/>
                
                <div class="form-group">
					<label class="col-lg-3 control-label" for="packageTypeId">服务套餐类型</label>
					<div class="col-lg-9" id="packageTypeId">
						<spring:select path="packageTypeId" cssClass="form-control">
							<spring:options itemValue="id" itemLabel="type"
								items="${packageTypeList}" />
						</spring:select>
					</div>
				</div>
				
				<div class="form-group">
                    <label class="col-lg-3 control-label" for="packageServiceName">服务套餐名称</label>
                    <div class="col-lg-9">
                        <spring:input id="packageServiceName" path="packageServiceName" cssClass="form-control" data-rule-required="true"/>
                    </div>
                </div>
				
				<div class="form-group">
                    <label class="col-lg-3 control-label" for="packageServicePrice">服务套餐费用</label>
                    <div class="col-lg-9">
                        <spring:input id="packageServicePrice" path="packageServicePrice" cssClass="form-control" data-rule-required="true"/>
                    </div>
                </div>
                
                <div class="form-group">
					<label class="col-lg-3 control-label" for="packageServiceDesc">服务套餐详情</label>
					<div class="col-lg-9">
						<spring:textarea id="packageServiceDesc" path="packageServiceDesc" cssClass="form-control"
							data-rule-required="true" />
					</div>
				</div>
				
                <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                    	<c:if test="${packages.id eq null}">
                            <label class="checkbox inline">
                                <input type="checkbox" name="continue" checked="checked" value="true"> 是否继续添加？
                            </label>
                        </c:if>
                        <button type="button" class="btn btn-primary" onclick="saveOrUpdatePackages()">保存</button>
                        <button type="button" class="btn btn-default" onclick="back()">返回</button>
                    </div>
                </div>
            </spring:form>
        </div>
        <%
        	}else{
        	%>
        		<h1><font color="red">没有服务套餐类型可选择</font></h1>
        	<%
        	}
         %>
    </div>
</div>
<script type="text/javascript">
    function saveOrUpdatePackages(){
        var packageServiceName=$("#packageServiceName").val();
        var packageServicePrice=$("#packageServicePrice").val();
        if(packageServiceName.trim()==null||packageServiceName.trim()==""){
            alert("服务套餐名称不能为空！");
            return;
        }
        if(packageServicePrice.trim()==null||packageServicePrice.trim()==""){
            alert("服务套餐费用不能为空！");
            return;
        }
        var form = $('#packagesForm');
        if (form.valid() && confirm("是否确认保存？")) {
            form.submit();
        }
    }

    function back(){
        location.href = "<jfeng:appContext/>/package/packages";
    }
</script>