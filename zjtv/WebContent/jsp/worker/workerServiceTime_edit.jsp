<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jfeng" uri="/WEB-INF/jfeng.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<jfeng:appContext var="appContext"/>
<div class="col-lg-7">
    <div class="panel panel-default">
        <%
        	if(request.getAttribute("workerList")!=null){
        	%>
        	<div class="panel-heading">员工可服务时间信息</div>
        <div class="panel-body">
            <spring:form id="workerServiceTimeForm" action="${appContext}/worker/saveOrUpdateWorkerServiceTime" modelAttribute="workerServiceTime" cssClass="form-horizontal">
                <spring:hidden path="id"/>
                
                <div class="form-group">
					<label class="col-lg-3 control-label" for="workerId">员工真实姓名</label>
					<div class="col-lg-9" id="workerId">
						<spring:select path="workerId" cssClass="form-control">
							<spring:options itemValue="id" itemLabel="name"
								items="${workerList}" />
						</spring:select>
					</div>
				</div>
                
				<div class="form-group">
                    <label class="col-lg-3 control-label" for="workerServiceTime">可服务时间</label>
                    <div class="col-lg-9">
                        <spring:input id="workerServiceTime" path="workerServiceTime" cssClass="form-control" data-rule-required="true"/>
                    </div>
                </div>
				
                <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                    	<c:if test="${workerServiceTime.id eq null}">
                            <label class="checkbox inline">
                                <input type="checkbox" name="continue" checked="checked" value="true"> 是否继续添加？
                            </label>
                        </c:if>
                        <button type="button" class="btn btn-primary" onclick="saveOrUpdateWorkerServiceTime()">保存</button>
                        <button type="button" class="btn btn-default" onclick="back()">返回</button>
                    </div>
                </div>
            </spring:form>
        </div>
        	<%
        	}else{
        	%>
        		<h1><font color="red">没有员工可选择</font></h1>
        	<%
        	}
         %>
    </div>
</div>
<script type="text/javascript">
    function saveOrUpdateWorkerServiceTime(){
        var workerServiceTime=$("#workerServiceTime").val();
        if(workerServiceTime.trim()==null||workerServiceTime.trim()==""){
            alert("可服务时间不能为空！");
            return;
        }
        var form = $('#workerServiceTimeForm');
        if (form.valid() && confirm("是否确认保存？")) {
            form.submit();
        }
    }

    function back(){
        location.href = "<jfeng:appContext/>/worker/workerservicetime";
    }
    
    
</script>