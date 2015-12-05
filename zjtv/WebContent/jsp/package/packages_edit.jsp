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
                <input type="hidden" id="iconHash" name="iconHash">
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
<div class="col-lg-5">
    <div class="panel panel-default">
        <div class="panel-heading">图片</div>
        <div class="panel-body gallery" id="preview">
            <jsp:include page="packages_image.jsp"/>
        </div>
    </div>
</div>
<script type="text/javascript">
$(document).ready(function(){
    initImgDrop(${advertise.id});
 });
 function initImgDrop(id){
     $("#img_dropzone").dropzone({ url: "http://up.qiniu.com/",
         clearBeforePreview:true,
         method: "post",
         paramName: "file",
         maxFilesize: 5,
         params:{"token":"${uptoken}"},
         init:function(){
             this.on(Dropzone.SUCCESS, function(file, resp){
                 if(resp.error != null && resp.error != undefined){
                     alert(resp.error);
                 }
                 $('#iconHash').val(resp.hash);
                 ajaxRefreshAsync("preview", "<jfeng:appContext/>/package/previewImage", "", "hash="+resp.hash, function(){
                     initImgDrop(id);
                 });
             });
             this.on(Dropzone.ERROR, function(file, message){
                 alert(message);
             });
             this.on("sending", function(file, xhr, formData){
             });
             this.previewsContainer = $("#preview")[0];
         }
     });
 }
 function checkRate(input){
     var re = /^[0-9]+.?[0-9]*$/;   //判断字符串是否为数字     //判断正整数 /^[1-9]+[0-9]*]*$/  
     if (!re.test(input.rate.value))
    {
        alert("请输入数字(例:30.5)");
        input.rate.focus();
        return false;
     }
}
 // 函数功能：判断对象值是否为非负浮点数（两位小数）
 function isFloat(obj) {
     var data=obj.value;
     var result=false;            
     if(typeof data!='undefined') {
        // 正则表达式 匹配 非负浮点数，小数点后最多两位小数   
        if(data.match(/^\+{0,1}\d+(\.\d{1,2})?$/)!=null){
            result=true;                
        }
    }
    return result;
}
function saveOrUpdatePackages(){
    var packageServiceName=$("#packageServiceName").val();
    if(packageServiceName.trim()==null||packageServiceName.trim()==""){
        alert("服务套餐名称不能为空！");
        $("#packageServiceName").focus();
        return;
    }
	var obj=document.getElementById('packageServicePrice');
    if (!isFloat(obj)){
        alert("服务套餐费用请输入数字(例:30.5)");
        obj.focus();
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